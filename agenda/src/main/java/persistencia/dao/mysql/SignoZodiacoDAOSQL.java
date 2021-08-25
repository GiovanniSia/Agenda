package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.SignoZodiacoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.SignoZodiacoDAO;

public class SignoZodiacoDAOSQL implements SignoZodiacoDAO {
	private static final String readall = "SELECT * FROM signozodiaco";
	public List<SignoZodiacoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<SignoZodiacoDTO> signoZodiaco = new ArrayList<SignoZodiacoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				signoZodiaco.add(getSignoZodiacoDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return signoZodiaco;
	}

	private SignoZodiacoDTO getSignoZodiacoDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("idSignoZodiaco");
		String nombre = resultSet.getString("nombreTipoContacto");
		return new SignoZodiacoDTO(id, nombre);
	}
}
