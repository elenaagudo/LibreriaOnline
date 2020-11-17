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

	public Libro(int isbn, String titulo, double precio, int stock, int codigoCategoria, int codigoEditorial) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.precio = precio;
		this.stock = stock;
		this.codigoCategoria = codigoCategoria;
		this.codigoEditorial = codigoEditorial;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getCodigoCategoria() {
		return codigoCategoria;
	}

	public void setCodigoCategoria(int codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public int getCodigoEditorial() {
		return codigoEditorial;
	}

	public void setCodigoEditorial(int codigoEditorial) {
		this.codigoEditorial = codigoEditorial;
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
		String feedback = "";
		Vector<Libro> libros = Libro.searchByIsbn(isbn);
		Vector<Categoria> categoria = Categoria.searchById(codigoCategoria);
		Vector<Editorial> editorial = Editorial.searchById(codigoEditorial);

		if (libros.size() > 0) {
			// no se puede insertar porque ya existe
			feedback = "El ISBN introducido ya existe";
		} else if (categoria.size() > 0 && editorial.size() > 0) {
			try {
				// se inserta porque esos codigo de editorial y categoria existen
				String sql = "insert into libro (isbn, titulo, precio, stock, cod_categoria, cod_editorial) values ("
						+ getIsbn() + ",'" + getTitulo() + "'," + getPrecio() + ", " + getStock() + ", "
						+ getCodigoCategoria() + "," + getCodigoEditorial() + ")";
				retorno = sentencia.executeUpdate(sql);
				feedback = (retorno > 0) ? "Registro de libro correcto." : "No ha sido posible registrar el libro.";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			// no existe el codigo de categoria o el codigo de editorial
			feedback = "No existe el codigo de categoria o codigo de editorial introducido";
		}

		return feedback;
	}

	// UPDATE isbn
	public String updateIsbn(int isbnNuevo) {
		try {
			String sql = "update libro set isbn=" + isbnNuevo + " where isbn=" + getIsbn();
			retorno = sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (retorno > 0) ? "Edición del ISBN de libro correcto." : "No ha sido posible editar el ISBN del libro.";
	}

	// UPDATE title
	public String updateTitle() {
		try {
			String sql = "update libro set titulo='" + getTitulo() + "' where isbn=" + getIsbn();
			retorno = sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (retorno > 0) ? "Edición del titulo de libro correcto."
				: "No ha sido posible editar el titulo del libro.";
	}

	// UPDATE price
	public String updatePrice() {
		try {
			String sql = "update libro set precio=" + getPrecio() + " where isbn=" + getIsbn();
			retorno = sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (retorno > 0) ? "Edición del precio de libro correcto."
				: "No ha sido posible editar el precio del libro.";
	}

	// UPDATE stock
	public String updateStock() {
		try {
			String sql = "update libro set stock=" + getStock() + " where isbn=" + getIsbn();
			retorno = sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (retorno > 0) ? "Edición del stock de libro correcto." : "No ha sido posible editar el stock del libro.";
	}

	// UPDATE category
	public String updateCategory() {
		try {
			String sql = "update libro set cod_categoria=" + getCodigoCategoria() + " where isbn=" + getIsbn();
			retorno = sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (retorno > 0) ? "Edición de la categoria de libro correcta."
				: "No ha sido posible editar la categoria del libro.";
	}

	// UPDATE editorial
	public String updateEditorial() {
		try {
			String sql = "update libro set cod_editorial=" + getCodigoEditorial() + " where isbn=" + getIsbn();
			retorno = sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (retorno > 0) ? "Edición de la editorial de libro correcta."
				: "No ha sido posible editar la editorial del libro.";
	}

	// DELETE
	public String delete() {
		try {
			String sql = "delete from libro where isbn=" + getIsbn();
			retorno = sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (retorno > 0) ? "Borrado de libro correcto." : "No ha sido posible borrar el libro.";
	}

	// READ
	// read all rows
	public static Vector<Libro> list() {
		String sql = "select * from libro";
		Vector<Libro> libros = search(sql);
		return libros;
	}

	// READ
	// search by isbn
	public static Vector<Libro> searchByIsbn(int isbn) {
		String sql = "select * from libro where isbn=" + isbn;
		Vector<Libro> libros = search(sql);
		return libros;
	}

	// READ
	// search by title
	public static Vector<Libro> searchByTitle(String titulo) {
		String sql = "select * from libro where titulo='" + titulo + "'";
		Vector<Libro> libros = search(sql);
		return libros;
	}

	// READ
	// search by price
	public static Vector<Libro> searchByPrice(double precio) {
		String sql = "select * from libro where precio=" + precio;
		Vector<Libro> libros = search(sql);
		return libros;
	}

	// READ
	// search by stock
	public static Vector<Libro> searchByStock(int stock) {
		String sql = "select * from libro where stock=" + stock;
		Vector<Libro> libros = search(sql);
		return libros;
	}

	// READ
	// search by category
	public static Vector<Libro> searchByCategory(int codigoCategoria) {
		String sql = "select * from libro where cod_categoria=" + codigoCategoria;
		Vector<Libro> libros = search(sql);
		return libros;
	}

	// READ
	// search by editorial
	public static Vector<Libro> searchByEditorial(int codigoEditorial) {
		String sql = "select * from libro where cod_editorial=" + codigoEditorial;
		Vector<Libro> libros = search(sql);
		return libros;
	}

	// UTILITIES
	public static Vector<Libro> search(String sql) {
		try {
			resultado = sentencia.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Vector<Libro> libros = resultSetToVector();
		return libros;
	}

	public static Vector<Libro> resultSetToVector() {
		Vector<Libro> libros = new Vector<Libro>();
		Libro libro;
		try {
			while (resultado.next()) {
				int isbn = resultado.getInt(1);
				String titulo = resultado.getString(2);
				double precio = resultado.getDouble(3);
				int stock = resultado.getInt(4);
				int codigoCategoria = resultado.getInt(5);
				int codigoEditorial = resultado.getInt(6);
				libro = new Libro(isbn, titulo, precio, stock, codigoCategoria, codigoEditorial);
				libros.add(libro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return libros;
	}

}
