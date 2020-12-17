package mvc.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Autor {

	// BEAN
	private int codigoAutor;
	private String nombreAutor;

	public Autor() {

	}

	public Autor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}

	public int getCodigoAutor() {
		return codigoAutor;
	}

	private void setCodigoAutor(int codigoAutor) {
		this.codigoAutor = codigoAutor;
	}

	public String getNombreAutor() {
		return nombreAutor;
	}

	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}

	// INTEGRACION CON LA BBDD
	private static Statement sentencia;
	private static ResultSet resultado;

	public static void setConnection(Statement sentencia, ResultSet resultado) {
		Autor.sentencia = sentencia;
		Autor.resultado = resultado;
	}

	// METODOS CRUD
	private int retorno;

	// INSERT
	public String insert() {
		try {
			String sql = "insert into autor (nombre) values ('" + getNombreAutor() + "')";
			retorno = sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (retorno > 0) ? "Registro de autor correcto." : "No ha sido posible registrar el autor.";
	}

	// UPDATE
	public String update() {
		try {
			String sql = "update autor set nombre='" + getNombreAutor() + "' where cod_autor=" + getCodigoAutor();
			retorno = sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (retorno > 0) ? "Edición de autor correcta." : "No ha sido posible editar el autor.";
	}

	// DELETE
	public String delete() {
		try {
			String sql = "delete from autor where cod_autor = " + getCodigoAutor();
			retorno = sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (retorno > 0) ? "Borrado de autor correcto." : "No ha sido posible borrar el autor.";
	}

	// READ
	// read all rows
	public static Vector<Autor> list() {
		String sql = "select * from autor";
		Vector<Autor> autores = search(sql);
		return autores;
	}

	// READ
	// search by id
	public static Vector<Autor> searchById(int codigoAutor) {
		String sql = "select * from autor where cod_autor=" + codigoAutor;
		Vector<Autor> autores = search(sql);
		return autores;
	}

	// READ
	// search by name
	public static Vector<Autor> searchByName(String nombreAutor) {
		String sql = "select * from autor where nombre='" + nombreAutor + "'";
		Vector<Autor> autores = search(sql);
		return autores;
	}

	// UTILITIES
	public static Vector<Autor> search(String sql) {
		try {
			resultado = sentencia.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Vector<Autor> autores = resultSetToVector();
		return autores;
	}

	public static Vector<Autor> resultSetToVector() {
		Vector<Autor> autores = new Vector<Autor>();
		Autor autor;
		try {
			while (resultado.next()) {
				int codigoAutor = resultado.getInt(1);
				String nombreAutor = resultado.getString(2);
				autor = new Autor(nombreAutor);
				autor.setCodigoAutor(codigoAutor);
				autores.addElement(autor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return autores;
	}

	/*
	 * Ampliacion para Swing
	 * 
	 */
	public static ResultSet obtenerDatosMasMetadatosAutor() {
		try {
			String sql = "select cod_autor as 'CODIGO', nombre as 'NOMBRE' from autor";
			resultado = sentencia.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("AUTOR: Fallo al obtener Datos mas Metadatos.");
			e.printStackTrace();
		}
		return resultado;
	}

	public static ResultSet obtenerDatosMasMetadatosAutorExcluir(int isbn) {
		try {
			String sql = "select cod_autor as 'CODIGO', nombre as 'NOMBRE' from autor where cod_autor not in (select cod_autor from autor_libro where isbn="
					+ isbn + ")";
			resultado = sentencia.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
}
