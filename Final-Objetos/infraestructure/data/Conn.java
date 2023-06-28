package data;

import java.sql.Connection;
import java.sql.DriverManager;

import portsout.DomainExceptionOut;

public class Conn {
	private static final String USER = "root";
	private static final String PWD = "";

	private String conn;
	private Connection connection;

	public Conn(String conn) {
		this.conn = conn;
	}

	Connection open() throws DomainExceptionOut {
		try {
			String url = this.conn;
//			String user = USER;
//			String password = PWD;

			connection = DriverManager.getConnection(url, USER, PWD);

			return connection;
		} catch (Exception ex) {
			throw new DomainExceptionOut("No fue posible conectarse a la base de datos", ex);
		}
	}

}
