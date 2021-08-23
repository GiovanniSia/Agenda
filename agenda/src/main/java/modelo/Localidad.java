package modelo;

import java.util.List;

import dto.LocalidadDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.LocalidadDAO;

public class Localidad {
private LocalidadDAO localidad;
	
	public Localidad(DAOAbstractFactory metodo_Persistencia) {
		this.localidad = metodo_Persistencia.createLocalidadDAO();
	}
	
	public void agregarLocalidad(LocalidadDTO nuevaLocalidad) {
		this.localidad.insert(nuevaLocalidad);
	}
	
	public void borrarLocalidad(LocalidadDTO Localidad_a_eliminar) {
		this.localidad.delete(Localidad_a_eliminar);
	}
	public void editarLocalidad(int idLocalidad,LocalidadDTO Localidad_a_editar) {
		this.localidad.edit(idLocalidad,Localidad_a_editar);
	}
	public List<LocalidadDTO> obtenerLocalidad(){
		return this.localidad.readAll();
	}
}
