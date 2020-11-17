package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.config.JDBCConfiguration;
import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {

	/**
	 * @return la liste de toutes les villes
	 */
	public ArrayList<Ville> getInfoVille() {
		ArrayList<Ville> villes = new ArrayList<Ville>();

		String requete = "SELECT * FROM ville_france";

		try (Connection con = JDBCConfiguration.getConnexionBDD(); Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery(requete);
			while (rs.next()) {
				/**
				 * ordre des variables dans le constructeur codeCommune, nomCommune, codePostal,
				 * libelleAcheminement, ligne, latitude, longitude
				 * 
				 */
				Ville ville = new Ville(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getFloat(6), rs.getFloat(7));
				villes.add(ville);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return villes;
	}

	/**
	 * @param param, code postal recherché
	 * @return la liste des villes correspondantes au code postal entré en
	 *         paramètres
	 */
	public ArrayList<Ville> getInfoVilles(String param) {
		ArrayList<Ville> villes = new ArrayList<Ville>();
		String requete = "SELECT * FROM ville_france WHERE code_postal = ?";
		try (Connection con = JDBCConfiguration.getConnexionBDD()) {
			PreparedStatement ps = con.prepareStatement(requete);
			ps.setString(1, param);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Ville ville = new Ville(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getFloat(6), rs.getFloat(7));
					villes.add(ville);
				}
			} catch (SQLException e) {
				e.getErrorCode();
				e.getMessage();
				e.printStackTrace();
				System.out.println("Une erreur s'est produite lors de la récupération des villes");
			}
		} catch (SQLException e) {
			e.getErrorCode();
			e.getMessage();
			e.printStackTrace();
			System.out.println("Une erreur s'est produite lors de la connexion à la base");
		}
		return villes;
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
		boolean booleanInsert = false;
		String requete = "INSERT INTO ville_france (Code_commune_INSEE, Nom_commune, Code_postal, Libelle_acheminement,Ligne_5, Latitude, Longitude) VALUES (?,?,?,?,?,?,?)";

		try (Connection con = JDBCConfiguration.getConnexionBDD();) {
			PreparedStatement ps = con.prepareStatement(requete);
			ps.setString(1, codeCommune);
			ps.setString(2, nomCommune);
			ps.setString(3, codePostal);
			ps.setString(4, libelleAcheminement);
			if (ligne != null) {
				ps.setString(5, ligne);
			} else {
				ps.setString(5, "");
			}
			ps.setFloat(6, latitude);
			ps.setFloat(7, longitude);

			int rs = ps.executeUpdate();
			if (rs >= 1) {
				booleanInsert = true;
			}

		} catch (SQLException e) {
			e.getErrorCode();
			e.getMessage();
			e.printStackTrace();
			System.out.println("Une erreur s'est produite lors de la connexion à la base");
		}
		return booleanInsert;
	}

}
