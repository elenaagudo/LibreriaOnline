package mvc.controller;

import java.util.Vector;

import mvc.model.Autor;
import mvc.model.Modelo;

public class Controlador {
	private Modelo modelo;
	private Vector<Autor> autores = new Vector<Autor>();
	private String feedback;

	public Controlador(Modelo modelo) {
		this.modelo = modelo;
	}

	// El controlador hace un mapeo de los metodos del modelo
	
	/*
	 * AUTORES
	 * 
	 * */
	public String insertAuthor(String nombreAutor) {
		feedback = modelo.insertAuthor(nombreAutor);
		return feedback;
	}
	
	public String deleteAuthor(int codigoAutor) {
		feedback = modelo.deleteAuthor(codigoAutor);
		return feedback;
	}
	
	public String updateAuthor(int codigoAutor, String nombreAutor) {
		feedback = modelo.updateAuthor(codigoAutor, nombreAutor);
		return feedback;
	}
	
	public Vector<Autor> listAuthors(){
		autores = modelo.listAuthors();
		return autores;
	}
	
	public void close() {
		modelo.close();
	}
	
	/*
	 * EDITORIALES
	 * 
	 * */

}
