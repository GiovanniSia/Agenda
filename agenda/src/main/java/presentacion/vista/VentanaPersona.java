package presentacion.vista;

import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;

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
	private JDateChooser fechaCumpleanios;
	private JTextField txtEmail;
	private JTextField txtCalle;
	private JTextField txtAltura;
	private JTextField txtPiso;
	private JTextField txtDepartamento;
	private JButton btnEditarTipoContacto;
	private JButton btnEditarPais;
	private JButton btnEditarLocalidad;
	private JButton btnEditarProvincia;
	private JComboBox cbPais;
	private JComboBox cbProvincia;
	private JComboBox cbTipoContacto;
	private JComboBox cbLocalidad;
	private JComboBox cbSignoZodiaco;

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
		setBounds(100, 100, 372, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 344, 463);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(21, 14, 113, 14);
		panel.add(lblNombreYApellido);

		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(39, 45, 113, 14);
		panel.add(lblTelfono);

		txtNombre = new JTextField();
		txtNombre.setBounds(173, 11, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(173, 42, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);

		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(248, 426, 89, 23);
		panel.add(btnAgregarPersona);

		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(49, 76, 113, 14);
		panel.add(lblNewLabel);

		txtEmail = new JTextField();
		txtEmail.setBounds(173, 73, 164, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Domicilio");
		lblNewLabel_1.setBounds(10, 135, 100, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Calle");
		lblNewLabel_2.setBounds(123, 135, 46, 14);
		panel.add(lblNewLabel_2);

		txtCalle = new JTextField();
		txtCalle.setBounds(173, 132, 164, 20);
		panel.add(txtCalle);
		txtCalle.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Altura");
		lblNewLabel_3.setBounds(123, 160, 46, 14);
		panel.add(lblNewLabel_3);

		txtAltura = new JTextField();
		txtAltura.setBounds(173, 157, 164, 20);
		panel.add(txtAltura);
		txtAltura.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Piso");
		lblNewLabel_4.setBounds(123, 185, 46, 14);
		panel.add(lblNewLabel_4);

		txtPiso = new JTextField();
		txtPiso.setBounds(173, 182, 164, 20);
		panel.add(txtPiso);
		txtPiso.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Departamento");
		lblNewLabel_5.setBounds(100, 210, 69, 14);
		panel.add(lblNewLabel_5);

		txtDepartamento = new JTextField();
		txtDepartamento.setBounds(173, 207, 164, 20);
		panel.add(txtDepartamento);
		txtDepartamento.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Tipo de Contacto");
		lblNewLabel_6.setBounds(10, 239, 113, 14);
		panel.add(lblNewLabel_6);

		cbTipoContacto = new JComboBox();
		cbTipoContacto.setBounds(173, 235, 164, 22);
		panel.add(cbTipoContacto);

		btnEditarTipoContacto = new JButton("Editar Contacto");
		btnEditarTipoContacto.setBounds(10, 255, 113, 23);
		panel.add(btnEditarTipoContacto);

		btnCancelar = new JButton("Cancelar");
		panel.add(btnCancelar);
		btnCancelar.setBounds(10, 427, 85, 21);
		btnCancelar.setVisible(false);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(248, 427, 85, 21);
		panel.add(btnAceptar);

		JLabel lblNewLabel_7 = new JLabel("Localidad");
		lblNewLabel_7.setBounds(10, 308, 100, 14);
		panel.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Pais");
		lblNewLabel_8.setBounds(129, 332, 46, 14);
		panel.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Provincia");
		lblNewLabel_9.setBounds(117, 357, 46, 14);
		panel.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Localidad");
		lblNewLabel_10.setBounds(117, 382, 46, 14);
		panel.add(lblNewLabel_10);

		cbPais = new JComboBox();
		cbPais.setBounds(173, 328, 164, 22);
		panel.add(cbPais);

		cbProvincia = new JComboBox();
		cbProvincia.setBounds(173, 354, 164, 22);
		panel.add(cbProvincia);

		cbLocalidad = new JComboBox();
		cbLocalidad.setBounds(173, 378, 164, 22);
		panel.add(cbLocalidad);

		fechaCumpleanios = new JDateChooser();
		fechaCumpleanios.setBounds(173, 105, 164, 19);
		fechaCumpleanios.setDate(new Date());
		panel.add(fechaCumpleanios);

		JLabel lblNewLabel_11 = new JLabel("Fecha de cumpleaños");
		lblNewLabel_11.setBounds(10, 110, 142, 14);
		panel.add(lblNewLabel_11);

		btnEditarPais = new JButton("Editar Pais");
		btnEditarPais.setBounds(6, 328, 107, 23);
		panel.add(btnEditarPais);

		btnEditarLocalidad = new JButton("Editar Localidad");
		btnEditarLocalidad.setBounds(6, 378, 107, 23);
		panel.add(btnEditarLocalidad);

		btnEditarProvincia = new JButton("Editar Provincia");
		btnEditarProvincia.setBounds(6, 353, 107, 23);
		panel.add(btnEditarProvincia);

		JLabel lblNewLabel_12 = new JLabel("Signo del zodiaco");
		lblNewLabel_12.setBounds(10, 289, 89, 14);
		panel.add(lblNewLabel_12);

		cbSignoZodiaco = new JComboBox();
		cbSignoZodiaco.setBounds(173, 285, 164, 22);
		panel.add(cbSignoZodiaco);

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

	public JButton getBtnEditarPais() {
		return btnEditarPais;
	}

	public JButton getBtnEditarProvincia() {
		return btnEditarProvincia;
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

	public JComboBox getCbSignoZodiaco() {
		return cbSignoZodiaco;
	}

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

	public JTextField getEmail() {
		return this.txtEmail;
	}

	public JDateChooser getFechaCumpleanios() {
		return this.fechaCumpleanios;
	}

	public void limpiarValores() {
		this.txtNombre.setText("");
		this.txtTelefono.setText("");
		this.txtEmail.setText("");
		this.fechaCumpleanios.setDate(null);
		this.txtCalle.setText("");
		this.txtAltura.setText("");
		this.txtPiso.setText("");
		this.txtDepartamento.setText("");
		this.getCbTipoContacto().setSelectedIndex(-1);
		this.getCbSignoZodiaco().setSelectedIndex(-1);
		this.getCbPais().setSelectedIndex(-1);
		this.getCbProvincia().setSelectedIndex(-1);
		this.getCbLocalidad().setSelectedIndex(-1);
	}


	public String getTipoDeContactoSeleccionado() {
		return (String) this.cbTipoContacto.getSelectedItem();
	}

	public String getPaisSeleccionado() {
		return (String) this.cbPais.getSelectedItem();
	}

	public String getProvinciaSeleccionado() {
		return (String) this.cbProvincia.getSelectedItem();
	}

	public String getLocalidadSeleccionado() {
		return (String) this.cbLocalidad.getSelectedItem();
	}
	
	public String getSignoZodiacoSeleccionado() {
		return (String) this.cbSignoZodiaco.getSelectedItem();
	}
}
