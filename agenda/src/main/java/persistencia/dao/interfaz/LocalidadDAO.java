package persistencia.dao.interfaz;

import java.util.List;

import dto.LocalidadDTO;

public interface LocalidadDAO {
	public boolean insert(LocalidadDTO Localidad);
	
	public boolean delete(LocalidadDTO Localidad_a_eliminar);
	
	public boolean edit(int idLocalidad,LocalidadDTO Localidad_a_editar);
	
	public List<LocalidadDTO> readAll();
}
