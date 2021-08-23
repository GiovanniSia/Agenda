package modelo;

import java.util.List;
import dto.TipoContactoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.TipoContactoDAO;

public class TipoContacto {
	private TipoContactoDAO tipoContacto;

	public TipoContacto(DAOAbstractFactory metodo_Persistencia) {
		this.tipoContacto = metodo_Persistencia.createTipoContactoDAO();
	}

	public void agregarTipoContacto(TipoContactoDTO nuevoTipoContacto) {
		this.tipoContacto.insert(nuevoTipoContacto);
	}

	public void borrarTipoContacto(TipoContactoDTO tipoContacto_a_eliminar) {
		this.tipoContacto.delete(tipoContacto_a_eliminar);
	}

	public void editarTipoContacto(int idTipoContacto, TipoContactoDTO tipoContacto_a_editar) {
		this.tipoContacto.edit(idTipoContacto, tipoContacto_a_editar);
	}

	public List<TipoContactoDTO> obtenerTipoContacto() {
		return this.tipoContacto.readAll();
	}
	
}
