package mvc.swingview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import mvc.controller.Controlador;

public class VistaCategoriaSwing
		implements ActionListener, KeyListener, ListSelectionListener, TableModelListener, MouseListener {

	private Controlador controlador;
	private JFrame ventana;
	private JLabel tituloFuncion = new JLabel("MANTENIMIENTO DE CATEGORIAS");
	private JLabel etiquetaNuevo = new JLabel("Nueva categoria: ");
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private ListSelectionModel seleccionTabla;
	private JButton nuevo;
	private JButton borrar;
	private JButton editar;
	private JTextField textoNuevo;
	private JButton cancelarNuevo;
	private JButton aceptarNuevo;
	private String valorInicialCeldaAUX;
	private boolean modoEdicion = false;
	private JPanel panelCentral;
	private Dimension tamanoMarcoRequerido;

	public VistaCategoriaSwing(JFrame ventana, Controlador controlador) {
		this.ventana = ventana;
		this.controlador = controlador;

		// creacion de la tabla
		// sobrescritura del metodo isCellEditable para indicarle a la vista de la tabla
		// lo que es y no es editable
		modeloTabla = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return columnIndex == 1;
			}
		};

		tabla = new JTable(modeloTabla);

		// el modo de seleccion de la tabla solo permite seleccionar una fila
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Registro de los manejadores de eventos para la tabla: seleccion y cambio de
		// datos
		seleccionTabla = tabla.getSelectionModel();
		seleccionTabla.addListSelectionListener(this);
		modeloTabla.addTableModelListener(this);
		tabla.addMouseListener(this);
		// wrapper scrolling
		JScrollPane scroll = new JScrollPane(tabla);

		// creacion de los botones para acciones CRUD
		editar = new JButton("Editar");
		borrar = new JButton("Borrar");
		nuevo = new JButton("Nuevo");
		// estado inicial de los botones del panel
		restablecerPanelCRUD();
		// creacion del panel para los botones CRUD
		JPanel botonesCRUD = new JPanel(new GridLayout(3, 1));
		botonesCRUD.add(editar);
		botonesCRUD.add(borrar);
		botonesCRUD.add(nuevo);

		// se añaden los manejadores de eventos para los botones CRUD
		editar.addActionListener(this);
		borrar.addActionListener(this);
		nuevo.addActionListener(this);

		// creacion de los componentes para edicion
		textoNuevo = new JTextField(45);
		cancelarNuevo = new JButton("Cancelar");
		aceptarNuevo = new JButton("Aceptar");
		deshabilitarPanelEdicion();
		// Paneles para edicion
		JPanel panelEdicion = new JPanel(new BorderLayout());
		JPanel panelEdicionNuevo = new JPanel();
		// BoxLayout ofrece mas control sobre la disposicion de los componentes
		panelEdicionNuevo.setLayout(new BoxLayout(panelEdicionNuevo, BoxLayout.LINE_AXIS));
		panelEdicionNuevo.add(textoNuevo);
		panelEdicionNuevo.add(cancelarNuevo);
		panelEdicionNuevo.add(aceptarNuevo);
		// distribucion de los controles
		panelEdicion.add(new JPanel(), BorderLayout.NORTH);
		panelEdicion.add(etiquetaNuevo, BorderLayout.CENTER);
		panelEdicion.add(panelEdicionNuevo, BorderLayout.SOUTH);

		// se añaden manejadores de eventos para la edicion
		textoNuevo.addKeyListener(this);
		cancelarNuevo.addActionListener(this);
		aceptarNuevo.addActionListener(this);

		// panel que agrupa todos los componentes para esta funcion
		panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());
		// añadir componentes al panel central
		panelCentral.add(tituloFuncion, BorderLayout.NORTH);
		panelCentral.add(scroll, BorderLayout.CENTER);
		panelCentral.add(botonesCRUD, BorderLayout.EAST);
		panelCentral.add(panelEdicion, BorderLayout.SOUTH);

		// tamaño del marco requerido
		tamanoMarcoRequerido = new Dimension(710, 250);
		ventana.setSize(tamanoMarcoRequerido);

		// se actualiza el marco principal (contenedor JFrame)
		ventana.add(panelCentral, BorderLayout.CENTER);

		// valida y repinta el contenedor despues de cambios de layout
		ventana.validate();
		ventana.repaint();

		// carga de datos en la tabla
		cargarDatosEnTabla(modeloTabla);

	}

	public void cargarDatosEnTabla(DefaultTableModel modelo) {
		try {
			ResultSet datos = controlador.obtenerDatosMasMetadatosCategoria();
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

	// EVENTOS TABLA
	// habilita botones CRUD se hay alguna fila seleccionada en la tabla
	// los indices para filas y columnas empiezan en 0
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if (tabla.getSelectedRow() == -1) {
			editar.setEnabled(false);
			borrar.setEnabled(false);
		} else {
			editar.setEnabled(true);
			borrar.setEnabled(true);
		}
	}

	// con este evento se controla si se han producido cambios en alguna celda, con
	// la colaboracion del evento de raton: mouseClicked, que captura el valor
	// inicial de la celda.
	// Este if es porque en la carga inicial de la tabla se lanza este evento con
	// valores -1, que provoca una excepcion
	@Override
	public void tableChanged(TableModelEvent tme) {
		if (tme.getFirstRow() >= 0 && tme.getColumn() >= 0) {
			// este if controla que no se realice dos veces el mismo proceso
			if (!modoEdicion) {
				String valorFinalCeldaAUX = (String) tabla.getValueAt(tme.getFirstRow(), tme.getColumn());
				if (valorInicialCeldaAUX.compareTo(valorFinalCeldaAUX) != 0) {
					modificarRegistro((int) tabla.getValueAt(tme.getFirstRow(), 0), valorFinalCeldaAUX,
							tme.getFirstRow(), tme.getColumn());
				}
			}
		}
	}

	// EVENTOS DE RATON
	@Override
	public void mouseClicked(MouseEvent arg0) {
		valorInicialCeldaAUX = (String) tabla.getValueAt(tabla.getSelectedRow(), 1);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	// EVENTOS DE TECLADO
	@Override
	public void keyPressed(KeyEvent ke) {
		if (KeyEvent.getKeyText(ke.getKeyCode()).equals("Enter")) {
			if (!modoEdicion) {
				registrarNuevo();
				deshabilitarPanelEdicion();
				habilitarPanelCRUD();
				tabla.setEnabled(true);
			} else {
				modificarRegistro();
				deshabilitarPanelEdicion();
				etiquetaNuevo.setText("Nuevo autor: ");
				aceptarNuevo.setActionCommand("Aceptar");
				habilitarPanelCRUD();
				tabla.setEnabled(true);
				modoEdicion = false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		switch (ae.getActionCommand()) {
		case "Nuevo":
			tabla.setEnabled(false);
			tabla.clearSelection();
			deshabilitarPanelCRUD();
			habilitarPanelEdicion();
			break;
		case "Cancelar":
			deshabilitarPanelEdicion();
			modoEdicion = false;
			etiquetaNuevo.setText("Nuevo autor: ");
			aceptarNuevo.setActionCommand("Aceptar");
			restablecerPanelCRUD();
			tabla.setEnabled(true);
			tabla.clearSelection();
			break;
		case "Aceptar":
			registrarNuevo();
			deshabilitarPanelEdicion();
			habilitarPanelCRUD();
			tabla.setEnabled(true);
			break;
		case "Borrar":
			borrarRegistro();
			break;
		case "Editar":
			modoEdicion = true;
			tabla.setEnabled(false);
			deshabilitarPanelCRUD();
			etiquetaNuevo.setText("Editar autor: ");
			textoNuevo.setText((String) tabla.getValueAt(tabla.getSelectedRow(), 1));
			habilitarPanelEdicion();
			aceptarNuevo.setActionCommand("Modificar");
			break;
		case "Modificar":
			modificarRegistro();
			deshabilitarPanelEdicion();
			etiquetaNuevo.setText("Nuevo autor: ");
			aceptarNuevo.setActionCommand("Aceptar");
			habilitarPanelCRUD();
			tabla.setEnabled(true);
			modoEdicion = false;
			break;
		}
	}

	// UTILITIES
	// habilita/deshabilita el panel de edicion
		private void habilitarPanelEdicion() {
			textoNuevo.setEnabled(true);
			textoNuevo.requestFocus();
			cancelarNuevo.setEnabled(true);
			aceptarNuevo.setEnabled(true);
		}

		private void deshabilitarPanelEdicion() {
			textoNuevo.setText("");
			textoNuevo.setEnabled(false);
			cancelarNuevo.setEnabled(false);
			aceptarNuevo.setEnabled(false);
		}

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
	
		// metodo para informar al usuario
		private void informarUsuario(String mensaje) {
			JOptionPane.showMessageDialog(ventana, mensaje, tituloFuncion.getText(), JOptionPane.INFORMATION_MESSAGE);
		}

		// metodo para preguntar al usuario
		private int preguntarUsuario(String pregunta) {
			int confirmacion = JOptionPane.showConfirmDialog(ventana, pregunta);
			return confirmacion;
		}

		// metodo que proporciona el panel centar a VistaMenuSwing
		public JPanel getPanelCentral() {
			return panelCentral;
		}

		// proporciona el tamaño del marco necesario a VistaMenuSwing
		public Dimension getTamanoMarcoRequerido() {
			return tamanoMarcoRequerido;
		}
		
		// metodos CRUD
		// modifica el registro seleccionado
		private void modificarRegistro() {
			String feedback = controlador.updateCategory((int) tabla.getValueAt(tabla.getSelectedRow(), 0),
					textoNuevo.getText());
			if (feedback.equals("Edición de categoria correcta.")) {
				modeloTabla.setValueAt(textoNuevo.getText(), tabla.getSelectedRow(), 1);
			}
			informarUsuario(feedback);
		}

		// cuando la modificacion se produce en la propia tabla
		private void modificarRegistro(int claveModificacion, String valorModificacion, int fila, int columna) {
			String pregunta = "Ha modificado el nombre de la categoria cuyo codigo es: " + claveModificacion + "\n¿Está seguro?";
			String feedback = "No se ha modificado la categoria.";
			if (preguntarUsuario(pregunta) == JOptionPane.YES_OPTION) {
				feedback = controlador.updateCategory(claveModificacion, valorModificacion);
			} else {
				tabla.setValueAt(valorInicialCeldaAUX, fila, columna);
			}
			informarUsuario(feedback);
		}
		
		// se borra el registro seleccionado
		private void borrarRegistro() {
			// preparar pregunta para el usuario
			String pregunta = "Va a borrar la categoria con codigo " + tabla.getValueAt(tabla.getSelectedRow(), 0)
					+ "\n¿Está seguro?";
			String feedback = "No se ha borrado la categoria.";
			if (preguntarUsuario(pregunta) == JOptionPane.YES_OPTION) {
				feedback = controlador.deleteCategory((int) tabla.getValueAt(tabla.getSelectedRow(), 0));
				if (feedback.equals("Borrado de categoria correcto.")) {
					modeloTabla.removeRow(tabla.getSelectedRow());
				}
			}
			informarUsuario(feedback);
		}
		
		// guardar un registro nuevo
		private void registrarNuevo() {
			// se solicita al controlador que registre los datos y se informa del resultado
			// al usuario
			informarUsuario(controlador.insertCategory(textoNuevo.getText()));
			// se limpia la tabla de datos
			modeloTabla.setRowCount(0);
			modeloTabla.setColumnCount(0);
			// se carga la tabla que ya contiene los datos nuevos y se muestra el ultimo
			// registro añadido
			cargarDatosEnTabla(modeloTabla);
			// se muestra la ultima entrada en la tabla
			tabla.setRowSelectionInterval(tabla.getRowCount() - 1, tabla.getRowCount() - 1);
			tabla.scrollRectToVisible(tabla.getCellRect(tabla.getRowCount() - 1, 0, true));
		}
	
}
