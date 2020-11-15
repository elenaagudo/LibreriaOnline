package mvc.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Libro {

	// BEAN
	private int isbn;
	private String titulo;
	private double precio;
	private int stock;
	private int codigoCategoria;
	private int codigoEditorial;

	public Libro() {
		
	}
	
	public Libro(String titulo) {
		this.titulo = titulo;
	}

	// INTEGRACION CON LA BBDD
	private static Statement sentencia;
	private static ResultSet resultado;

	public static void setConnection(Statement sentencia, ResultSet resultado) {
		Libro.sentencia = sentencia;
		Libro.resultado = resultado;
	}

	// METODOS CRUD
	private int retorno;

	// INSERT
	public String insert() {
		return "";
	}

	// UPDATE
	public String update() {
		return "";
	}

	// DELETE
	public String delete() {
		return "";
	}

	// READ
	// read all rows
	public static Vector<Libro> list() {
		
		return null;
	}

	// READ
	// search by id
	public static Vector<Libro> searchById(int isbn) {
		
		return null;
	}

	// READ
	// search by name
	public static Vector<Libro> searchByName(String titulo) {
		
		return null;
	}

	// UTILITIES
	public static Vector<Libro> search(String sql) {
		
		return null;
	}

	public static Vector<Libro> resultSetToVector() {
		
		return null;
	}

}
