package mvc.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Autor {

	private int codigoAutor;
	private String nombreAutor;

	public Autor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}

	public int getCodigoAutor() {
		return codigoAutor;
	}

	public void setCodigoAutor(int codigoAutor) {
		this.codigoAutor = codigoAutor;
	}

	public String getNombreAutor() {
		return nombreAutor;
	}

	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}

	//INTEGRACION CON LA BBDD
	private static Statement sentencia;
	private static ResultSet resultado;
	private int retorno;
	
	public static void setConexion(Statement sentencia, ResultSet resultado) {
		Autor.sentencia = sentencia;
		Autor.resultado = resultado;
	}
	
	//UTILIDADES DE LA CLASE
	
	//METODOS CRUD
	public String insert() {
		try {
			String sql = "insert into autor (nombre) values (" + getNombreAutor() + "')";
			retorno = sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (retorno > 0) ? "Registro de autor correcto." : "No ha sido posible registrar el autor.";
	}
	
}
