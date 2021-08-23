package presentacion.vista;

import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;

public class VentanaPersona extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JButton btnAgregarPersona;
	private static VentanaPersona INSTANCE;
	JPanel panel;
	JButton btnCancelar;
	private JButton btnAceptar;

	// Datos nuevos persona, aun no puestos en la vista
	private JTextField calle;

//	private JTextField altura;
//	private JTextField piso;
//	private JTextField departamento;
//	private JTextField domicilio;
//	private JTextField email;

	// Fecha cumpleanios
	private JDateChooser fechaCumpleanios;

	private JTextField txtEmail;
	private JTextField txtCalle;
	private JTextField txtAltura;
	private JTextField txtPiso;
	private JTextField txtDepartamento;

	private JButton btnEditarTipoContacto;

	//Localidad
	private JButton btnEditarLocalidad;
	private JComboBox cbPais;
	private JComboBox cbProvincia;
	private JComboBox cbTipoContacto;
	private JComboBox cbLocalidad;
	
	
	public static VentanaPersona getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VentanaPersona();
			return new VentanaPersona();
		} else
			return INSTANCE;
	}

	private VentanaPersona() {
		super();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 463);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);

		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(10, 42, 113, 14);
		panel.add(lblTelfono);

		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 39, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);

		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(218, 287, 89, 23);
		btnAgregarPersona.setBounds(208, 425, 89, 23);
		panel.add(btnAgregarPersona);

		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(10, 73, 113, 14);
		panel.add(lblNewLabel);

		txtEmail = new JTextField();
		txtEmail.setBounds(133, 70, 164, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Domicilio");
		lblNewLabel_1.setBounds(10, 111, 46, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Calle");
		lblNewLabel_2.setBounds(77, 132, 46, 14);
		panel.add(lblNewLabel_2);

		txtCalle = new JTextField();
		txtCalle.setBounds(133, 129, 164, 20);
		panel.add(txtCalle);
		txtCalle.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Altura");
		lblNewLabel_3.setBounds(77, 157, 46, 14);
		panel.add(lblNewLabel_3);

		txtAltura = new JTextField();
		txtAltura.setBounds(133, 154, 164, 20);
		panel.add(txtAltura);
		txtAltura.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Piso");
		lblNewLabel_4.setBounds(77, 182, 46, 14);
		panel.add(lblNewLabel_4);

		txtPiso = new JTextField();
		txtPiso.setBounds(133, 179, 164, 20);
		panel.add(txtPiso);
		txtPiso.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Departamento");
		lblNewLabel_5.setBounds(54, 207, 69, 14);
		panel.add(lblNewLabel_5);

		txtDepartamento = new JTextField();
		txtDepartamento.setBounds(133, 204, 164, 20);
		panel.add(txtDepartamento);
		txtDepartamento.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Tipo de Contacto");
		lblNewLabel_6.setBounds(10, 239, 100, 14);
		panel.add(lblNewLabel_6);

		cbTipoContacto = new JComboBox();
		cbTipoContacto.setBounds(133, 235, 164, 22);
		panel.add(cbTipoContacto);

		btnEditarTipoContacto = new JButton("Editar Contacto");
		btnEditarTipoContacto.setBounds(10, 255, 113, 23);
		panel.add(btnEditarTipoContacto);

		btnCancelar = new JButton("Cancelar");
		panel.add(btnCancelar);
		btnCancelar.setBounds(10, 288, 85, 21);
		btnCancelar.setVisible(false);

		btnAceptar = new JButton("Aceptar");

		
		btnAceptar.setBounds(218, 288, 89, 21);

		btnAceptar.setBounds(113, 426, 85, 21);


		panel.add(btnAceptar);
		btnCancelar.setVisible(false);

		
		//LOCALIDAD

		JLabel lblNewLabel_7 = new JLabel("Localidad");
		lblNewLabel_7.setBounds(10, 289, 100, 14);
		panel.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Pais");
		lblNewLabel_8.setBounds(77, 308, 46, 14);
		panel.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Provincia");
		lblNewLabel_9.setBounds(77, 333, 46, 14);
		panel.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Localidad");
		lblNewLabel_10.setBounds(77, 358, 46, 14);
		panel.add(lblNewLabel_10);

		cbPais = new JComboBox();
//		cbPais.setModel(new DefaultComboBoxModel(new String[] {"Argentina"}));
		cbPais.setBounds(133, 304, 164, 22);
		panel.add(cbPais);

		cbProvincia = new JComboBox();
//		cbProvincia.setModel(new DefaultComboBoxModel(new String[] {"Buenos Aires"}));
		cbProvincia.setBounds(133, 329, 164, 22);
		panel.add(cbProvincia);

		cbLocalidad = new JComboBox();
//		cbLocalidad.setModel(new DefaultComboBoxModel(new String[] {"San Miguel"}));
		cbLocalidad.setBounds(133, 355, 164, 22);
		panel.add(cbLocalidad);

		btnEditarLocalidad = new JButton("Editar Localidad");
		btnEditarLocalidad.setBounds(10, 383, 113, 23);
		panel.add(btnEditarLocalidad);
		
		fechaCumpleanios = new JDateChooser();
		fechaCumpleanios.setBounds(143, 100, 135, 19);
		fechaCumpleanios.setDate(new Date());
		panel.add(fechaCumpleanios);
		
		
		
		
		
		
		
		
		
		this.setVisible(false);
	}

	public void mostrarVentana() {
		this.btnAceptar.setVisible(false);
		this.btnAgregarPersona.setVisible(true);
		this.setVisible(true);
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public JButton getBtnAgregarPersona() {
		return btnAgregarPersona;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public JButton getBtnEditarTipoContacto() {
		return btnEditarTipoContacto;
	}


	
	public JButton getBtnEditarLocalidad() {
		return btnEditarLocalidad;
	}
//
//	public JComboBox getCbPais() {
//		return cbPais;
//	}
//
//	public JComboBox getCbProvincia() {
//		return cbProvincia;
//	}
//
//	public JComboBox getCbTipoContacto() {
//		return cbTipoContacto;
//	}
//
//	public JComboBox getCbLocalidad() {
//		return cbLocalidad;
//	}

	public void cerrar() {
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		this.dispose();
	}

	public JTextField getCalle() {
		return this.txtCalle;
	}

	public JTextField getAltura() {
		return this.txtAltura;
	}

	public JTextField getPiso() {
		return this.txtPiso;
	}

	public JTextField getDepartamento() {
		return this.txtDepartamento;
	}

//	public JTextField getDomicilio() {
//		return this.txtDomicilio;
//	}

	public JTextField getEmail() {
		return this.txtEmail;
	}

//<<<<<<< HEAD
	public void limpiarValores() {
		this.txtNombre.setText("");
		this.txtTelefono.setText("");
		this.txtEmail.setText("");
		this.txtCalle.setText("");
		this.txtAltura.setText("");
		this.txtPiso.setText("");
		this.txtDepartamento.setText("");
		this.cbTipoContacto.setSelectedItem(null);
//		this.cbPais.setSelectedItem(null);
//		this.cbProvincia.setSelectedItem(null);
//		this.cbLocalidad.setSelectedItem(null);
	}
//=======
	public JDateChooser getFechaCumpleanios() {
		return this.fechaCumpleanios;

	}

	// NOSE SI LA VISTA PUEDE TENER CONTACTO CON EL CÓDIGO
	public void mostrarVentanaConValores(PersonaDTO persona, List<TipoContactoDTO> tiposDeContactosEnTabla, List<PaisDTO> paisEnTabla, List<ProvinciaDTO> provinciaEnTabla, List<LocalidadDTO> localidadEnTabla) {
		this.txtNombre.setText(persona.getNombre());
		this.txtTelefono.setText(persona.getTelefono());
		this.txtEmail.setText(persona.getEmail());
		this.fechaCumpleanios.setDate(persona.getFechaDeCumpleanios());
		this.txtCalle.setText(persona.getDomicilio().getCalle());
		this.txtAltura.setText(persona.getDomicilio().getAltura());
		this.txtPiso.setText(persona.getDomicilio().getPiso());
		this.txtDepartamento.setText(persona.getDomicilio().getDepartamento());
		this.escribirComboBoxTipoDeContacto(tiposDeContactosEnTabla);
		this.escribirComboBoxPais(paisEnTabla);
		this.escribirComboBoxProvincia(provinciaEnTabla);
		this.escribirComboBoxLocalidad(localidadEnTabla);
	
		this.btnAgregarPersona.setVisible(false);
		this.btnCancelar.setVisible(true);
		this.btnAceptar.setVisible(true);

		this.setVisible(true);
	}
	
	
	
	
	
	//Localidad
	public String getTipoDeContactoSeleccionado() {
		return (String) this.cbTipoContacto.getSelectedItem();
	}

	public String getPaisSeleccionado() {
		return (String)this.cbPais.getSelectedItem();
	}
	
	public String getProvinciaSeleccionado() {
		return (String)this.cbProvincia.getSelectedItem();
	}
	
	public String getLocalidadSeleccionado() {
		return (String)this.cbLocalidad.getSelectedItem();
	}
	
	
	
	public void escribirComboBoxTipoDeContacto(List<TipoContactoDTO> tiposDeContactosEnTabla) {
		this.cbTipoContacto.removeAllItems();
		for (TipoContactoDTO tipo : tiposDeContactosEnTabla) {
			this.cbTipoContacto.addItem(tipo.getNombreTipoContacto());
		}
	}
	
	public void escribirComboBoxPais(List<PaisDTO> paisEnTabla) {
		this.cbPais.removeAllItems();
		for (PaisDTO p : paisEnTabla) {
			this.cbTipoContacto.addItem(p.getNombrePais());
		}
	}
	
	public void escribirComboBoxProvincia(List<ProvinciaDTO> provinciaEnTabla) {
		this.cbProvincia.removeAllItems();
		for (ProvinciaDTO p : provinciaEnTabla) {
			this.cbTipoContacto.addItem(p.getNombreProvincia());
		}
	}
	
	public void escribirComboBoxLocalidad(List<LocalidadDTO> localidadEnTabla) {
		this.cbTipoContacto.removeAllItems();
		for (LocalidadDTO l : localidadEnTabla) {
			this.cbTipoContacto.addItem(l.getNombreLocalidad());
		}
	}
}
