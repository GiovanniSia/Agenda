package dto;

public class ProvinciaDTO {
	private int idProvincia;
	private String nombreProvincia;
	private int idForeignPais;

	public ProvinciaDTO(int idProvincia, String nombreProvincia, int idPaisForeign)
	{
		this.idProvincia = idProvincia;
		this.nombreProvincia = nombreProvincia;
		this.idForeignPais = idForeignPais;
	}

	public int getIdProvincia() 
	{
		return this.idProvincia;
	}

	public void setIdProvincia(int idProvincia) 
	{
		this.idProvincia = idProvincia;
	}

	public String getNombreProvincia() 
	{
		return this.nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) 
	{
		this.nombreProvincia = nombreProvincia;
	}
	
	public int getIdForeignPais() {
		return idForeignPais;
	}

	public void setIdForeignPais(int idPaisForeign) {
		this.idForeignPais = idPaisForeign;
	}
}
