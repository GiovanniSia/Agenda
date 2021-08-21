package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

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
		
		public Controlador(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona(a));
			this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
			this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));
			this.ventanaPersona = VentanaPersona.getInstance();
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
			this.agenda.agregarPersona(nuevaPersona);
			this.refrescarTabla();
			this.ventanaPersona.cerrar();
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
}
