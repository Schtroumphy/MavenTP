package com.dao;

import java.util.List;

import com.config.DAOException;
import com.models.Ville;

public interface VilleDAO {

	String ajouter( Ville ville ) throws DAOException;

	String supprimer ( Ville ville ) throws DAOException;
	
	List<Ville> lister () throws DAOException;
	
	List<Ville> filtrer ( Ville ville ) throws DAOException;

}
