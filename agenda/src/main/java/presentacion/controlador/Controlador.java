package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.Agenda;
import modelo.Localidad;
import modelo.Pais;
import modelo.Provincia;
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
import dto.TipoContactoDTO;

public class Controlador implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personasEnTabla;
		private List<TipoContactoDTO> tipoContactoEnTabla;
		private VentanaPersona ventanaPersona; 
		private VentanaTipoContacto ventanaTipoContacto;
		private Agenda agenda;
		private TipoContacto tipoContacto;
		int filaSeleccionada;
		private Pais pais;
		private List<PaisDTO> paisEnTabla;
		private Provincia provincia;
		private Localidad localidad;
		private List<ProvinciaDTO> provinciaEnTabla;
		private List<LocalidadDTO> localidadEnTabla;
		
		private VentanaEditarLocalidad ventanaEditarLocalidad;
		
		public Controlador(Vista vista, Agenda agenda)
		{		
			this.ventanaTipoContacto =new VentanaTipoContacto();
			this.ventanaTipoContacto.getBtnAgregar().addActionListener(a->agregarTipoContacto(a));
			this.ventanaTipoContacto.getBtnEditar().addActionListener(e->editarTipoContacto(e));
			this.ventanaTipoContacto.getBtnBorrar().addActionListener(b->borrarTipoContacto(b));
			this.ventanaTipoContacto.getBtnSalir().addActionListener(s->salirTipoContacto(s));
			
			this.tipoContacto = new TipoContacto(new DAOSQLFactory());
			this.refrescarTablaTipoContacto();
//			
			this.pais = new Pais(new DAOSQLFactory());
			this.paisEnTabla= this.pais.obtenerPais();
//			this.refrescarTablaPais();
//			
			this.provincia = new Provincia(new DAOSQLFactory());
			this.provinciaEnTabla= this.provincia.obtenerProvincia();
//			this.refrescarTablaProvincia();
//			
			this.localidad = new Localidad(new DAOSQLFactory());
			this.localidadEnTabla = this.localidad.obtenerLocalidades();
//			this.refrescarTablaLocalidad();
			
			this.ventanaEditarLocalidad = new VentanaEditarLocalidad();
			
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
			
			this.ventanaPersona.getBtnEditarLocalidad().addActionListener(c -> mostrarVentanaEditarLocalidad(c));
			escucharCbLocalidad();
			
			
			//Localidad
			this.ventanaEditarLocalidad.getBtnAgregarLocalidad().addActionListener(a -> agregarLocalidad(a));
			this.ventanaEditarLocalidad.getComboBoxPaises().addActionListener(a -> actualizarCbProvincia_EditarLocalidad(a));
			this.ventanaEditarLocalidad.getComboProvincias().addActionListener(a -> actualizarTablaLocalidad_EditarLocalidad(a));
			this.ventanaEditarLocalidad.getBtnEditarLocalidad().addActionListener(a -> editarLocalidad(a));
			this.ventanaEditarLocalidad.getBtnBorrarLocalidad().addActionListener(a -> borrarLocalidad(a));
			this.ventanaEditarLocalidad.getTable().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int filaSeleccionada = ventanaEditarLocalidad.getTable().rowAtPoint(e.getPoint());
					ventanaEditarLocalidad.getTxtNuevaLocalidad().setText(ventanaEditarLocalidad.getTable().getValueAt(filaSeleccionada, 2).toString());
				}
			});
			
			this.agenda = agenda;	
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
			
			this.ventanaPersona.getCbPais().setSelectedItem(persona.getPais());
			this.ventanaPersona.getCbProvincia().setSelectedItem(persona.getProvincia());
			this.ventanaPersona.getCbLocalidad().setSelectedItem(persona.getLocalidad());
			
			escribirComboBoxEditar();
		
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
			String pais = ventanaPersona.getPaisSeleccionado();
			String provincia = ventanaPersona.getProvinciaSeleccionado();
			String localidad = ventanaPersona.getLocalidadSeleccionado();
			
			return new PersonaDTO(0, nombre, telefono, domicilio, email, fechaDeCumpleanios, tipoContacto,pais,provincia,localidad);	
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
		
		
		public void mostrarVentanaEditarLocalidad(ActionEvent e) {
			//DADO EL PAIS-PROV-LOCALIDAD ELEGIDO EN EL COMBO BOX DE VENTANAPERSONA
			
			String paisElegido = (String) this.ventanaPersona.getCbPais().getSelectedItem();
			String provElegida = (String) this.ventanaPersona.getCbProvincia().getSelectedItem();
			
			llenarTablaEditarLocalidad(paisElegido,provElegida);
			llenarCbEditarLocalidad(paisElegido,provElegida);
			this.ventanaEditarLocalidad.show();
		}
			
		
		public void agregarLocalidad(ActionEvent a) {
			String nombreLocalidadNueva = this.ventanaEditarLocalidad.getTxtNuevaLocalidad().getText();
			if(this.ventanaEditarLocalidad.getComboProvincias().getSelectedItem()==null) {
				JOptionPane.showMessageDialog(null, "El pais no tiene provincias!");
				return;
			}
			
			if(nombreLocalidadNueva.equals("")) {
				JOptionPane.showMessageDialog(null, "Por favor escriba una localidad nueva para agregar");
				return;
			}

			
			String nombrePaisSeleccionado=(String) this.ventanaEditarLocalidad.getComboBoxPaises().getSelectedItem();
			String nombreProvinciaSeleccionada = (String) this.ventanaEditarLocalidad.getComboProvincias().getSelectedItem();
			
			int idPaisSeleccionado = getPaisDeTabla(nombrePaisSeleccionado).getIdPais();
			int idProvinciaSeleccionada = getProvinciaDeTabla(nombreProvinciaSeleccionada, idPaisSeleccionado).getIdProvincia();
			
			LocalidadDTO nuevaLocalidad = new LocalidadDTO(0, nombreLocalidadNueva, idProvinciaSeleccionada);

			this.localidad.agregarLocalidad(nuevaLocalidad);
			this.localidadEnTabla = this.localidad.obtenerLocalidades();
			this.ventanaEditarLocalidad.getTxtNuevaLocalidad().setText("");
			llenarTablaEditarLocalidad(nombrePaisSeleccionado,nombreProvinciaSeleccionada);
		}
		
		public void llenarTablaEditarLocalidad(String paisElegido, String provElegida) {	
			this.ventanaEditarLocalidad.getModelTabla().setRowCount(0);
			this.ventanaEditarLocalidad.getModelTabla().setColumnCount(0);
			this.ventanaEditarLocalidad.getModelTabla().setColumnIdentifiers(this.ventanaEditarLocalidad.getNombreColumnas());
			PaisDTO paisReferenciado = getPaisDeTabla(paisElegido);
			ProvinciaDTO provinciaReferenciada = getProvinciaDeTabla(provElegida,paisReferenciado.getIdPais());
			
			//Si el pais no tiene provincias
			if(provinciaReferenciada==null) {
				Object[] fila = {paisElegido,"",""};
				this.ventanaEditarLocalidad.getModelTabla().addRow(fila);
				return;
			}
			int cantLoc=0;
			for(LocalidadDTO localidad: this.localidadEnTabla) {				
				if(localidad.getIdForeignProvincia() == provinciaReferenciada.getIdProvincia() && provinciaReferenciada.getIdProvincia()==paisReferenciado.getIdPais()) {
					
					Object[] fila = {paisElegido,provElegida,localidad.getNombreLocalidad()};
					this.ventanaEditarLocalidad.getModelTabla().addRow(fila);
				}
				cantLoc++;
			}
//			Si una prov no tiene localidades le agregamos una vacia
			if(cantLoc==0) {
				Object[] fila = {paisElegido,provElegida,""};
				this.ventanaEditarLocalidad.getModelTabla().addRow(fila);
			}
		}
		
		public void llenarCbEditarLocalidad(String paisElegido, String provinciaElegida) {
			this.ventanaEditarLocalidad.getComboBoxPaises().removeAllItems();
			for(PaisDTO pais: this.paisEnTabla) {
				this.ventanaEditarLocalidad.getComboBoxPaises().addItem(pais.getNombrePais());
			}
			//seteamos el pais que se eligio
			this.ventanaEditarLocalidad.getComboBoxPaises().setSelectedItem(paisElegido);
			
			this.ventanaEditarLocalidad.getComboProvincias().removeAllItems();
			int idPaisElegido = getPaisDeTabla(paisElegido).getIdPais();
			for(ProvinciaDTO provincia: this.provinciaEnTabla) {
				if(provinciaElegida.equals(provincia.getNombreProvincia()) && idPaisElegido == provincia.getForeignPais()) {
					this.ventanaEditarLocalidad.getComboProvincias().addItem(provincia.getNombreProvincia());
				}
			}
		}
		
		
		public PaisDTO getPaisDeTabla(String nombrePais) {
			for(PaisDTO pais: this.paisEnTabla) {
				if(pais.getNombrePais().equals(nombrePais)) {
					return pais;
				}
			}
			return null;
		}
		public ProvinciaDTO getProvinciaDeTabla(String nombreProvincia,int idPais) {
			for(ProvinciaDTO provincia: this.provinciaEnTabla) {
				if(provincia.getForeignPais() == idPais && nombreProvincia.equals(provincia.getNombreProvincia())) {
					return provincia;
				}
			}
			return null;
		}
		
		
		
		
		public void actualizarCbProvincia_EditarLocalidad(ActionEvent e) {
//		OBTENEMOS EL PAIS SELECCIONADO
		String nombrePaisSeleccionado = (String) this.ventanaEditarLocalidad.getComboBoxPaises().getSelectedItem();
		
//		por alguna razón se ejecuta la escucha de cb de ventanaLocalidad cuando se inicia la ventana 
		if(nombrePaisSeleccionado==null) return;
		
		PaisDTO paisSeleccionado = getPaisDeTabla(nombrePaisSeleccionado);
//		OBTENEMOS LAS PROV DE ESE PAIS Y LAS ESCRIBIMOS
		this.ventanaEditarLocalidad.getComboProvincias().removeAllItems();
		for(ProvinciaDTO provincia: this.provinciaEnTabla) {
			if(paisSeleccionado.getIdPais() == provincia.getForeignPais()) {
				this.ventanaEditarLocalidad.getComboProvincias().addItem(provincia.getNombreProvincia());;
			}
		}
		String provElegida =(String) this.ventanaEditarLocalidad.getComboProvincias().getSelectedItem();
		llenarTablaEditarLocalidad(nombrePaisSeleccionado,provElegida);
		}
		
		
		public void actualizarTablaLocalidad_EditarLocalidad(ActionEvent a){
			String paisElegido = (String) this.ventanaEditarLocalidad.getComboBoxPaises().getSelectedItem();
			String provElegida = (String) this.ventanaEditarLocalidad.getComboProvincias().getSelectedItem();
			if(paisElegido==null) return;
			if(provElegida==null) return;
			llenarTablaEditarLocalidad(paisElegido,provElegida);
		}
		
		public void editarLocalidad(ActionEvent a){			
			int filaSeleccionada = this.ventanaEditarLocalidad.getTable().getSelectedRow();
			
			if(filaSeleccionada == -1) {
				JOptionPane.showMessageDialog(null, "Por favor seleccione una localidad de la fila para editar");
				return;
			}			
			DefaultTableModel modelo = (DefaultTableModel) this.ventanaEditarLocalidad.getTable().getModel();
			PaisDTO paisSeleccionado = obtenerPaisDeTablaLocalidad(filaSeleccionada);
			ProvinciaDTO provinciaSeleccionada = obtenerProvinciaDeTablaLocalidad(filaSeleccionada,paisSeleccionado.getIdPais());
			String nombreLocalidadBorrar = (String) modelo.getValueAt(filaSeleccionada, 2);
			String provinciaEnTabla= (String) modelo.getValueAt(filaSeleccionada, 1);
			
			if(provinciaEnTabla.equals("")) {
				JOptionPane.showMessageDialog(null, "El pais no tiene provincias!");
				return;
			}			
			if(nombreLocalidadBorrar.equals("")) {
				JOptionPane.showMessageDialog(null, "La provincia no tiene localidades!");
				return;
			}
			
			String nuevoNombre = this.ventanaEditarLocalidad.getTxtNuevaLocalidad().getText();
			for(LocalidadDTO loc: this.localidadEnTabla) {
				if(loc.getNombreLocalidad().equals(nombreLocalidadBorrar) && loc.getIdForeignProvincia()==provinciaSeleccionada.getIdProvincia() && provinciaSeleccionada.getForeignPais()==paisSeleccionado.getIdPais()) {
					this.localidad.editarLocalidad(loc,nuevoNombre);
				}
			}
			
			this.localidadEnTabla=this.localidad.obtenerLocalidades();
			String paisElegido = (String) this.ventanaEditarLocalidad.getComboBoxPaises().getSelectedItem();
			String provElegida = (String) this.ventanaEditarLocalidad.getComboProvincias().getSelectedItem();
			
			this.localidadEnTabla = this.localidad.obtenerLocalidades();
			llenarTablaEditarLocalidad(paisElegido,provElegida);
			
		}
		
		public PaisDTO obtenerPaisDeTablaLocalidad(int fila) {
			DefaultTableModel modelo = (DefaultTableModel) this.ventanaEditarLocalidad.getTable().getModel();
			String nombrePais = (String) modelo.getValueAt(filaSeleccionada, 0);
			return getPaisDeTabla(nombrePais);
		}
		public ProvinciaDTO obtenerProvinciaDeTablaLocalidad(int fila,int idPais) {
			DefaultTableModel modelo = (DefaultTableModel) this.ventanaEditarLocalidad.getTable().getModel();
			String nombreProvincia = (String) modelo.getValueAt(filaSeleccionada, 1);
			return getProvinciaDeTabla(nombreProvincia,idPais); 
			
		}
//		public LocalidadDTO obtenerLocalidadDeTablaLocalidad(int fila, int idProvincia) {
//			DefaultTableModel modelo = (DefaultTableModel) this.ventanaEditarLocalidad.getTable().getModel();
//			String nombreProvincia = (String) modelo.getValueAt(filaSeleccionada, 2);
//			
//			return null;
//		}
		
		public void borrarLocalidad(ActionEvent a){
			int filaSeleccionada = this.ventanaEditarLocalidad.getTable().getSelectedRow();

			if(filaSeleccionada == -1) {
				JOptionPane.showMessageDialog(null, "Seleccione una localidad de la tabla para borrar");
				return;
			}
			
			//Obtenemos toda la tabla con sus datos
			PaisDTO paisEnTabla = obtenerPaisDeTablaLocalidad(filaSeleccionada);
			ProvinciaDTO provEnTabla = obtenerProvinciaDeTablaLocalidad(filaSeleccionada, paisEnTabla.getIdPais());
			
			DefaultTableModel modelo = (DefaultTableModel) this.ventanaEditarLocalidad.getTable().getModel();
//			LocalidadDTO localidadBorrar = obtenerLocalidadDeTablaLocalidad(filaSeleccionada, provEnTabla.getIdProvincia());
			String nombreLocalidadBorrar = (String) modelo.getValueAt(filaSeleccionada, 2);
			String provinciaEnTabla = (String) modelo.getValueAt(filaSeleccionada, 1);

			if(provinciaEnTabla.equals("")) {
				JOptionPane.showMessageDialog(null, "El país no tiene provincias ni localidad para borrar");
				return;
			}
			if(nombreLocalidadBorrar.equals("")) {
				JOptionPane.showMessageDialog(null, "La provincia seleccionada no tiene localidades para borrar");
				return;
			}
			//Obtenemos los obj :(
			
			
			LocalidadDTO loca=null;
			
			for(LocalidadDTO localidad: this.localidadEnTabla) {
				if(localidad.getIdForeignProvincia() == provEnTabla.getIdProvincia() && provEnTabla.getForeignPais()==paisEnTabla.getIdPais() && localidad.getNombreLocalidad().equals(nombreLocalidadBorrar)) {
					loca = localidad;
				}	
			}
			
			for(int i=0; i<this.localidadEnTabla.size(); i++) {
				if(this.localidadEnTabla.get(i).equals(loca)) {
					this.localidad.borrarLocalidad(this.localidadEnTabla.get(i));
				}
			}
			this.localidadEnTabla=this.localidad.obtenerLocalidades();
			String paisElegido = (String) this.ventanaEditarLocalidad.getComboBoxPaises().getSelectedItem();
			String provElegida = (String) this.ventanaEditarLocalidad.getComboProvincias().getSelectedItem();
			
			this.localidadEnTabla = this.localidad.obtenerLocalidades();
			
//			Si solo queda una fila en la tabla solo borramos la localidad
			if(this.ventanaEditarLocalidad.getTable().getRowCount()==1) {
				llenarTablaConSinLocalidad(paisElegido,provElegida);
			}else {
				llenarTablaEditarLocalidad(paisElegido,provElegida);
			}
			
		}
			
		public void llenarTablaConSinLocalidad(String paisElegido,String provElegida) {
			this.ventanaEditarLocalidad.getModelTabla().setRowCount(0);
			this.ventanaEditarLocalidad.getModelTabla().setColumnCount(0);
			this.ventanaEditarLocalidad.getModelTabla().setColumnIdentifiers(this.ventanaEditarLocalidad.getNombreColumnas());
			PaisDTO paisReferenciado = getPaisDeTabla(paisElegido);
			ProvinciaDTO provinciaReferenciada = getProvinciaDeTabla(provElegida,paisReferenciado.getIdPais());
			
						
			Object[] fila = {paisElegido,provElegida,""};
			this.ventanaEditarLocalidad.getModelTabla().addRow(fila);
				
			
		}
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
	

		private void mostrarReporte(ActionEvent r) {
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
			reporte.mostrar();	
		}


		
	
		@Override
		public void actionPerformed(ActionEvent e) { }
		
		
	
		
	
	
}
