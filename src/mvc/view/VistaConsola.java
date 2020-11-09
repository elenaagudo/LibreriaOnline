package mvc.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import mvc.controller.Controlador;

public class VistaConsola {

	private Controlador controlador;
	private Scanner sc;
	private int opcion;
	private ResultSet resultado;

	public VistaConsola(Controlador controlador) {
		this.controlador = controlador;
	}

	public void getAccion() {
		getMenu();
		getOption();

		do {
			switch (opcion) {
			case 1:
				listAuthors();
				break;
			case 2:
				listCategories();
				break;
			case 3:
				listEditorials();
				break;
			case 4:
				listBooks();
				break;
			case 0:
				closeApp();
				break;
			default:
				System.out.println("\nLa opcion tecleada no es correcta.");
				break;
			}
			if (opcion != 0) {
				getMenu();
				getOption();
			}
		} while (opcion != 0);

		System.out.println("*****");
		System.out.println("ADIOS.");
		System.exit(0);
	}

	public void getMenu() {
		System.out.println("Elige una opcion: ");
		System.out.println("1 - Autores");
		System.out.println("2 - Editoriales");
		System.out.println("3 - Categorias");
		System.out.println("4 - Libros");
		System.out.println("0- Salir");
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

	public void closeApp() {
		sc.close();
		controlador.close();
	}

	public void listAuthors() {
		resultado = controlador.getAuthors();

		try {
			System.out.println("\n***** LISTADO DE AUTORES *****");
			while (resultado.next()) {
				int codAutor = resultado.getInt(1);
				String nombreAutor = resultado.getString(2);
				System.out.println("Codigo del autor: " + codAutor + " Nombre: " + nombreAutor);
			}
			System.out.println("***** FIN LISTADO AUTORES *****\n");
		} catch (SQLException e) {
			System.out.println("Vista: Error al mostrar los autores");
			e.printStackTrace();
		}
	}

	public void listCategories() {
		resultado = controlador.getCategories();
		try {
			System.out.println("\n***** LISTADO DE CATEGORIAS *****");
			while (resultado.next()) {
				int cod_categoria = resultado.getInt(1);
				String nombre = resultado.getString(2);
				System.out.println("Codigo de la categoria: " + cod_categoria + " Nombre: " + nombre);
			}
			System.out.println("***** FIN LISTADO CATEGORIAS *****\n");
		} catch (SQLException e) {
			System.out.println("Vista: Error al mostrar las categorias");
			e.printStackTrace();
		}
	}

	public void listEditorials() {
		resultado = controlador.getEditorials();
		try {
			System.out.println("\n***** LISTADO DE EDITORIALES *****");
			while (resultado.next()) {
				int cod_editorial = resultado.getInt(1);
				String nombre = resultado.getString(2);
				System.out.println("Codigo de la editorial: " + cod_editorial + " Nombre: " + nombre);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void listBooks() {
		resultado = controlador.getBooks();

		try {
			System.out.println("\n***** LISTADO DE LIBROS *****");
			System.out.println("ISBN\tTITULO\tPRECIO\tSTOCK\tCATEGORIA\tEDITORIAL");
			while (resultado.next()) {
				int isbn = resultado.getInt(1);
				String titulo = resultado.getString(2);
				double precio = resultado.getDouble(3);
				int stock = resultado.getInt(4);
				int cod_categoria = resultado.getInt(5);
				int cod_editorial = resultado.getInt(6);
				System.out.println(isbn + "\t" + titulo + "\t" + precio + "\t" + stock + "\t" + cod_categoria + "\t"
						+ cod_editorial);
			}
			System.out.println("***** FIN LISTADO LIBROS *****\n");
		} catch (SQLException e) {
			System.out.println("Vista: Error al mostrar los libros");
			e.printStackTrace();
		}
	}
}
