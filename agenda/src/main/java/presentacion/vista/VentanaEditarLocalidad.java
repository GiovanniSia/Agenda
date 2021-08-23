package presentacion.vista;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.ProvinciaDTO;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class VentanaEditarLocalidad extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnSalir;
	private String[] nombreColumnas = { "Id", "Nombre" };
	private String[] nombreColumnasProvincia = { "Id","Nombre", "Id Pais"};
	private String[] nombreColumnasLocalidad = { "Id","Nombre","Id Provincia","Id Pais"};
	private JLabel txtGestionar;
	private JLabel txtIdPais;
	private JLabel txtIdProvincia;
	private JComboBox TipoLocalidad;
	private JTextField txtFieldNombreLocalidad;
	private JTextField txtFieldId;
	private String[] opciones = {"Pais","Provincia","Localidad"};
	private DefaultTableModel modelPais;
	private JTable tablaPais;
	private JButton btnEditar;
	private DefaultTableModel modelProvincia;
	private JTable tablaProvincia;
	private DefaultTableModel modelLocalidad;
	private JTable tablaLocalidad;
	private List<PaisDTO> listaDePaises;
	private List<ProvinciaDTO> listaDeProvincias;
	private List<LocalidadDTO> listaDeLocalidades;

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

		JScrollPane spPais = new JScrollPane();
		spPais.setBounds(10, 11, 250, 182);
		panel.add(spPais);
		
//		JScrollPane spProvincia = new JScrollPane();
//		spProvincia.setBounds(10, 11, 250, 182);
//		panel.add(spProvincia);
//		
//		JScrollPane spLocalidad = new JScrollPane();
//		spLocalidad.setBounds(10, 11, 250, 182);
//		panel.add(spLocalidad);
		
		//TABLA PROVINCIA
		modelProvincia = new DefaultTableModel(null, nombreColumnasProvincia );
		tablaProvincia = new JTable(modelProvincia);
		tablaProvincia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tablaProvincia.rowAtPoint(e.getPoint());
				txtFieldNombreLocalidad.setText(tablaProvincia.getValueAt(filaSeleccionada, 1).toString());
			}
		});
		tablaProvincia.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaProvincia.getColumnModel().getColumn(0).setResizable(false);
		tablaProvincia.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaProvincia.getColumnModel().getColumn(1).setResizable(false);
		
		//spProvincia.setViewportView(tablaProvincia);
		
		//TABLA LOCALIDAD
		modelLocalidad = new DefaultTableModel(null, nombreColumnasLocalidad);
		tablaLocalidad = new JTable(modelLocalidad);
		tablaLocalidad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tablaLocalidad.rowAtPoint(e.getPoint());
				txtFieldNombreLocalidad.setText(tablaLocalidad.getValueAt(filaSeleccionada, 1).toString());
			}
		});
		tablaLocalidad.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaLocalidad.getColumnModel().getColumn(0).setResizable(false);
		tablaLocalidad.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaLocalidad.getColumnModel().getColumn(1).setResizable(false);
		
		//spLocalidad.setViewportView(tablaLocalidad);
		
		//TABLA DE PAIS
		modelPais = new DefaultTableModel(null, nombreColumnas);
		tablaPais = new JTable(modelPais);
		tablaPais.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tablaPais.rowAtPoint(e.getPoint());
				txtFieldNombreLocalidad.setText(tablaPais.getValueAt(filaSeleccionada, 1).toString());
			}
		});
		tablaPais.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPais.getColumnModel().getColumn(0).setResizable(false);
		tablaPais.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPais.getColumnModel().getColumn(1).setResizable(false);
		
		spPais.setViewportView(tablaPais);
		
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

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(91, 198, 71, 23);
		panel_1.add(btnEditar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(172, 198, 71, 23);
		panel_1.add(btnBorrar);

		txtGestionar = new JLabel("Gestionar localidades");
		txtGestionar.setBounds(50, 10, 150, 30);
		txtGestionar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(txtGestionar);

		TipoLocalidad = new JComboBox();
		TipoLocalidad.setBounds(66, 50, 118, 20);
		TipoLocalidad.setModel(new DefaultComboBoxModel(opciones));
		panel_1.add(TipoLocalidad);
		
		txtIdPais = new JLabel("Nombre:");
		txtIdPais.setBounds(40, 87, 70, 20);
		txtIdPais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(txtIdPais);
		
		txtIdPais = new JLabel("Id Pais:");
		txtIdPais.setBounds(40, 127, 50, 20);
		txtIdPais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(txtIdPais);
		txtIdPais.setVisible(false);
		
		txtIdProvincia = new JLabel("Id Provincia:");
		txtIdProvincia.setBounds(30, 127, 100, 20);
		txtIdProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(txtIdProvincia);
		txtIdProvincia.setVisible(false);
		
		txtFieldNombreLocalidad = new JTextField();
		txtFieldNombreLocalidad.setBounds(130, 90, 100, 20);
		panel_1.add(txtFieldNombreLocalidad);
		
		txtFieldId = new JTextField();
		txtFieldId.setBounds(130, 130, 100, 20);
		panel_1.add(txtFieldId);
		txtFieldId.setVisible(false);
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
	
	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public DefaultTableModel getModelTipoPais() {
		return modelPais;
	}

	public JTable getTablaTipoPais() {
		return tablaPais;
	}
	
	public DefaultTableModel getModelTipoProvincia() {
		return modelProvincia;
	}

	public JTable getTablaTipoProvincia() {
		return tablaProvincia;
	}
	
	public DefaultTableModel getModelTipoLocalidad() {
		return modelLocalidad;
	}

	public JTable getTablaTipoLocalidad() {
		return tablaLocalidad;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
	
	public String[] getNombreColumnasProvincia() {
		return nombreColumnasProvincia;
	}
	
	public String[] getNombreColumnasLocalidad() {
		return nombreColumnasLocalidad;
	}
	
	public JLabel getTxtIdPais() {
		return txtIdPais;
	}

	public JLabel getTxtIdProvincia() {
		return txtIdProvincia;
	}

	public JTextField getTxtFieldId() {
		return txtFieldId;
	}
	
	public JTable getTablaPais() {
		return tablaPais;
	}
	
	public JTextField getTxtFieldNombreLocalidad() {
		return txtFieldNombreLocalidad;
	}
	
	public void guardarSeleccion() {
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
	
	public void obtenerSeleccion() {
		String s = (String) TipoLocalidad.getSelectedItem();//get the selected item

        switch (s) {//check for a match
            case "Pais":
            	getTxtIdPais().setVisible(false);
            	getTxtIdProvincia().setVisible(false);
            	getTxtFieldId().setVisible(false);
            	
            	//Para limpiar el texto de los text field
            	borrarTablas();
            	limpiarTodosTxt();
            	tablaPais.setVisible(true);
            	llenarTablaPais(listaDePaises);
                break;
                
            case "Provincia":
            	getTxtIdPais().setVisible(true);
            	getTxtIdProvincia().setVisible(false);
            	getTxtFieldId().setVisible(true);
            	
            	//Para limpiar el texto de los text field
            	borrarTablas();
            	limpiarTodosTxt();
            	tablaProvincia.setVisible(true);
            	llenarTablaProvincia(listaDeProvincias);
                break;
                
            case "Localidad":
            	getTxtIdPais().setVisible(false);
            	getTxtIdProvincia().setVisible(true);
            	getTxtFieldId().setVisible(true);
            	
            	//Para limpiar el texto de los text field
            	borrarTablas();
            	limpiarTodosTxt();
            	tablaLocalidad.setVisible(true);
            	llenarTablaLocalidad(listaDeLocalidades);
                break;
        }
	}

	public void llenarTablaPais(List<PaisDTO> tipoPaisEnTabla) {
		listaDePaises = tipoPaisEnTabla;
		tablaPais.setVisible(true);
		this.getModelTipoPais().setRowCount(0); // Para vaciar la tabla
		this.getModelTipoPais().setColumnCount(2);
		this.getModelTipoPais().setColumnIdentifiers(this.getNombreColumnas());

		for (PaisDTO t : tipoPaisEnTabla) {
			int id = t.getIdPais();
			String nombre = t.getNombrePais();
			Object[] fila = { id, nombre };
			this.getModelTipoPais().addRow(fila);
		}
		return;
	}
	
	public void llenarTablaProvincia(List<ProvinciaDTO> tipoProvinciaEnTabla) {
		listaDeProvincias = tipoProvinciaEnTabla;
		tablaProvincia.setVisible(true);
		this.getModelTipoProvincia().setRowCount(0); // Para vaciar la tabla
		this.getModelTipoProvincia().setColumnCount(3);
		this.getModelTipoProvincia().setColumnIdentifiers(this.getNombreColumnasProvincia());

		for (ProvinciaDTO t : tipoProvinciaEnTabla) {
			int id = t.getIdProvincia();
			String nombre = t.getNombreProvincia();
			int idPais = t.getForeignPais();
			Object[] fila = { id, nombre , idPais};
			this.getModelTipoProvincia().addRow(fila);
		}
		return;
	}
	
	public void llenarTablaLocalidad(List<LocalidadDTO> tipoLocalidadEnTabla) {
		listaDeLocalidades = tipoLocalidadEnTabla;
		tablaLocalidad.setVisible(true);
		this.getModelTipoLocalidad().setRowCount(0); // Para vaciar la tabla
		this.getModelTipoLocalidad().setColumnCount(3);
		this.getModelTipoLocalidad().setColumnIdentifiers(this.getNombreColumnasLocalidad());

		for (LocalidadDTO t : tipoLocalidadEnTabla) {
			int id = t.getIdLocalidad();
			String nombre = t.getNombreLocalidad();
			int idProvincia = t.getIdForeignProvincia();
			Object[] fila = { id, nombre, idProvincia};
			this.getModelTipoLocalidad().addRow(fila);
		}
		return;
	}
	
	public void borrarTablas() {
		tablaPais.setVisible(false);
		tablaProvincia.setVisible(false);
		tablaLocalidad.setVisible(false);
		return;
	}
	
	public int tablaPaisSeleccionada() {
		return tablaPais.getSelectedRow();
	}
	
	public int tablaProvinciaSeleccionada() {
		return tablaProvincia.getSelectedRow();
	}
	
	public int tablaLocalidadSeleccionada() {
		return tablaLocalidad.getSelectedRow();
	}
	
	public void cerrar() {
		this.limpiarTodosTxt();
		frame.setVisible(false);
	}
	
	public void limpiarTodosTxt() {
		getTxtFieldId().setText(null);
    	getTxtFieldNombreLocalidad().setText(null);
    	return;
	}
}
