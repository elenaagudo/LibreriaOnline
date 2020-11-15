package mvc.controller;

import java.util.Vector;

import mvc.model.Autor;
import mvc.model.Categoria;
import mvc.model.Editorial;
import mvc.model.Modelo;

public class Controlador {
	private Modelo modelo;
	private Vector<Autor> autores;
	private Vector<Editorial> editoriales;
	private Vector<Categoria> categorias;
	//private Vector<Libro> libros;
	private String feedback;

	public Controlador(Modelo modelo) {
		this.modelo = modelo;
	}

	public void close() {
		modelo.close();
	}

	// El controlador hace un mapeo de los metodos del modelo

	/*
	 * AUTORES
	 * 
	 */
	public String insertAuthor(String nombreAutor) {
		feedback = modelo.insertAuthor(nombreAutor);
		return feedback;
	}

	public String updateAuthor(int codigoAutor, String nombreAutor) {
		feedback = modelo.updateAuthor(codigoAutor, nombreAutor);
		return feedback;
	}

	public String deleteAuthor(int codigoAutor) {
		feedback = modelo.deleteAuthor(codigoAutor);
		return feedback;
	}

	public Vector<Autor> listAuthors() {
		autores = modelo.listAuthors();
		return autores;
	}

	/*
	 * EDITORIALES
	 * 
	 */
	public String insertEditorial(String nombreEditorial) {
		feedback = modelo.insertEditorial(nombreEditorial);
		return feedback;
	}

	public String updateEditorial(int codigoEditorial, String nombreEditorial) {
		feedback = modelo.updateEditorial(codigoEditorial, nombreEditorial);
		return feedback;
	}

	public String deleteEditorial(int codigoEditorial) {
		feedback = modelo.deleteEditorial(codigoEditorial);
		return feedback;
	}

	public Vector<Editorial> listEditorials() {
		editoriales = modelo.listEditorials();
		return editoriales;
	}

	/*
	 * CATEGORIAS
	 * 
	 */
	public String insertCategory(String nombreCategoria) {
		feedback = modelo.insertCategory(nombreCategoria);
		return feedback;
	}

	public String updateCategory(int codigoCategoria, String nombreCategoria) {
		feedback = modelo.updateCategory(codigoCategoria, nombreCategoria);
		return feedback;
	}
	
	public String deleteCategory(int codigoCategoria) {
		feedback = modelo.deleteCategory(codigoCategoria);
		return feedback;
	}
	
	public Vector<Categoria> listCategories(){
		categorias = modelo.listCategories();
		return categorias;
	}
}
