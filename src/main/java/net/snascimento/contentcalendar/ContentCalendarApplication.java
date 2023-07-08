package net.snascimento.contentcalendar;

import java.time.LocalDateTime;
import net.snascimento.contentcalendar.model.Content;
import net.snascimento.contentcalendar.model.Status;
import net.snascimento.contentcalendar.model.Type;
import net.snascimento.contentcalendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ContentCalendarApplication {

  public static void main(String[] args) {
    SpringApplication.run(ContentCalendarApplication.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(ContentRepository repository) {
    return args -> {
      // insert data into the database
      Content content =
          new Content(
              null,
              "My NEW Blog Post",
              "My NEW blog post",
              Status.IDEA,
              Type.VIDEO,
              LocalDateTime.now(),
              null,
              "");
      repository.save(content);
    };
  }
}
