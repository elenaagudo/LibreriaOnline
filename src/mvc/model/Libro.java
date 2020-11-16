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

	//en el constructor meter todos los campos
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
		Vector<Libro> libros = Libro.searchById(isbn);
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

	// UPDATE
	public String update() {
		return "";
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
		Vector<Libro> libros = new Vector<Libro>();
		String sql = "select * from libro";
		libros = search(sql);
		return libros;
	}

	// READ
	// search by id
	public static Vector<Libro> searchById(int isbn) {
		Vector<Libro> libros = new Vector<Libro>();
		String sql = "select * from libro where isbn=" + isbn;
		libros = search(sql);
		return libros;
	}

	// READ
	// search by name
	public static Vector<Libro> searchByName(String titulo) {
		Vector<Libro> libros = new Vector<Libro>();
		String sql = "select * from libro where titulo='"+titulo+"'";
		libros = search(sql);
		return libros;
	}

	// UTILITIES
	public static Vector<Libro> search(String sql) {
		Vector<Libro> libros = new Vector<Libro>();
		try {
			resultado = sentencia.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		libros = resultSetToVector();
		return libros;
	}

	public static Vector<Libro> resultSetToVector() {
		Vector<Libro> libros = new Vector<Libro>();
		Libro libro;
		try {
			while(resultado.next()) {
				int isbn = resultado.getInt(1);
				String titulo = resultado.getString(2);
				double precio = resultado.getDouble(3);
				int stock = resultado.getInt(4);
				int codigoCategoria = resultado.getInt(5);
				int codigoEditorial = resultado.getInt(6);
				libro = new Libro(isbn, titulo, precio, stock, codigoCategoria, codigoEditorial);
				libros.add(libro);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return libros;
	}

}
