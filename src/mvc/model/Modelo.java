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
			conexion = ServicioMySQL.getService().getConnection();
			sentencia = conexion.createStatement();
		} catch (SQLException e) {
			System.out.println("MODELO: Error al crear la sentencia");
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

	public ResultSet getAuthors() {
		try {
			String sql = "SELECT * FROM autor";
			resultado = sentencia.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Modelo: Error al ejecutar la sentencia de autores");
			e.printStackTrace();
		}
		return resultado;
	}
	
	public ResultSet getCategories() {
		try {
			String sql = "SELECT * FROM categoria";
			resultado = sentencia.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Modelo: Error al ejecutar la sentencia de categorias");
			e.printStackTrace();
		}
		return resultado;
	}
	
	public ResultSet getEditorials() {
		try {
			String sql = "SELECT * FROM editorial";
			resultado = sentencia.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Modelo: Error al ejecutar la sentencia de editoriales");
			e.printStackTrace();
		}
		return resultado;
	}
	
	public ResultSet getBooks() {
		try {
			String sql = "SELECT * FROM libro";
			resultado = sentencia.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Modelo: Error al ejecutar la sentencia de libros");
			e.printStackTrace();
		}
		return resultado;
	}
	
	
}
