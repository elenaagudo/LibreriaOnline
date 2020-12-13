package mvc.swingview;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import mvc.controller.Controlador;

public class VistaLibroSwing implements WindowListener {

	private Controlador controlador;

	public VistaLibroSwing(Controlador controlador) {
		this.controlador = controlador;

		JFrame ventana = new JFrame("LIBROS");
		ventana.setSize(650, 200);
		ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel titulo = new JLabel("LISTADO DE LIBROS");
		DefaultTableModel modeloTabla = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return true;
			}
		};

		JTable tabla = new JTable(modeloTabla);
		JScrollPane scroll = new JScrollPane(tabla);
		JPanel panelIzquierda = new JPanel();
		JPanel panelDerecha = new JPanel();
		JPanel panelSur = new JPanel();

		ventana.addWindowListener(this);

		ventana.add(titulo, BorderLayout.NORTH);
		ventana.add(scroll, BorderLayout.CENTER);
		ventana.add(panelIzquierda, BorderLayout.WEST);
		ventana.add(panelDerecha, BorderLayout.EAST);
		ventana.add(panelSur, BorderLayout.SOUTH);

		ventana.setVisible(true);

		cargarDatosEnTabla(modeloTabla);
	}

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

	@Override
	public void windowClosing(WindowEvent arg0) {
		controlador.close();
		System.out.println("Adios!!");
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}

}
