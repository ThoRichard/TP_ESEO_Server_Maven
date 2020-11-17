package com.controler;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.VilleBLO;
import com.dto.Ville;

@RestController
public class VilleControler {

	@Autowired
	VilleBLO villeService;

	/**
	 * Méthode GET, trouver toutes les villes
	 * 
	 * @param monParam, code postal
	 * @return liste des villes
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@RequestMapping(value = "/ville", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Ville> AllVilles() throws ClassNotFoundException, SQLException {
		System.out.println("All Villes");
		ArrayList<Ville> ville = villeService.getInfoVille(null);
		return ville;
	}

	/**
	 * Méthode GET, trouver une ou plusieurs villes avec un codePostal
	 * 
	 * @param monParam, code postal
	 * @return liste des villes
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@RequestMapping(value = "/ville/{codePostal}", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Ville> findVille(@PathVariable String codePostal) throws ClassNotFoundException, SQLException {
		System.out.println("find Ville");
		System.out.println("codePostal = " + codePostal);
		ArrayList<Ville> ville = villeService.getInfoVille(codePostal);
		return ville;
	}

	/**
	 * Méthode POST
	 * 
	 * @param ville, ville à insérer
	 * @return si l'insertion s'est correctement déroulé
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@RequestMapping(value = "/ville", method = RequestMethod.POST)
	@ResponseBody
	public boolean appelPost(@RequestBody Ville ville) throws ClassNotFoundException, SQLException {
		System.out.println("Appel POST");
		boolean reponse = villeService.createVille(ville);
		return reponse;
	}

	/**
	 * Méthode PUT
	 */
	@RequestMapping(value = "/ville/{Code_commune_INSEE}", method = RequestMethod.PUT)
	@ResponseBody
	public boolean appelPut(@PathVariable String Code_commune_INSEE, @RequestBody Ville ville)
			throws ClassNotFoundException, SQLException {
		System.out.println("Appel PUT");
		ville.setCodeCommune(Code_commune_INSEE);
		boolean reponse = villeService.updateVille(ville);
		return reponse;
	}

	/**
	 * Méthode DELETE
	 */
	@RequestMapping(value = "/ville/{Code_commune_INSEE}", method = RequestMethod.DELETE)
	@ResponseBody
	public void appelDelete(@PathVariable String Code_commune_INSEE) throws ClassNotFoundException, SQLException {
		System.out.println("Appel DELETE");
		villeService.deleteVille(Code_commune_INSEE);
		
	}

}
