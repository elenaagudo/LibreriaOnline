package mvc.controller;

import java.sql.ResultSet;

import mvc.model.Modelo;

public class Controlador {
	private Modelo modelo;
	private ResultSet resultado;
	// int opcion
	// scanner

	// modelo, vista

	public Controlador(Modelo modelo) {
		this.modelo = modelo;
	}

	// El controlador hace un mapeo de los metodos del modelo
	public String insertAuthor(String nombreAutor) {
		return "";
	}
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

	// getAccion controla desde el principio

	// obtenerOpcion

	// mantenerAutores
	// caso1: mostrarFormularioNuevoAutor
	// teclado.nextLine()
	// vista.mostrarFeedback(modelo.registrarAutor(teclado.nextLine()))
	// caso2: vista.mostrarAutores(modelo.obtenerAutores())
	// vista.formularioBorrarAutor()
	// teclado.nextLine()
	// vista.mostrarFeedback(modelo.borrarAutor(teclado.nextInt()))
	// caso3:mostrarAutores()
	// mostrarFormularioEditarAutor()
	// teclado.nextLine()
	// caso4:mostrarAutores()

}
