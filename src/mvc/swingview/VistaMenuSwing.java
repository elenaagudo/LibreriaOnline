package mvc.swingview;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import mvc.controller.Controlador;

public class VistaMenuSwing implements WindowListener, ActionListener {

	private Controlador controlador;
	private JFrame ventana;
	private VistaAutorSwing panelAutor;
	private VistaEditorialSwing panelEditorial;
	private VistaCategoriaSwing panelCategoria;
	private VistaLibroSwing panelLibro;
	private JMenuBar menuBar; // Contenedor del menu principal
	private JMenu menuApp;
	private JMenuItem itemAppAbout, itemAppTerminar;
	private JMenu menuAutor; // Menu de autor
	private JMenuItem itemAutorMantenimiento, itemAutorListar;
	private JMenu menuEditorial; // Menu editorial
	private JMenuItem itemEditorialMantenimiento;
	private JMenu menuCategoria; // Menu categoria
	private JMenuItem itemCategoriaMantenimiento;
	private JMenu menuAlmacen; // Menu almacen
	private JMenuItem itemAlmacenMantenimiento;
	private JMenu menuCatalogo; // Menu catalogo
	private JMenuItem itemCatalogoMantenimiento;
	private JMenu menuVenta; // Menu ventas
	private JMenuItem itemVentaInforme;
	private JMenuItem itemMenuAUX; // Almacena el item seleccionado

	public VistaMenuSwing(Controlador controlador) {
		this.controlador = controlador;

		ventana = new JFrame("Libreria Online");
		ventana.setLayout(new BorderLayout());
		ventana.setSize(710, 250);
		ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// se crea la barra de menu y se añade al frame
		menuBar = new JMenuBar();

		// creacion del menu principal de APLICACION
		menuApp = new JMenu("Aplicacion");
		itemAppAbout = new JMenuItem("Acerca de");
		itemAppTerminar = new JMenuItem("Terminar");
		menuApp.add(itemAppAbout);
		menuApp.add(itemAppTerminar);
		menuBar.add(menuApp);

		// creacion de iconos y shortcuts para opciones de menu APLICACION
		ImageIcon acercaDeIcon = new ImageIcon("src/iconos/about.png");
		itemAppAbout.setIcon(acercaDeIcon);
		KeyStroke ctrlBKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK);
		itemAppAbout.setAccelerator(ctrlBKeyStroke);
		// ------------------------------------------
		ImageIcon terminarIcon = new ImageIcon("src/iconos/terminar.png");
		itemAppTerminar.setIcon(terminarIcon);
		KeyStroke ctrlTKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK);
		itemAppTerminar.setAccelerator(ctrlTKeyStroke);

		// creacion de menu principal AUTOR
		menuAutor = new JMenu("Autor");
		itemAutorMantenimiento = new JMenuItem("Mantener autores");
		itemAutorListar = new JMenuItem("Listar");
		menuAutor.add(itemAutorMantenimiento);
		menuAutor.add(itemAutorListar);
		menuBar.add(menuAutor);

		// creacion de iconos y shortcuts para opciones de menu AUTOR
		ImageIcon autorIcon = new ImageIcon("src/iconos/autor.png");
		itemAutorMantenimiento.setIcon(autorIcon);
		KeyStroke ctrlAKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK);
		itemAutorMantenimiento.setAccelerator(ctrlAKeyStroke);
		// --------------------------------------------
		ImageIcon listarIcon = new ImageIcon("src/iconos/listar.png");
		itemAutorListar.setIcon(listarIcon);
		KeyStroke ctrlIKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK);
		itemAutorListar.setAccelerator(ctrlIKeyStroke);

		// creacion de menu principal EDITORIAL
		menuEditorial = new JMenu("Editorial");
		itemEditorialMantenimiento = new JMenuItem("Mantener editoriales");
		menuEditorial.add(itemEditorialMantenimiento);
		menuBar.add(menuEditorial);

		// creacion de iconos y shortcuts para opciones de menu EDITORIAL
		ImageIcon editorialIcon = new ImageIcon("src/iconos/editorial.png");
		itemEditorialMantenimiento.setIcon(editorialIcon);
		KeyStroke ctrlEKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK);
		itemEditorialMantenimiento.setAccelerator(ctrlEKeyStroke);

		// creacion de menu principal CATEGORIA
		menuCategoria = new JMenu("Categoria");
		itemCategoriaMantenimiento = new JMenuItem("Mantener categorías");
		menuCategoria.add(itemCategoriaMantenimiento);
		menuBar.add(menuCategoria);

		// creacion de iconos y shortcuts para opciones de menu CATEGORIA
		ImageIcon categoriaIcon = new ImageIcon("src/iconos/categoria.png");
		itemCategoriaMantenimiento.setIcon(categoriaIcon);
		KeyStroke ctrlCKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK);
		itemCategoriaMantenimiento.setAccelerator(ctrlCKeyStroke);

		// Menu ALMACEN
		menuAlmacen = new JMenu("Almacen");
		itemAlmacenMantenimiento = new JMenuItem("Mantener libros");
		menuAlmacen.add(itemAlmacenMantenimiento);
		menuBar.add(menuAlmacen);

		ImageIcon almacenIcon = new ImageIcon("src/iconos/almacen.png");
		itemAlmacenMantenimiento.setIcon(almacenIcon);
		KeyStroke ctrlLKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK);
		itemAlmacenMantenimiento.setAccelerator(ctrlLKeyStroke);

		// Menu CATALOGO
		menuCatalogo = new JMenu("Catalogo");
		itemCatalogoMantenimiento = new JMenuItem("Mantener catalogo");
		menuCatalogo.add(itemCatalogoMantenimiento);
		menuBar.add(menuCatalogo);

		ImageIcon catalogoIcon = new ImageIcon("src/iconos/catalogo.png");
		itemCatalogoMantenimiento.setIcon(catalogoIcon);
		KeyStroke ctrlOKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK);
		itemCatalogoMantenimiento.setAccelerator(ctrlOKeyStroke);

		// Menu VENTA
		menuVenta = new JMenu("Venta");
		itemVentaInforme = new JMenuItem("Informe de ventas");
		menuVenta.add(itemVentaInforme);
		menuBar.add(menuVenta);

		ImageIcon ventaIcon = new ImageIcon("src/iconos/venta.png");
		itemVentaInforme.setIcon(ventaIcon);
		KeyStroke ctrlVKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK);
		itemVentaInforme.setAccelerator(ctrlVKeyStroke);

		// se añade el menu al frame
		ventana.setJMenuBar(menuBar);

		// se añaden escuchadores
		ventana.addWindowListener(this);
		itemAppAbout.addActionListener(this);
		itemAppTerminar.addActionListener(this);
		itemAutorMantenimiento.addActionListener(this);
		itemAutorListar.addActionListener(this);
		itemEditorialMantenimiento.addActionListener(this);
		itemCategoriaMantenimiento.addActionListener(this);
		itemAlmacenMantenimiento.addActionListener(this);
		itemCatalogoMantenimiento.addActionListener(this);
		itemVentaInforme.addActionListener(this);

		// se crean los paneles
		JPanel panelSuperior = new JPanel();
		JPanel panelIzquierdo = new JPanel();
		JPanel panelDerecho = new JPanel();
		JPanel panelInferior = new JPanel();

		// se añaden los paneles a content pane
		ventana.add(panelSuperior, BorderLayout.NORTH);
		ventana.add(panelIzquierdo, BorderLayout.WEST);
		ventana.add(panelDerecho, BorderLayout.EAST);
		ventana.add(panelInferior, BorderLayout.SOUTH);

		// se visualiza el frame
		ventana.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// se recupera el action command del item que lanza el evento
		String actionCommand = e.getActionCommand();

		if (actionCommand.equals("Terminar")) {
			controlador.close();
			System.out.println("**ADIOS**");
			System.exit(0);
		}

		if (actionCommand.equals("Mantener autores")) {
			if (itemMenuAUX != null) {
				itemMenuAUX.setEnabled(true);
			}
			itemMenuAUX = itemAutorMantenimiento;
			itemAutorMantenimiento.setEnabled(false);

			// si habia algo cargado en la parte central de la ventana se quita
			if (ventana.getContentPane().getComponentCount() == 5) {
				ventana.getContentPane().remove(4);
			}

			// si es la primera vez que entramos en la funcion se crea, si no, se reutiliza
			if (panelAutor == null) {
				panelAutor = new VistaAutorSwing(ventana, controlador);
			} else {
				ventana.add(panelAutor.getPanelCentral(), BorderLayout.CENTER);
				ventana.setSize(panelAutor.getTamanoMarcoRequerido());
				ventana.validate();
				ventana.repaint();
			}
		}

		if (actionCommand.equals("Mantener editoriales")) {
			if (itemMenuAUX != null) {
				itemMenuAUX.setEnabled(true);
			}
			itemMenuAUX = itemEditorialMantenimiento;
			itemEditorialMantenimiento.setEnabled(false);

			if (ventana.getContentPane().getComponentCount() == 5) {
				ventana.getContentPane().remove(4);
			}

			if (panelEditorial == null) {
				panelEditorial = new VistaEditorialSwing(ventana, controlador);
			} else {
				ventana.add(panelEditorial.getPanelCentral(), BorderLayout.CENTER);
				ventana.setSize(panelEditorial.getTamanoMarcoRequerido());
				ventana.validate();
				ventana.repaint();
			}
		}

		if (actionCommand.equals("Mantener categorías")) {
			if (itemMenuAUX != null) {
				itemMenuAUX.setEnabled(true);
			}
			itemMenuAUX = itemCategoriaMantenimiento;
			itemCategoriaMantenimiento.setEnabled(false);

			if (ventana.getContentPane().getComponentCount() == 5) {
				ventana.getContentPane().remove(4);
			}

			if (panelCategoria == null) {
				panelCategoria = new VistaCategoriaSwing(ventana, controlador);
			} else {
				ventana.add(panelCategoria.getPanelCentral(), BorderLayout.CENTER);
				ventana.setSize(panelCategoria.getTamanoMarcoRequerido());
				ventana.validate();
				ventana.repaint();
			}
		}

		if (actionCommand.equals("Mantener libros")) {
			if (itemMenuAUX != null) {
				itemMenuAUX.setEnabled(true);
			}
			itemMenuAUX = itemAlmacenMantenimiento;
			itemAlmacenMantenimiento.setEnabled(false);

			if (ventana.getContentPane().getComponentCount() == 5) {
				ventana.getContentPane().remove(4);
			}

			if (panelLibro == null) {
				// panelLibro = new VistaLibroSwing(ventana, controlador);
			} else {
				// ventana.add(panelLibro.getPanelCentral(), BorderLayout.CENTER);
				// ventana.setSize(panelLibro.getTamanoMarcoRequerido());
				ventana.validate();
				ventana.repaint();
			}
		}
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		controlador.close();
		System.out.println("ADIOS");
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
