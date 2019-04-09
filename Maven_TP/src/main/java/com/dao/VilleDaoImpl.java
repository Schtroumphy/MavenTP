package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.DAOUtilitaire.*;

import com.config.DAOException;
import com.models.Ville;

public class VilleDaoImpl implements VilleDAO {
	
	 private DAOFactory daoFactory;
	 private static final String SQL_INSERT_INTO = "INSERT INTO `ville_france`(`Code_commune_INSEE`, `Nom_commune`, `Code_postal`, `Libelle_acheminement`, `Ligne_5`, `Latitude`, `Longitude`) VALUES (?, ?, ?, ?, ?, ?, ?)";
	 private static final String SQL_DELETE = "DELETE FROM `ville_france` WHERE `Nom_commune`= ?";
	 private static final String SQL_SELECT = "SELECT * FROM ville_france LIMIT 400";
	 private static final String SQL_SELECT_FILTRE = "SELECT * FROM ville_france WHERE `Nom_commune` LIKE ?";
	 
	 

	 /*
		 * Simple méthode utilitaire permettant de faire la correspondance (le
		 * mapping) entre une ligne issue de la table des villes de france (un
		 * ResultSet) et un bean Ville.
		 */
		private static Ville map( ResultSet resultSet ) throws SQLException {
		    Ville ville = new Ville();
		    ville.setCode( resultSet.getString( "Code_commune_INSEE" ) );
		    ville.setNom( resultSet.getString( "Nom_commune" ) );
		    ville.setCode_postal( resultSet.getString( "Code_postal" ) );
		    ville.setLibelle( resultSet.getString( "Libelle_acheminement" ) );
		    ville.setLigne_5( resultSet.getString( "Ligne_5" ) );
		    ville.setLatitude( resultSet.getString( "Latitude" ) );
		    ville.setLongitude( resultSet.getString( "Longitude" ) );
		    return ville;
		}

	   VilleDaoImpl(DAOFactory daoFactory ) {
	        this.daoFactory = daoFactory;
	    }

	   public List<Ville> lister() throws DAOException {
			Connection connexion = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        List<Ville> listeVille = new ArrayList<Ville>();
	        
	        try {
	            /* Récupération d'une connexion depuis la Factory */
	            connexion = daoFactory.getConnection();
	            preparedStatement = DAOUtilitaire.initialisationRequetePreparee( connexion, SQL_SELECT, false);
	            System.out.println(preparedStatement);
	            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	            resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					Ville ville = new Ville(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString("Latitude"), resultSet.getString("Longitude"));
					listeVille.add(ville);
				}
				
	        } catch ( SQLException e ) {
	            throw new DAOException( e );
	        } finally {
	            DAOUtilitaire.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	        }
	        return listeVille;
		}
	   
	   public List<Ville> filtrer( Ville villeToFind) throws DAOException {
			Connection connexion = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        List<Ville> listeVille = new ArrayList<Ville>();
	        
	        try {
	            /* Récupération d'une connexion depuis la Factory */
	            connexion = daoFactory.getConnection();
	            System.out.println("Avant prepared Statement");
	            preparedStatement = DAOUtilitaire.initialisationRequetePreparee( connexion, SQL_SELECT_FILTRE, false, villeToFind.getNom());
	            System.out.println(preparedStatement);
	            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
	            resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					Ville ville = new Ville(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString("Latitude"), resultSet.getString("Longitude"));
					listeVille.add(ville);
				}
				
	        } catch ( SQLException e ) {
	            throw new DAOException( e );
	        } finally {
	            DAOUtilitaire.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	        }
	        return listeVille;
		}

	public String supprimer(Ville ville) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String resultat = "";
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisationRequetePreparee( connexion, SQL_DELETE, false, ville.getNom() );
            System.out.println(preparedStatement);
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            boolean result = preparedStatement.execute();
			if(!result) {
				resultat =  "Suppression réussie de"+ ville.getNom()+ "!";
				System.out.println(resultat);
			}else {
				resultat = "Echec de la suppression de "+ ville.getNom() +" !";
				System.out.println(resultat);
			}
			
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            DAOUtilitaire.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return resultat;
	}

	public String ajouter(Ville ville) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String resultat = "";
        
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initialisationRequetePreparee( connexion, SQL_INSERT_INTO, false, ville.getCode(), ville.getNom() ,ville.getCode_postal(),ville.getLibelle(),ville.getLigne_5(),ville.getLatitude(),ville.getLongitude() );
            System.out.println("query : "+ preparedStatement);
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            boolean result = preparedStatement.execute();
			if(!result) {
				resultat =  "Insertion réussie de "+ ville.getNom();
				System.out.println(resultat);
			}else {
				resultat = "Echec de l'insertion de "+ ville.getNom();
				System.out.println(resultat);
			}
			
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            DAOUtilitaire.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return resultat;
	}

    /* Implémentation de la méthode creer() définie dans l'interface UtilisateurDao */
   
}