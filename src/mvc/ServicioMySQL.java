package mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServicioMySQL {

	private static ServicioMySQL servicio;
	private Connection conexion;

	// contructor privado
	private ServicioMySQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/libreria";
			String user = "root";
			String password = "admin";

			conexion = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e) {
			System.out.println("ServicioMySQL: ERROR AL CARGAR EL DRIVER");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("ServicioMySQL: FALLO AL ESTABLECER LA CONEXION");
			e.printStackTrace();
		}

	}

	public static synchronized ServicioMySQL obtenerServicio() {
		if (servicio == null) {
			servicio = new ServicioMySQL();
		}
		return servicio;
	}

	public Connection obtenerConexion() {
		return conexion;
	}
}
