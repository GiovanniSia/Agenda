package dto;

public class ProvinciaDTO {
	private int idProvincia;
	private String nombreProvincia;
	private int idPaisForeign;

	public ProvinciaDTO(int idProvincia, String nombreProvincia, int idPaisForeign)
	{
		this.idProvincia = idProvincia;
		this.nombreProvincia = nombreProvincia;
		this.idPaisForeign = idPaisForeign;
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
	
	public int getIdPaisForeign() {
		return idPaisForeign;
	}

	public void setIdPaisForeign(int idPaisForeign) {
		this.idPaisForeign = idPaisForeign;
	}
}
