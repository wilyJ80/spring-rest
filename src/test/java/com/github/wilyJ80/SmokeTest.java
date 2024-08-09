package com.github.wilyJ80;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.wilyJ80.topic.TopicController;

@SpringBootTest
public class SmokeTest {

	@Autowired
	private TopicController topicController;

	@Test
	void contextLoads() throws Exception {
		Assertions.assertThat(topicController).isNotNull();
	}
}
