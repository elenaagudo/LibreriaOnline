package mvc.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import mvc.controller.Controlador;

public class MenuPrincipal {
	
	private Controlador controlador;
	private Scanner sc;
	private int opcion;

	public MenuPrincipal(Controlador controlador) {
		this.controlador = controlador;
	}
	
	public void getAction() {
		getMenuPrincipal();
		getOption();

		do {
			switch (opcion) {
			case 1:
				VistaAutor vistaAutor = new VistaAutor(controlador);
				vistaAutor.init();
				break;
			case 2:
				VistaCategoria vistaCategoria = new VistaCategoria(controlador);
				vistaCategoria.init();
				break;
			case 3:
				VistaEditorial vistaEditorial = new VistaEditorial(controlador);
				vistaEditorial.init();
				break;
			case 4:
				VistaLibro vistaLibro = new VistaLibro(controlador);
				vistaLibro.init();
				break;
			case 0:
				closeApp();
				break;
			default:
				System.out.println("\nLa opcion tecleada no es correcta.");
				break;
			}
			if (opcion != 0) {
				getMenuPrincipal();
				getOption();
			}
		} while (opcion != 0);

		System.out.println("*****");
		System.out.println("ADIOS.");
		System.exit(0);
	}
	
	public void getMenuPrincipal() {
		System.out.println("Elige una opcion: ");
		System.out.println("1 - Autores");
		System.out.println("2 - Categorias");
		System.out.println("3 - Editoriales");
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
	
}
