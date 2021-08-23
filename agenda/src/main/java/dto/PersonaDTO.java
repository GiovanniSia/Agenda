package dto;

import java.sql.Date;

public class PersonaDTO 
{
	private int idPersona;
	private String nombre;
	private String telefono;

	//Datos nuevos, falta la localidad en domicilio
	private Domicilio domicilio;
	private String email;
	private Date fechaDeCumpleanios;
	
	public PersonaDTO(int idPersona, String nombre, String telefono, Domicilio dom, String email, Date fechaCumpleanios)
	{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		
		this.domicilio = dom;
		this.email=email;
		this.fechaDeCumpleanios = fechaCumpleanios;
	}
	
	public int getIdPersona() 
	{
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) 
	{
		this.idPersona = idPersona;
	}

	public String getNombre() 
	{
		return this.nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getTelefono() 
	{
		return this.telefono;
	}

	public void setTelefono(String telefono) 
	{
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
}
