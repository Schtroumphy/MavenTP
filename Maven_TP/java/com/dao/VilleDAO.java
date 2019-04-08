package com.dao;

import com.config.DAOException;
import com.models.Ville;

public interface VilleDAO {

	String ajouter( Ville ville ) throws DAOException;

	String supprimer ( Ville ville ) throws DAOException;

}
