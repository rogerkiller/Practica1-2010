package Practica1.RUM_WVS;

import java.util.Date;

public class Batallas {
	
	private int IDBatalla;
	private String Lugar;
	private Date FechaIni;
	private Date FechaFin;
	
	public Batallas(int IDBatalla, String Lugar, Date FechaIni, Date FechaFin)
	{
		this.IDBatalla = IDBatalla;
		this.Lugar = Lugar;
		this.FechaIni = FechaIni;
		this.FechaFin = FechaFin;
	}

	public int getIDBatalla() {
		return IDBatalla;
	}

	public void setIDBatalla(int iDBatalla) {
		IDBatalla = iDBatalla;
	}

	public String getLugar() {
		return Lugar;
	}

	public void setLugar(String lugar) {
		Lugar = lugar;
	}

	public Date getFechaIni() {
		return FechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		FechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return FechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		FechaFin = fechaFin;
	}
	
	

}
