package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import portsout.DomainExceptionOut;
import portsout.RegistrarCompra;

public class RegistrarCompraBD implements RegistrarCompra {

	private Conn connStr;

	public RegistrarCompraBD(String connStr) {

		this.connStr = new Conn(connStr);
	}

	@Override
	public void registro(int litros, double total) throws DomainExceptionOut {

		String consulta = "insert into registrar_ventas(fecha, litros, total) values(?,?,?)";
		try {

			Connection dbconn = this.connStr.open();
			PreparedStatement statement = dbconn.prepareStatement(consulta);
			statement.setDate(1, Date.valueOf(LocalDate.now()));
			statement.setLong(2, litros);
			statement.setLong(3, (long) total);
			statement.executeUpdate();
			statement.close();
		} catch (

		SQLException e) {

			throw new DomainExceptionOut("Hubo un error en la consulta a la base de datos", e);

		}
	}
}