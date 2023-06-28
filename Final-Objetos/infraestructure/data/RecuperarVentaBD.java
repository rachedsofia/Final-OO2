package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import portsout.DomainExceptionOut;
import portsout.RecuperarVentas;
import portsout.VentasRecordPortOut;

public class RecuperarVentaBD implements RecuperarVentas {

	private Conn connStr;
	private ResultSet rs;

	public RecuperarVentaBD(String connStr) {

		this.connStr = new Conn(connStr);
	}

	@Override
	public List<VentasRecordPortOut> recuperarVentas() {
		List<VentasRecordPortOut> listaVentas = new ArrayList<VentasRecordPortOut>();
		String registrar = "select * from registrar_ventas";
		try {
			Connection dbconn = this.connStr.open();
			Statement statement = dbconn.createStatement();
			rs = statement.executeQuery(registrar);

			while (rs.next()) {
				VentasRecordPortOut nuevaVenta = new VentasRecordPortOut(rs.getString("fecha"), rs.getString("litros"),
						rs.getString("total"));

				listaVentas.add(nuevaVenta);
			}
			return listaVentas;
		} catch (SQLException e) {

			throw new DomainExceptionOut("hubo un error en la consulta a la base de datos", e);

		}
	}

}
