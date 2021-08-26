package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Agenda;
import modelo.Localidad;
import modelo.Pais;
import modelo.Provincia;
import modelo.SignoZodiaco;
import modelo.TipoContacto;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaEditarLocalidad;
import presentacion.vista.VentanaPersona;
import presentacion.vista.VentanaTipoContacto;
import presentacion.vista.Vista;
import dto.Domicilio;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.SignoZodiacoDTO;
import dto.TipoContactoDTO;

public class Controlador implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personasEnTabla;
		private List<TipoContactoDTO> tipoContactoEnTabla;
		private List<SignoZodiacoDTO> signoZodiacoEnTabla;
		private VentanaPersona ventanaPersona; 
		private VentanaTipoContacto ventanaTipoContacto;
		private Agenda agenda;
		private TipoContacto tipoContacto;
		private SignoZodiaco signoZodiaco;
		private VentanaEditarLocalidad ventanaLocalidad;
		int filaSeleccionada;
		private Pais pais;
		private List<PaisDTO> paisEnTabla;
		private Provincia provincia;
		private Localidad localidad;
		private List<ProvinciaDTO> provinciaEnTabla;
		private List<LocalidadDTO> localidadEnTabla;
		
		public Controlador(Vista vista, Agenda agenda)
		{		
			this.ventanaTipoContacto =new VentanaTipoContacto();
			this.ventanaTipoContacto.getBtnAgregar().addActionListener(a->agregarTipoContacto(a));
			this.ventanaTipoContacto.getBtnEditar().addActionListener(e->editarTipoContacto(e));
			this.ventanaTipoContacto.getBtnBorrar().addActionListener(b->borrarTipoContacto(b));
			this.ventanaTipoContacto.getBtnSalir().addActionListener(s->salirTipoContacto(s));
			
			this.ventanaLocalidad =new VentanaEditarLocalidad();
			this.ventanaLocalidad.getBtnAgregar().addActionListener(g->agregarLocalidad(g));
			this.ventanaLocalidad.getBtnEditar().addActionListener(h->editarPais(h));
			this.ventanaLocalidad.getBtnBorrar().addActionListener(j->borrarFilaTabla(j));
			this.ventanaLocalidad.getBtnSalir().addActionListener(k->salirLocalidad(k));
			
			this.ventanaLocalidad.getComboBox().addActionListener(x->obtenerSeleccionCombo(x));
			this.ventanaLocalidad.getBtnAgregar().addActionListener(y->guardarLocalidad(y));
			
			this.tipoContacto = new TipoContacto(new DAOSQLFactory());
			this.refrescarTablaTipoContacto();
			
			this.signoZodiaco = new SignoZodiaco(new DAOSQLFactory());
			this.refrescarTablaSignoZodiaco();
			
			this.pais = new Pais(new DAOSQLFactory());
			this.refrescarTablaPais();
			
			this.provincia = new Provincia(new DAOSQLFactory());
			this.refrescarTablaProvincia();
			
			this.localidad = new Localidad(new DAOSQLFactory());
			this.refrescarTablaLocalidad();
			
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(a->ventanaAgregarPersona(a));
			this.vista.getBtnBorrar().addActionListener(s->borrarPersona(s));
			this.vista.getBtnReporte().addActionListener(r->mostrarReporte(r));
			
			this.ventanaPersona = VentanaPersona.getInstance();
			this.vista.getBtnEditar().addActionListener(e ->mostrarVentanaEditar(e));
			this.ventanaPersona.getBtnAceptar().addActionListener(a -> editarPersona(a));
			this.ventanaPersona.getBtnCancelar().addActionListener(c -> cerrarVentanaEditar(c));
			
			this.ventanaPersona.getBtnAgregarPersona().addActionListener(p->guardarPersona(p));
			this.ventanaPersona.getBtnEditarTipoContacto().addActionListener(t->ventanaEditarTipoContacto(t));
			
			this.ventanaPersona.getBtnEditarLocalidad().addActionListener(z->ventanaEditarLocalidad(z));
			escucharCbLocalidad();
			
			this.agenda = agenda;	
		}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
//Signo Zodiaco
		private void refrescarCbSignoZodiaco() {
			this.ventanaPersona.getCbSignoZodiaco().removeAllItems();
			for(SignoZodiacoDTO s : this.signoZodiacoEnTabla) {
				this.ventanaPersona.getCbSignoZodiaco().addItem(s.getNombreSignoZodiaco());
			}
		} 
		
		public void refrescarTablaSignoZodiaco() {
			this.signoZodiacoEnTabla = signoZodiaco.obtenerSignosZodiaco();
		}
		
		public void escribirComboBoxSignoZodiaco() {
			String signoZodiacoSeleccionado = (String) this.ventanaPersona.getCbSignoZodiaco().getSelectedItem();
			if(signoZodiacoSeleccionado == null) {
				this.ventanaPersona.getCbSignoZodiaco().addItem("");
				return;
			}
			for(SignoZodiacoDTO signo : this.signoZodiacoEnTabla) {
				String signoZodiacoReferenciado = obtenerSignoZodiacoReferenciado(signo).getNombreSignoZodiaco();
				if(signoZodiacoSeleccionado.equals(signoZodiacoReferenciado)) {
					this.ventanaPersona.getCbSignoZodiaco().addItem(signo.getNombreSignoZodiaco());
				}
			}
		}
		
		public SignoZodiacoDTO obtenerSignoZodiacoReferenciado(SignoZodiacoDTO signoZodiaco) {
			for(SignoZodiacoDTO signo: this.signoZodiacoEnTabla) {
				if(signo.getIdSignoZodiaco() == signoZodiaco.getIdSignoZodiaco()) {
					return signo;
				}
			}
			return null;
		}

		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
//		VISTA

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

		
		private void ventanaAgregarPersona(ActionEvent a) {
			escribirComboBoxesAgregar();
			this.ventanaPersona.mostrarVentana();
			this.ventanaPersona.limpiarValores();
		}
		
		private void guardarPersona(ActionEvent p) {
			PersonaDTO nuevaPersona = obtenerPersonaDeVista();
			if(todosLosCamposSonValidos(nuevaPersona)) {
				this.agenda.agregarPersona(nuevaPersona);
				this.refrescarTabla();
				this.ventanaPersona.cerrar();				
			}
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
			this.mostrarVentanaConValores();
		}
		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
//		VISTA PERSONA
		
		
		public void mostrarVentanaConValores() {
			PersonaDTO persona = this.personasEnTabla.get(filaSeleccionada);
			this.ventanaPersona.getTxtNombre().setText(persona.getNombre());
			this.ventanaPersona.getTxtTelefono().setText(persona.getTelefono());
			this.ventanaPersona.getEmail().setText(persona.getEmail());
			this.ventanaPersona.getFechaCumpleanios().setDate(persona.getFechaDeCumpleanios());
			this.ventanaPersona.getCalle().setText(persona.getDomicilio().getCalle());
			this.ventanaPersona.getAltura().setText(persona.getDomicilio().getAltura());
			this.ventanaPersona.getPiso().setText(persona.getDomicilio().getPiso());
			this.ventanaPersona.getDepartamento().setText(persona.getDomicilio().getDepartamento());
			this.ventanaPersona.getCbTipoContacto().setSelectedItem(persona.getTipoDeContacto());
			this.ventanaPersona.getCbSignoZodiaco().setSelectedItem(persona.getSignoZodiaco());
			//defectuoso
			this.ventanaPersona.getCbPais().setSelectedItem(persona.getPais());
			this.ventanaPersona.getCbProvincia().setSelectedItem(persona.getProvincia());
			this.ventanaPersona.getCbLocalidad().setSelectedItem(persona.getLocalidad());
			
			//escribirComboBoxEditar();
		
			this.ventanaPersona.getBtnAgregarPersona().setVisible(false);
			this.ventanaPersona.getBtnCancelar().setVisible(true);
			this.ventanaPersona.getBtnAceptar().setVisible(true);

			this.ventanaPersona.setVisible(true);
		}
		
		public void escribirComboBoxEditar() {
			
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
			String signoZodiaco = ventanaPersona.getSignoZodiacoSeleccionado();
			String pais = ventanaPersona.getPaisSeleccionado();
			String provincia = ventanaPersona.getProvinciaSeleccionado();
			String localidad = ventanaPersona.getLocalidadSeleccionado();
			
			return new PersonaDTO(0, nombre, telefono, domicilio, email, fechaDeCumpleanios, tipoContacto, signoZodiaco, pais, provincia, localidad);	
		}
		



		public boolean todosLosCamposSonValidos(PersonaDTO datosNuevos) {	
			String nombre = datosNuevos.getNombre();
			boolean expresionNombre = nombre.matches("[\\w\\s&&[^\\d]]{1,45}");
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
			String calle = datosNuevos.getDomicilio().getCalle();
			boolean expresionCalle = calle.matches("[\\w\\s&&[^\\d]]{1,45}");
			if(!expresionCalle) {
				JOptionPane.showMessageDialog(null,"Por favor ingrese una calle válida");
				return false;
			}
			String altura = datosNuevos.getDomicilio().getAltura();
			boolean expresionAltura = altura.matches("[\\d&&[^a-zA-Z]]+");
			if(!expresionAltura) {
				JOptionPane.showMessageDialog(null,"Por favor ingrese una altura válida");
				return false;
			}
			String piso = datosNuevos.getDomicilio().getPiso();
			boolean expresionPiso = piso.matches("[\\d&&[^a-zA-Z]]*");
			if(!expresionPiso) {
				JOptionPane.showMessageDialog(null,"Por favor ingrese un piso válido");
				return false;
			}
			
			String departamento = datosNuevos.getDomicilio().getDepartamento();
			boolean expresionDepartamento = departamento.matches("[\\d&&[^a-zA-Z]]*");
			if(!expresionDepartamento) {
				JOptionPane.showMessageDialog(null,"Por favor ingrese un departamento válido");
				return false;
			}
			
			String mail = datosNuevos.getEmail();
			boolean expresionMail = mail.matches("^[A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");//NOSE
			if(!expresionMail) {
				JOptionPane.showMessageDialog(null,"Por favor ingrese una dirección de mail válida");
				return false;
			}
			Date fechaCumpleanios= datosNuevos.getFechaDeCumpleanios();
			if(fechaCumpleanios == null) {
				JOptionPane.showMessageDialog(null,"Por favor seleccione una fecha de cumpleaños");
				return false;
			}
		
		if(datosNuevos.getTipoDeContacto()==null) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de contacto");
			return false;
		}
		return true;
	}
		
		private void refrescarCbTipoContacto() {
//			this.ventanaPersona.escribirComboBoxTipoDeContacto(this.tipoContactoEnTabla);
			this.ventanaPersona.getCbTipoContacto().removeAllItems();
			for (TipoContactoDTO tipo : this.tipoContactoEnTabla) {
				this.ventanaPersona.getCbTipoContacto().addItem(tipo.getNombreTipoContacto());
			}
		}
		private void refresecarCbPaises() {
			this.ventanaPersona.getCbPais().removeAllItems();
			for(PaisDTO pais: this.paisEnTabla) {
				this.ventanaPersona.getCbPais().addItem(pais.getNombrePais());
			}
		}
		
		
		public void escribirComboBoxProvincias() {
			this.ventanaPersona.getCbProvincia().removeAllItems();
			String paisSeleccionado = (String) this.ventanaPersona.getCbPais().getSelectedItem();
			if(paisSeleccionado==null) {
				this.ventanaPersona.getCbProvincia().addItem("");
				return;
			}
			for(ProvinciaDTO provincia: this.provinciaEnTabla) {
				String paisReferenciado = obtenerPaisReferenciado(provincia).getNombrePais();
				if(paisSeleccionado.equals(paisReferenciado)) {
					this.ventanaPersona.getCbProvincia().addItem(provincia.getNombreProvincia());
				}
			}
		}
		
		public void escribirComboBoxLocalidades() {
			this.ventanaPersona.getCbLocalidad().removeAllItems();
			String provinciaSeleccionada = (String) this.ventanaPersona.getCbProvincia().getSelectedItem();
			if(provinciaSeleccionada==null) {
				this.ventanaPersona.getCbLocalidad().addItem("");
				return;
			}
			for(LocalidadDTO localidad: this.localidadEnTabla) {
				String provReferenciada = obtenerProvinciaReferenciada(localidad).getNombreProvincia();
				if(provinciaSeleccionada.equals(provReferenciada)) {
					this.ventanaPersona.getCbLocalidad().addItem(localidad.getNombreLocalidad());
				}
				
			}
		}
		
		public ProvinciaDTO obtenerProvinciaReferenciada(LocalidadDTO localidad) {
			for(ProvinciaDTO provincia: this.provinciaEnTabla) {
				if(provincia.getIdProvincia() == localidad.getIdForeignProvincia()) {
					return provincia;
				}
			}
			return null;
		}
		
		public PaisDTO obtenerPaisReferenciado(ProvinciaDTO provincia) {
			for(PaisDTO pais: this.paisEnTabla) {
				if(pais.getIdPais() == provincia.getForeignPais()) {
					return pais;
				}
			}
			return null;
		}
		
		public void escribirComboBoxesAgregar() {
			refrescarCbTipoContacto();
			refrescarCbSignoZodiaco();
			refresecarCbPaises();
			escribirComboBoxProvincias();
			escribirComboBoxLocalidades();
		}
		
		
		public void escucharCbLocalidad() {
			this.ventanaPersona.getCbPais().addActionListener(e -> refrescarCbProvLoca(e));
			this.ventanaPersona.getCbProvincia().addActionListener(e -> actualizarCbLocalidades(e));
		}
		
		
		public void refrescarCbProvLoca(ActionEvent e) {
			escribirComboBoxProvincias();
			escribirComboBoxLocalidades();
		}
		public void actualizarCbLocalidades(ActionEvent e) {
			escribirComboBoxLocalidades();
		}
		
		public void refrescarComboBoxes() {
			refrescarCbTipoContacto();
			refresecarCbPaises();
			escribirComboBoxProvincias();
			escribirComboBoxLocalidades();
		}
		
		public void cerrarVentanaEditar(ActionEvent e) {
			this.ventanaPersona.cerrar();
		}
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
//		VISTA EDITAR TIPO CONTACTO
		

		private void ventanaEditarTipoContacto(ActionEvent t) {
			this.ventanaTipoContacto.mostrarVentana();
		}
		
		
		private void agregarTipoContacto(ActionEvent a) {
			String nombreTipoContacto = this.ventanaTipoContacto.getTxtTipoContacto().getText();
			TipoContactoDTO nuevoTipoContacto = new TipoContactoDTO(0, nombreTipoContacto);
			this.tipoContacto.agregarTipoContacto(nuevoTipoContacto);
			this.refrescarTablaTipoContacto();
			this.ventanaTipoContacto.limpiarTxtTipoContacto();

			refrescarCbTipoContacto();
		}
		
		private void editarTipoContacto(ActionEvent e) {
			int filaSeleccionado = this.ventanaTipoContacto.tablaTipoContactoSeleccionada();
			int idModificar = this.tipoContactoEnTabla.get(filaSeleccionado).getIdTipoContacto();

			String nombreNuevo = ventanaTipoContacto.getTxtTipoContacto().getText();
			
			TipoContactoDTO datosNuevos = new TipoContactoDTO(0,nombreNuevo);
			tipoContacto.editarTipoContacto(idModificar,datosNuevos);
			this.refrescarTablaTipoContacto();	
			refrescarCbTipoContacto();
			
		}
		
		private void borrarTipoContacto(ActionEvent b) {
			int[] filasSeleccionadas = this.ventanaTipoContacto.getTablaTipoContacto().getSelectedRows();
			for (int fila : filasSeleccionadas)
			{
				this.tipoContacto.borrarTipoContacto(this.tipoContactoEnTabla.get(fila));
			}
			this.refrescarTablaTipoContacto();
			this.ventanaTipoContacto.limpiarTxtTipoContacto();
			refrescarCbTipoContacto();
		}
		
		private void salirTipoContacto(ActionEvent s) {
			this.ventanaTipoContacto.cerrar();
			this.ventanaTipoContacto.limpiarTxtTipoContacto();
			refrescarCbTipoContacto();
		}
		
		public void refrescarTablaTipoContacto() {
			this.tipoContactoEnTabla = tipoContacto.obtenerTipoContacto();
			this.ventanaTipoContacto.llenarTabla(tipoContactoEnTabla);
		}
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
//		VISTA EDITAR LOCALIDAD
		
		
		private void agregarLocalidad(ActionEvent a) {
			String nombreLocalidad = this.ventanaLocalidad.getTxtFieldNombreLocalidad().getText();
			String comboBox = (String) this.ventanaLocalidad.getComboBox().getSelectedItem();
			
			if(comboBox == "Pais") {
				System.out.println("Agrego Pais");
				PaisDTO nuevaLocalidad = new PaisDTO(0, nombreLocalidad);
				this.pais.agregarPais(nuevaLocalidad);
				this.refrescarTablaPais();
				this.ventanaLocalidad.limpiarTodosTxt();
			}
			if(comboBox == "Provincia") {
				System.out.println("Agrego Provincia");
				int idPais = Integer.parseInt((String)this.ventanaLocalidad.getTxtFieldId().getText());
				ProvinciaDTO nuevaLocalidad = new ProvinciaDTO(0, nombreLocalidad, idPais);
				this.provincia.agregarProvincia(nuevaLocalidad);
				this.refrescarTablaProvincia();
				this.ventanaLocalidad.limpiarTodosTxt();
			}
			if(comboBox == "Localidad") {
				System.out.println("Agrego localidad");
				int idPais = Integer.parseInt((String)this.ventanaLocalidad.getTxtFieldId().getText());
				LocalidadDTO nuevaLocalidad = new LocalidadDTO(0, nombreLocalidad,idPais);
				this.localidad.agregarLocalidad(nuevaLocalidad);
				this.refrescarTablaLocalidad();
				this.ventanaLocalidad.limpiarTodosTxt();
			}
			refrescarComboBoxes();
		}
		
		private void editarPais(ActionEvent e) {
			int filaSeleccionado = this.ventanaLocalidad.tablaPaisSeleccionada();
			int idModificar = this.paisEnTabla.get(filaSeleccionado).getIdPais();
			String nombreNuevo = ventanaLocalidad.getTxtFieldNombreLocalidad().getText();
			
			PaisDTO datosNuevos = new PaisDTO(0,nombreNuevo);
			pais.editarPais(idModificar,datosNuevos);
			this.refrescarTablaPais();	
			
		}
		
		private void borrarPais(ActionEvent b) {
			int[] filasSeleccionadas = this.ventanaLocalidad.getTablaPais().getSelectedRows();
			for (int fila : filasSeleccionadas)
			{
				this.pais.borrarPais(this.paisEnTabla.get(fila));
			}
			this.refrescarTablaPais();
			this.ventanaLocalidad.limpiarTodosTxt();
		}
		
		private void salirLocalidad(ActionEvent s) {
			this.ventanaLocalidad.limpiarTodosTxt();
			this.ventanaLocalidad.cerrar();
			refrescarComboBoxes();

		}
		
		public void refrescarTablaPais() {
			this.paisEnTabla = pais.obtenerPais();
			this.ventanaLocalidad.llenarTablaPais(paisEnTabla);
		}
		public void refrescarTablaProvincia() {
			this.provinciaEnTabla = provincia.obtenerProvincia();
			this.ventanaLocalidad.llenarTablaProvincia(provinciaEnTabla);
		}
		public void refrescarTablaLocalidad() {
			this.localidadEnTabla = localidad.obtenerLocalidad();
			this.ventanaLocalidad.llenarTablaLocalidad(localidadEnTabla);
		}
		
		
		
		public void borrarFilaTabla(ActionEvent e) {
			int filaPaisSeleccionada = this.ventanaLocalidad.getTablaPais().getSelectedRow();
			if(filaPaisSeleccionada != -1) {
				this.pais.borrarPais(this.paisEnTabla.get(filaPaisSeleccionada));
				this.refrescarTablaPais();
				this.ventanaLocalidad.limpiarTodosTxt();
				return;
			}
			
			int filaProvinciaSeleccionada = this.ventanaLocalidad.getTablaTipoProvincia().getSelectedRow();
			if(filaProvinciaSeleccionada != -1) {
				this.provincia.borrarProvincia(this.provinciaEnTabla.get(filaProvinciaSeleccionada));
				System.out.println("se borro provincia de la tabla");
				return;
			}
			
			int filaLocalidadSeleccionada = this.ventanaLocalidad.getTablaTipoLocalidad().getSelectedRow();
			if(filaLocalidadSeleccionada != -1) {
				this.localidad.borrarLocalidad(this.localidadEnTabla.get(filaLocalidadSeleccionada));
				return;
			}
		}
		
		
		
		
		
		
		private Object ventanaEditarLocalidad(ActionEvent z) {
			this.ventanaLocalidad.mostrarVentana();
			return null;
		}

		private Object obtenerSeleccionCombo(ActionEvent x) {
			this.ventanaLocalidad.obtenerSeleccion();
			return null;
		}
		
		private Object guardarLocalidad(ActionEvent y) {
			this.ventanaLocalidad.guardarSeleccion();
			return null;
		}
		
		
		
		
		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
	

		private void mostrarReporte(ActionEvent r) {
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
			reporte.mostrar();	
		}


		
	
		@Override
		public void actionPerformed(ActionEvent e) { }
		
		
	
		
	
	
}
