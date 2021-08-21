package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import dto.PersonaDTO;

public class VentanaPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JButton btnAgregarPersona;
	private static VentanaPersona INSTANCE;
	JPanel panel;
	JButton btnCancelar;
	private JButton btnAceptar;
	
	
	//Datos nuevos persona, aun no puestos en la vista
	private JTextField calle;
	
	private JTextField altura;
	private JTextField piso;
	private JTextField departamento;
	private JTextField domicilio;
	private JTextField email;
	
	//Fecha cumpleanios
	private JDateChooser fechaCumpleanios;
	
	
	
	
	public static VentanaPersona getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaPersona(); 	
			return new VentanaPersona();
		}
		else
			return INSTANCE;
	}

	private VentanaPersona() 
	{
		super();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch (Exception e){
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 183);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 307, 123);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(10, 52, 113, 14);
		panel.add(lblTelfono);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 49, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(210, 101, 89, 23);
		panel.add(btnAgregarPersona);
		
		btnCancelar = new JButton("Cancelar");
		panel.add(btnCancelar);
		btnCancelar.setBounds(20, 102, 85, 21);
		btnCancelar.setVisible(false);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(212, 102, 85, 21);
		panel.add(btnAceptar);
		btnCancelar.setVisible(false);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.btnAceptar.setVisible(false);
		this.btnAgregarPersona.setVisible(true);
		this.setVisible(true);
	}
	
	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JTextField getTxtTelefono() 
	{
		return txtTelefono;
	}

	public JButton getBtnAgregarPersona() 
	{
		return btnAgregarPersona;
	}

	public JButton getBtnCancelar()
	{
		return btnCancelar;
	}
	
	public JButton getBtnAceptar() {
		return btnAceptar;
	}
	
	public void cerrar()
	{
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

	
	//NOSE SI LA VISTA PUEDE TENER CONTACTO CON EL CÓDIGO
	public void mostrarVentanaConValores(PersonaDTO persona) {
		this.txtNombre.setText(persona.getNombre());
		this.txtTelefono.setText(persona.getTelefono());
		
		//Cambiamos los botones
		this.btnAgregarPersona.setVisible(false);
		this.btnCancelar.setVisible(true);
		this.btnAceptar.setVisible(true);
		
		this.setVisible(true);
	}
}

