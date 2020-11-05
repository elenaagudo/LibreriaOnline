package mvc.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import mvc.controller.Controlador;

public class VistaSwing extends WindowAdapter implements ActionListener {

	// textarea y botones
	private Controlador controlador;
	private ResultSet resultado;
	private JTextArea txtArea;
	private JButton listBtn;
	private JButton resetBtn;
	private JButton exitBtn;

	public VistaSwing(Controlador controlador) {
		this.controlador = controlador;

		JFrame frame = new JFrame("Listado autores");
		frame.setLayout(null);
		frame.setBounds(400, 200, 350, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		txtArea = new JTextArea(30, 4);
		listBtn = new JButton("Listar");
		resetBtn = new JButton("Limpiar");
		exitBtn = new JButton("Salir");

		listBtn.addActionListener(this);
		resetBtn.addActionListener(this);
		exitBtn.addActionListener(this);

		frame.add(txtArea);
		frame.add(listBtn);
		frame.add(resetBtn);
		frame.add(exitBtn);

		frame.addWindowListener(this);
		frame.setVisible(true);
	}

	public void windowClosing(WindowEvent ev) {
		closeApp();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// if(e.getActionCommand().equals("terminar"))
		if (e.getSource() == listBtn) {
			listAuthors();
		} else if (e.getSource() == resetBtn) {
			reset();
		} else if (e.getSource() == exitBtn) {
			closeApp();
		}

	}

	public void listAuthors() {
		resultado = controlador.getAuthors();
		try {
			while (resultado.next()) {
				int cod_autor = resultado.getInt(1);
				String nombre = resultado.getString(2);
				txtArea.setText(cod_autor + ": " + nombre);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// reset
	public void reset() {
		txtArea.setText("");
	}

	// closeApp
	public void closeApp() {
		controlador.close();
	}
}
