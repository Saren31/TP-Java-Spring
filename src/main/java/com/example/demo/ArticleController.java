package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path="/article") // This means URL's start with /demo (after Application path)
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;
	
	 @PostMapping(path="/add") // Map ONLY POST Requests
	  public @ResponseBody String addArticle (@RequestParam String contenu, 
		    @RequestParam String date
	      , @RequestParam Integer user) {

	    Article n = new Article();
	    n.setDatePublication(date);
	    n.setIdAuteur(user);
	    n.setContenu(contenu);
	    articleRepository.save(n);
	    return "Saved";
	  }
	 
	 @GetMapping(path="/all")
	  public @ResponseBody Iterable<Article> getArticles() {
	    // This returns a JSON or XML with the users
	    return articleRepository.findAll();
	  }
	
}
