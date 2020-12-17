package mvc.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class AutorLibro {

	private int codigoAutor;
	private int isbn;

	public AutorLibro(int codigoAutor, int isbn) {
		this.codigoAutor = codigoAutor;
		this.isbn = isbn;
	}

	public int getCodigoAutor() {
		return codigoAutor;
	}

	public void setCodigoAutor(int codigoAutor) {
		this.codigoAutor = codigoAutor;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	// INTEGRACION CON LA BBDD
	private static Statement sentencia;
	private static ResultSet resultado;

	public static void setConnection(Statement sentencia, ResultSet resultado) {
		AutorLibro.sentencia = sentencia;
		AutorLibro.resultado = resultado;
	}

	// METODOS CRUD
	int retorno;

	// INSERT
	public String insert() {
		try {
			String sql = "insert into autor_libro (cod_autor, isbn) values (" + getCodigoAutor() + ", " + getIsbn()
					+ ")";
			retorno = sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (retorno > 0) ? "Se ha añadido el autor del libro." : "No ha sido posible añadir el autor del libro.";
	}

	// DELETE
	public String deleteAuthor() {
		try {
			String sql = "delete from autor_libro where cod_autor=" + getCodigoAutor() + " and isbn=" + getIsbn();
			retorno = sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (retorno > 0) ? "Se ha quitado el autor del libro." : "No ha sido posible quitar el autor del libro.";
	}

	// READ
	public static Vector<AutorLibro> searchByAuthorIsbn(int codigoAutor, int isbn) {
		String sql = "select * from autor_libro where cod_autor=" + codigoAutor + " and isbn=" + isbn;
		Vector<AutorLibro> autorLibro = search(sql);
		return autorLibro;
	}

	public static Vector<AutorLibro> searchByAuthor(int codigoAutor) {
		String sql = "select * from autor_libro where cod_autor=" + codigoAutor;
		Vector<AutorLibro> autorLibro = search(sql);
		return autorLibro;
	}

	public static Vector<AutorLibro> searchByIsbn(int isbn) {
		String sql = "select * from autor_libro where isbn=" + isbn;
		Vector<AutorLibro> autorLibro = search(sql);
		return autorLibro;
	}

	// UTILITIES
	public static Vector<AutorLibro> search(String sql) {
		try {
			resultado = sentencia.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Vector<AutorLibro> autorLibro = resultSetToVector();
		return autorLibro;
	}

	public static Vector<AutorLibro> resultSetToVector() {
		Vector<AutorLibro> autoresLibro = new Vector<AutorLibro>();
		AutorLibro autorLibro;
		try {
			while (resultado.next()) {
				int codigoAutor = resultado.getInt(1);
				int isbn = resultado.getInt(2);
				autorLibro = new AutorLibro(codigoAutor, isbn);
				autoresLibro.addElement(autorLibro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return autoresLibro;
	}

	/*
	 * Ampliacion para Swing
	 * 
	 */

	public static ResultSet obtenerDatosMasMetadatosAutorLibro(int isbn) {
		try {
			String sql = "select a.cod_autor as 'CODIGO', a.nombre as 'NOMBRE' from autor_libro al, autor a where al.cod_autor=a.cod_autor and isbn="
					+ isbn;
			resultado = sentencia.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

}
