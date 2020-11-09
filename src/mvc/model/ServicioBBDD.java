package mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServicioBBDD {
	//CONSTANTES con los nombres de las BBDD
//	public static final String MYSQL = "mysql";
//	public static final String ORACLE = "oracle";
//	public static final String POSTRGRESQL = "postgresql";
	
	//DRIVERS
//	private static final String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
	//oracle.jdbc.OracleDriver
	//org.porgresql.Driver
	
	//URL
	//jdbc:oracle:thin:@localhost:1521:libreria
	//
	
	//USER
	
	//USER_KEY

	private static ServicioBBDD servicio;
	private Connection conexion;

	// contructor privado, recibe todos los campos (diver, url, usuario, contraseña)
	private ServicioBBDD() {
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

	//recibe string servicio
	public static synchronized ServicioBBDD getService() {
		//dentro del if crear switch para ver de que tipo es
		if (servicio == null) {
			servicio = new ServicioBBDD();
		}
		return servicio;
	}

	public Connection getConnection() {
		return conexion;
	}
}
