package lgh.spring.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class App implements CommandLineRunner {
	@Autowired
	private KafkaTemplate<String, String> template;
	@Value("${app.topic}") String topic;
	@Value("${app.payload}") String payload;
	@Value("${app.count}") Long count;
	@Value("${app.period.ms}") Long periodMs;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		while (count > 0) {
			this.template.send(topic, String.valueOf(System.currentTimeMillis()) + ":" + payload);
			count --;
			Thread.sleep(periodMs);
		}
	}
}
