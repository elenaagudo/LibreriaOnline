package mvc;

import mvc.controller.Controlador;
import mvc.model.Modelo;
import mvc.view.VistaConsola;

public class Launcher {

	public static void main(String[] args) {

		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador(modelo);
		VistaConsola vista = new VistaConsola(controlador);

		vista.getAccion();
	}

}
