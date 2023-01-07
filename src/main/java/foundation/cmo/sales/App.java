package foundation.cmo.sales;

import java.text.MessageFormat;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
//@ComponentScan({"foundation.cmo"})
//@EntityScan("com.delivery.domain")
//@EnableJpaRepositories("foundation.cmo.sales.db.repositories")
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
//@ComponentScan({"module-service", "foundation"})
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
