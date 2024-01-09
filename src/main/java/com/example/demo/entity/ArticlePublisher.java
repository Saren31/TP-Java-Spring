package com.example.demo.entity;

import java.time.LocalDate;

public class ArticlePublisher extends ArticleDTO{

	private Integer nbDislikes;
	private Integer nbLikes;

	public ArticlePublisher(String titre, LocalDate date, String nomAuteur, String contenu) {
		super(titre, date, nomAuteur, contenu);
	}

	public Integer getNbDislikes() {
		return nbDislikes;
	}

	public void setNbDislikes(Integer nbDislikes) {
		this.nbDislikes = nbDislikes;
	}

	public Integer getNbLikes() {
		return nbLikes;
	}

	public void setNbLikes(Integer nbLikes) {
		this.nbLikes = nbLikes;
	}

}
