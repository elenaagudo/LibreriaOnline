package mvc.swingview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
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
import javax.swing.table.DefaultTableModel;

import mvc.controller.Controlador;
import mvc.model.Categoria;
import mvc.model.Editorial;

public class VistaLibroSwing implements ActionListener, MouseListener {

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
	private JButton cancelar;
	private JButton aceptar;
	private JButton limpiar;

	private DefaultTableModel modeloTablaLibros;
	private DefaultTableModel modeloTablaLibroAutor;
	private DefaultTableModel modeloTablaAutores;

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
	private JComboBox<Categoria> comboCategoria;
	private JComboBox<Editorial> comboEditorial;

	private DefaultComboBoxModel<Categoria> modeloComboCategoria;
	private DefaultComboBoxModel<Editorial> modeloComboEditorial;

	private Vector<Categoria> listaCategorias;
	private Vector<Editorial> listaEditoriales;

	private JTabbedPane tabs;

	private Dimension tamanoMarcoRequerido;

	private int isbnAUX;
	private boolean modoEdicion;
//	private boolean primeraVez = true;

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

		tablaLibros = new JTable(modeloTablaLibros);
		tablaLibroAutor = new JTable(modeloTablaLibroAutor);
		tablaAutores = new JTable(modeloTablaAutores);

		tablaLibros.addMouseListener(this);
		tablaLibroAutor.addMouseListener(this);
		tablaAutores.addMouseListener(this);

		tablaLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaLibroAutor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaAutores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollTablaLibros = new JScrollPane(tablaLibros);
		scrollTablaLibros.setPreferredSize(new Dimension(600, 100));

		JScrollPane scrollTablaAutorLibro = new JScrollPane(tablaLibroAutor);
		scrollTablaAutorLibro.setPreferredSize(new Dimension(600, 130));

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
		JPanel panelBotonPrueba = new JPanel();
		panelBotonPrueba.setLayout(new BoxLayout(panelBotonPrueba, BoxLayout.Y_AXIS));
		panelBotonPrueba.add(quitarAutor);
		panelLibroAutor.add(panelBotonPrueba, BorderLayout.EAST);

		panelAutores = new JPanel(new BorderLayout());
		anadirAutor = new JButton("Añadir autor");

		panelBotonesAutores = new JPanel();
		panelBotonesAutores.setLayout(new BoxLayout(panelBotonesAutores, BoxLayout.Y_AXIS));
		panelBotonesAutores.add(anadirAutor);
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
		panelBotonesFormulario = new JPanel();
		panelBotonesFormulario.setLayout(new BoxLayout(panelBotonesFormulario, BoxLayout.Y_AXIS));

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
		comboCategoria = new JComboBox<Categoria>();
		comboEditorial = new JComboBox<Editorial>();

		// botones del formulario
		cancelar = new JButton("Cancelar");
		cancelar.setMaximumSize(new Dimension(150, 30));
		aceptar = new JButton("Aceptar");
		aceptar.setMaximumSize(new Dimension(150, 30));
		limpiar = new JButton("Limpiar");
		limpiar.setMaximumSize(new Dimension(150, 30));

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

		panelTabNuevoLibro.add(panelFormulario, BorderLayout.CENTER);
		panelTabNuevoLibro.add(panelBotonesFormulario, BorderLayout.EAST);

		tabs.add("Autores", panelTabAutores);
		tabs.add("Nuevo libro", panelTabNuevoLibro);

		panelCentral = new JPanel(new BorderLayout());
		panelCentral.add(tituloFuncion, BorderLayout.NORTH);
		panelCentral.add(panelLibros, BorderLayout.CENTER);
		panelCentral.add(tabs, BorderLayout.SOUTH);

		cargarCombos();
		restablecerPanelBotones();

		// se añaden los listeners a los controles
		nuevo.addActionListener(this);
		editar.addActionListener(this);
		borrar.addActionListener(this);
		aceptar.addActionListener(this);
		quitarAutor.addActionListener(this);
		anadirAutor.addActionListener(this);
		cancelar.addActionListener(this);
		limpiar.addActionListener(this);

		// cambia el tamaño de la ventana
		tamanoMarcoRequerido = new Dimension(710, 600);
		ventana.setSize(tamanoMarcoRequerido);

		ventana.add(panelCentral, BorderLayout.CENTER);

		ventana.validate();
		ventana.repaint();
		// obtenerDatosParaTabla(modeloTablaLibro,
		// controlador.getClass().getMethod("obtenerDatosMasMetadatosLibro"), 0);
		// en el catch: noSuchMethodException

		cargarDatosEnTablaLibro();
		cargarDatosEnTablaAutor();

	}

	// obtenerDatosParaTabla(DefaultTableModel modeloTabla, Method metodo, int
	// parametroMetodo)
	// obtenerDatosParaLista(DefaultComboBoxModel<String> modeloLista, Method
	// metodo)
	// Vector<?> resultado = null;

	// mousePressed()
	// String componenteOrigen = getClass().getName()
	public void cargarDatosEnTablaLibro() {
		modeloTablaLibros.setRowCount(0);
		modeloTablaLibros.setColumnCount(0);
		try {
			ResultSet datos = controlador.obtenerDatosMasMetadatosLibro();
			ResultSetMetaData metadatos = datos.getMetaData();

			for (int col = 1; col <= metadatos.getColumnCount(); col++) {
				modeloTablaLibros.addColumn(metadatos.getColumnLabel(col));
			}

			while (datos.next()) {
				Object[] fila = new Object[metadatos.getColumnCount()];
				for (int col = 0; col < metadatos.getColumnCount(); col++) {
					fila[col] = datos.getObject(col + 1);
				}
				modeloTablaLibros.addRow(fila);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void cargarDatosEnTablaAutorLibro() {
		modeloTablaLibroAutor.setRowCount(0);
		modeloTablaLibroAutor.setColumnCount(0);
		try {
			int isbn = (int) tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 0);
			ResultSet datos = controlador.obtenerDatosMasMetadatosAutorLibro(isbn);
			ResultSetMetaData metadatos = datos.getMetaData();

			for (int col = 1; col <= metadatos.getColumnCount(); col++) {
				modeloTablaLibroAutor.addColumn(metadatos.getColumnLabel(col));
			}

			while (datos.next()) {
				Object[] fila = new Object[metadatos.getColumnCount()];
				for (int col = 0; col < metadatos.getColumnCount(); col++) {
					fila[col] = datos.getObject(col + 1);
				}
				modeloTablaLibroAutor.addRow(fila);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}

	}

	public void cargarDatosEnTablaAutor() {
		modeloTablaAutores.setRowCount(0);
		modeloTablaAutores.setColumnCount(0);
		try {
			ResultSet datos = controlador.obtenerDatosMasMetadatosAutor();
			ResultSetMetaData metadatos = datos.getMetaData();

			for (int col = 1; col <= metadatos.getColumnCount(); col++) {
				modeloTablaAutores.addColumn(metadatos.getColumnLabel(col));
			}

			while (datos.next()) {
				Object[] fila = new Object[metadatos.getColumnCount()];
				for (int col = 0; col < metadatos.getColumnCount(); col++) {
					fila[col] = datos.getObject(col + 1);
				}
				modeloTablaAutores.addRow(fila);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cargarDatosEnTablaAutorExcluir() {
		modeloTablaAutores.setRowCount(0);
		modeloTablaAutores.setColumnCount(0);
		try {
			
			int isbn = (int) tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 0);
			ResultSet datos = controlador.obtenerDatosMasMetadatosAutorExcluir(isbn);

			ResultSetMetaData metadatos = datos.getMetaData();

			for (int col = 1; col <= metadatos.getColumnCount(); col++) {
				modeloTablaAutores.addColumn(metadatos.getColumnLabel(col));
			}

			while (datos.next()) {
				Object[] fila = new Object[metadatos.getColumnCount()];
				for (int col = 0; col < metadatos.getColumnCount(); col++) {
					fila[col] = datos.getObject(col + 1);
				}
				modeloTablaAutores.addRow(fila);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cargarCombos() {
		listaCategorias = controlador.listCategories();
		modeloComboCategoria = new DefaultComboBoxModel<Categoria>(listaCategorias);
		comboCategoria.setModel(modeloComboCategoria);

		listaEditoriales = controlador.listEditorials();
		modeloComboEditorial = new DefaultComboBoxModel<Editorial>(listaEditoriales);
		comboEditorial.setModel(modeloComboEditorial);
	}

	// UTILITIES
	// limpia los campos
	private void limpiarCampos() {
		txtIsbn.setText("");
		txtTitulo.setText("");
		txtPrecio.setText("");
		txtStock.setText("");
		comboCategoria.setSelectedIndex(0);
		comboEditorial.setSelectedIndex(0);
	}

	// Restablece/habilita/deshabilita el panel CRUD
	private void restablecerPanelBotones() {
		editar.setEnabled(false);
		borrar.setEnabled(false);
		nuevo.setEnabled(true);
		quitarAutor.setEnabled(false);
		anadirAutor.setEnabled(false);
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

	// metodo para meter los datos en las cajas de texto al pulsar editar
	private void rellenarCampos() {
		txtIsbn.setText(String.valueOf(tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 0)));
		txtTitulo.setText((String) tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 1));
		txtPrecio.setText(String.valueOf(tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 2)));
		txtStock.setText(String.valueOf(tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 3)));

		Vector<Categoria> categorias = controlador.listCategories();
		int posicion = 0;
		for (int i = 0; i < categorias.size(); i++) {
			if (categorias.get(i).getNombreCategoria()
					.equals(tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 4))) {
				posicion = i;
			}
		}
		Categoria cat = comboCategoria.getItemAt(posicion);
		modeloComboCategoria.setSelectedItem(cat);

		Vector<Editorial> editoriales = controlador.listEditorials();
		posicion = 0;
		for (int i = 0; i < editoriales.size(); i++) {
			if (editoriales.get(i).getNombreEditorial()
					.equals(tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 5))) {
				posicion = i;
			}
		}
		Editorial edit = comboEditorial.getItemAt(posicion);
		modeloComboEditorial.setSelectedItem(edit);

		// se recoge el valor inicial del isbn por si se modifica
		isbnAUX = Integer.parseInt(txtIsbn.getText());
	}

	// METODOS CRUD
	// modifica el registro seleccionado
	private void modificarRegistro() {
		int isbnNuevo = Integer.parseInt(txtIsbn.getText());
		String titulo = txtTitulo.getText();
		double precio = Double.parseDouble(txtPrecio.getText());
		int stock = Integer.parseInt(txtStock.getText());
		Categoria categoria = (Categoria) comboCategoria.getSelectedItem();
		int codigoCategoria = categoria.getCodigoCategoria();
		Editorial editorial = (Editorial) comboEditorial.getSelectedItem();
		int codigoEditorial = editorial.getCodigoEditorial();
		String feedback = controlador.updateBook(isbnAUX, isbnNuevo, titulo, precio, stock, codigoCategoria,
				codigoEditorial);
		informarUsuario(feedback);
		cargarDatosEnTablaLibro();
		restablecerPanelBotones();
	}

	// borra el registro seleccionado
	private void borrarRegistro() {
		String pregunta = "Va a borrar el libro con isbn " + tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 0)
				+ "\n¿Está seguro?";
		String feedback = "No se ha borrado el libro.";
		if (preguntarUsuario(pregunta) == JOptionPane.YES_OPTION) {
			feedback = controlador.deleteBook((int) tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 0));
			cargarDatosEnTablaLibro();
			if (feedback.equals("Borrado de autor correcto.")) {
				modeloTablaLibros.removeRow(tablaLibros.getSelectedRow());
			}
		}
		cargarDatosEnTablaLibro();
		cargarDatosEnTablaAutor();
		modeloTablaLibroAutor.setColumnCount(0);
		modeloTablaLibroAutor.setRowCount(0);
		restablecerPanelBotones();
		informarUsuario(feedback);
	}

	private void registrarNuevo() {
		if (!txtIsbn.getText().equals("") && !txtTitulo.getText().equals("") && !txtPrecio.getText().equals("")
				&& !txtStock.getText().equals("")) {
			try {
				int isbn = Integer.parseInt(txtIsbn.getText());
				String titulo = txtTitulo.getText();
				double precio = Double.parseDouble(txtPrecio.getText());
				int stock = Integer.parseInt(txtStock.getText());
				Categoria categoria = (Categoria) comboCategoria.getSelectedItem();
				Editorial editorial = (Editorial) comboEditorial.getSelectedItem();
				int codigoCategoria = categoria.getCodigoCategoria();
				int codigoEditorial = editorial.getCodigoEditorial();
				informarUsuario(controlador.insertBook(isbn, titulo, precio, stock, codigoCategoria, codigoEditorial));
				cargarDatosEnTablaLibro();
				limpiarCampos();
			} catch (Exception e) {
				informarUsuario("Introduce tipos de dato válidos");
			}
		} else {
			informarUsuario("Rellena todos los campos para registrar un nuevo libro");
		}

	}

	private void quitarAutor() {
		// necesito el codigo del autor que quiero quitar y el isbn del libro del que
		// quiero quitarlo
		int codigoAutor = (int) tablaLibroAutor.getValueAt(tablaLibroAutor.getSelectedRow(), 0);
		int isbn = (int) tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 0);
		informarUsuario(controlador.deleteBookAuthor(codigoAutor, isbn));
		cargarDatosEnTablaAutorLibro();
		cargarDatosEnTablaAutorExcluir();
		quitarAutor.setEnabled(false);
	}

	private void anadirAutor() {
		// necesito el codigo del autor que quiero añadir y el isbn del libro al que
		// quiero añadirlo
		int codigoAutor = (int) tablaAutores.getValueAt(tablaAutores.getSelectedRow(), 0);
		int isbn = (int) tablaLibros.getValueAt(tablaLibros.getSelectedRow(), 0);
		informarUsuario(controlador.insertBookAuthor(codigoAutor, isbn));
		cargarDatosEnTablaAutorLibro();
		cargarDatosEnTablaAutorExcluir();
		anadirAutor.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		switch (ae.getActionCommand()) {
		case "Nuevo":
			tabs.setSelectedIndex(1);
			limpiarCampos();
			modoEdicion = false;
			break;
		case "Borrar":
			borrarRegistro();
			limpiarCampos();
			break;
		case "Aceptar":
			if (modoEdicion) {
				modificarRegistro();
			} else {
				registrarNuevo();
			}
			break;
		case "Editar":
			tabs.setSelectedIndex(1);
			
			rellenarCampos();
			modoEdicion = true;
			break;
		case "Cancelar":
			limpiarCampos();
			modoEdicion = false;
			informarUsuario("Ahora puedes registrar un nuevo libro");
			break;
		case "Limpiar":
			limpiarCampos();
			modoEdicion = false;
			informarUsuario("Ahora puedes registrar un nuevo libro");
			break;
		case "Quitar autor":
			quitarAutor();
			break;
		case "Añadir autor":
			anadirAutor();
			break;
		default:
			break;
		}
	}

	@Override
	public void mousePressed(MouseEvent me) {
		if (me.getSource() == tablaLibros) {
			cargarDatosEnTablaAutorLibro();
			cargarDatosEnTablaAutorExcluir();
			editar.setEnabled(true);
			borrar.setEnabled(true);
		} else if (me.getSource() == tablaLibroAutor) {
			quitarAutor.setEnabled(true);
		} else if (me.getSource() == tablaAutores) {
			anadirAutor.setEnabled(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
