package presentacion.vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import javax.swing.JComboBox;

public class VentanaTipoContacto extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnSalir;
	private static VentanaTipoContacto INSTANCE;
	private JTextField txtAgregarNuevoTipoContacto;
	private JComboBox cbTipoCliente;
	private JButton btnAgregarNuevoTipoContacto;
	private JButton btnAgregarTipoContacto;
	
	public static VentanaTipoContacto getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaTipoContacto(); 	
			return new VentanaTipoContacto();
		}
		else
			return INSTANCE;
	}

	private VentanaTipoContacto() 
	{
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 284, 282);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 248, 229);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(149, 200, 89, 23);
		panel.add(btnSalir);
		
		JLabel lblNewLabel = new JLabel("Tipo de contacto");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(67, 11, 118, 14);
		panel.add(lblNewLabel);
		
		cbTipoCliente = new JComboBox();
		cbTipoCliente.setBounds(67, 36, 118, 22);
		panel.add(cbTipoCliente);
		
		btnAgregarTipoContacto = new JButton("Agregar");
		btnAgregarTipoContacto.setBounds(77, 69, 89, 23);
		panel.add(btnAgregarTipoContacto);
		
		JLabel lblNewLabel_1 = new JLabel("Crear nuevo tipo de contacto:");
		lblNewLabel_1.setBounds(20, 110, 146, 14);
		panel.add(lblNewLabel_1);
		
		txtAgregarNuevoTipoContacto = new JTextField();
		txtAgregarNuevoTipoContacto.setBounds(20, 133, 146, 20);
		panel.add(txtAgregarNuevoTipoContacto);
		txtAgregarNuevoTipoContacto.setColumns(10);
		
		btnAgregarNuevoTipoContacto = new JButton("Agregar");
		btnAgregarNuevoTipoContacto.setBounds(20, 164, 89, 23);
		panel.add(btnAgregarNuevoTipoContacto);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public JTextField getTxtAgregarNuevoTipoContacto() 
	{
		return txtAgregarNuevoTipoContacto;
	}

	public JComboBox getCbTipoCliente() 
	{
		return cbTipoCliente;
	}

	public JButton getBtnAgregarNuevoTipoContacto() 
	{
		return btnAgregarNuevoTipoContacto;
	}
	
	public JButton getBtnAgregarTipoContacto() {
		return btnAgregarTipoContacto;
	}
	

	public void cerrar()
	{
		this.txtAgregarNuevoTipoContacto.setText(null);
		this.cbTipoCliente.setSelectedItem(null);
		this.dispose();
	}
	
}

