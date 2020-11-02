package mvc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Vista {

	private Controlador controlador;
	private Scanner sc;
	private int opcion;
	private ResultSet resultado;

	public Vista(Controlador controlador) {
		this.controlador = controlador;
	}

	public void getAccion() {
		getMenu();
		getOpcion();

		do {
			switch (opcion) {
			case 1:
				listarAutores();
				break;
			case 2:
				terminarAplicacion();
				break;
			default:
				System.out.println("\nLa opcion tecleada no es correcta.");
				break;
			}
			if (opcion != 2) {
				getMenu();
				getOpcion();
			}
		} while (opcion != 2);

		System.out.println("*****");
		System.out.println("ADIOS.");
		System.exit(0);
	}

	public void getMenu() {
		System.out.println("Elige una opcion: ");
		System.out.println("1 - Listado autores");
		System.out.println("2- Salir");
	}

	public void getOpcion() {
		sc = new Scanner(System.in);
		System.out.println("Introduzca una opcion: ");
		try {
			opcion = sc.nextInt();
		} catch (InputMismatchException e) {
			opcion = -1;
		}
	}

	public void terminarAplicacion() {
		sc.close();
		controlador.terminar();
	}

	public void listarAutores() {
		resultado = controlador.obtenerAutores();

		try {
			System.out.println("\n***** LISTADO DE AUTORES *****");
			while (resultado.next()) {
				int codAutor = resultado.getInt(1);
				String nombreAutor = resultado.getString(2);
				System.out.println("Codigo del autor: " + codAutor + " Nombre: " + nombreAutor);
			}
			System.out.println("***** FIN LISTADO AUTORES *****\n");
		} catch (SQLException e) {
			System.out.println("Vista: Error al mostrar los resultados");
			e.printStackTrace();
		}
	}
}
