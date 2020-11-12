package mvc.view;

//import java.sql.ResultSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

import mvc.controller.Controlador;
import mvc.model.Autor;

public class VistaAutor {

	private Controlador controlador;
	private Scanner sc = new Scanner(System.in);
	private int opcion;

	// private ResultSet resultado;
	private String feedback;

	public VistaAutor(Controlador controlador) {
		this.controlador = controlador;
	}

	public void init() {
		getMenuAutor();
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
				getMenuAutor();
				getOption();
			}
		} while (opcion != 0);
	}

	public void getMenuAutor() {
		System.out.println("**** AUTORES ****");
		System.out.println("Elige una opcion: ");
		System.out.println("1 - Nuevo");
		System.out.println("2 - Editar existente");
		System.out.println("3 - Listar");
		System.out.println("4 - Borrar");
		System.out.println("0 - Menu principal");
		// System.out.println("q - Salir");
	}

	public void getOption() {
		System.out.println("Introduzca una opcion: ");
		try {
			opcion = sc.nextInt();
		} catch (InputMismatchException e) {
			opcion = -1;
		}
	}

	public void insert() {
		System.out.println("Introduce el nombre del autor");
		System.out.println("Maximo 45 caracteres");
		sc.nextLine();
		String nombreAutor = sc.nextLine();
		feedback = controlador.insertAuthor(nombreAutor);
		System.out.println(feedback);
	}

	public void update() {
		list();
		System.out.println("Introduce el codigo del autor que quieres editar");
		int codigoAutor = 0;
		try {
			codigoAutor = sc.nextInt();
			System.out.println("Introduce el nuevo nombre");
			System.out.println("Maximo 45 caracteres");
			sc.nextLine();
			String nombreAutor = sc.nextLine();
			feedback = controlador.updateAuthor(codigoAutor, nombreAutor);
			System.out.println(feedback);
		} catch (InputMismatchException e) {
		}

	}

	public void list() {
		Vector<Autor> autores = controlador.listAuthors();
		System.out.println("\n***** LISTADO DE AUTORES *****");
		for (Autor autor : autores) {
			System.out.println(autor.getCodigoAutor() + " - " + autor.getNombreAutor());
		}
		System.out.println("***** FIN LISTADO AUTORES *****\n");
	}

	public void delete() {
		list();
		System.out.println("Introduce el codigo del autor que quieres borrar");
		System.out.println("0 - Salir");
		int codigoAutor = 0;
		try {
			codigoAutor = sc.nextInt();
			if(codigoAutor != 0) {
				feedback = controlador.deleteAuthor(codigoAutor);
				System.out.println(feedback);
			}
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("No es una opcion valida\n");
		}
	}

}
