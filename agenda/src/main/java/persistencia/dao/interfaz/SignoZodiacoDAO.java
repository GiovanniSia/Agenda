package persistencia.dao.interfaz;

import java.util.List;

import dto.SignoZodiacoDTO;

public interface SignoZodiacoDAO {
	public List<SignoZodiacoDTO> readAll();
}
