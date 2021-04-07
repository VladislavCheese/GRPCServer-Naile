package org.example;

import io.grpc.stub.StreamObserver;
import org.example.grpc.GreetingGrpc;
import org.example.grpc.GreetingService;

public class GreetingServiceImpl extends GreetingGrpc.GreetingImplBase {
    @Override
    public void greeting(GreetingService.HelloRequest request, StreamObserver<GreetingService.HelloResponse> observer){

        for (int i = 0; i < 10000; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            GreetingService.HelloResponse response = GreetingService.HelloResponse
                    .newBuilder().setGreeting("Hello from server " + request.getName()).build();
            observer.onNext(response);
        }

        observer.onCompleted();
    }
}
