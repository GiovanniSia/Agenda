package persistencia.conexion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import modelo.ConfiguracionBD;

import org.apache.ibatis.jdbc.ScriptRunner;


public class Conexion 
{
	
	public static Conexion instancia;
	private Connection connection;
	private Logger log = Logger.getLogger(Conexion.class);	
	
	private ConfiguracionBD configBD = ConfiguracionBD.getInstance();
	String url;
	
	
	
	public boolean conectar()
	{
		try
		{
//			BasicConfigurator.configure();//si es la primera vez se crea el archivo, sino todo piola
//			crearBD();
//			if(configBD.obtenerProperty("firstTime").equals("true")) {
//				crearBD();
//				setearDatos();
//				configBD.writeValue("firstTime", "false");
//			}
			
			
			
			// quitar si no es necesario
			Class.forName("com.mysql.cj.jdbc.Driver");
			
//			url = "jdbc:mysql://"+configBD.obtenerProperty("ip") + ":"+ configBD.obtenerProperty("puerto") + "/" +"agenda";
			
//			this.connection = DriverManager.getConnection(this.url, configBD.obtenerProperty("usuario"), configBD.obtenerProperty("contrasenia"));
			
//			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda","root","root");

			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", configBD.obtenerProperty("usuario"), configBD.obtenerProperty("contrasenia"));
			
			this.connection.setAutoCommit(false);
	
			log.info("Conexión exitosa");
			
//			if(configBD.obtenerProperty("firstTime").equals("true")) {
//				setearDatos();
//				configBD.writeValue("firstTime", "false");
//			}
			
			
			return true;
		}
		catch(Exception e)
		{
		}
		return false;
	}
	
//	public boolean esPrimeraConexion() {
//		try {
//			BasicConfigurator.configure();
//			if(configBD.obtenerProperty("firstTime").equals("true")) {
//				System.out.println("es primera conexion");
//				return true;
//			}
//		}catch(Exception e) {
//			
//		}
//		return false;
//	}
	
	
	
	public static Conexion getConexion()   
	{								
		if(instancia == null)
		{
			instancia = new Conexion();
			instancia.conectar();
		}
		return instancia;
	}

	public Connection getSQLConexion() 
	{
		return this.connection;
	}
	
	public void cerrarConexion()
	{
		try 
		{
			this.connection.close();
			log.info("Conexion cerrada");
		}
		catch (SQLException e) 
		{
			log.error("Error al cerrar la conexión!", e);
		}
		instancia = null;
	}
	
	

	
	
//	public boolean crearBD() {
//		//CREAMOS LA BASe
//		String sql = "CREATE DATABASE IF NOT EXISTS agenda";//se crea si no existe
//		try {
//			
//			url = "jdbc:mysql://"+configBD.obtenerProperty("ip") + ":"+ configBD.obtenerProperty("puerto");
//			try {
//				this.connection = DriverManager.getConnection(this.url, configBD.obtenerProperty("usuario"), configBD.obtenerProperty("contrasenia"));
////				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","root");			
//				PreparedStatement stmt = connection.prepareStatement(sql);				
//				stmt.execute();
//				return true;
//			}catch(Exception e) {
//				return false;
//			}
//			
//		} catch (NullPointerException e) {
//			return false;
//		}
//	}
//	
//	
//	public void borrarBD() {
//		String sql = "DROP DATABASE IF EXISTS agenda";
//		try {
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","root");
//			
//			
//			PreparedStatement stmt = connection.prepareStatement(sql);
//			stmt.execute();
//			
//			log.info("Base de datos borrada");
//			stmt.execute();
//		} catch (Exception e) {
//			log.error("Error al borrar la BD", e);
//		}
//	}
//	
//	
//	public void setearDatos() throws Exception{
//		ScriptRunner runner = new ScriptRunner(connection);
//		InputStreamReader reader = null;		
////        InputStream stream = Conexion.class.getResourceAsStream("/sql/scriptAgenda.sql");
//		
//		try {
////			reader = new InputStreamReader(stream, "UTF-8");
////			runner.runScript(reader);
//			
//			runner.runScript( new InputStreamReader(new FileInputStream("sql//scriptAgenda.sql")));
//			
////			reader.close();
//		} finally {
//			
//			if (reader != null) {
//				try {
//					reader.close();
//				} catch (IOException e) {
////					e.printStackTrace();
//				}
//				reader = null;
//			}			
//		}		
//	}
	
	
}
