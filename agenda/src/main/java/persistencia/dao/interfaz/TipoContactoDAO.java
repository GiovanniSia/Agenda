package persistencia.dao.interfaz;

import java.util.List;
import dto.TipoContactoDTO;

public interface TipoContactoDAO {
	public boolean insert(TipoContactoDTO tipoContacto);

	public boolean delete(TipoContactoDTO tipoContacto_a_eliminar);

	public boolean edit(int idTipoContacto, TipoContactoDTO tipoContacto_a_editar);

	public List<TipoContactoDTO> readAll();
}
