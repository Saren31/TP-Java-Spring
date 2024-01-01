package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Liker {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private Utilisateur user;

    @ManyToOne
    @JoinColumn(name = "article_id") 
    private Article article;
    
    private Boolean isLiked;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur utilisateur) {
		this.user = utilisateur;
	}

	public Boolean getLike() {
		return isLiked;
	}

	public void setLike(Boolean like) {
		this.isLiked = like;
	}
	
}
