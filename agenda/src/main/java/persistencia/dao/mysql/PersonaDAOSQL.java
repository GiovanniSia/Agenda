package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import dto.Domicilio;
import dto.PersonaDTO;

public class PersonaDAOSQL implements PersonaDAO
{
//	private static final String insert = "INSERT INTO personas(idPersona, nombre, telefono, calle, altura, piso, departamento, email, fechaCumpleanios) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String insert = "INSERT INTO personas VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM personas";
	private static final String update = "UPDATE personas SET nombre = ?, telefono = ? WHERE idPersona = ?";
		
	public boolean insert(PersonaDTO persona)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			
			//se agregan los datos en orden como estan en la tabla
			statement.setString(1, "0");
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getTelefono());
			
			//nuevos datos de domicilio falta localidad y la etiqueta
			statement.setString(4, persona.getDomicilio().getCalle());
			statement.setString(5, persona.getDomicilio().getAltura());
			statement.setString(6, persona.getDomicilio().getPiso());
			statement.setString(7, persona.getDomicilio().getDepartamento());
			statement.setString(8, persona.getEmail());
			statement.setDate(9, persona.getFechaDeCumpleanios());

			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isInsertExitoso;
	}
	
	
	public boolean delete(PersonaDTO persona_a_eliminar)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}
	
	public List<PersonaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				personas.add(getPersonaDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return personas;
	}
	
	private PersonaDTO getPersonaDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("idPersona");
		String nombre = resultSet.getString("Nombre");
		String tel = resultSet.getString("Telefono");
		
//		Datos nuevos HARDCODEADOS PORQUE EN LA  VISTA NO SE LOS PUEDE ELEGIR AUN
		//domicilio
//		String calle = resultSet.getString("calle");
//		String altura = resultSet.getString("altura");
//		String piso = resultSet.getString("piso");
//		String departamento = resultSet.getString("departamento");
//		
		Domicilio domicilio = new Domicilio("ComandanteVidela","1600","","");
//		
//		String email = resultSet.getString("email");
//		Date fechaCumpleanios = resultSet.getDate("fechaCumpleanios");
		Date d = new Date(2100,8,21);		
		return new PersonaDTO(id, nombre, tel, domicilio, "capitanVidela@gmail.com",d);
	}
	
	public boolean updatePersona(int idPersona, PersonaDTO nuevosDatos) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(update);
	
			//aca poner los get de la vista con todos los nuevos campos
			statement.setString(1, nuevosDatos.getNombre());
			statement.setString(2, nuevosDatos.getTelefono());
			statement.setInt(3, idPersona);
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isUpdateExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isUpdateExitoso;
	}
	
}
