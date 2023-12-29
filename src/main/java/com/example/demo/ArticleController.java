package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path="/article") // This means URL's start with /demo (after Application path)
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	 @PostMapping(path="/add") // Map ONLY POST Requests
	  public @ResponseBody String addArticle (@RequestParam String contenu, 
		    @RequestParam String date
	      , @RequestParam Integer auteurId) {

	    Article n = new Article();
	    n.setDatePublication(date);
	    n.setContenu(contenu);
	    n.setUser(utilisateurRepository.findById(auteurId).get());
	    articleRepository.save(n);
	    return "Saved";
	  }
	 
	 @GetMapping(path="/all")
	  public @ResponseBody Iterable<Article> getArticles() {
	    // This returns a JSON or XML with the users
	    return articleRepository.findAll();
	  }
	 
	 @GetMapping(path="/get")
	    public @ResponseBody Article getArticleById(@RequestParam Integer id) {
	        // Get article by ID
	        return articleRepository.findById(id).orElse(null);
	    }

	 
	 @PutMapping(path="/put")
	 public @ResponseBody String changeArticle(@RequestParam Integer articleId
			 , @RequestParam String contenu, 
			    @RequestParam String date
			      , @RequestParam Integer auteurId ) {
		 
		 Article a = articleRepository.findById(articleId)
				 .orElseThrow(()->new IllegalArgumentException("Il n'y a pas d'article avec cet id"));
		 a.setDatePublication(date);
		 a.setContenu(contenu);
		 a.setUser(utilisateurRepository.findById(auteurId).get());
		 
		 articleRepository.save(a);
		 return "Saved";
	 }
	 
	 @DeleteMapping(path="/delete")
	 public @ResponseBody String deleteArticle(@RequestParam Integer id) {
		 articleRepository.deleteById(id);
		 return "Delete";
	 }
	
}
