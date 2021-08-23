package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import dto.PersonaDTO;

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

	private JTextField altura;
	private JTextField piso;
	private JTextField departamento;
	private JTextField domicilio;
	private JTextField email;

	// Fecha cumpleanios
	private JDateChooser fechaCumpleanios;

	private JTextField txtEmail;
	private JTextField txtCalle;
	private JTextField txtAltura;
	private JTextField txtPiso;
	private JTextField txtDepartamento;

	private JButton btnEditarTipoContacto;
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
		setBounds(100, 100, 343, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 447);
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
		cbPais.setBounds(133, 304, 164, 22);
		panel.add(cbPais);

		cbProvincia = new JComboBox();
		cbProvincia.setBounds(133, 329, 164, 22);
		panel.add(cbProvincia);

		cbLocalidad = new JComboBox();
		cbLocalidad.setBounds(133, 355, 164, 22);
		panel.add(cbLocalidad);

		btnEditarLocalidad = new JButton("Editar Localidad");
		btnEditarLocalidad.setBounds(10, 383, 113, 23);
		panel.add(btnEditarLocalidad);

		btnCancelar = new JButton("Cancelar");
		panel.add(btnCancelar);
		btnCancelar.setBounds(0, 426, 85, 21);
		btnCancelar.setVisible(false);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(113, 426, 85, 21);
		panel.add(btnAceptar);
		btnCancelar.setVisible(false);

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

	public JComboBox getCbPais() {
		return cbPais;
	}

	public JComboBox getCbProvincia() {
		return cbProvincia;
	}

	public JComboBox getCbTipoContacto() {
		return cbTipoContacto;
	}

	public JComboBox getCbLocalidad() {
		return cbLocalidad;
	}

	public void cerrar() {
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		this.dispose();
	}

	public JTextField getCalle() {
		return calle;
	}

	public JTextField getAltura() {
		return altura;
	}

	public JTextField getPiso() {
		return piso;
	}

	public JTextField getDepartamento() {
		return departamento;
	}

	public JTextField getDomicilio() {
		return domicilio;
	}

	public JTextField getEmail() {
		return email;
	}

	public JDateChooser getFechaCumpleanios() {
		return fechaCumpleanios;
	}

	// NOSE SI LA VISTA PUEDE TENER CONTACTO CON EL CÓDIGO
	public void mostrarVentanaConValores(PersonaDTO persona) {
		this.txtNombre.setText(persona.getNombre());
		this.txtTelefono.setText(persona.getTelefono());

		// Cambiamos los botones
		this.btnAgregarPersona.setVisible(false);
		this.btnCancelar.setVisible(true);
		this.btnAceptar.setVisible(true);

		this.setVisible(true);
	}
}
