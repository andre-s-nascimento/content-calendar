package net.snascimento.contentcalendar.controller;

import java.util.List;
import net.snascimento.contentcalendar.model.Content;
import net.snascimento.contentcalendar.repository.ContentCollectionRepository;
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

  private final ContentCollectionRepository contentCollectionRepository;

  public ContentController(ContentCollectionRepository contentCollectionRepository) {
    this.contentCollectionRepository = contentCollectionRepository;
  }

  @GetMapping("")
  public List<Content> findAll(){
    return contentCollectionRepository.findAll();
  }

  @GetMapping("/{id}")
  public Content findById(@PathVariable Integer id){
    return contentCollectionRepository.findById(id)
        .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Conteudo não encontrado."));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  public void create(@RequestBody Content content){
    contentCollectionRepository.save(content);
  }

  @PutMapping("/{id}")
  public void update(@RequestBody Content content, @PathVariable Integer id){
     if (!contentCollectionRepository.existsById(id)){
       throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conteudo não encontrado.");
     }
     contentCollectionRepository.save(content);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id){
    contentCollectionRepository.delete(id);
  }
}
