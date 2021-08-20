package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import com.itextpdf.text.log.SysoCounter;

import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
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
			String nombre = this.ventanaPersona.getTxtNombre().getText();
			String tel = ventanaPersona.getTxtTelefono().getText();
			PersonaDTO nuevaPersona = new PersonaDTO(0, nombre, tel);
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
			PersonaDTO datosNuevos = new PersonaDTO(0,nombreNuevo,telefonoNuevo);
			agenda.editarPersona(idModificar,datosNuevos);
			ventanaPersona.cerrar();
			refrescarTabla();
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
		
}
