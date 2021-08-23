package dto;

public class Domicilio {

	private String calle;
	private String altura;
	private String piso;
	private String departamento;

	public Domicilio(String calle, String altura, String piso, String departamento) {
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.departamento = departamento;
	}

	public String getCalle() {
		return calle;
	}

	public String getAltura() {
		return altura;
	}

	public String getPiso() {
		return piso;
	}

	public String getDepartamento() {
		return departamento;
	}
	
}
