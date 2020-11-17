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
	 * @param param, code postal 
	 * @return liste des villes correspondantes au code postal
	 */
	public ArrayList<Ville> getInfoVilles(String param);

	/**
	 * @param codeCommune,         clé primaire (code INSEE)
	 * @param nomCommune,          le nom de la commune
	 * @param codePostal,          le code postal
	 * @param libelleAcheminement, libelle postal
	 * @param ligne,               complément de localisation
	 * @param latitude,            latitude en degrés décimaux
	 * @param longitude,           longitude en degrés décimaux
	 * @return si l'insertion s'est correctement déroulé
	 */
	public boolean createVille(String codeCommune, String nomCommune, String codePostal, String libelleAcheminement,
			String ligne, float latitude, float longitude);

}
