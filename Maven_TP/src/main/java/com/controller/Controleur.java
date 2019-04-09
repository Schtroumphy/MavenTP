package com.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.dao.DAOFactory;
import com.dao.VilleDAO;
import com.models.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/path")  //Permet de rajouter des éléments dans le path 
public class Controleur {
		
	
		private VilleDAO villeDao;
		private DAOFactory daoFactory = DAOFactory.getInstance();
		@CrossOrigin
		@RequestMapping(value="/get", method=RequestMethod.GET)
		@ResponseBody
		public List<Ville> get(@RequestParam(required = false, value="value") String value) {
			
			List<Ville> listeVille = new ArrayList<Ville>();
	        this.villeDao = daoFactory.getVilleDao();
	        
	        listeVille =  villeDao.lister();
	       System.out.println("Liste ville : "+ listeVille);
			return listeVille;
		}
		@CrossOrigin
		@RequestMapping(value="/getFiltre", method=RequestMethod.GET)
		@ResponseBody
		public List<Ville> getFiltre(@RequestParam(required = true, value="nomCommune") String value) {
			
			List<Ville> listeVille = new ArrayList<Ville>();
	        this.villeDao = daoFactory.getVilleDao();
	        
	        Ville ville = new Ville();
	        ville.setNom(value);
	        
	        listeVille =  villeDao.filtrer(ville);
	       System.out.println("Liste ville : "+ listeVille);
			return listeVille;
		}
		
		@RequestMapping(value="/post", method=RequestMethod.POST)
		@ResponseBody
		public String post(@RequestParam(required = true, value="code") String code, @RequestParam(required = true, value="nom_commune") String nom_commune,
					@RequestParam(required = false, value="code_postal") String code_postal,@RequestParam(required = false, value="libelle") String libelle,
					@RequestParam(required = false, value="ligne_5") String ligne_5,@RequestParam(required = false, value="latitude") String latitude,
					@RequestParam(required = false, value="longitude") String longitude){
			Ville villeToAdd = new Ville(code,nom_commune, code_postal, libelle, ligne_5, latitude, longitude);
			
			String resultat="";
	        this.villeDao = daoFactory.getVilleDao();
	        
	       resultat =  villeDao.ajouter(villeToAdd);
	       System.out.println("Après ajout");
			return resultat;
		}
		
		@RequestMapping(value="/delete", method=RequestMethod.DELETE)
		@ResponseBody
		public String delete(@RequestParam(required = true, value="nomCommune") String value){
			Ville villeToSuppress = new Ville();
			villeToSuppress.setNom(value);
			
			String resultat="";
	        this.villeDao = daoFactory.getVilleDao();
	        
	       resultat =  villeDao.supprimer(villeToSuppress);
	       System.out.println("Après suppression ");
			return resultat;
		}
		@CrossOrigin
		@RequestMapping(value="/put", method=RequestMethod.PUT)
		@ResponseBody
		public List<Resultat> put(@RequestParam(required = true, value="nomCommune") String villeToEdit,@RequestParam(required = true, value="nouveauNom") String newName, @RequestParam(required = true, value="codePostal") String codePostal ){
			String resultat ="";
			List<Resultat> resultatList = new ArrayList<Resultat>();
			try {
				//DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/maven?useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","");
				
				Statement stmt = conn.createStatement();
				String query = "UPDATE `ville_france` SET `Nom_commune`='"+ newName +"' ,`Code_postal`='"+ codePostal +"' WHERE `Nom_commune` ='"+ villeToEdit +"'";
				System.out.println("Query : "+ query);
				boolean result = stmt.execute(query);
				if(!result) {
					System.out.println("Update réussie de "+ villeToEdit +" !");
					resultat =  "Update réussie de "+ villeToEdit +" !";
				}else {
					System.out.println("Echec de la mise à jour !");
					resultat = "Echec mise à jour "+ villeToEdit;
				}
				stmt.close();
				conn.close();
				
				resultatList.add(new Resultat(!result, resultat));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return resultatList;
		}
}
		