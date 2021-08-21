package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import dto.Domicilio;
import dto.PersonaDTO;

public class Controlador implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personasEnTabla;
		private VentanaPersona ventanaPersona; 
		private Agenda agenda;
		
		int filaSeleccionada;
		public Controlador(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona(a));
			this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
			this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));
	
			//editar
			this.ventanaPersona = VentanaPersona.getInstance();
			this.vista.getBtnEditar().addActionListener(e ->mostrarVentanaEditar(e));
			this.ventanaPersona.getBtnAceptar().addActionListener(a -> editarPersona(a));
			this.ventanaPersona.getBtnCancelar().addActionListener(c -> cerrarVentanaEditar(c));
			
			this.ventanaPersona.getBtnAgregarPersona().addActionListener(p->guardarPersona(p));
			this.agenda = agenda;
		}
		
		private void ventanaAgregarPersona(ActionEvent a) {
			this.ventanaPersona.mostrarVentana();
		}

		private void guardarPersona(ActionEvent p) {
//			String nombre = this.ventanaPersona.getTxtNombre().getText();
//			String tel = ventanaPersona.getTxtTelefono().getText();
			PersonaDTO nuevaPersona = obtenerPersonaDeVista();
			if(todosLosCamposSonValidos(nuevaPersona)) {
				this.agenda.agregarPersona(nuevaPersona);
				this.refrescarTabla();
				this.ventanaPersona.cerrar();				
			}
			
		}

		private void mostrarReporte(ActionEvent r) {
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
			reporte.mostrar();	
		}

		public void borrarPersona(ActionEvent s)
		{
			int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			for (int fila : filasSeleccionadas)
			{
				this.agenda.borrarPersona(this.personasEnTabla.get(fila));
			}
			
			this.refrescarTabla();
		}
		
		public void mostrarVentanaEditar(ActionEvent e) {
			filaSeleccionada = this.vista.getTablaPersonas().getSelectedRow();
			if(filaSeleccionada == -1) {
				JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna persona para editar");
				return;
			}	
			this.ventanaPersona.mostrarVentanaConValores(this.personasEnTabla.get(filaSeleccionada));
		}
		
		public void editarPersona(ActionEvent e) {
			int idModificar = this.personasEnTabla.get(filaSeleccionada).getIdPersona();
			
			//aca poner todos los nuevos campos con los nuevos datos
			String nombreNuevo = ventanaPersona.getTxtNombre().getText();
			String telefonoNuevo = ventanaPersona.getTxtTelefono().getText();
			
			//Se hardcodean los datos que no estan en la vista
			Domicilio domicilio = new Domicilio("Videla","1600","","Nose que es el departamento");
			Date d = new Date(2100,8,21);
			
			PersonaDTO datosNuevos = new PersonaDTO(0,nombreNuevo,telefonoNuevo,domicilio,"nose@gmail.com",d);
			if(todosLosCamposSonValidos(datosNuevos)) {
				agenda.editarPersona(idModificar,datosNuevos);
				refrescarTabla();
				ventanaPersona.cerrar();
			}
			
		}
		
		public void cerrarVentanaEditar(ActionEvent e) {
			this.ventanaPersona.cerrar();
		}
		
		public void inicializar()
		{
			this.refrescarTabla();
			this.vista.show();
		}
		
		private void refrescarTabla()
		{
			this.personasEnTabla = agenda.obtenerPersonas();
			this.vista.llenarTabla(this.personasEnTabla);
		}

		@Override
		public void actionPerformed(ActionEvent e) { }
		
		
		
		public PersonaDTO obtenerPersonaDeVista() {
			String nombre = this.ventanaPersona.getTxtNombre().getText();
			String tel = ventanaPersona.getTxtTelefono().getText();
			
			//DATOS HARDCODEADOS PORQUE NO SE LOS PUEDE ELEGIR DE LA VISTA TODAVIA
			//Domicilio falta la localidad
//			String calle = ventanaPersona.getDomicilio().getText();
//			String altura = ventanaPersona.getAltura().getText();
//			String piso = ventanaPersona.getPiso().getText();
//			String departamento = ventanaPersona.getDepartamento().getText();
//			
			Domicilio domicilio = new Domicilio("Videla","1600","","Nose que es el departamento");
//			String email = ventanaPersona.getEmail().getText();
//			Date fechaCumpleanios = (Date) ventanaPersona.getFechaCumpleanios().getDate();
			Date d = new Date(2100,8,21);
			
			return new PersonaDTO(0,nombre,tel,domicilio,"nose@gmail.com",d);
		}
			
		public boolean todosLosCamposSonValidos(PersonaDTO datosNuevos) {
			String nombre = datosNuevos.getNombre();
			boolean expresionNombre = nombre.matches("[\\w&&[^\\d]]{1,45}");//de 1 a 45 caracteres que no sean digitos
			if(!expresionNombre) {
				JOptionPane.showMessageDialog(null,"Por favor complete los caracteres de nombre correctamente");
				return false;
			}
			String telefono = datosNuevos.getTelefono();
			boolean expresionTelefono = telefono.matches("[\\d&&[^a-zA-Z]]{10,20}");
			if(!expresionTelefono) {
				JOptionPane.showMessageDialog(null,"Por favor ingrese un teléfono válido");
				return false;
			}
			
			return true;
		}
}
