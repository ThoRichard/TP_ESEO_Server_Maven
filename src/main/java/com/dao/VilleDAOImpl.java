package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.config.JDBCConfiguration;
import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO{

	public ArrayList<Ville> getInfoVille() {
		Ville ville = null;
		ArrayList<Ville> villes = new ArrayList<Ville>();
		Connection con = JDBCConfiguration.getonnexionBDD();

		String requete = "SELECT * FROM ville_france";

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(requete);
			while (rs.next()) {
				ville = new Ville();
				ville.setCodeCommune(rs.getString(1));
				ville.setCodePostal(rs.getString(3));
				ville.setNomCommune(rs.getString(2));
				ville.setLibelleAcheminement(rs.getString(4));
				ville.setLigne(rs.getString(5));
				//ville.setLatitude(rs.getString(6));
				//ville.setLongitude(rs.getString(7));
				villes.add(ville);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return villes;
	}

	public ArrayList<Ville> getInfoVilles(String param) {
		ArrayList<Ville> villes = new ArrayList<Ville>();
		Ville ville = null;

		String requete = "SELECT * FROM ville_france WHERE code_postal = " + param;
		Connection con = JDBCConfiguration.getonnexionBDD();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(requete);
			while (rs.next()) {
				ville = new Ville();
				ville.setCodeCommune(rs.getString(1));
				ville.setCodePostal(rs.getString(3));
				ville.setNomCommune(rs.getString(2));
				ville.setLibelleAcheminement(rs.getString(4));
				ville.setLigne(rs.getString(5));
				//ville.setLatitude(rs.getString(6));
				//ville.setLongitude(rs.getString(7));
				villes.add(ville);
			}
			return villes;
		} catch (SQLException e) {
			System.out.println("Une erreur s'est produite.");
			return null;
		}

	}

}
