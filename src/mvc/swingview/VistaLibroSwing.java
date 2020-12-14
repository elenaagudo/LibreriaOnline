package mvc.swingview;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import mvc.controller.Controlador;

public class VistaLibroSwing implements ListSelectionListener {

	private Controlador controlador;
	private JFrame ventana;

	private JPanel panelCentral;
	private JPanel panelLibros;
	private JPanel panelPestanas;
	private JPanel panelBotonesCRUD;
	private JPanel panelLibroAutor;
	private JPanel panelAutores;

	private JButton editar;
	private JButton borrar;
	private JButton nuevo;
	private JButton quitarAutor;
	private JButton anadirAutor;
	private JButton recargarAutores;
	/*
	 * private JButton cancelar; private JButton aceptar; private JButton limpiar;
	 * private JButton recargarListas;
	 */

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

		tablaLibros = new JTable(modeloTablaLibros);
		tablaLibroAutor = new JTable(modeloTablaLibroAutor);
		tablaAutores = new JTable(modeloTablaAutores);

		tablaLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaLibroAutor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaAutores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		seleccionTablaLibros = tablaLibros.getSelectionModel();
		seleccionTablaLibros.addListSelectionListener(this);
		JScrollPane scrollTablaLibros = new JScrollPane(tablaLibros);

		seleccionTablaAutorLibro = tablaLibroAutor.getSelectionModel();
		seleccionTablaAutorLibro.addListSelectionListener(this);
		JScrollPane scrollTablaAutorLibro = new JScrollPane(tablaLibroAutor);

		seleccionTablaAutores = tablaAutores.getSelectionModel();
		seleccionTablaAutores.addListSelectionListener(this);
		JScrollPane scrollTablaAutores = new JScrollPane(tablaAutores);

		editar = new JButton("Editar");
		borrar = new JButton("Borrar");
		nuevo = new JButton("Nuevo");
		panelBotonesCRUD = new JPanel();
		panelBotonesCRUD.add(editar);
		panelBotonesCRUD.add(borrar);
		panelBotonesCRUD.add(nuevo);
		

		quitarAutor = new JButton("Quitar autor");

		anadirAutor = new JButton("Añadir autor");
		recargarAutores = new JButton("Recargar autores");

		// paneles 5
		// para los botones boxlayout

		ventana.setVisible(true);
		// obtenerDatosParaTabla(modeloTablaLibro,
		// controlador.getClass().getMethod("obtenerDatosMasMetadatosLibro"), 0);
		// en el catch: noSuchMethodException

		// cargarDatosEnTabla(modeloTabla);

	}

	// obtenerDatosParaTabla(DefaultTableModel modeloTabla, Method metodo, int
	// parametroMetodo)
	// obtenerDatosParaLista(DefaultComboBoxModel<String> modeloLista, Method
	// metodo)
	// Vector<?> resultado = null;

	// mousePressed()
	// String componenteOrigen = getClass().getName()
	public void cargarDatosEnTabla(DefaultTableModel modelo) {
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

	// UTILITIES
	// Restablece/habilita/deshabilita el panel CRUD
	private void restablecerPanelCRUD() {
		editar.setEnabled(false);
		borrar.setEnabled(false);
		nuevo.setEnabled(true);
	}

	private void habilitarPanelCRUD() {
		editar.setEnabled(true);
		borrar.setEnabled(true);
		nuevo.setEnabled(true);
	}

	private void deshabilitarPanelCRUD() {
		editar.setEnabled(false);
		borrar.setEnabled(false);
		nuevo.setEnabled(false);
	}

	public JPanel getPanelCentral() {
		return panelCentral;
	}

	public Dimension getTamanoMarcoRequerido() {
		return tamanoMarcoRequerido;
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
