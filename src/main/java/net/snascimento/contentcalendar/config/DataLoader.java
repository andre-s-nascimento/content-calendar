package net.snascimento.contentcalendar.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;
import net.snascimento.contentcalendar.model.Content;
import net.snascimento.contentcalendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
  private final ContentRepository repository;
  private final ObjectMapper objectMapper;

  public DataLoader(ContentRepository repository, ObjectMapper objectMapper) {
    this.repository = repository;
    this.objectMapper = objectMapper;
  }

  @Override
  public void run(String... args) throws Exception {
    if (repository.count() == 0)
      try (InputStream inputStream =
          TypeReference.class.getResourceAsStream("/data/content.json")) {
        repository.saveAll(
            objectMapper.readValue(inputStream, new TypeReference<List<Content>>() {}));
      }
  }
}
