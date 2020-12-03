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

import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class VilleDAOImpl implements VilleDAO {
	// assumes the current class is called MyLogger
	private static final Logger LOGGER = Logger.getLogger(VilleDAOImpl.class.getName());
	private static final String ERROR_BDD = "Une erreur s'est produite lors de la connexion à la base : ";

	/**
	 * @return la liste de toutes les villes
	 */
	public ArrayList<Ville> getInfoVille() {
		ArrayList<Ville> villes = new ArrayList<Ville>();

		String requete = "SELECT * FROM ville_france";

		try (Connection con = JDBCConfiguration.getConnexionBDD();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(requete);) {
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
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, ERROR_BDD + e.getMessage(),
					e);
		}
		return villes;
	}

	/**
	 * @param param, code postal recherché
	 * @return la liste des villes correspondantes au code postal entré en
	 *         paramètres
	 */
	public ArrayList<Ville> getInfoVilles(String codePostal) {
		ArrayList<Ville> villes = new ArrayList<Ville>();
		String requete = "SELECT * FROM ville_france WHERE code_postal = ?";
		try (Connection con = JDBCConfiguration.getConnexionBDD();
				PreparedStatement ps = con.prepareStatement(requete);) {
			ps.setString(1, codePostal);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Ville ville = new Ville(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getFloat(6), rs.getFloat(7));
					villes.add(ville);
				}
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING,
						"Une erreur s'est produite lors de la récupération des villes : " + e.getMessage(), e);
			}
			ps.close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, ERROR_BDD + e.getMessage(),
					e);
		}
		return villes;
	}

	/**
	 * @param ville, ville à insérer
	 * @return si l'insertion s'est correctement déroulé
	 */
	public boolean createVille(Ville ville) {
		boolean booleanInsert = false;
		String requete = "INSERT INTO ville_france (Code_commune_INSEE, Nom_commune, Code_postal, Libelle_acheminement,Ligne_5, Latitude, Longitude) VALUES (?,?,?,?,?,?,?)";

		try (Connection con = JDBCConfiguration.getConnexionBDD();
				PreparedStatement ps = con.prepareStatement(requete);) {
			ps.setString(1, ville.getCodeCommune());
			ps.setString(2, ville.getNomCommune());
			ps.setString(3, ville.getCodePostal());
			ps.setString(4, ville.getLibelleAcheminement());
			if (ville.getLigne() != null) {
				ps.setString(5, ville.getLigne());
			} else {
				ps.setString(5, "");
			}
			ps.setFloat(6, ville.getLatitude());
			ps.setFloat(7, ville.getLongitude());

			int rs = ps.executeUpdate();
			if (rs >= 1) {
				booleanInsert = true;
			}
			ps.close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, ERROR_BDD + e.getMessage(),
					e);
		}
		return booleanInsert;
	}

	/**
	 * @param ville, informations à modifier dans la ville cible
	 */
	public boolean updateVille(Ville ville) {
		boolean booleanInsert = false;
		String requete = "UPDATE ville_france "
				+ "SET Nom_commune = (CASE WHEN ? is not null THEN ? ELSE Nom_commune END), Code_postal = (CASE WHEN ? is not null THEN ? ELSE Code_postal END), Libelle_acheminement = (CASE WHEN ? is not null THEN ? ELSE Libelle_acheminement END), "
				+ "Ligne_5 = (CASE WHEN ? is not null THEN ? ELSE Ligne_5 END), Latitude = (CASE WHEN ? != 0.0 THEN ? ELSE Latitude END), Longitude = (CASE WHEN ? != 0.0 THEN ? ELSE Longitude END) "
				+ "WHERE Code_commune_INSEE = ?";
		try (Connection con = JDBCConfiguration.getConnexionBDD();
				PreparedStatement ps = con.prepareStatement(requete);) {
			ps.setString(1, ville.getNomCommune());
			ps.setString(2, ville.getNomCommune());
			ps.setString(3, ville.getCodePostal());
			ps.setString(4, ville.getCodePostal());
			ps.setString(5, ville.getLibelleAcheminement());
			ps.setString(6, ville.getLibelleAcheminement());
			ps.setString(7, ville.getLigne());
			ps.setString(8, ville.getLigne());
			ps.setFloat(9, ville.getLatitude());
			ps.setFloat(10, ville.getLatitude());
			ps.setFloat(11, ville.getLongitude());
			ps.setFloat(12, ville.getLongitude());
			ps.setString(13, ville.getCodeCommune());

			int rs = ps.executeUpdate();
			if (rs >= 1) {
				booleanInsert = true;
			}
			ps.close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, ERROR_BDD + e.getMessage(),
					e);
		}
		return booleanInsert;
	}

	public void deleteVille(String codeCommune) {
		String requete = "DELETE FROM ville_france WHERE Code_commune_INSEE = ?";
		try (Connection con = JDBCConfiguration.getConnexionBDD();
				PreparedStatement ps = con.prepareStatement(requete);) {
			ps.setString(1, codeCommune);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, ERROR_BDD + e.getMessage(),
					e);
		}
	}

}
