package com.MCXP.app.task;

import com.MCXP.app.AppApplication;
import com.MCXP.app.dto.response.UserResponse;
import com.MCXP.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class UserAsync {

        private final ExecutorService executor =  Executors.newFixedThreadPool(5);
        private final UserService userService;

        public List<UserResponse> getUserAsync(List<Long> ids){

                List<CompletableFuture<UserResponse>> futures = ids.stream()
                        .map(id ->CompletableFuture.supplyAsync(() -> userService.getUserById(id), executor))
                        .toList();
                return futures.stream().map(CompletableFuture::join).toList();
        }

        public void shutdown() {
                executor.shutdown();
        }

}
