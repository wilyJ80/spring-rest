package com.github.wilyJ80.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/hello")
	public WelcomeMessage sayHi() {
		return new WelcomeMessage("Welcome");
	}

	public record WelcomeMessage(String message) {
	}
}
