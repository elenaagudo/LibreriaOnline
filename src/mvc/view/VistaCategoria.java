package mvc.view;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

import mvc.controller.Controlador;
import mvc.model.Categoria;

public class VistaCategoria {

	private Controlador controlador;
	private Scanner sc;
	private int opcion;

	private String feedback;

	public VistaCategoria(Controlador controlador) {
		this.controlador = controlador;
	}

	public void init() {
		getMenuCategoria();
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
				getMenuCategoria();
				getOption();
			}
		} while (opcion != 0);
	}

	public void getMenuCategoria() {
		System.out.println("**** CATEGORIAS ****");
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
		System.out.println("Introduce el nombre de la categoria");
		System.out.println("Maximo 45 caracteres");
		sc.nextLine();
		String nombreCategoria = sc.nextLine();
		feedback = controlador.insertCategory(nombreCategoria);
		System.out.println(feedback);
	}

	public void update() {
		list();
		System.out.println("Introduce el codigo de la categoria que quieres editar");
		int codigoCategoria = 0;
		try {
			codigoCategoria = sc.nextInt();
			System.out.println("Introduce el nuevo nombre");
			System.out.println("Maximo 45 caracteres");
			sc.nextLine();
			String nombreCategoria = sc.nextLine();
			feedback = controlador.updateCategory(codigoCategoria, nombreCategoria);
			System.out.println(feedback);
		} catch (InputMismatchException e) {
		}
	}

	public void list() {
		Vector<Categoria> categorias = controlador.listCategories();
		System.out.println("\n***** LISTADO DE CATEGORIAS *****");
		for (Categoria categoria : categorias) {
			System.out.println(categoria.getCodigoCategoria() + " - " + categoria.getNombreCategoria());
		}
		System.out.println("***** FIN LISTADO CATEGORIAS *****\n");
	}

	public void delete() {
		list();
		System.out.println("Introduce el codigo de la categoria que quieres borrar");
		System.out.println("0 - Salir");
		int codigoCategoria = 0;
		try {
			codigoCategoria = sc.nextInt();
			if(codigoCategoria != 0) {
				feedback = controlador.deleteCategory(codigoCategoria);
				System.out.println(feedback);
			}
		} catch (InputMismatchException e) {
			sc.nextLine();
			System.out.println("No es una opcion valida\n");
		}
	}
	
}
