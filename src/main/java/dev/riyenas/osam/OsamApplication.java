package dev.riyenas.osam;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@Log4j2
@SpringBootApplication
public class OsamApplication {

	private static volatile ConfigurableApplicationContext context;
	private static ClassLoader mainThreadClassLoader;

	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
		log.info("현재시각 : " + new Date());
	}

	public static void main(String[] args) {
		mainThreadClassLoader = Thread.currentThread().getContextClassLoader();
		context = SpringApplication.run(OsamApplication.class, args);
	}

	public static void restart() {
		ApplicationArguments args = context.getBean(ApplicationArguments.class);

		Thread thread = new Thread(() -> {
			context.close();
			context = SpringApplication.run(OsamApplication.class, args.getSourceArgs());
		});

		thread.setContextClassLoader(mainThreadClassLoader);
		thread.setDaemon(false);
		thread.start();
	}
}
