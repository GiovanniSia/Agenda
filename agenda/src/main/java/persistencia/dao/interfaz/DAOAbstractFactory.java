package persistencia.dao.interfaz;


public interface DAOAbstractFactory 
{
	public PersonaDAO createPersonaDAO();
	
	public TipoContactoDAO createTipoContactoDAO();
	
	public PaisDAO createPaisDAO();
	
	public ProvinciaDAO createProvinciaDAO();
	
	public LocalidadDAO createLocalidadDAO();
}
