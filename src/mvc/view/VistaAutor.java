package mvc.view;

import java.sql.ResultSet;
import java.util.InputMismatchException;
import java.util.Scanner;

import mvc.controller.Controlador;

public class VistaAutor {

	private Controlador controlador;
	private Scanner sc = new Scanner(System.in);
	private int opcion;

	private ResultSet resultado;

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
		System.out.println("Insertar: EN CONSTRUCCI�N");
		insertForm();
		String nombreAutor = sc.nextLine();
		controlador.insertAuthor(nombreAutor);
	}

	public void insertForm() {
		System.out.println("Introduce el nombre del autor");
		System.out.println("Maximo 45 caracteres");
	}

	public void update() {
		System.out.println("Editar: EN CONSTRUCCI�N");
	}

	public void list() {
		System.out.println("Listar: EN CONSTRUCCI�N");
	}

	public void delete() {
		System.out.println("Borrar: EN CONSTRUCCI�N");
	}

}