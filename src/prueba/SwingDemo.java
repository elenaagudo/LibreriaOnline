package prueba;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class SwingDemo {

	
	public SwingDemo() {
	
		//se crea el contenedor de alto nivel
		JFrame marco = new JFrame("Ejemplo de aplicacion Swing");
		
		//se da al frame un tamaño inicial
		marco.setSize(300,100);
		
		//cuando el usuario presione x termina la aplicacion
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//creacion de una etiqueta de texto
		JLabel etiqueta = new JLabel("SWING es un 'framework' para definir GUI's Java");
		
		//se añade la etiqueta a content pane
		marco.add(etiqueta);
		
		//se muestra el contenedor
		marco.setVisible(true);
	}
	
	public static void main(String[] args) {
		//se crea la aplicacion (marco) en el thread de despacho de eventos (event dispatching thread)
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new SwingDemo();
			}
			
		});
	}
	
	

}
