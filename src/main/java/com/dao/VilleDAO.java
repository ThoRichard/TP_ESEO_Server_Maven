package com.dao;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleDAO {

	/**
	 * 
	 * @return liste de toutes les villes
	 */
	public ArrayList<Ville> getInfoVille();

	/**
	 * 
	 * @param codePostal, code postal 
	 * @return liste des villes correspondantes au code postal
	 */
	public ArrayList<Ville> getInfoVilles(String codePostal);

	/**
	 * @param ville,         ville à insérer
	 * @return si l'insertion s'est correctement déroulé
	 */
	public boolean createVille(Ville ville);
	
	public boolean updateVille(Ville ville);
	
	public void deleteVille(String codeCommune);

}
