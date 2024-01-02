package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Utilisateur;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer>{
	
	void deleteById(Integer id);
	
	Utilisateur findByNom(String nom); 
	 
}
