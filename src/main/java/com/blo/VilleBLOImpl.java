package com.blo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDAO;
import com.dto.Ville;

@Service
public class VilleBLOImpl implements VilleBLO {

	@Autowired
	private VilleDAO villeDAO;

	/**
	 * @param param, code postal ou vide 
	 * @return liste des villes
	 */
	public ArrayList<Ville> getInfoVille(String param) {
		ArrayList<Ville> ville = null;
		if (param != null) {
			ville = villeDAO.getInfoVilles(param);
		} else {
			ville = villeDAO.getInfoVille();
		}
		return ville;
	}

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
			String ligne, float latitude, float longitude) {
		boolean reponse;
		if (ligne != null) {
			reponse = villeDAO.createVille(codeCommune, nomCommune, codePostal, libelleAcheminement, ligne, latitude, longitude);
		} else {
			reponse = villeDAO.createVille(codeCommune, nomCommune, codePostal, libelleAcheminement, null, latitude, longitude);
		}
		return reponse;
	}

}
