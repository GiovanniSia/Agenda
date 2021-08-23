package persistencia.dao.interfaz;

import java.util.List;

import dto.PaisDTO;

public interface PaisDAO {
	public boolean insert(PaisDTO Pais);
	
	public boolean delete(PaisDTO Pais_a_eliminar);
	
	public boolean edit(int idPais,PaisDTO Pais_a_editar);
	
	public List<PaisDTO> readAll();
}
