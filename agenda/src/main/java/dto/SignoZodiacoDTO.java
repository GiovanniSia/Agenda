package dto;

public class SignoZodiacoDTO {
	private int idSignoZodiaco;
	private String nombreSignoZodiaco;

	public SignoZodiacoDTO(int idSignoZodiaco, String nombreSignoZodiaco)
	{
		this.idSignoZodiaco = idSignoZodiaco;
		this.nombreSignoZodiaco = nombreSignoZodiaco;
	}
	
	public int getIdSignoZodiaco() 
	{
		return this.idSignoZodiaco;
	}

	public void setIdSignoZodiaco(int idSignoZodiaco) 
	{
		this.idSignoZodiaco = idSignoZodiaco;
	}

	public String getNombreTipoContacto() 
	{
		return this.nombreSignoZodiaco;
	}

	public void setNombreSignoZodiaco(String nombreSignoZodiaco) 
	{
		this.nombreSignoZodiaco = nombreSignoZodiaco;
	}
}
