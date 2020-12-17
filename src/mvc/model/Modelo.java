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
	private Libro libro;
	private Vector<Autor> autores;
	private Vector<Editorial> editoriales;
	private Vector<Categoria> categorias;
	private Vector<Libro> libros;
	private String feedback;

	// recibira el servicio, para pasarselo a getService()
	public Modelo() {
		try {
			conexion = ServicioBBDD.getService().getConnection();
			sentencia = conexion.createStatement();
			resultado = null;
			Autor.setConnection(sentencia, resultado);
			Categoria.setConnection(sentencia, resultado);
			Editorial.setConnection(sentencia, resultado);
			Libro.setConnection(sentencia, resultado);
			AutorLibro.setConnection(sentencia, resultado);
		} catch (SQLException e) {
			System.out.println("MODELO: Error al crear recursos de BBDD");
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
	 */
	public String insertAuthor(String nombreAutor) {
		autor = new Autor(nombreAutor);
		feedback = autor.insert();
		return feedback;
	}

	public String updateAuthor(int codigoAutor, String nombreAutor) {
		try {
			autores = Autor.searchById(codigoAutor);
			autor = autores.get(0);
			autor.setNombreAutor(nombreAutor);
			feedback = autor.update();
		} catch (ArrayIndexOutOfBoundsException e) {
			feedback = "No existe un autor con ese codigo\n";
		}
		return feedback;
	}

	public String deleteAuthor(int codigoAutor) {
		try {
			autores = Autor.searchById(codigoAutor);
			autor = autores.get(0);
			feedback = autor.delete();
		} catch (ArrayIndexOutOfBoundsException e) {
			feedback = "No existe un autor con ese codigo\n";
		}
		return feedback;
	}

	public Vector<Autor> listAuthors() {
		autores = Autor.list();
		return autores;
	}

	public Vector<Autor> searchAuthorById(int codigoAutor) {
		autores = Autor.searchById(codigoAutor);
		return autores;
	}

	// Ampliacion para Swing
	public ResultSet obtenerDatosMasMetadatosAutor() {
		resultado = Autor.obtenerDatosMasMetadatosAutor();
		return resultado;
	}

	/*
	 * EDITORIALES
	 * 
	 */
	public String insertEditorial(String nombreEditorial) {
		editorial = new Editorial(nombreEditorial);
		feedback = editorial.insert();
		return feedback;
	}

	public String updateEditorial(int codigoEditorial, String nombreEditorial) {
		try {
			editoriales = Editorial.searchById(codigoEditorial);
			editorial = editoriales.get(0);
			editorial.setNombreEditorial(nombreEditorial);
			feedback = editorial.update();
		} catch (ArrayIndexOutOfBoundsException e) {
			feedback = "No existe una editorial con ese codigo\n";
		}
		return feedback;
	}

	public String deleteEditorial(int codigoEditorial) {
		try {
			editoriales = Editorial.searchById(codigoEditorial);
			editorial = editoriales.get(0);
			feedback = editorial.delete();
		} catch (ArrayIndexOutOfBoundsException e) {
			feedback = "No existe una editorial con ese codigo\n";
		}
		return feedback;
	}

	public Vector<Editorial> listEditorials() {
		editoriales = Editorial.list();
		return editoriales;
	}

	public Vector<Editorial> searchEditorialById(int codigoEditorial) {
		editoriales = Editorial.searchById(codigoEditorial);
		return editoriales;
	}

	// Ampliacion para Swing
	public ResultSet obtenerDatosMasMetadatosEditorial() {
		resultado = Editorial.obtenerDatosMasMetadatosEditorial();
		return resultado;
	}

	/*
	 * CATEGORIAS
	 * 
	 */
	public String insertCategory(String nombreCategoria) {
		categoria = new Categoria(nombreCategoria);
		feedback = categoria.insert();
		return feedback;
	}

	public String updateCategory(int codigoCategoria, String nombreCategoria) {
		try {
			categorias = Categoria.searchById(codigoCategoria);
			categoria = categorias.get(0);
			categoria.setNombreCategoria(nombreCategoria);
			feedback = categoria.update();
		} catch (ArrayIndexOutOfBoundsException e) {
			feedback = "No existe una categoria con ese codigo\n";
		}
		return feedback;
	}

	public String deleteCategory(int codigoCategoria) {
		try {
			categorias = Categoria.searchById(codigoCategoria);
			categoria = categorias.get(0);
			feedback = categoria.delete();
		} catch (ArrayIndexOutOfBoundsException e) {
			feedback = "No existe una categoria con ese codigo\n";
		}
		return feedback;
	}

	public Vector<Categoria> listCategories() {
		categorias = Categoria.list();
		return categorias;
	}

	public Vector<Categoria> searchCategoryById(int codigoCategoria) {
		categorias = Categoria.searchById(codigoCategoria);
		return categorias;
	}

	// Ampliacion para Swing
	public ResultSet obtenerDatosMasMetadatosCategoria() {
		resultado = Categoria.obtenerDatosMasMetadatosCategoria();
		return resultado;
	}

	/*
	 * LIBROS
	 * 
	 */
	public String insertBook(int isbn, String titulo, double precio, int stock, int codigoCategoria,
			int codigoEditorial) {
		libro = new Libro(isbn, titulo, precio, stock, codigoCategoria, codigoEditorial);
		feedback = libro.insert();
		return feedback;
	}
	
	//UPDATE
	public String updateBook(int isbn, int isbnNuevo, String titulo, double precio, int stock, int codigoCategoria, int codigoEditorial) {
		libros = Libro.searchByIsbn(isbn);
		libro = libros.get(0);
		libro.setIsbn(isbn);
		libro.setTitulo(titulo);
		libro.setPrecio(precio);
		libro.setStock(stock);
		libro.setCodigoCategoria(codigoCategoria);
		libro.setCodigoEditorial(codigoEditorial);
		feedback = libro.update(isbnNuevo);
		return feedback;
	}

	// UPDATE isbn
	public String updateBookIsbn(int isbn, int isbnNuevo) {
		libros = Libro.searchByIsbn(isbn);
		libro = libros.get(0);
		feedback = libro.updateIsbn(isbnNuevo);

		return feedback;
	}

	// UPDATE title
	public String updateBookTitle(int isbn, String titulo) {
		try {
			libros = Libro.searchByIsbn(isbn);
			libro = libros.get(0);
			libro.setTitulo(titulo);
			feedback = libro.updateTitle();
		} catch (ArrayIndexOutOfBoundsException e) {
			feedback = "No existe un libro con ese isbn\n";
		}
		return feedback;
	}

	// UPDATE price
	public String updateBookPrice(int isbn, double precio) {
		try {
			libros = Libro.searchByIsbn(isbn);
			libro = libros.get(0);
			libro.setPrecio(precio);
			feedback = libro.updatePrice();
		} catch (ArrayIndexOutOfBoundsException e) {
			feedback = "No existe un libro con ese isbn\n";
		}
		return feedback;
	}

	// UPDATE stock
	public String updateBookStock(int isbn, int stock) {
		try {
			libros = Libro.searchByIsbn(isbn);
			libro = libros.get(0);
			libro.setStock(stock);
			feedback = libro.updateStock();
		} catch (ArrayIndexOutOfBoundsException e) {
			feedback = "No existe un libro con ese isbn\n";
		}
		return feedback;
	}

	// UPDATE category
	public String updateBookCategory(int isbn, int codigoCategoria) {
		try {
			libros = Libro.searchByIsbn(isbn);
			libro = libros.get(0);
			libro.setCodigoCategoria(codigoCategoria);
			feedback = libro.updateCategory();
		} catch (ArrayIndexOutOfBoundsException e) {
			feedback = "No existe un libro con ese isbn\n";
		}
		return feedback;
	}

	// UPDATE editorial
	public String updateBookEditorial(int isbn, int codigoEditorial) {
		try {
			libros = Libro.searchByIsbn(isbn);
			libro = libros.get(0);
			libro.setCodigoEditorial(codigoEditorial);
			feedback = libro.updateEditorial();
		} catch (ArrayIndexOutOfBoundsException e) {
			feedback = "No existe un libro con ese isbn\n";
		}
		return feedback;
	}

	public String deleteBook(int isbn) {
		try {
			libros = Libro.searchByIsbn(isbn);
			libro = libros.get(0);
			feedback = libro.delete();
		} catch (ArrayIndexOutOfBoundsException e) {
			feedback = "No existe un libro con ese isbn\n";
		}
		return feedback;
	}

	public Vector<Libro> listBooks() {
		libros = Libro.list();
		return libros;
	}

	public Vector<Libro> searchBookByIsbn(int isbn) {
		libros = Libro.searchByIsbn(isbn);
		return libros;
	}

	public Vector<Libro> searchBookByTitle(String titulo) {
		libros = Libro.searchByTitle(titulo);
		return libros;
	}

	public Vector<Libro> searchBookByPrice(double precio) {
		libros = Libro.searchByPrice(precio);
		return libros;
	}

	public Vector<Libro> searchBookByStock(int stock) {
		libros = Libro.searchByStock(stock);
		return libros;
	}

	public Vector<Libro> searchBookByCategory(int codigoCategoria) {
		libros = Libro.searchByCategory(codigoCategoria);
		return libros;
	}

	public Vector<Libro> searchBookByEditorial(int codigoEditorial) {
		libros = Libro.searchByEditorial(codigoEditorial);
		return libros;
	}

	/*
	 * AUTOR_LIBRO
	 * 
	 */
	// INSERT
	public String insertBookAuthor(int codigoAutor, int isbn) {
		AutorLibro autorLibro = new AutorLibro(codigoAutor, isbn);
		feedback = autorLibro.insert();
		return feedback;
	}

	// DELETE
	public String deleteBookAuthor(int codigoAutor, int isbn) {
		Vector<AutorLibro> autorLibro = AutorLibro.searchByAuthorIsbn(codigoAutor, isbn);
		AutorLibro autorL = autorLibro.get(0);
		feedback = autorL.deleteAuthor();
		return feedback;
	}

	// LIST
	public Vector<AutorLibro> searchBookByAuthorIsbn(int codigoAutor, int isbn) {
		Vector<AutorLibro> autorLibro = AutorLibro.searchByAuthorIsbn(codigoAutor, isbn);
		return autorLibro;
	}

	public Vector<AutorLibro> searchBookIsbnByAuthor(int codigoAutor) {
		Vector<AutorLibro> autorLibro = AutorLibro.searchByAuthor(codigoAutor);
		return autorLibro;
	}

	public Vector<AutorLibro> searchBookAuthorByIsbn(int isbn) {
		Vector<AutorLibro> autorLibro = AutorLibro.searchByIsbn(isbn);
		return autorLibro;
	}

	//Ampliacion para Swing
	public ResultSet obtenerDatosMasMetadatosLibro() {
		resultado = Libro.obtenerDatosMasMetadatosLibro();
		return resultado;
	}
	
	public ResultSet obtenerDatosMasMetadatosAutorLibro(int isbn) {
		resultado = AutorLibro.obtenerDatosMasMetadatosAutorLibro(isbn);
		return resultado;
	}
	
	public ResultSet obtenerDatosMasMetadatosAutorExcluir(int isbn) {
		resultado = Autor.obtenerDatosMasMetadatosAutorExcluir(isbn);
		return resultado;
	}
}
