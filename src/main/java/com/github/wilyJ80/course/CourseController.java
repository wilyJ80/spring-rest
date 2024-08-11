package com.github.wilyJ80.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.wilyJ80.topic.Topic;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;

	@GetMapping("/topics/{id}/courses")
	public List<Course> getAllCourses(@PathVariable String id) {
		return courseService.getAllCourses(id);
	}

	@GetMapping("/topics/{topicId}/courses/{id}")
	public Course getCourse(@PathVariable String id) {
		return courseService.getCourse(id);
	}

	@PostMapping("/topics/{topicId}/courses")
	@ResponseStatus(HttpStatus.CREATED)
	public Course addCourse(@RequestBody Course course, @PathVariable String topicId) {
		course.setTopic(new Topic(topicId, "", ""));
		courseService.addCourse(course);
		return course;
	}

	@PutMapping("/topics/{topicId}/courses/{id}")
	public Course updateCourse(@RequestBody Course course, @PathVariable String topicId, @PathVariable String id) {
		course.setTopic(new Topic(topicId, "", ""));
		courseService.updateCourse(course);
		return course;
	}

	@DeleteMapping("/topics/{topicId}/courses/{id}")
	public void deleteCourse(@PathVariable String id) {
		courseService.deleteCourse(id);
	}
}
