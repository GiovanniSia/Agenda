package modelo;

import java.util.List;

import dto.SignoZodiacoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.SignoZodiacoDAO;

public class SignoZodiaco {
	private SignoZodiacoDAO signoZodiaco;
	public SignoZodiaco(DAOAbstractFactory metodo_Persistencia) {
		this.signoZodiaco = metodo_Persistencia.createSignoZodiacoDAO();
	}
	
	public List<SignoZodiacoDTO> obtenerSignosZodiaco(){
		return this.signoZodiaco.readAll();
	}
}
