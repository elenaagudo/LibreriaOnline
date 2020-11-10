package mvc.view;

import java.sql.ResultSet;
import java.util.InputMismatchException;
import java.util.Scanner;

import mvc.controller.Controlador;

public class VistaLibro {

	private Controlador controlador;
	private Scanner sc;
	private int opcion;

	private ResultSet resultado;

	public VistaLibro(Controlador controlador) {
		this.controlador = controlador;
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
		System.out.println("Insertar: EN CONSTRUCCIÓN");
	}

	public void update() {
		System.out.println("Editar: EN CONSTRUCCIÓN");
	}

	public void list() {
		System.out.println("Listar: EN CONSTRUCCIÓN");
	}

	public void delete() {
		System.out.println("Borrar: EN CONSTRUCCIÓN");
	}
	
}
