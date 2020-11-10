package com.dao;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleDAO {
	
	public ArrayList<Ville> getInfoVille();

	public ArrayList<Ville> getInfoVilles(String param);

}
