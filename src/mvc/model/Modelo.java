package mvc.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Modelo {

	private Connection conexion;
	private Statement sentencia;
	private ResultSet resultado;

	public Modelo() {
		try {
			conexion = ServicioMySQL.obtenerServicio().obtenerConexion();
			sentencia = conexion.createStatement();
		} catch (SQLException e) {
			System.out.println("MODELO: Error al crear la sentencia");
			e.printStackTrace();
		}
	}

	public void terminar() {
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

	public ResultSet obtenerAutores() {
		try {
			String sql = "SELECT * FROM autor";
			resultado = sentencia.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Modelo: Error al ejecutar la sentencia");
			e.printStackTrace();
		}
		return resultado;
	}
	
	public ResultSet obtenerEditoriales() {
		try {
			String sql = "SELECT * FROM autor";
			resultado = sentencia.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Modelo: Error al ejecutar la sentencia");
			e.printStackTrace();
		}
		return resultado;
	}
}
