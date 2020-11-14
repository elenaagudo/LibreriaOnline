package mvc.view;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

import mvc.controller.Controlador;
import mvc.model.Editorial;

public class VistaEditorial {
	private Controlador controlador;
	private Scanner sc = new Scanner(System.in);
	private int opcion;

	private String feedback;

	public VistaEditorial(Controlador controlador) {
		this.controlador = controlador;
	}

	public void init() {
		getMenuEditorial();
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
				getMenuEditorial();
				getOption();
			}
		} while (opcion != 0);
	}

	public void getMenuEditorial() {
		System.out.println("**** EDITORIALES ****");
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
		System.out.println("Introduce el nombre de la editorial");
		System.out.println("Maximo 45 caracteres");
		sc.nextLine();
		String nombreEditorial = sc.nextLine();
		feedback = controlador.insertEditorial(nombreEditorial);
		System.out.println(feedback);
	}

	public void update() {
		list();
		System.out.println("Introduce el codigo de la editorial que quieres editar");
		int codigoEditorial = 0;
		try {
			codigoEditorial = sc.nextInt();
			System.out.println("Introduce el nuevo nombre");
			System.out.println("Maximo 45 caracteres");
			sc.nextLine();
			String nombreEditorial = sc.nextLine();
			feedback = controlador.updateEditorial(codigoEditorial, nombreEditorial);
			System.out.println(feedback);
		} catch (InputMismatchException e) {
		}
	}

	public void list() {
		Vector<Editorial> editoriales = controlador.listEditorials();
		System.out.println("\n***** LISTADO DE EDITORIALES *****");
		for (Editorial editorial : editoriales) {
			System.out.println(editorial.getCodigoEditorial() + " - " + editorial.getNombreEditorial());
		}
		System.out.println("***** FIN LISTADO EDITORIALES *****\n");
	}

	public void delete() {
		list();
		System.out.println("Introduce el codigo de la editorial que quieres borrar");
		System.out.println("0 - Salir");
		int codigoEditorial = 0;
		try {
			codigoEditorial = sc.nextInt();
			if(codigoEditorial != 0) {
				feedback = controlador.deleteEditorial(codigoEditorial);
				System.out.println(feedback);
			}
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("No es una opcion valida\n");
		}
	}

}
