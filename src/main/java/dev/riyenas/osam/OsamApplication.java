package dev.riyenas.osam;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@Log4j2
@SpringBootApplication
public class OsamApplication {

	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
		log.info("현재시각 : " + new Date());
	}

	public static void main(String[] args) {
		SpringApplication.run(OsamApplication.class, args);
	}

}
