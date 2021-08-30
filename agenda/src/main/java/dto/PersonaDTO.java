package dto;

import java.sql.Date;

public class PersonaDTO 
{
	private int idPersona;
	private String nombre;
	private String telefono;
	private Domicilio domicilio;
	private String email;
	private Date fechaDeCumpleanios;
	private String tipoContacto;
	private String signoZodiaco;
	private String Pais;
	private String Provincia;
	private String Localidad;

	public PersonaDTO(int idPersona, String nombre, String telefono, Domicilio dom, String email, Date fechaCumpleanios,
			String tipoContacto,String signoZodiaco, String Pais, String Provincia, String Localidad) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.domicilio = dom;
		this.email = email;
		this.fechaDeCumpleanios = fechaCumpleanios;
		this.tipoContacto = tipoContacto;
		this.signoZodiaco = signoZodiaco;
		this.Pais = Pais;
		this.Provincia = Provincia;
		this.Localidad = Localidad;
	}

	public int getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public String getEmail() {
		return email;
	}

	public Date getFechaDeCumpleanios() {
		return fechaDeCumpleanios;
	}

	public String getTipoDeContacto() {
		return tipoContacto;
	}
	
	public String getSignoZodiaco() {
		return signoZodiaco;
	}
	
	public String getPais() {
		return Pais;
	}
	
	public String getProvincia() {
		return Provincia;
	}
	
	public String getLocalidad() {
		return Localidad;
	}
	
}
