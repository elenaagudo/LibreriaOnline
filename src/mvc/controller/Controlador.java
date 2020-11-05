package mvc.controller;

import java.sql.ResultSet;

import mvc.model.Modelo;

public class Controlador {
	private Modelo modelo;
	private ResultSet resultado;

	public Controlador(Modelo modelo) {
		this.modelo = modelo;
	}

	// El controlador hace un mapeo de los metodos del modelo
	public ResultSet getAuthors() {
		resultado = modelo.getAuthors();
		return resultado;
	}
	
	public ResultSet getCategories() {
		resultado = modelo.getCategories();
		return resultado;
	}
	
	public ResultSet getEditorials() {
		resultado = modelo.getEditorials();
		return resultado;
	}
	
	public ResultSet getBooks() {
		resultado = modelo.getBooks();
		return resultado;
	}

	public void close() {
		modelo.close();
	}

}
