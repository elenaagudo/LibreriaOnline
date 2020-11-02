package prueba;

import java.sql.*;

public class PruebaBBDD {

	public static void main(String[] args) {
		try {
			// Driver de MySQL
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/libreria", "root", "admin");

			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM autor");

			while (rs.next()) {
				int codAutor = rs.getInt(1);
				String nombreAutor = rs.getString(2);
				System.out.println("Codigo autor: " + codAutor + ", Nombre autor: " + nombreAutor);
			}
			
			rs.close();
			stmt.close();
			conexion.close();

		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la conexion.");
			e.printStackTrace();
		}

	}

}
