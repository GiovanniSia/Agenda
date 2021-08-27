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
import presentacion.vista.VentanaEditarPais;
import presentacion.vista.VentanaEditarProvincia;
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
		private VentanaEditarProvincia ventanaProvincia;
		
		private VentanaEditarPais ventanaEditarPaises;
		private VentanaEditarLocalidad ventanaEditarLocalidad;
		
		public Controlador(Vista vista, Agenda agenda)
		{		
			this.ventanaTipoContacto =new VentanaTipoContacto();
			this.ventanaTipoContacto.getBtnAgregar().addActionListener(a->agregarTipoContacto(a));
			this.ventanaTipoContacto.getBtnEditar().addActionListener(e->editarTipoContacto(e));
			this.ventanaTipoContacto.getBtnBorrar().addActionListener(b->borrarTipoContacto(b));
			this.ventanaTipoContacto.getBtnSalir().addActionListener(s->salirTipoContacto(s));
			
//			this.ventanaLocalidad =new VentanaEditarLocalidad();
//			this.ventanaLocalidad.getBtnAgregar().addActionListener(g->agregarLocalidad(g));
//			this.ventanaLocalidad.getBtnEditar().addActionListener(h->editarPais(h));
//			this.ventanaLocalidad.getBtnBorrar().addActionListener(j->borrarFilaTabla(j));
//			this.ventanaLocalidad.getBtnSalir().addActionListener(k->salirLocalidad(k));
			
			this.ventanaProvincia =new VentanaEditarProvincia();
			this.ventanaProvincia.getBtnAgregar().addActionListener(q->agregarProvincia(q));
			this.ventanaProvincia.getBtnEditar().addActionListener(w->editarProvincia(w));
			this.ventanaProvincia.getBtnBorrar().addActionListener(r->borrarProvincia(r));
			this.ventanaProvincia.getBtnSalir().addActionListener(t->salirProvincia(t));
			this.ventanaProvincia.getComboBox().addActionListener(t->actualizarTablaProvincia(t));
			
//			this.ventanaLocalidad.getComboBox().addActionListener(x->obtenerSeleccionCombo(x));
//			this.ventanaLocalidad.getBtnAgregar().addActionListener(y->guardarLocalidad(y));
			
			this.tipoContacto = new TipoContacto(new DAOSQLFactory());
			this.refrescarTablaTipoContacto();
//			
			this.pais = new Pais(new DAOSQLFactory());
			this.paisEnTabla= this.pais.obtenerPaises();
//			this.refrescarTablaPais();
//			
			this.provincia = new Provincia(new DAOSQLFactory());
			this.provinciaEnTabla= this.provincia.obtenerProvincia();
//			this.refrescarTablaProvincia();
//			
			this.localidad = new Localidad(new DAOSQLFactory());
			this.localidadEnTabla = this.localidad.obtenerLocalidades();
//			this.refrescarTablaLocalidad();
			
			
			this.ventanaEditarPaises = new VentanaEditarPais();
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
			
			
			
			this.ventanaPersona.getBtnEditarPais().addActionListener(a -> mostrarVentanaEditarPais(a));
			this.ventanaPersona.getBtnEditarLocalidad().addActionListener(c -> mostrarVentanaEditarLocalidad(c));
			escucharCbLocalidad();
			
			
			//Paises
			this.ventanaEditarPaises.getBtnEliminarPais().addActionListener(a -> borrarPaisEditarPais(a));
			this.ventanaEditarPaises.getBtnAgregarPais().addActionListener(a-> agregarPaisEditarPais(a));
			this.ventanaEditarPaises.getBtnEditarPais().addActionListener(a -> editarPais(a));
			this.ventanaEditarPaises.getBtnSalirPais().addActionListener(a -> salirEditarPais(a));
			this.ventanaEditarPaises.getTable().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int filaSeleccionada = ventanaEditarPaises.getTable().rowAtPoint(e.getPoint());
					ventanaEditarPaises.getTextPaisNuevo().setText(ventanaEditarPaises.getTable().getValueAt(filaSeleccionada, 0).toString());
				}
			});
			
			//Localidad
			this.ventanaEditarLocalidad.getBtnAgregarLocalidad().addActionListener(a -> agregarLocalidad(a));
			this.ventanaEditarLocalidad.getComboBoxPaises().addActionListener(a -> actualizarCbProvincia_EditarLocalidad(a));
			this.ventanaEditarLocalidad.getComboProvincias().addActionListener(a -> actualizarTablaLocalidad_EditarLocalidad(a));
			this.ventanaEditarLocalidad.getBtnEditarLocalidad().addActionListener(a -> editarLocalidad(a));
			this.ventanaEditarLocalidad.getBtnBorrarLocalidad().addActionListener(a -> borrarLocalidad(a));
			this.ventanaEditarLocalidad.getBtnSalirLocalidad().addActionListener(a -> salirLocalidad(a));
			this.ventanaEditarLocalidad.getTable().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int filaSeleccionada = ventanaEditarLocalidad.getTable().rowAtPoint(e.getPoint());
					ventanaEditarLocalidad.getTxtNuevaLocalidad().setText(ventanaEditarLocalidad.getTable().getValueAt(filaSeleccionada, 2).toString());
				}
			});
			
//			this.ventanaPersona.getBtnEditarLocalidad().addActionListener(z->ventanaEditarLocalidad(z));
			
			this.ventanaPersona.getBtnEditarProvincia().addActionListener(c->ventanaEditarProvincia(c));
			escucharCbLocalidad();
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
			mostrarVentanaConValores();
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
			
//			escribirComboBoxEditar();
			refrescarComboBoxes();
			
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
			
			java.sql.Date fechaDeCumpleanios;
			
			if(ventanaPersona.getFechaCumpleanios().getDate() == null) {
				fechaDeCumpleanios=null;
			}else {
				fechaDeCumpleanios=new java.sql.Date(ventanaPersona.getFechaCumpleanios().getDate().getTime());;
				System.out.println(fechaDeCumpleanios);
			}
			 
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
//			Date fechaCumpleanios= datosNuevos.getFechaDeCumpleanios();
//			if(fechaCumpleanios == null) {
//				JOptionPane.showMessageDialog(null,"Por favor seleccione una fecha de cumpleaños");
//				return false;
//			}
		
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
			for(int i=0; i<this.paisEnTabla.size(); i++) {
				this.ventanaPersona.getCbPais().addItem(this.paisEnTabla.get(i).getNombrePais());
			}
			
//			for(PaisDTO pais: this.paisEnTabla) {
//				this.ventanaPersona.getCbPais().addItem(pais.getNombrePais());
//			}
		}


		
		public void escribirComboBoxProvincias() {
			this.ventanaPersona.getCbProvincia().removeAllItems();
			String nombrePaisSeleccionado = (String) this.ventanaPersona.getCbPais().getSelectedItem();
			
			PaisDTO paisSeleccionado = getPaisDeTabla(nombrePaisSeleccionado);
			
			if(paisSeleccionado==null) {
				this.ventanaPersona.getCbProvincia().addItem("");
				return;
			}
			for(ProvinciaDTO provincia: this.provinciaEnTabla) {
				if(provincia.getForeignPais()==paisSeleccionado.getIdPais()) {
					this.ventanaPersona.getCbProvincia().addItem(provincia.getNombreProvincia());
				}
			}
		}
		
		public void escribirComboBoxLocalidades() {
			this.ventanaPersona.getCbLocalidad().removeAllItems();
			String nombrePaisSeleccionado = (String) this.ventanaPersona.getCbPais().getSelectedItem();
			String nombreProvinciaSeleccionada = (String) this.ventanaPersona.getCbProvincia().getSelectedItem();			
			if(nombrePaisSeleccionado==null) {
				this.ventanaPersona.getCbLocalidad().addItem("");
				return;
			}
			if(nombreProvinciaSeleccionada==null) {
				this.ventanaPersona.getCbLocalidad().addItem("");
				return;
			}
			
			PaisDTO paisSeleccionado = getPaisDeTabla(nombrePaisSeleccionado);
			ProvinciaDTO provinciaSeleccionda = getProvinciaDeTabla(nombreProvinciaSeleccionada, paisSeleccionado.getIdPais());

			
			for(LocalidadDTO localidad: this.localidadEnTabla) {
				if(provinciaSeleccionda.getIdProvincia() == localidad.getIdForeignProvincia()) {
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
			
			if(yaExisteTipoContacto(nombreTipoContacto)) {
				JOptionPane.showMessageDialog(null, "El tipo de contacto ya existe");
				return;
			}
			
			TipoContactoDTO nuevoTipoContacto = new TipoContactoDTO(0, nombreTipoContacto);
			this.tipoContacto.agregarTipoContacto(nuevoTipoContacto);
			this.refrescarTablaTipoContacto();
			this.ventanaTipoContacto.limpiarTxtTipoContacto();

			refrescarCbTipoContacto();
		}
		
		public boolean yaExisteTipoContacto(String nombreNuevo) {
			for(TipoContactoDTO tipo: this.tipoContactoEnTabla) {
				if(nombreNuevo.equals(tipo.getNombreTipoContacto())) {
					return true;
				}
			}
			return false;
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
		
		
		
		
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//VISTA EDITAR PAIS


		public void mostrarVentanaEditarPais(ActionEvent e) {
			llenarTablaEditarPais();
			this.ventanaEditarPaises.show();
		}


		
		public void llenarTablaEditarPais() {
			this.ventanaEditarPaises.getModelTabla().setRowCount(0);
			this.ventanaEditarPaises.getModelTabla().setColumnCount(0);
			this.ventanaEditarPaises.getModelTabla().setColumnIdentifiers(this.ventanaEditarPaises.getNombreColumnas());
			
			for(PaisDTO p: this.paisEnTabla) {
				Object[] fila = {p.getNombrePais()};
				this.ventanaEditarPaises.getModelTabla().addRow(fila);
			}
		}
		
		public void borrarPaisEditarPais(ActionEvent a) {
			int filaSeleccionada = this.ventanaEditarPaises.getTable().getSelectedRow();
			if(filaSeleccionada == -1) {
				JOptionPane.showMessageDialog(null, "Seleccione un pais para borrar");
				return;
			}
			
			DefaultTableModel modelo = (DefaultTableModel) this.ventanaEditarPaises.getTable().getModel();
			String nombrePaisBorrar = (String) modelo.getValueAt(filaSeleccionada, 0);
			
			PaisDTO paisElegido = getPaisDeTabla(nombrePaisBorrar);
			
			for(int i=0; i<this.paisEnTabla.size(); i++) {
				if(this.paisEnTabla.get(i).equals(paisElegido)) {
					this.pais.borrarPais(this.paisEnTabla.get(i));
				}
			}
			
			this.paisEnTabla = this.pais.obtenerPaises();
			llenarTablaEditarPais();
			escribirComboBoxesAgregar();
		}
		
		public void agregarPaisEditarPais(ActionEvent a) {
			String nombrePaisNuevo = this.ventanaEditarPaises.getTextPaisNuevo().getText();
			if(nombrePaisNuevo.equals("")) {
				JOptionPane.showMessageDialog(null, "Escriba el nombre del nuevo país");
				return;
			}
			if(yaExisteElPais(nombrePaisNuevo)) {
				JOptionPane.showMessageDialog(null, "Este país ya existe!");
				return;
			}
			
			PaisDTO nuevoPais = new PaisDTO(0, nombrePaisNuevo);
			this.pais.agregarPais(nuevoPais);
			this.paisEnTabla = this.pais.obtenerPaises();
			this.ventanaEditarPaises.getTextPaisNuevo().setText("");
			llenarTablaEditarPais();
			escribirComboBoxesAgregar();
		}
		
		public boolean yaExisteElPais(String nuevoPais) {
			for(PaisDTO p: this.paisEnTabla) {
				if(p.getNombrePais().equals(nuevoPais)) {
					return true;
				}
			}return false;
		}
		
		public void editarPais(ActionEvent e) {
			String nombreNuevo = this.ventanaEditarPaises.getTextPaisNuevo().getText();
			if(yaExisteElPais(nombreNuevo)) {
				JOptionPane.showMessageDialog(null, "Este país ya existe!");
				return;
			}
			int filaSeleccionada = this.ventanaEditarPaises.getTable().getSelectedRow();
			if(filaSeleccionada==-1) {
				JOptionPane.showMessageDialog(null, "Seleccione un país para editar!");
				return;
			}
			DefaultTableModel modelo = (DefaultTableModel) this.ventanaEditarPaises.getTable().getModel();
			String nombrePais = (String) modelo.getValueAt(filaSeleccionada, 0);
			
			PaisDTO paisEditar = getPaisDeTabla(nombrePais); 
			this.pais.editarPais(paisEditar, nombreNuevo);
			this.paisEnTabla = this.pais.obtenerPaises();
			this.ventanaEditarPaises.getTextPaisNuevo().setText("");
			llenarTablaEditarPais();
			escribirComboBoxesAgregar();
		}
		
		
		public void salirEditarPais(ActionEvent a) {
			this.ventanaEditarPaises.cerrar();
			refrescarComboBoxes();
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

//			una vez que verificamos que la localidad tenga provincias, vemos si esa localidad ya existe para esa prov
			String nombrePaisSeleccionado=(String) this.ventanaEditarLocalidad.getComboBoxPaises().getSelectedItem();
			String nombreProvinciaSeleccionada = (String) this.ventanaEditarLocalidad.getComboProvincias().getSelectedItem();
			
			PaisDTO PaisSeleccionado = getPaisDeTabla(nombrePaisSeleccionado);
			ProvinciaDTO ProvinciaSeleccionada = getProvinciaDeTabla(nombreProvinciaSeleccionada, PaisSeleccionado.getIdPais());
			
			if(yaExisteLocalidad(ProvinciaSeleccionada,nombreLocalidadNueva)) {
				JOptionPane.showMessageDialog(null, "Esa localidad ya existe para esta provincia");
				return;
			}
			
			LocalidadDTO nuevaLocalidad = new LocalidadDTO(0, nombreLocalidadNueva,ProvinciaSeleccionada.getIdProvincia());

			this.localidad.agregarLocalidad(nuevaLocalidad);
			this.localidadEnTabla = this.localidad.obtenerLocalidades();
			this.ventanaEditarLocalidad.getTxtNuevaLocalidad().setText("");
			
//			refresecarCbPaises();
//			escribirComboBoxProvincias();
//			escribirComboBoxLocalidades();
			llenarTablaEditarLocalidad(nombrePaisSeleccionado,nombreProvinciaSeleccionada);
			escribirComboBoxesAgregar();
		}
		
		public void llenarTablaEditarLocalidad(String paisElegido, String provElegida) {	
			this.ventanaEditarLocalidad.getModelTabla().setRowCount(0);
			this.ventanaEditarLocalidad.getModelTabla().setColumnCount(0);
			this.ventanaEditarLocalidad.getModelTabla().setColumnIdentifiers(this.ventanaEditarLocalidad.getNombreColumnas());
			
			PaisDTO paisReferenciado = getPaisDeTabla(paisElegido);
			
			//Si el no hay paises
			if(paisReferenciado==null) {
				Object[] fila = {"","",""};
				this.ventanaEditarLocalidad.getModelTabla().addRow(fila);
				return;
			}
			
			ProvinciaDTO provinciaReferenciada = getProvinciaDeTabla(provElegida,paisReferenciado.getIdPais());
			//Si el pais no tiene provincias
			if(provinciaReferenciada==null) {
				System.out.println("el pais: "+paisElegido+" no tiene provincias");
				Object[] fila = {paisElegido,"",""};
				this.ventanaEditarLocalidad.getModelTabla().addRow(fila);
				return;
			}


			int cantLoc=0;
			for(LocalidadDTO localidad: this.localidadEnTabla) {	
				if(localidad.getIdForeignProvincia() == provinciaReferenciada.getIdProvincia()) {
					Object[] fila = {paisElegido,provElegida,localidad.getNombreLocalidad()};
					this.ventanaEditarLocalidad.getModelTabla().addRow(fila);
					cantLoc++;
				}
				
			}
//			Si una prov no tiene localidades le agregamos una vacia
			if(cantLoc==0) {
				Object[] fila = {paisElegido,provElegida,""};
				this.ventanaEditarLocalidad.getModelTabla().addRow(fila);
			}
		}
		
		public void llenarCbEditarLocalidad(String paisElegido, String provinciaElegida) {
			this.ventanaEditarLocalidad.getComboBoxPaises().removeAllItems();
			this.ventanaEditarLocalidad.getComboProvincias().removeAllItems();
			
			if(paisElegido == null) {
				return;
			}
			
			for(PaisDTO pais: this.paisEnTabla) {
				this.ventanaEditarLocalidad.getComboBoxPaises().addItem(pais.getNombrePais());
			}
			//seteamos el pais que se eligio
			this.ventanaEditarLocalidad.getComboBoxPaises().setSelectedItem(paisElegido);
			
//			Si el pais no tiene provincias
			if(provinciaElegida == null) {
				return;
			}
			this.ventanaEditarLocalidad.getComboProvincias().removeAllItems();
			int idPaisElegido = getPaisDeTabla(paisElegido).getIdPais();
			for(ProvinciaDTO provincia: this.provinciaEnTabla) {
				if(idPaisElegido == provincia.getForeignPais()) {
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
			if(paisElegido==null||provElegida==null) return;
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
			escribirComboBoxesAgregar();
			
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
//			String provinciaEnTabla = (String) modelo.getValueAt(filaSeleccionada, 1);

			if(provEnTabla==null) {
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
				llenarTablaLocalidadSinLocalidades(paisElegido,provElegida);
			}else {
				llenarTablaEditarLocalidad(paisElegido,provElegida);
			}
			escribirComboBoxesAgregar();
		}
		
		public void llenarTablaLocalidadSinLocalidades(String paisElegido,String provElegida) {
			this.ventanaEditarLocalidad.getModelTabla().setRowCount(0);
			this.ventanaEditarLocalidad.getModelTabla().setColumnCount(0);
			this.ventanaEditarLocalidad.getModelTabla().setColumnIdentifiers(this.ventanaEditarLocalidad.getNombreColumnas());
			
			Object[] fila = {paisElegido,provElegida,""};
			this.ventanaEditarLocalidad.getModelTabla().addRow(fila);
				
			
		}
		
		public boolean yaExisteLocalidad(ProvinciaDTO provincia, String nombre) {
			for(LocalidadDTO localidad: this.localidadEnTabla) {
				if(localidad.getIdForeignProvincia()==provincia.getIdProvincia() && localidad.getNombreLocalidad().equals(nombre)) {
					return true;
				}
			}
			return false;
		}
		
		public void salirLocalidad(ActionEvent a){
			this.ventanaEditarLocalidad.cerrar();
			refrescarComboBoxes();
		}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
//VISTA EDITAR PROVINCIA

		private void ventanaEditarProvincia(ActionEvent t) {
			String paisElegido = (String) this.ventanaPersona.getCbPais().getSelectedItem();
			llenarTablaProvincia(paisElegido);
			llenarCbEditarProvincia(paisElegido);
			this.ventanaProvincia.show();
		}
		
		private void agregarProvincia(ActionEvent q) {
			System.out.println("Agrego Provincia");
			String nombreProvincia = (String)this.ventanaProvincia.getTxtFieldId().getText();
			String paisElegido = (String) this.ventanaProvincia.getComboBox().getSelectedItem();
			
			if(nombreProvincia.contentEquals("")) {
				JOptionPane.showMessageDialog(null, "Campo nombre no debe estar vacio");
				return;
			}
			
			PaisDTO PaisSeleccionado = getPaisDeTabla(paisElegido);
			
			if(yaExisteProvincia(PaisSeleccionado,nombreProvincia)) {
			JOptionPane.showMessageDialog(null, "Esa localidad ya existe para esta provincia");
			return;
			}
			
			int idPais = buscarIdPaisPorElComboBox();
			ProvinciaDTO nuevaLocalidad = new ProvinciaDTO(0, nombreProvincia, idPais);
			
			this.provincia.agregarProvincia(nuevaLocalidad);
			this.provinciaEnTabla = this.provincia.obtenerProvincia();
			
			this.ventanaProvincia.limpiarTodosTxt();
			this.escribirComboBoxLocalidades();
			
			if(paisElegido==null) {return;}
			llenarTablaProvincia(paisElegido);
			return;
		}
		
		private void editarProvincia(ActionEvent w) {
			int filaSeleccionada = this.ventanaProvincia.tablaProvinciaSeleccionada();
			
			String nombreNuevo = (String)this.ventanaProvincia.getTxtFieldId().getText();
			
			if(nombreNuevo.contentEquals("") || filaSeleccionada==-1) {
				JOptionPane.showMessageDialog(null, "Campo nombre no debe estar vacio y se debe seleccionar una fila");
				return;
			}
			
			String paisElegido = (String) this.ventanaProvincia.getComboBox().getSelectedItem();
			if(paisElegido==null) {return;}
			
			PaisDTO PaisSeleccionado = getPaisDeTabla(paisElegido);
			
			DefaultTableModel modelo = (DefaultTableModel) this.ventanaProvincia.getTablaProvincia().getModel();
			String nombreProvincia = (String) modelo.getValueAt(filaSeleccionada, 0);
			
			ProvinciaDTO provinciaEditar = getProvinciaDeTabla(nombreProvincia,PaisSeleccionado.getIdPais()); 
			this.provincia.editarProvincia(nombreNuevo, provinciaEditar);
			this.provinciaEnTabla = this.provincia.obtenerProvincia();
			
			//SEPARACION
//			
//			int idModificar = this.provinciaEnTabla.get(filaSeleccionada).getIdProvincia();
//			int idPais = buscarIdPaisPorElComboBox();
//			
//			ProvinciaDTO datosNuevos = new ProvinciaDTO(0,nombreNuevo,idPais);
//			provincia.editarProvincia(idModificar,datosNuevos);
//			
//			String paisElegido = (String) this.ventanaProvincia.getComboBox().getSelectedItem();
//			if(paisElegido==null) {return;}
//			
//			this.provinciaEnTabla = this.provincia.obtenerProvincia();
			this.ventanaProvincia.limpiarTodosTxt();
			llenarTablaProvincia(paisElegido);
			return;
		}

		private void borrarProvincia(ActionEvent r) {
			int filaSeleccionada = this.ventanaProvincia.getTablaProvincia().getSelectedRow();
			if(filaSeleccionada==-1) {
				JOptionPane.showMessageDialog(null, "Debes seleccionar al menos una fila");
				return;
			}
			
			String paisElegido = (String) this.ventanaProvincia.getComboBox().getSelectedItem();
			if(paisElegido==null) {return;}
			
			DefaultTableModel modelo = (DefaultTableModel) this.ventanaProvincia.getTablaProvincia().getModel();
			PaisDTO PaisSeleccionado = getPaisDeTabla(paisElegido);
			String nombreProvinciaBorrar = (String) modelo.getValueAt(filaSeleccionada, 0);
			
			ProvinciaDTO provinciaElegida = getProvinciaDeTabla(nombreProvinciaBorrar,PaisSeleccionado.getIdPais());
			
			for(int i=0; i<this.provinciaEnTabla.size(); i++) {
				if(this.provinciaEnTabla.get(i).equals(provinciaElegida)) {
					this.provincia.borrarProvincia(this.provinciaEnTabla.get(i));
				}
			}
			
			
			
			this.ventanaProvincia.limpiarTodosTxt();
			this.provinciaEnTabla = this.provincia.obtenerProvincia();
			llenarTablaProvincia(paisElegido);
			return;
		}
		
		private void salirProvincia(ActionEvent t) {
			this.ventanaProvincia.cerrarVentana();
		}
		
		public void actualizarTablaProvincia(ActionEvent t){
			String paisElegido = (String) this.ventanaProvincia.getComboBox().getSelectedItem();
			if(paisElegido==null) {return;}
			llenarTablaProvincia(paisElegido);
		}
		
		public void llenarTablaProvincia(String paisElegido) {
			this.ventanaProvincia.getModelProvincia().setRowCount(0); // Para vaciar la tabla
			this.ventanaProvincia.getModelProvincia().setColumnCount(0);
			this.ventanaProvincia.getModelProvincia().setColumnIdentifiers(this.ventanaProvincia.getNombreColumnasProvincia());
			
			PaisDTO paisReferenciado = getPaisDeTabla(paisElegido);
			
			//Si el no hay paises
			if(paisReferenciado==null) {
				Object[] fila = {"","",""};
				this.ventanaEditarLocalidad.getModelTabla().addRow(fila);
				return;
			}
			
			for(ProvinciaDTO provincia: this.provinciaEnTabla) {	
				if(provincia.getForeignPais() == paisReferenciado.getIdPais()) {
					Object[] fila = {provincia.getNombreProvincia(),paisElegido};
					this.ventanaProvincia.getModelProvincia().addRow(fila);
				}
			}
			return;
		}
		
		public int buscarIdPaisPorElComboBox() {
			int idPais = 0;
			String comboBox = (String) this.ventanaProvincia.getComboBox().getSelectedItem();
			
			for (int i = 0; i < paisEnTabla.size(); i++) {
				if(paisEnTabla.get(i).getNombrePais()==comboBox) {
					idPais =paisEnTabla.get(i).getIdPais();
				}
			}
			return idPais;
		}
		
		public void llenarCbEditarProvincia(String paisElegido) {
			this.ventanaProvincia.getComboBox().removeAllItems();
			
			if(paisElegido == null) {
				return;
			}
			
			for(PaisDTO pais: this.paisEnTabla) {
				this.ventanaProvincia.getComboBox().addItem(pais.getNombrePais());
			}
			
			//seteamos el pais que se eligio
			this.ventanaProvincia.getComboBox().setSelectedItem(paisElegido);
			return;
			}
		
		public boolean yaExisteProvincia(PaisDTO pais, String nombre) {
			for(ProvinciaDTO provincia : this.provinciaEnTabla) {
				if(provincia.getForeignPais()==pais.getIdPais() && provincia.getNombreProvincia().equals(nombre)) {
					return true;
				}
			}
			return false;
		}
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
	

		private void mostrarReporte(ActionEvent r) {
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
			reporte.mostrar();	
		}


		
	
		@Override
		public void actionPerformed(ActionEvent e) { }
		
		
	
		
	
	
}
