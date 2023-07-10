package net.snascimento.contentcalendar.controller;

import jakarta.validation.Valid;
import java.util.List;
import net.snascimento.contentcalendar.model.Content;
import net.snascimento.contentcalendar.model.Status;
import net.snascimento.contentcalendar.repository.ContentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/content")
public class ContentController {

  private final ContentRepository contentRepository;

  public ContentController(ContentRepository contentRepository) {
    this.contentRepository = contentRepository;
  }

  @GetMapping("")
  public List<Content> findAll() {
    return contentRepository.findAll();
  }

  @GetMapping("/{id}")
  public Content findById(@PathVariable Integer id) {
    return contentRepository
        .findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conteudo não encontrado."));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  public void create(@Valid @RequestBody Content content) {
    contentRepository.save(content);
  }

  @PutMapping("/{id}")
  public void update(@RequestBody Content content, @PathVariable Integer id) {
    if (!contentRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conteudo não encontrado.");
    }
    contentRepository.save(content);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    contentRepository.deleteById(id);
  }

  @GetMapping("/filter/{keyword}")
  public List<Content> findByTitle(@PathVariable String keyword) {
    return contentRepository.findAllByTitleContains(keyword);
  }

  @GetMapping("/filter/status/{status}")
  public List<Content> findByStatus(@PathVariable Status status) {
    return contentRepository.listByStatus(status);
  }
}
