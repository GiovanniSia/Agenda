package presentacion.vista;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class VentanaEditarLocalidad {

	private JFrame frame;

	private JTable table;
	private DefaultTableModel modelTabla;

	private String[] nombreColumnas = { "País", "Provincia", "Localidad" };

	private JLabel lblNuevaLocalidad;
	private JLabel lblPaises;
	private JLabel lblProvincias;

	private JComboBox comboBoxPaises;
	private JComboBox comboProvincias;
	private JTextField txtNuevaLocalidad;

	private JButton btnAgregarLocalidad;
	private JButton btnEditarLocalidad;
	private JButton btnBorrarLocalidad;
	private JButton btnSalirLocalidad;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ventanaEditarLocalidadd window = new ventanaEditarLocalidadd();
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
	public VentanaEditarLocalidad() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 568, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboBoxPaises = new JComboBox();
		comboBoxPaises.setBounds(62, 34, 123, 21);
		frame.getContentPane().add(comboBoxPaises);
		
		lblPaises = new JLabel("Seleccione un pais");
		lblPaises.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaises.setBounds(62, 12, 123, 13);
		frame.getContentPane().add(lblPaises);
		
		lblProvincias = new JLabel("Seleccione una provincia");
		lblProvincias.setHorizontalAlignment(SwingConstants.CENTER);
		lblProvincias.setBounds(62, 74, 123, 13);
		frame.getContentPane().add(lblProvincias);
		
		comboProvincias = new JComboBox();
		comboProvincias.setBounds(62, 97, 123, 21);
		frame.getContentPane().add(comboProvincias);
		
		txtNuevaLocalidad = new JTextField();
		txtNuevaLocalidad.setBounds(62, 172, 123, 21);
		frame.getContentPane().add(txtNuevaLocalidad);
		txtNuevaLocalidad.setColumns(10);
		
		lblNuevaLocalidad = new JLabel("Escriba una nueva localidad");
		lblNuevaLocalidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevaLocalidad.setBounds(46, 149, 154, 13);
		frame.getContentPane().add(lblNuevaLocalidad);
		
		JScrollPane scrollPane = new JScrollPane(this.table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(301, 10, 243, 243);
		frame.getContentPane().add(scrollPane);
		
		modelTabla = new DefaultTableModel(null,nombreColumnas);
		table = new JTable(modelTabla);
		
		this.table.getColumnModel().getColumn(0).setPreferredWidth(103);
		this.table.getColumnModel().getColumn(0).setResizable(false);
		this.table.getColumnModel().getColumn(1).setPreferredWidth(100);
		this.table.getColumnModel().getColumn(1).setResizable(false);
		
		scrollPane.setViewportView(table);
		
		btnAgregarLocalidad = new JButton("Agregar");
		btnAgregarLocalidad.setBounds(0, 232, 76, 21);
		frame.getContentPane().add(btnAgregarLocalidad);
		
		btnEditarLocalidad = new JButton("Editar");
		btnEditarLocalidad.setBounds(78, 232, 61, 21);
		frame.getContentPane().add(btnEditarLocalidad);
		
		btnBorrarLocalidad = new JButton("Borrar");
		btnBorrarLocalidad.setBounds(149, 232, 61, 21);
		frame.getContentPane().add(btnBorrarLocalidad);
		
		btnSalirLocalidad = new JButton("Salir");
		btnSalirLocalidad.setBounds(220, 232, 71, 21);
		frame.getContentPane().add(btnSalirLocalidad);
	}
	
	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);
			}
		});
		this.frame.setVisible(true);
	}

	public void cerrar() {
		frame.setVisible(false);
	}

	
	
	public JComboBox getComboBoxPaises() {
		return comboBoxPaises;
	}

	public JComboBox getComboProvincias() {
		return comboProvincias;
	}

	public JTextField getTxtNuevaLocalidad() {
		return txtNuevaLocalidad;
	}
	
	public JButton getBtnAgregarLocalidad() {
		return btnAgregarLocalidad;
	}

	public JButton getBtnEditarLocalidad() {
		return btnEditarLocalidad;
	}

	public JButton getBtnBorrarLocalidad() {
		return btnBorrarLocalidad;
	}

	public JTable getTable() {
		return table;
	}
	
	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
	public DefaultTableModel getModelTabla() {
		return modelTabla;
	}

	public JButton getBtnSalirLocalidad() {
		return btnSalirLocalidad;
	}
}
