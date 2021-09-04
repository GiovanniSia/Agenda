package presentacion.vista;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import javax.swing.JTextField;

import persistencia.conexion.Conexion;

import javax.swing.JButton;

public class VentanaLogin {

	private JFrame frame;

	private JTextField textUsuario;
	private JPasswordField textContrasenia;
	private JTextField textDireccionIP;
	private JTextField textPuerto;
	private JButton btnEntrar;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaLogin window = new VentanaLogin();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}



	/**
	 * Create the application.
	 */
	public VentanaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Inciar Sesión");
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsuario.setBounds(10, 24, 80, 24);
		frame.getContentPane().add(lblUsuario);
		
		JLabel lblContraseña = new JLabel("Contraseña: ");
		lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblContraseña.setBounds(10, 73, 114, 24);
		frame.getContentPane().add(lblContraseña);
		
		JLabel lblDireccionIp = new JLabel("Dirección IP:");
		lblDireccionIp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDireccionIp.setBounds(57, 138, 114, 24);
		frame.getContentPane().add(lblDireccionIp);
		
		JLabel lblPuerto = new JLabel("Puerto: ");
		lblPuerto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPuerto.setBounds(290, 138, 70, 24);
		frame.getContentPane().add(lblPuerto);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(140, 24, 218, 24);
		frame.getContentPane().add(textUsuario);
		textUsuario.setColumns(10);
		
		textContrasenia = new JPasswordField();
		textContrasenia.setColumns(10);
		textContrasenia.setBounds(142, 73, 218, 24);
		frame.getContentPane().add(textContrasenia);
		
		textDireccionIP = new JTextField();
		textDireccionIP.setColumns(10);
		textDireccionIP.setBounds(10, 172, 218, 24);
		frame.getContentPane().add(textDireccionIP);
		
		textPuerto = new JTextField();
		textPuerto.setColumns(10);
		textPuerto.setBounds(290, 172, 96, 24);
		frame.getContentPane().add(textPuerto);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEntrar.setBounds(301, 223, 96, 30);
		frame.getContentPane().add(btnEntrar);
		
		
		
		
	}

	
	
	
	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
			             null, "¿Estás seguro que quieres salir de la Agenda?", 
			             "Confirmación", JOptionPane.YES_NO_OPTION,
			             JOptionPane.QUESTION_MESSAGE, null, null, null);
			        if (confirm == 0) {
			           System.exit(0);
			        }
			}
		});
		this.frame.setVisible(true);
	}
	
	public void cerrar() {
		frame.setVisible(false);
	}
	
	
	
	
	public JTextField getTextUsuario() {
		return textUsuario;
	}

	public JPasswordField getTextContrasenia() {
		return textContrasenia;
	}

	public JTextField getTextDireccionIP() {
		return textDireccionIP;
	}

	public JTextField getTextPuerto() {
		return textPuerto;
	}

	public JButton getBtnEntrar() {
		return btnEntrar;
	}
}
