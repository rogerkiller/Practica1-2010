package Practica1.RUM_WVS;

import java.util.ArrayList;
import Practica1.RUM_WVS.Batallas;

public class Heroes {
	
	private int HeroeID ;
	private String NombreHeroe;
	private String SecretID;
	private String Genero;
	private String Procedencia;
	private ArrayList<Batallas> Batallas;
	private ArrayList<Habilidades> Habilidades;
	
	public Heroes (int HeroeID, String NombreHeroe, 
			String SecretID, String Genero, String Procedencia, ArrayList<Batallas> Batallas,
			ArrayList<Habilidades> Habilidades)
	{
		this.HeroeID = HeroeID;
		this.NombreHeroe = NombreHeroe;
		this.SecretID = SecretID;
		this.Genero = Genero;
		this.Procedencia = Procedencia;
		this.setBatallas(Batallas);
		this.setHabilidades(Habilidades);
	}
	

	public int getHeroeID() {
		return HeroeID;
	}

	public void setHeroeID(int heroeID) {
		HeroeID = heroeID;
	}

	public String getNombreHeroe() {
		return NombreHeroe;
	}

	public void setNombreHeroe(String nombreHeroe) {
		NombreHeroe = nombreHeroe;
	}

	public String getSecretID() {
		return SecretID;
	}

	public void setSecretID(String secretID) {
		SecretID = secretID;
	}

	public String getGenero() {
		return Genero;
	}

	public void setGenero(String genero) {
		Genero = genero;
	}

	public String getProcedencia() {
		return Procedencia;
	}

	public void setProcedencia(String procedencia) {
		Procedencia = procedencia;
	}

	public ArrayList<Habilidades> getHabilidades() {
		return Habilidades;
	}

	public void setHabilidades(ArrayList<Habilidades> habilidades) {
		Habilidades = habilidades;
	}
	
	public ArrayList<Batallas> getBatallas() {
		return Batallas;
	}

	public void setBatallas(ArrayList<Batallas> Batallas) {
		this.Batallas = Batallas;
	}
	
	public void addBatallas(Batallas Batallas) {
		this.Batallas.add(Batallas);
	}
	
	
}
