package net.snascimento.contentcalendar.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
  List<Video> videos =
      List.of(
          new Video("Need HELP with your SPRING BOOT 3 "),
          new Video("Don't do THIS to your own CODE!"),
          new Video("SECRETS to fix BROKEN CODE!"));

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("videos", videos);
    return "index";
  }

  record Video(String name) {}
}
