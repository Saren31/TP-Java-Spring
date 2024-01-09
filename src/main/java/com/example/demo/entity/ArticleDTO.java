package com.example.demo.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ArticleDTO {
	
	private String titre;
	private String nomAuteur;
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate datePublication;
	private String contenu;

	public ArticleDTO(String titre, LocalDate datePublication, String nomAuteur, String contenu) {
		this.setTitre(titre);
		this.setNomAuteur(nomAuteur);
		this.setDatePublication(datePublication);
		this.setContenu(contenu);
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getNomAuteur() {
		return nomAuteur;
	}

	public void setNomAuteur(String nomAuteur) {
		this.nomAuteur = nomAuteur;
	}

	public LocalDate getDatePublication() {
		return datePublication;
	}

	public void setDatePublication(LocalDate datePublication) {
		this.datePublication = datePublication;
	}
	
	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
}
