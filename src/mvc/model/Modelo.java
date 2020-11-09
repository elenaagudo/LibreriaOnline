package mvc.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Modelo {

	private Connection conexion;
	private Statement sentencia;
	private ResultSet resultado;
	//string feedback

	
	//recibe el servicio, para pasarselo a getService()
	public Modelo() {
		try {
			conexion = ServicioBBDD.getService().getConnection();
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
	
	
	/*
	 * LOGICA DE NEGOCIO
	 * */
	//registrarAutor(String autor)
		//sql de insertar
		//retorno = executeUpdate(sql);
		//feedback = (retorno > 0 ? "Registro de autor correcto" : "No ha sido posible registrar el autor");
		//retornar feedback

	//borrar autor(int codAutor)
		//int retorno = 0;
		//sql de borrar
		//retorno = executeUpdate(sql)
		//feedback = (retorno > 0 ? "Borrado de autor correcto" : "No ha sido posible borrar el autor");
		//retornar feedback
	
	//obtenerAutor(int codAutor)
		//sql de consultar
		//resultado = executeQuery(sql)
		//retornar resultado
	
	//obtenerAutores()
		//sql de select *
		
	//editarAutor(int codAutor, String nombreAutor)
		//retorno = 0
		//sql de update
		//retorno = executeUpdate(sql)
		//feedback = (retorno > 0 ? "Edicion de autor correcto" : "No ha sido posible editar el autor")
	
	
	
}
