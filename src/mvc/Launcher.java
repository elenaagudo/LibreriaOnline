package mvc;

import mvc.controller.Controlador;
import mvc.model.Modelo;
import mvc.view.MenuPrincipal;

public class Launcher {

	public static void main(String[] args) {

		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador(modelo);
		MenuPrincipal menuPrincipal = new MenuPrincipal(controlador);

		menuPrincipal.getAction();
	}

}
