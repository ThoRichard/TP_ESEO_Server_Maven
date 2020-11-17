package com.blo;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleBLO {

	/**
	 * 
	 * @param codePostal, code postal
	 * @return liste des villes
	 */
	public ArrayList<Ville> getInfoVille(String codePostal);

	/**
	 * @param ville, ville à insérer
	 * @return si l'insertion s'est correctement déroulé
	 */
	public boolean createVille(Ville ville);

	/**
	 * @param ville, ville à modifier
	 * @return si la ville a bien été modifié
	 */
	public boolean updateVille(Ville ville);
	
	/**
	 * 
	 * @param codeCommune, code INSEE de la commune
	 */
	public void deleteVille(String codeCommune);

}
