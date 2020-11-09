package mvc;

import javax.swing.SwingUtilities;

import mvc.controller.Controlador;
import mvc.model.Modelo;
import mvc.view.VistaSwing;

public class SwingLauncher {

	public static void main(String[] args) {
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador(modelo);
		
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				new VistaSwing(controlador);
			}
		});

	}

}
