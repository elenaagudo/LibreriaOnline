package mvc.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Modelo {

	private Connection conexion;
	private Statement sentencia;
	private ResultSet resultado;
	private Autor autor;
	private Editorial editorial;
	private Categoria categoria;
	//private Libro libro;
	private Vector<Autor> autores;
	private Vector<Editorial> editoriales;
	private Vector<Categoria> categorias;
	//private Vector<Libro> libros;
	private String feedback;

	
	//recibira el servicio, para pasarselo a getService()
	public Modelo() {
		try {
			conexion = ServicioBBDD.getService().getConnection();
			sentencia = conexion.createStatement();
			Autor.setConnection(sentencia, resultado);
			Categoria.setConnection(sentencia, resultado);
			Editorial.setConnection(sentencia, resultado);
		} catch (SQLException e) {
			System.out.println("MODELO: Error al crear la sentencia");
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (resultado != null) {
				resultado.close();
			}
			sentencia.close();
			conexion.close();
		} catch (SQLException e) {
			System.out.println("Modelo: Error al cerrar ResultSet, Statement o Connection");
			e.printStackTrace();
		}
	}
	
	/*
	 * AUTORES
	 * 
	 * */
	public String insertAuthor(String nombreAutor) {
		autor = new Autor(nombreAutor);
		feedback = autor.insert();
		return feedback;
	}
	
	public String updateAuthor(int codigoAutor, String nombreAutor) {
		autores = Autor.searchById(codigoAutor);
		autor = autores.get(0);
		autor.setNombreAutor(nombreAutor);
		feedback = autor.update();
		return feedback;
	}
	
	public String deleteAuthor(int codigoAutor) {
		try {
			autores = Autor.searchById(codigoAutor);
			autor = autores.get(0);
			feedback = autor.delete();
		}catch(ArrayIndexOutOfBoundsException e) {
			feedback = "No existe un autor con ese codigo\n";
		}
		return feedback;
	}
	
	public Vector<Autor> listAuthors() {
		autores = Autor.list();
		return autores;
	}
	
	/*
	 * EDITORIALES
	 * 
	 * */
	public String insertEditorial(String nombreEditorial) {
		editorial = new Editorial(nombreEditorial);
		feedback = editorial.insert();
		return feedback;
	}
	
	public String updateEditorial(int codigoEditorial, String nombreEditorial) {
		editoriales = Editorial.searchById(codigoEditorial);
		editorial = editoriales.get(0);
		editorial.setNombreEditorial(nombreEditorial);
		feedback = editorial.update();
		return feedback;
	}
	
	public String deleteEditorial(int codigoEditorial) {
		try {
			editoriales = Editorial.searchById(codigoEditorial);
			editorial = editoriales.get(0);
			feedback = editorial.delete();
		}catch(ArrayIndexOutOfBoundsException e) {
			feedback = "No existe un autor con ese codigo\n";
		}
		return feedback;
	}
	
	public Vector<Editorial> listEditorials(){
		editoriales = Editorial.list();
		return editoriales;
	}
	
	/*
	 * CATEGORIAS
	 * 
	 * */
	public String insertCategory(String nombreCategoria) {
		categoria = new Categoria(nombreCategoria);
		feedback = categoria.insert();
		return feedback;
	}
	
	public String updateCategory(int codigoCategoria, String nombreCategoria) {
		categorias = Categoria.searchById(codigoCategoria);
		categoria = categorias.get(0);
		categoria.setNombreCategoria(nombreCategoria);
		feedback = categoria.update();
		return feedback;
	}
	
	public String deleteCategory(int codigoCategoria) {
		try {
			categorias = Categoria.searchById(codigoCategoria);
			categoria = categorias.get(0);
			feedback = categoria.delete();
		}catch(ArrayIndexOutOfBoundsException e) {
			feedback = "No existe una categoria con ese codigo\n";
		}
		return feedback;
	}
	
	public Vector<Categoria> listCategories(){
		categorias = Categoria.list();
		return categorias;
	}
	
}
