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
	 * @param codePostal, code postal ou vide
	 * @return liste des villes
	 */
	public ArrayList<Ville> getInfoVille(String codePostal) {
		ArrayList<Ville> ville = null;
		if (codePostal != null) {
			ville = villeDAO.getInfoVilles(codePostal);
		} else {
			ville = villeDAO.getInfoVille();
		}
		return ville;
	}

	/**
	 * @param ville, ville à insérer
	 * @return si l'insertion s'est correctement déroulé
	 */
	public boolean createVille(Ville ville) {
		boolean reponse = villeDAO.createVille(ville);
		return reponse;
	}

	public boolean updateVille(Ville ville) {
		boolean reponse = villeDAO.updateVille(ville);
		return reponse;
	}
	
	public void deleteVille(String codeCommune) {
		villeDAO.deleteVille(codeCommune);
	}

}
