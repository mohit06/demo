package com.mapstructdemo.app.restController;

import java.time.Instant;
import java.util.List;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapstructdemo.app.entities.Student;
import com.mapstructdemo.app.service.LoadDataIntoDB;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class DbRestApiController {

	private final LoadDataIntoDB ldb;
	private final KafkaTemplate<String, Student> template;
	private final NewTopic topic;
	
	@GetMapping("/save")
	public void save() {
		System.out.println("---> Starting synchronous data load at "+Instant.now());
		ldb.saveData();
		System.out.println("---> Synchronous data load completed at "+Instant.now());
	}
	
	@GetMapping("/send-to-kafka-broker")
	public void sendToKafka() {
		List<Student> list = ldb.fetchAll();
		list.stream().forEach(x -> template.send(topic.name(),x));
	}
	
}
