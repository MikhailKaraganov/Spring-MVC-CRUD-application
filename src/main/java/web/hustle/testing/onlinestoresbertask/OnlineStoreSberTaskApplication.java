package web.hustle.testing.onlinestoresbertask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineStoreSberTaskApplication {
    private static final Logger logger = LoggerFactory.getLogger(OnlineStoreSberTaskApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OnlineStoreSberTaskApplication.class, args);
        logger.info("Application started");
    }

}
