package mvc.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Categoria {

	// BEAN
	private int codigoCategoria;
	private String nombreCategoria;

	public Categoria() {

	}

	public Categoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public int getCodigoCategoria() {
		return codigoCategoria;
	}

	private void setCodigoCategoria(int codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	// INTEGRACION CON LA BBDD
	private static Statement sentencia;
	private static ResultSet resultado;

	public static void setConnection(Statement sentencia, ResultSet resultado) {
		Categoria.sentencia = sentencia;
		Categoria.resultado = resultado;
	}

	// METODOS CRUD
	private int retorno;

	// INSERT
	public String insert() {
		try {
			String sql = "insert into categoria (nombre) values ('" + getNombreCategoria() + "')";
			retorno = sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (retorno > 0) ? "Registro de categoria correcto." : "No ha sido posible registrar la categoria.";
	}

	// UPDATE
	public String update() {
		try {
			String sql = "update categoria set nombre='" + getNombreCategoria() + "' where cod_categoria="
					+ getCodigoCategoria();
			retorno = sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (retorno > 0) ? "Edición de categoria correcto." : "No ha sido posible editar la categoria.";
	}

	// DELETE
	public String delete() {
		try {
			String sql = "delete from categoria where cod_categoria = " + getCodigoCategoria();
			retorno = sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (retorno > 0) ? "Borrado de categoria correcto." : "No ha sido posible borrar la categoria.";
	}

	// READ
	// read all rows
	public static Vector<Categoria> list() {
		String sql = "select * from categoria";
		Vector<Categoria> categorias = search(sql);
		return categorias;
	}

	// READ
	// search by id
	public static Vector<Categoria> searchById(int codigoCategoria) {
		String sql = "select * from categoria where cod_categoria=" + codigoCategoria;
		Vector<Categoria> categorias = search(sql);
		return categorias;
	}

	// READ
	// search by name
	public static Vector<Categoria> searchByName(String nombreCategoria) {
		String sql = "select * from categoria where nombre='" + nombreCategoria + "'";
		Vector<Categoria> categorias = search(sql);
		return categorias;
	}

	// UTILITIES
	public static Vector<Categoria> search(String sql) {
		try {
			resultado = sentencia.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Vector<Categoria> categorias = resultSetToVector();
		return categorias;
	}

	public static Vector<Categoria> resultSetToVector() {
		Vector<Categoria> categorias = new Vector<Categoria>();
		Categoria categoria;
		try {
			while (resultado.next()) {
				int codigoCategoria = resultado.getInt(1);
				String nombreCategoria = resultado.getString(2);
				categoria = new Categoria(nombreCategoria);
				categoria.setCodigoCategoria(codigoCategoria);
				categorias.addElement(categoria);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorias;
	}

}
