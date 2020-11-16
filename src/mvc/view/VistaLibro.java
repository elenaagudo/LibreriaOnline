package mvc.view;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

import mvc.controller.Controlador;
import mvc.model.Categoria;
import mvc.model.Editorial;
import mvc.model.Libro;

public class VistaLibro {

	private Controlador controlador;
	VistaCategoria vistaCategoria;
	VistaEditorial vistaEditorial;
	private Scanner sc;
	private int opcion;

	private String feedback;

	// añadir menu principal para volver atras, vistaCategoria y vistaEditorial para
	// pedirle la lista
	public VistaLibro(Controlador controlador, VistaCategoria vistaCategoria, VistaEditorial vistaEditorial) {
		this.controlador = controlador;
		this.vistaCategoria = vistaCategoria;
		this.vistaEditorial = vistaEditorial;
	}

	public void init() {
		getMenuLibro();
		getOption();
		do {
			switch (opcion) {
			case 1:
				insert();
				break;
			case 2:
				update();
				break;
			case 3:
				list();
				break;
			case 4:
				delete();
				break;
			case 0:

				break;
			default:
				System.out.println("\nLa opcion tecleada no es correcta.");
				break;
			}
			if (opcion != 0) {
				getMenuLibro();
				getOption();
			}
		} while (opcion != 0);
	}

	public void getMenuLibro() {
		System.out.println("**** LIBROS ****");
		System.out.println("Elige una opcion: ");
		System.out.println("1 - Nuevo");
		System.out.println("2 - Editar existente");
		System.out.println("3 - Listar");
		System.out.println("4 - Borrar");
		System.out.println("0 - Menu principal");
		// System.out.println("q - Salir");
	}

	public void getOption() {
		sc = new Scanner(System.in);
		System.out.println("Introduzca una opcion: ");
		try {
			opcion = sc.nextInt();
		} catch (InputMismatchException e) {
			opcion = -1;
		}
	}

	public void insert() {
		System.out.println("ISBN");
		int isbn = sc.nextInt();

		System.out.println("Titulo");
		sc.nextLine();
		String titulo = sc.nextLine();

		System.out.println("Precio");
		double precio = sc.nextDouble();

		System.out.println("Stock");
		int stock = sc.nextInt();

		// listar categoria
		vistaCategoria = new VistaCategoria(controlador);
		vistaCategoria.list();
		System.out.println("Codigo Categoria");
		int codigoCategoria = sc.nextInt();

		// listar editorial
		vistaEditorial = new VistaEditorial(controlador);
		vistaEditorial.list();
		System.out.println("Codigo Editorial");
		int codigoEditorial = sc.nextInt();

		feedback = controlador.insertBook(isbn, titulo, precio, stock, codigoCategoria, codigoEditorial);
		System.out.println(feedback);
	}

	public void update() {
		System.out.println("Editar: EN CONSTRUCCIÓN");
	}

	public void list() {
		Vector<Libro> libros = controlador.listBooks();
		System.out.println("\n***** LISTADO DE LIBROS *****");
		System.out.println("ISBN\tTITULO\t\tPRECIO\tSTOCK\tCATEGORIA\tEDITORIAL");
		for (Libro libro : libros) {
			Vector<Categoria> categoria = Categoria.searchById(libro.getCodigoCategoria());
			Vector<Editorial> editorial = Editorial.searchById(libro.getCodigoEditorial());
			System.out.println(libro.getIsbn() + "\t" + libro.getTitulo() + "\t" + libro.getPrecio() + "\t"
					+ libro.getStock() + "\t" + categoria.get(0).getNombreCategoria() + "\t"
					+ editorial.get(0).getNombreEditorial());
		}
		System.out.println("***** FIN LISTADO LIBROS *****\n");
	}

	public void delete() {
		list();
		System.out.println("Introduce el ISBN del libro que quieres borrar");
		System.out.println("0 - Salir");
		int isbn = 0;
		try {
			isbn = sc.nextInt();
			if (isbn != 0) {
				feedback = controlador.deleteBook(isbn);
				System.out.println(feedback);
			}
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("No es una opcion valida\n");
		}
	}

}
