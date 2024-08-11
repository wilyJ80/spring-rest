package com.github.wilyJ80;

import static org.assertj.core.api.Assertions.assertThat;
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
		assertThat(topicController).isNotNull();
	}
}
