package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;

public class ArticleModerator extends ArticlePublisher{

	private List<Utilisateur> listeUsersLikes;
	private List<Utilisateur> listeUsersDislikes;
	
	public ArticleModerator(String titre, LocalDate date, String nomAuteur, String contenu) {
		super(titre, date, nomAuteur, contenu);
	}

	public List<Utilisateur> getListeUsersLikes() {
		return listeUsersLikes;
	}

	public void setListeUsersLikes(List<Utilisateur> listeUsersLikes) {
		this.listeUsersLikes = listeUsersLikes;
	}

	public List<Utilisateur> getListeUsersDislikes() {
		return listeUsersDislikes;
	}

	public void setListeUsersDislikes(List<Utilisateur> listeUsersDislikes) {
		this.listeUsersDislikes = listeUsersDislikes;
	}

}
