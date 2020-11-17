package com.controler;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
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

	// Methode GET
	@RequestMapping(value = "/ville", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Ville> appelGet(@RequestParam(required = false, value = "codePostal") String monParam)
			throws ClassNotFoundException, SQLException {
		System.out.println("Appel GET");
		System.out.println("param = " + monParam);
		ArrayList<Ville> ville = villeService.getInfoVille(monParam);
		return ville;
	}

	// Methode POST
	@RequestMapping(value = "/ville", method = RequestMethod.POST)
	@ResponseBody
	public boolean appelPut(@RequestParam(required = true, value = "codeCommune") String codeCommune,
			@RequestParam(required = true, value = "nomCommune") String nomCommune,
			@RequestParam(required = true, value = "codePostal") String codePostal,
			@RequestParam(required = true, value = "libelleAcheminement") String libelleAcheminement,
			@RequestParam(required = false, value = "ligne") String ligne,
			@RequestParam(required = true, value = "latitude") float latitude,
			@RequestParam(required = true, value = "longitude") float longitude)
			throws ClassNotFoundException, SQLException {
		System.out.println("Appel POST");
		boolean reponse = villeService.createVille(codeCommune, nomCommune, codePostal, libelleAcheminement, ligne,
				latitude, longitude);
		return reponse;
	}

}
