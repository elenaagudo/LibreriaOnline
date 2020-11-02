package mvc;

import java.sql.ResultSet;

public class Controlador {
	private Modelo modelo;
	private ResultSet resultado;

	public Controlador(Modelo modelo) {
		this.modelo = modelo;
	}

	// El controlador hace un mapeo de los metodos del modelo
	public ResultSet obtenerAutores() {
		resultado = modelo.obtenerAutores();
		return resultado;
	}

	public void terminar() {
		modelo.terminar();
	}

}
