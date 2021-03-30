package Practica1.RUM_WVS;

public class Habilidades {
	
	private String Tipo;
	private String Definicion;
	
	public Habilidades(String Tipo,String Definicion)
	{
		this.Tipo = Tipo;
		this.Definicion = Definicion;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getDefinicion() {
		return Definicion;
	}

	public void setDefinicion(String definicion) {
		Definicion = definicion;
	}

}
