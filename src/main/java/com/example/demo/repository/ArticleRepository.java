package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Article;
import com.example.demo.entity.Utilisateur;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer>{
	public void deleteByUser(Utilisateur utilisateur);
	
	public void deleteById(Integer id);
}
