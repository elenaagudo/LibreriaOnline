package mvc.swingview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import mvc.controller.Controlador;
import mvc.model.Categoria;
import mvc.model.Editorial;

public class VistaLibroSwing implements ListSelectionListener, ActionListener {

	private Controlador controlador;
	private JFrame ventana;

	private JPanel panelCentral;
	private JPanel panelLibros;
	private JPanel panelBotonesCRUD;
	private JPanel panelTabAutores;
	private JPanel panelTabNuevoLibro;
	private JPanel panelLibroAutor;
	private JPanel panelAutores;
	private JPanel panelBotonesAutores;
	private JPanel panelFormulario;
	private JPanel panelBotonesFormulario;

	private JButton editar;
	private JButton borrar;
	private JButton nuevo;
	private JButton quitarAutor;
	private JButton anadirAutor;
	private JButton recargarAutores;
	private JButton cancelar;
	private JButton aceptar;
	private JButton limpiar;
	private JButton recargarListas;

	private DefaultTableModel modeloTablaLibros;
	private DefaultTableModel modeloTablaLibroAutor;
	private DefaultTableModel modeloTablaAutores;

	private ListSelectionModel seleccionTablaLibros;
	private ListSelectionModel seleccionTablaAutorLibro;
	private ListSelectionModel seleccionTablaAutores;

	private JTable tablaLibros;
	private JTable tablaLibroAutor;
	private JTable tablaAutores;

	private JLabel tituloFuncion = new JLabel("MANTENIMIENTO LIBROS");
	private JLabel etiquetaIsbn;
	private JLabel etiquetaTitulo;
	private JLabel etiquetaPrecio;
	private JLabel etiquetaStock;
	private JLabel etiquetaCategoria;
	private JLabel etiquetaEditorial;

	private JTextField txtIsbn;
	private JTextField txtTitulo;
	private JTextField txtPrecio;
	private JTextField txtStock;
	private JComboBox<String> comboCategoria;
	private JComboBox<Editorial> comboEditorial;
	
	private DefaultComboBoxModel<Categoria> modeloComboCategoria;
	private DefaultComboBoxModel<Editorial> modeloComboEditorial;

	private JTabbedPane tabs;

	private Dimension tamanoMarcoRequerido;

	public VistaLibroSwing(JFrame ventana, Controlador controlador) {
		this.ventana = ventana;
		this.controlador = controlador;

		modeloTablaLibros = new DefaultTableModel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}

		};

		modeloTablaLibroAutor = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};

		modeloTablaAutores = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		
		cargarComboCategoria();
		
		tablaLibros = new JTable(modeloTablaLibros);
		tablaLibroAutor = new JTable(modeloTablaLibroAutor);
		tablaAutores = new JTable(modeloTablaAutores);

		tablaLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaLibroAutor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaAutores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		seleccionTablaLibros = tablaLibros.getSelectionModel();
		seleccionTablaLibros.addListSelectionListener(this);
		JScrollPane scrollTablaLibros = new JScrollPane(tablaLibros);
		scrollTablaLibros.setPreferredSize(new Dimension(600, 100));

		seleccionTablaAutorLibro = tablaLibroAutor.getSelectionModel();
		seleccionTablaAutorLibro.addListSelectionListener(this);
		JScrollPane scrollTablaAutorLibro = new JScrollPane(tablaLibroAutor);
		scrollTablaAutorLibro.setPreferredSize(new Dimension(600, 130));

		seleccionTablaAutores = tablaAutores.getSelectionModel();
		seleccionTablaAutores.addListSelectionListener(this);
		JScrollPane scrollTablaAutores = new JScrollPane(tablaAutores);
		scrollTablaAutores.setPreferredSize(new Dimension(600, 130));

		// panel de libros
		editar = new JButton("Editar");
		borrar = new JButton("Borrar");
		nuevo = new JButton("Nuevo");
		panelBotonesCRUD = new JPanel(new GridLayout(3, 1));
		panelBotonesCRUD.add(editar);
		panelBotonesCRUD.add(borrar);
		panelBotonesCRUD.add(nuevo);

		panelLibros = new JPanel(new BorderLayout());
		panelLibros.add(scrollTablaLibros, BorderLayout.CENTER);
		panelLibros.add(panelBotonesCRUD, BorderLayout.EAST);

		// pestañas
		tabs = new JTabbedPane();
		// pestaña autores
		panelTabAutores = new JPanel(new BorderLayout());

		panelLibroAutor = new JPanel(new BorderLayout());
		quitarAutor = new JButton("Quitar autor");
		panelLibroAutor.add(scrollTablaAutorLibro, BorderLayout.CENTER);
		panelLibroAutor.add(quitarAutor, BorderLayout.EAST);

		panelAutores = new JPanel(new BorderLayout());
		anadirAutor = new JButton("Añadir autor");
//		anadirAutor.setMaximumSize(new Dimension(140,25));
		recargarAutores = new JButton("Recargar autores");
//		recargarAutores.setMaximumSize(new Dimension(140,25));
		panelBotonesAutores = new JPanel(new GridLayout(2, 1));
		panelBotonesAutores.add(anadirAutor);
		panelBotonesAutores.add(recargarAutores);
		panelAutores.add(scrollTablaAutores, BorderLayout.CENTER);
		panelAutores.add(panelBotonesAutores, BorderLayout.EAST);

		panelTabAutores.add(panelLibroAutor, BorderLayout.CENTER);
		panelTabAutores.add(panelAutores, BorderLayout.SOUTH);
		panelTabAutores.setPreferredSize(new Dimension(600, 260));

		// pestaña nuevo libro
		panelTabNuevoLibro = new JPanel(new BorderLayout());
		panelTabNuevoLibro.setPreferredSize(new Dimension(600, 260));

		// paneles pestaña nuevo libro
		panelFormulario = new JPanel(new GridLayout(12, 1));
		panelBotonesFormulario = new JPanel(new GridLayout(4, 1));

		// etiquetas para el formulario
		etiquetaIsbn = new JLabel("ISBN");
		etiquetaTitulo = new JLabel("Titulo");
		etiquetaPrecio = new JLabel("Precio");
		etiquetaStock = new JLabel("Stock");
		etiquetaCategoria = new JLabel("Categoria");
		etiquetaEditorial = new JLabel("Editorial");
		// textfield para el formulario
		txtIsbn = new JTextField();
		txtTitulo = new JTextField();
		txtPrecio = new JTextField();
		txtStock = new JTextField();
		comboCategoria = new JComboBox();
		comboEditorial = new JComboBox();

		// botones del formulario
		cancelar = new JButton("Cancelar");
		aceptar = new JButton("Aceptar");
		limpiar = new JButton("Limpiar");
		recargarListas = new JButton("Recargar listas");

		// añadir los componentes a los paneles
		panelFormulario.add(etiquetaIsbn);
		panelFormulario.add(txtIsbn);
		panelFormulario.add(etiquetaTitulo);
		panelFormulario.add(txtTitulo);
		panelFormulario.add(etiquetaPrecio);
		panelFormulario.add(txtPrecio);
		panelFormulario.add(etiquetaStock);
		panelFormulario.add(txtStock);
		panelFormulario.add(etiquetaCategoria);
		panelFormulario.add(comboCategoria);
		panelFormulario.add(etiquetaEditorial);
		panelFormulario.add(comboEditorial);

		panelBotonesFormulario.add(cancelar);
		panelBotonesFormulario.add(aceptar);
		panelBotonesFormulario.add(limpiar);
		panelBotonesFormulario.add(recargarListas);

		panelTabNuevoLibro.add(panelFormulario, BorderLayout.CENTER);
		panelTabNuevoLibro.add(panelBotonesFormulario, BorderLayout.EAST);

		tabs.add("Autores", panelTabAutores);
		tabs.add("Nuevo libro", panelTabNuevoLibro);

		panelCentral = new JPanel(new BorderLayout());
		panelCentral.add(tituloFuncion, BorderLayout.NORTH);
		panelCentral.add(panelLibros, BorderLayout.CENTER);
		panelCentral.add(tabs, BorderLayout.SOUTH);

		restablecerPanelBotones();

		// se añaden los listeners a los controles
		nuevo.addActionListener(this);
		editar.addActionListener(this);
		borrar.addActionListener(this);

		// cambia el tamaño de la ventana
		tamanoMarcoRequerido = new Dimension(710, 500);
		ventana.setSize(tamanoMarcoRequerido);

		ventana.add(panelCentral, BorderLayout.CENTER);

		ventana.pack();
		ventana.validate();
		ventana.repaint();
		// obtenerDatosParaTabla(modeloTablaLibro,
		// controlador.getClass().getMethod("obtenerDatosMasMetadatosLibro"), 0);
		// en el catch: noSuchMethodException

		cargarDatosEnTablaLibro(modeloTablaLibros);
		cargarDatosEnTablaAutor(modeloTablaAutores);

	}

	// obtenerDatosParaTabla(DefaultTableModel modeloTabla, Method metodo, int
	// parametroMetodo)
	// obtenerDatosParaLista(DefaultComboBoxModel<String> modeloLista, Method
	// metodo)
	// Vector<?> resultado = null;

	// mousePressed()
	// String componenteOrigen = getClass().getName()
	public void cargarDatosEnTablaLibro(DefaultTableModel modelo) {
		try {
			ResultSet datos = controlador.obtenerDatosMasMetadatosLibro();
			ResultSetMetaData metadatos = datos.getMetaData();

			for (int col = 1; col <= metadatos.getColumnCount(); col++) {
				modelo.addColumn(metadatos.getColumnLabel(col));
			}

			while (datos.next()) {
				Object[] fila = new Object[metadatos.getColumnCount()];
				for (int col = 0; col < metadatos.getColumnCount(); col++) {
					fila[col] = datos.getObject(col + 1);
				}
				modelo.addRow(fila);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void cargarDatosEnTablaAutorLibro(DefaultTableModel modelo) {
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
		try {
			int isbn = (int) tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 0);
			ResultSet datos = controlador.obtenerDatosMasMetadatosAutorLibro(isbn);
			ResultSetMetaData metadatos = datos.getMetaData();

			for (int col = 1; col <= metadatos.getColumnCount(); col++) {
				modelo.addColumn(metadatos.getColumnLabel(col));
			}

			while (datos.next()) {
				Object[] fila = new Object[metadatos.getColumnCount()];
				for (int col = 0; col < metadatos.getColumnCount(); col++) {
					fila[col] = datos.getObject(col + 1);
				}
				modelo.addRow(fila);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void cargarDatosEnTablaAutor(DefaultTableModel modelo) {
		try {
			ResultSet datos = controlador.obtenerDatosMasMetadatosAutor();
			ResultSetMetaData metadatos = datos.getMetaData();

			for (int col = 1; col <= metadatos.getColumnCount(); col++) {
				modelo.addColumn(metadatos.getColumnLabel(col));
			}

			while (datos.next()) {
				Object[] fila = new Object[metadatos.getColumnCount()];
				for (int col = 0; col < metadatos.getColumnCount(); col++) {
					fila[col] = datos.getObject(col + 1);
				}
				modelo.addRow(fila);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void cargarComboCategoria() {
		Vector<Categoria> listaCategorias = controlador.listCategories();
		modeloComboCategoria = new DefaultComboBoxModel<Categoria>(listaCategorias);
		comboCategoria = new JComboBox<String>();
		for (Categoria categoria : listaCategorias) {
			comboCategoria.addItem(categoria.getNombreCategoria());
		}
	}

	// UTILITIES
	// Restablece/habilita/deshabilita el panel CRUD
	private void restablecerPanelBotones() {
		editar.setEnabled(false);
		borrar.setEnabled(false);
		nuevo.setEnabled(true);
		quitarAutor.setEnabled(false);
		anadirAutor.setEnabled(false);
		recargarAutores.setEnabled(true);
	}

	public JPanel getPanelCentral() {
		return panelCentral;
	}

	public Dimension getTamanoMarcoRequerido() {
		return tamanoMarcoRequerido;
	}

	// metodo para informar al usuario
		private void informarUsuario(String mensaje) {
			JOptionPane.showMessageDialog(ventana, mensaje, tituloFuncion.getText(), JOptionPane.INFORMATION_MESSAGE);
		}

		// metodo para preguntar al usuario
		private int preguntarUsuario(String pregunta) {
			int confirmacion = JOptionPane.showConfirmDialog(ventana, pregunta);
			return confirmacion;
		}
	
	// METODOS CRUD
	// modifica el registro seleccionado
	private void modificarRegistro() {
		// String feedback = controlador.upd
	}

	// borra el regitro seleccionado
	private void borrarRegistro() {
		String pregunta = "Va a borrar el libro con isbn " + tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 0)
				+ "\n¿Está seguro?";
		String feedback = "No se ha borrado el libro.";
		if(preguntarUsuario(pregunta) == JOptionPane.YES_OPTION) {
			feedback = controlador.deleteBook((int)tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 0));
			if(feedback.equals("Borrado de libro correcto.")) {
				modeloTablaLibros.removeRow(tablaLibros.getSelectedRow());
			}
		}
		informarUsuario(feedback);
	}
	
	private void registrarNuevo() {
		
	}

	// EVENTO CUANDO SE SELECCIONA ALGO EN LA TABLA
	@Override
	public void valueChanged(ListSelectionEvent lse) {
		if (tablaLibros.getSelectedRow() != -1) {
			cargarDatosEnTablaAutorLibro(modeloTablaLibroAutor);
			editar.setEnabled(true);
			borrar.setEnabled(true);
		} else {
			editar.setEnabled(false);
			borrar.setEnabled(false);
		}

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		switch (ae.getActionCommand()) {
		case "Nuevo":
			tabs.setSelectedIndex(1);
			break;
		case "Borrar":
			borrarRegistro();
			break;
		default:
			break;
		}
	}

}
