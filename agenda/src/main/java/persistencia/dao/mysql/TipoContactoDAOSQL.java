package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.TipoContactoDAO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import presentacion.vista.VentanaTipoContacto;

public class TipoContactoDAOSQL implements TipoContactoDAO
{
	private static final String insert = "INSERT INTO tiposdecontactos(idTipoContacto, nombreTipoContacto) VALUES(?, ?)";
	private static final String delete = "DELETE FROM tiposdecontactos WHERE idTipoContacto = ?";
	private static final String edit = "UPDATE tiposdecontactos set nombreTipoContacto=? where idTipoContacto=?";
	private static final String readall = "SELECT * FROM tiposdecontactos";
	
	private VentanaTipoContacto ventanaTipoContacto;
	
	@Override
	public boolean insert(TipoContactoDTO tipoContacto) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, tipoContacto.getIdTipoContacto());
			statement.setString(2, tipoContacto.getNombreTipoContacto());
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

	@Override
	public boolean delete(TipoContactoDTO tipoContacto_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(tipoContacto_a_eliminar.getIdTipoContacto()));
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

	@Override
	public boolean edit(TipoContactoDTO tipoContacto_a_editar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isEditExitoso = false;
		try
		{
			int filaSeleccionado =ventanaTipoContacto.getTablaTipoContacto().getSelectedRow();
			String DAO = (String) ventanaTipoContacto.getTablaTipoContacto().getValueAt(filaSeleccionado, 0);
			statement = conexion.prepareStatement(edit);
			
			statement.setInt(1, tipoContacto_a_editar.getIdTipoContacto());
			statement.setString(2, tipoContacto_a_editar.getNombreTipoContacto());
			statement.setString(3, DAO);
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isEditExitoso = true;
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
		
		return isEditExitoso;

	}

	@Override
	public List<TipoContactoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<TipoContactoDTO> tipoContactos = new ArrayList<TipoContactoDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				tipoContactos.add(getTipoContactoDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return tipoContactos;
	}

	private TipoContactoDTO getTipoContactoDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("idTipoContacto");
		String nombre = resultSet.getString("nombreTipoContacto");
		return new TipoContactoDTO(id, nombre);
	}
}
