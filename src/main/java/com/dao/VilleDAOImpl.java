package com.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO{

	@Override
	public ArrayList<Ville> findAllVilles() {
		ArrayList<Ville> listeVille = new ArrayList<Ville>();
		
		//TODO récupération et requête sql 
		
		return listeVille;
	}

}
