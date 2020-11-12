package mvc.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Editorial {

	// BEAN
	private int codigoEditorial;
	private String nombreEditorial;

	public Editorial() {
		
	}
	
	public Editorial(String nombreEditorial) {
		this.nombreEditorial = nombreEditorial;
	}

	public int getCodigoEditorial() {
		return codigoEditorial;
	}

	public void setCodigoEditorial(int codigoEditorial) {
		this.codigoEditorial = codigoEditorial;
	}

	public String getNombreEditorial() {
		return nombreEditorial;
	}

	public void setNombreEditorial(String nombreEditorial) {
		this.nombreEditorial = nombreEditorial;
	}

	// INTEGRACION CON LA BBDD
	private static Statement sentencia;
	private static ResultSet resultado;

	public static void setConnection(Statement sentencia, ResultSet resultado) {
		Editorial.sentencia = sentencia;
		Editorial.resultado = resultado;
	}

	// METODOS CRUD
	private int retorno;
	private static Vector<Editorial> editoriales;

	// INSERT
	public String insert() {
		try {
			String sql = "insert into editorial (nombre) values ('" + getNombreEditorial() + "')";
			retorno = sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (retorno > 0) ? "Registro de editorial correcto." : "No ha sido posible registrar la editorial.";
	}

	// UPDATE
	public String update() {
		try {
			String sql = "update editorial set nombre=" + getNombreEditorial() + "where cod_editorial="
					+ getCodigoEditorial();
			retorno = sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (retorno > 0) ? "Edicion de editorial correcto." : "No ha sido posible editar la editorial.";
	}

	// DELETE
	public String delete() {
		try {
			String sql = "delete from editorial where cod_editorial=" + getCodigoEditorial();
			retorno = sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (retorno > 0) ? "Edicion de editorial correcto." : "No ha sido posible editar la editorial.";
	}

	// READ
	// read all rows
	public static Vector<Editorial> list() {
		try {
			String sql = "select * from editorial";
			resultado = sentencia.executeQuery(sql);
			editoriales = resultSetToVector();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return editoriales;
	}

	// search by id
	public Vector<Editorial> listById() {
		try {
			String sql = "select * from editorial where cod_editorial=" + getCodigoEditorial();
			resultado = sentencia.executeQuery(sql);
			editoriales = resultSetToVector();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return editoriales;
	}

	// search by name
	public Vector<Editorial> listByName() {
		try {
			String sql = "sqlect * from editorial where nombre=" + getNombreEditorial();
			resultado = sentencia.executeQuery(sql);
			editoriales = resultSetToVector();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return editoriales;
	}

	// UTILITIES
	public static Vector<Editorial> resultSetToVector() {
		Editorial editorial;
		
		try {
			while(resultado.next()) {
				int codigoEditorial = resultado.getInt(1);
				String nombreEditorial = resultado.getString(2);
				editorial = new Editorial(nombreEditorial);
				editorial.setCodigoEditorial(codigoEditorial);
				editoriales.addElement(editorial);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return editoriales;
	}

}
