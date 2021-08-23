package dto;

public class LocalidadDTO {
	private int idLocalidad;
	private String nombreLocalidad;
	private int idForeignProvincia;
	private int idForeignPais;

	public LocalidadDTO(int idLocalidad, String nombreLocalidad, int idForeignProvincia, int idForeignPais)
	{
		this.idLocalidad = idLocalidad;
		this.nombreLocalidad = nombreLocalidad;
		this.idForeignProvincia = idForeignProvincia;
		this.idForeignPais = idForeignPais;
	}

	public int getIdLocalidad() 
	{
		return this.idLocalidad;
	}

	public void setIdLocalidad(int idLocalidad) 
	{
		this.idLocalidad = idLocalidad;
	}

	public String getNombreLocalidad() 
	{
		return this.nombreLocalidad;
	}

	public void setNombreLocalidad(String nombreLocalidad) 
	{
		this.nombreLocalidad = nombreLocalidad;
	}
	
	public int getIdForeignProvincia() {
		return idForeignProvincia;
	}

	public void setIdForeignProvincia(int idForeignProvincia) {
		this.idForeignProvincia = idForeignProvincia;
	}
	
	public int getIdForeignPais() {
		return idForeignPais;
	}

	public void setIdForeignPais(int idForeignPais) {
		this.idForeignPais = idForeignPais;
	}
}
