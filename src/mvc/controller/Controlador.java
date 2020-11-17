package mvc.controller;

import java.util.Vector;

import mvc.model.Autor;
import mvc.model.Categoria;
import mvc.model.Editorial;
import mvc.model.Libro;
import mvc.model.Modelo;

public class Controlador {
	private Modelo modelo;
	private Vector<Autor> autores;
	private Vector<Editorial> editoriales;
	private Vector<Categoria> categorias;
	private Vector<Libro> libros;
	// private Vector<Libro> libros;
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

	public Vector<Categoria> listCategories() {
		categorias = modelo.listCategories();
		return categorias;
	}

	/*
	 * LIBROS
	 * 
	 */
	public String insertBook(int isbn, String titulo, double precio, int stock, int codigoCategoria,
			int codigoEditorial) {
		feedback = modelo.insertBook(isbn, titulo, precio, stock, codigoCategoria, codigoEditorial);
		return feedback;
	}

	// UPDATE isbn
	public String updateBookIsbn(int isbn, int isbnNuevo) {
		feedback = modelo.updateBookIsbn(isbn, isbnNuevo);
		return feedback;
	}

	// UPDATE title
	public String updateBookTitle(int isbn, String titulo) {
		feedback = modelo.updateBookTitle(isbn, titulo);
		return feedback;
	}

	// UPDATE price
	public String updateBookPrice(int isbn, double precio) {
		feedback = modelo.updateBookPrice(isbn, precio);
		return feedback;
	}

	// UPDATE stock
	public String updateBookStock(int isbn, int stock) {
		feedback = modelo.updateBookStock(isbn, stock);
		return feedback;
	}

	// UPDATE category
	public String updateBookCategory(int isbn, int codigoCategoria) {
		feedback = modelo.updateBookCategory(isbn, codigoCategoria);
		return feedback;
	}

	// UPDATE editorial
	public String updateBookEditorial(int isbn, int codigoEditorial) {
		feedback = modelo.updateBookEditorial(isbn, codigoEditorial);
		return feedback;
	}

	public String deleteBook(int isbn) {
		feedback = modelo.deleteBook(isbn);
		return feedback;
	}

	public Vector<Libro> listBooks() {
		libros = modelo.listBooks();
		return libros;
	}

}
