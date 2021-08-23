package persistencia.dao.interfaz;

import java.util.List;

import dto.ProvinciaDTO;

public interface ProvinciaDAO {
public boolean insert(ProvinciaDTO Provincia);
	
	public boolean delete(ProvinciaDTO Provincia_a_eliminar);
	
	public boolean edit(int idProvincia,ProvinciaDTO Provincia_a_editar,int ForeignPais);
	
	public List<ProvinciaDTO> readAll();
}
