package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Agenda;
import modelo.TipoContacto;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersona;
import presentacion.vista.VentanaTipoContacto;
import presentacion.vista.Vista;
import dto.Domicilio;
import dto.PersonaDTO;
import dto.TipoContactoDTO;

public class Controlador implements ActionListener {
	private Vista vista;
	private List<PersonaDTO> personasEnTabla;
	private List<TipoContactoDTO> tipoContactoEnTabla;

	private VentanaPersona ventanaPersona;
	private VentanaTipoContacto ventanaTipoContacto;
	private Agenda agenda;
	private TipoContacto tipoContacto;

	int filaSeleccionada;

	public Controlador(Vista vista, Agenda agenda) {
		this.ventanaTipoContacto = new VentanaTipoContacto();
		this.ventanaTipoContacto.getBtnAgregar().addActionListener(a -> agregarTipoContacto(a));
		this.ventanaTipoContacto.getBtnEditar().addActionListener(e -> editarTipoContacto(e));
		this.ventanaTipoContacto.getBtnBorrar().addActionListener(b -> borrarTipoContacto(b));
		this.ventanaTipoContacto.getBtnSalir().addActionListener(s -> salirTipoContacto(s));

		this.tipoContacto = new TipoContacto(new DAOSQLFactory());
		this.refrescarTablaTipoContacto();

		this.vista = vista;
		this.vista.getBtnAgregar().addActionListener(a -> ventanaAgregarPersona(a));
		this.vista.getBtnBorrar().addActionListener(s -> borrarPersona(s));
		this.vista.getBtnReporte().addActionListener(r -> mostrarReporte(r));

		this.ventanaPersona = VentanaPersona.getInstance();
		this.vista.getBtnEditar().addActionListener(e -> mostrarVentanaEditar(e));
		this.ventanaPersona.getBtnAceptar().addActionListener(a -> editarPersona(a));
		this.ventanaPersona.getBtnCancelar().addActionListener(c -> cerrarVentanaEditar(c));

		this.ventanaPersona.getBtnAgregarPersona().addActionListener(p -> guardarPersona(p));
		this.ventanaPersona.getBtnEditarTipoContacto().addActionListener(t -> ventanaEditarTipoContacto(t));
		this.agenda = agenda;
	}

	private void ventanaAgregarPersona(ActionEvent a) {
		this.ventanaPersona.limpiarValores();
		this.ventanaPersona.mostrarVentana(tipoContactoEnTabla);
	}

	private void ventanaEditarTipoContacto(ActionEvent t) {
		this.ventanaTipoContacto.mostrarVentana();
	}

	private void agregarTipoContacto(ActionEvent a) {
		String nombreTipoContacto = this.ventanaTipoContacto.getTxtTipoContacto().getText();
		TipoContactoDTO nuevoTipoContacto = new TipoContactoDTO(0, nombreTipoContacto);
		this.tipoContacto.agregarTipoContacto(nuevoTipoContacto);
		this.refrescarTablaTipoContacto();
		this.ventanaTipoContacto.limpiarTxtTipoContacto();
	}

	private void editarTipoContacto(ActionEvent e) {
		if (this.seSeleccionoTablaTipoContacto()) {

			int filaSeleccionado = this.ventanaTipoContacto.tablaTipoContactoSeleccionada();
			int idModificar = this.tipoContactoEnTabla.get(filaSeleccionado).getIdTipoContacto();
			System.out.println(idModificar);
			String nombreNuevo = ventanaTipoContacto.getTxtTipoContacto().getText();

			TipoContactoDTO datosNuevos = new TipoContactoDTO(0, nombreNuevo);
			tipoContacto.editarTipoContacto(idModificar, datosNuevos);
			this.refrescarTablaTipoContacto();
		}
		this.ventanaTipoContacto.limpiarTxtTipoContacto();
	}

	private void borrarTipoContacto(ActionEvent b) {
		int[] filasSeleccionadas = this.ventanaTipoContacto.getTablaTipoContacto().getSelectedRows();
		for (int fila : filasSeleccionadas) {
			this.tipoContacto.borrarTipoContacto(this.tipoContactoEnTabla.get(fila));
		}
		this.refrescarTablaTipoContacto();
		this.ventanaTipoContacto.limpiarTxtTipoContacto();
	}

	private void salirTipoContacto(ActionEvent s) {
		this.ventanaTipoContacto.cerrar();
		this.ventanaTipoContacto.limpiarTxtTipoContacto();
		
		
		
		
		
		
	}

	public void refrescarTablaTipoContacto() {
		this.tipoContactoEnTabla = tipoContacto.obtenerTipoContacto();
		this.ventanaTipoContacto.llenarTabla(tipoContactoEnTabla);
	}

	private void guardarPersona(ActionEvent p) {
		PersonaDTO nuevaPersona = obtenerPersonaDeVista();
		if (todosLosCamposSonValidos(nuevaPersona)) {
			this.agenda.agregarPersona(nuevaPersona);
			this.refrescarTabla();
			this.ventanaPersona.cerrar();
		}

	}

	private void mostrarReporte(ActionEvent r) {
		ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
		reporte.mostrar();
	}

	public void borrarPersona(ActionEvent s) {
		int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
		for (int fila : filasSeleccionadas) {
			this.agenda.borrarPersona(this.personasEnTabla.get(fila));
		}
		this.refrescarTabla();
	}

	public void mostrarVentanaEditar(ActionEvent e) {
		filaSeleccionada = this.vista.getTablaPersonas().getSelectedRow();
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna persona para editar");
			return;
		}
		this.ventanaPersona.mostrarVentanaConValores(this.personasEnTabla.get(filaSeleccionada), tipoContactoEnTabla);
	}

	public void editarPersona(ActionEvent e) {
		int idModificar = this.personasEnTabla.get(filaSeleccionada).getIdPersona();
		PersonaDTO datosNuevos = obtenerPersonaDeVista();
		if (todosLosCamposSonValidos(datosNuevos)) {
			agenda.editarPersona(idModificar, datosNuevos);
			refrescarTabla();
			ventanaPersona.cerrar();
		}
	}

	public void cerrarVentanaEditar(ActionEvent e) {
		this.ventanaPersona.cerrar();
	}

	public void inicializar() {
		this.refrescarTabla();
		this.vista.show();
	}

	private void refrescarTabla() {
		this.personasEnTabla = agenda.obtenerPersonas();// lo obtiene de la bd
		this.tipoContactoEnTabla = agenda.obtenerTiposDeContactos();
		this.vista.llenarTabla(this.personasEnTabla, this.tipoContactoEnTabla);
	}
	
	private void refrescarCbTipoContacto() {
		this.ventanaPersona.escribirComboBoxTipoDeContacto(this.tipoContactoEnTabla);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	public PersonaDTO obtenerPersonaDeVista() {
		String nombre = ventanaPersona.getTxtNombre().getText();
		String telefono = ventanaPersona.getTxtTelefono().getText();
		String calle = ventanaPersona.getCalle().getText();
		String altura = ventanaPersona.getAltura().getText();
		String piso = ventanaPersona.getPiso().getText();
		String departamento = ventanaPersona.getDepartamento().getText();
		String email = ventanaPersona.getEmail().getText();
		java.sql.Date fechaDeCumpleanios = new java.sql.Date(ventanaPersona.getFechaCumpleanios().getDate().getTime());
		Domicilio domicilio = new Domicilio(calle, altura, piso, departamento);
		String tipoContacto = ventanaPersona.getTipoDeContactoSeleccionado();
		return new PersonaDTO(0, nombre, telefono, domicilio, email, fechaDeCumpleanios, tipoContacto);
	}

	public boolean seSeleccionoTablaTipoContacto() {
		if(this.ventanaTipoContacto.getTablaTipoContacto().getSelectedRow() != -1) {
			return true;
		}
		return false;
	}
	
	public boolean todosLosCamposSonValidos(PersonaDTO datosNuevos) {
		String nombre = datosNuevos.getNombre();
		boolean expresionNombre = nombre.matches("[\\w\\s&&[^\\d]]{1,45}");
		if (!expresionNombre) {
			JOptionPane.showMessageDialog(null, "Por favor complete los caracteres de nombre correctamente");
			return false;
		}
		String telefono = datosNuevos.getTelefono();
		boolean expresionTelefono = telefono.matches("[\\d&&[^a-zA-Z]]{10,20}");
		if (!expresionTelefono) {
			JOptionPane.showMessageDialog(null, "Por favor ingrese un teléfono válido");
			return false;
		}
		String calle = datosNuevos.getDomicilio().getCalle();
		boolean expresionCalle = calle.matches("[\\w\\s&&[^\\d]]{1,45}");
		if (!expresionCalle) {
			JOptionPane.showMessageDialog(null, "Por favor ingrese una calle válida");
			return false;
		}
		String altura = datosNuevos.getDomicilio().getAltura();
		boolean expresionAltura = altura.matches("[\\d&&[^a-zA-Z]]+");
		if (!expresionAltura) {
			JOptionPane.showMessageDialog(null, "Por favor ingrese una altura válida");
			return false;
		}
		String piso = datosNuevos.getDomicilio().getPiso();
		boolean expresionPiso = piso.matches("[\\d&&[^a-zA-Z]]*");
		if (!expresionPiso) {
			JOptionPane.showMessageDialog(null, "Por favor ingrese un piso válido");
			return false;
		}

		String departamento = datosNuevos.getDomicilio().getDepartamento();
		boolean expresionDepartamento = departamento.matches("[\\d&&[^a-zA-Z]]*");
		if (!expresionDepartamento) {
			JOptionPane.showMessageDialog(null, "Por favor ingrese un departamento válido");
			return false;
		}

		String mail = datosNuevos.getEmail();
		boolean expresionMail = mail
				.matches("^[A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");// NOSE
		if (!expresionMail) {
			JOptionPane.showMessageDialog(null, "Por favor ingrese una dirección de mail válida");
			return false;
		}
		Date fechaCumpleanios = datosNuevos.getFechaDeCumpleanios();
		if (fechaCumpleanios == null) {
			JOptionPane.showMessageDialog(null, "Por favor seleccione una fecha de cumpleaños");
			return false;
		}
		return true;
	}
}
