package com.MCXP.app;

import com.MCXP.app.dto.response.UserResponse;
import com.MCXP.app.task.UserAsync;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class AppApplication implements CommandLineRunner {

	private final UserAsync userAsync;


	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run( String... args){

		List<Long> ids = List.of(10L, 51L, 101L, 102L, 103L, 104L, 105L, 106L, 107L, 108L);

		List<UserResponse> users = userAsync.getUserAsync(ids);
		users.forEach(System.out::println);

		userAsync.shutdown();
	}

}
