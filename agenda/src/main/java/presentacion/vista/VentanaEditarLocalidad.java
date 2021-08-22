package presentacion.vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dto.PaisDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import persistencia.conexion.Conexion;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaEditarLocalidad extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTable tablaTipoLocalidad;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnSalir;
	private DefaultTableModel modelTipoLocalidad;
	private String[] nombreColumnas = { "Id", "Nombre" };
	private JLabel lblNewLabel;
	private JComboBox TipoLocalidad;
	private JTextField txtTipoLocalidad;
	private String[] opciones = {"Pais","Provincia","Localidad"};

	public VentanaEditarLocalidad() {
		super();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 532, 271);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(252, 0, 271, 232);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane spTipoContacto = new JScrollPane();
		spTipoContacto.setBounds(10, 11, 250, 182);
		panel.add(spTipoContacto);

		modelTipoLocalidad = new DefaultTableModel(null, nombreColumnas);
		tablaTipoLocalidad = new JTable(modelTipoLocalidad);

		tablaTipoLocalidad.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaTipoLocalidad.getColumnModel().getColumn(0).setResizable(false);
		tablaTipoLocalidad.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaTipoLocalidad.getColumnModel().getColumn(1).setResizable(false);

		spTipoContacto.setViewportView(tablaTipoLocalidad);

		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		
		btnSalir.setBounds(150, 198, 89, 23);
		panel.add(btnSalir);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 254, 232);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 198, 71, 23);
		panel_1.add(btnAgregar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(91, 198, 71, 23);
		panel_1.add(btnEditar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(172, 198, 71, 23);
		panel_1.add(btnBorrar);

		lblNewLabel = new JLabel("Gestionar localidades");
		lblNewLabel.setBounds(50, 10, 150, 30);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

		TipoLocalidad = new JComboBox();
		TipoLocalidad.setBounds(66, 50, 118, 20);
		TipoLocalidad.setModel(new DefaultComboBoxModel(opciones));
		panel_1.add(TipoLocalidad);
		
		txtTipoLocalidad = new JTextField();
		txtTipoLocalidad.setBounds(66, 90, 118, 20);
		panel_1.add(txtTipoLocalidad);
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.frame.setVisible(true);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}
	
	public JComboBox getComboBox() {
		return TipoLocalidad;
	}
	
	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public DefaultTableModel getModelTipoLocalidad() {
		return modelTipoLocalidad;
	}

	public JTable getTablaTipoLocalidad() {
		return tablaTipoLocalidad;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
	
	public void obtenerSeleccion() {
		String s = (String) TipoLocalidad.getSelectedItem();//get the selected item

        switch (s) {//check for a match
            case "Pais":
                System.out.println("Day selected, emailvalue:" + s);
                break;
            case "Provincia":
                System.out.println("Week selected, emailvalue:" + s);
                break;
            case "Localidad":
                System.out.println("Month selected, emailvalue:" + s);
                break;
        }
	}

	public void llenarTabla(List<PaisDTO> tipoLocalidadEnTabla) {
		this.getModelTipoLocalidad().setRowCount(0); // Para vaciar la tabla
		this.getModelTipoLocalidad().setColumnCount(0);
		this.getModelTipoLocalidad().setColumnIdentifiers(this.getNombreColumnas());

		//for (PaisDTO t : tipoLocalidadEnTabla) {
		//	int id = t.getIdTipoLocalidad();
		//	String nombre = t.getNombreTipoLocalidad();
		//	Object[] fila = { id, nombre };
		//	this.getModelTipoLocalidad().addRow(fila);
		//}

	}
}
