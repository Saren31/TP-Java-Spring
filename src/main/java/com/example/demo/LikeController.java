package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path="/like") // This means URL's start with /demo (after Application path)
public class LikeController {

	@Autowired
	private LikerRepository likeRepository;
	
	@Autowired
	private DislikerRepository dislikeRepository;
	
	 @PostMapping(path="/addLike") // Map ONLY POST Requests
	  public @ResponseBody String addLike (@RequestParam Integer auteurId, 
	         @RequestParam Integer articleId) {

	    Liker n = new Liker();
	    n.setIdArticle(articleId);
	    n.setIdAuteur(auteurId);
	    likeRepository.save(n);
	    return "Saved";
	  }
	 
	 @GetMapping(path="/allLikes")
	  public @ResponseBody Iterable<Liker> getLikes() {
	    // This returns a JSON or XML with the users
	    return likeRepository.findAll();
	  }
	 
	 @PostMapping(path="/addDislike") // Map ONLY POST Requests
	  public @ResponseBody String addDislike (@RequestParam Integer auteurId, 
	         @RequestParam Integer articleId) {

	    Disliker n = new Disliker();
	    n.setIdArticle(articleId);
	    n.setIdAuteur(auteurId);
	    dislikeRepository.save(n);
	    return "Saved";
	  }
	 
	 @GetMapping(path="/allDislikes")
	  public @ResponseBody Iterable<Disliker> getDislikes() {
	    // This returns a JSON or XML with the users
	    return dislikeRepository.findAll();
	  }
	
}
