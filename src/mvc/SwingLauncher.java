package mvc;

import javax.swing.SwingUtilities;

import mvc.controller.Controlador;
import mvc.model.Modelo;
import mvc.swingview.*;

public class SwingLauncher {

	public static void main(String[] args) {
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador(modelo);
		
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				//new VistaAutorSwing(controlador);
				//new VistaEditorialSwing(controlador);
				//new VistaCategoriaSwing(controlador);
				//new VistaLibroSwing(controlador);
				new VistaMenuSwing(controlador);
			}
		});

	}

}
