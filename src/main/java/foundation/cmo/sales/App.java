package foundation.cmo.sales;

import java.text.MessageFormat;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@EnableRedisRepositories
@SpringBootApplication()
public class App extends SpringBootServletInitializer{
	
	private Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public void init() {
	    TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
	    logger.info(MessageFormat.format("Set TimeZone: {0}", new Date()));
	}
	
}
