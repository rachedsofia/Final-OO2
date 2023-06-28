package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import portsin.DomainExceptionIn;
import portsin.ES_HOY_Estacion;
import portsin.NaftaRecord;
import portsin.VentasRecord;
import portsout.DomainExceptionOut;
import portsout.RecuperarVentas;
import portsout.RegistrarCompra;
import portsout.VentasRecordPortOut;

public class EstacionESHOY implements ES_HOY_Estacion {
	private List<Nafta> naftas;
	private RegistrarCompra registrarCompra;
	private RecuperarVentas recuperarVenta;

	public EstacionESHOY(RegistrarCompra registrarCompra, RecuperarVentas recuperarVenta) {
		super();
		this.naftas = new ArrayList<Nafta>();
		this.CargarNaftas();
		this.registrarCompra = registrarCompra;
		this.recuperarVenta = recuperarVenta;
	}

	private void CargarNaftas() {
		naftas.add(new ExtraSuper());
		naftas.add(new Super());

	}

	private void validarLitros(String cantidadLitros) throws DomainExceptionIn {
		try {
			int litros = Integer.parseInt(cantidadLitros);
			if (litros <= 0)
				throw new DomainExceptionIn("La cantidad de litros debe ser mayor a 0");
		} catch (NumberFormatException e) {
			throw new DomainExceptionIn("El valor ingresado debe ser un número");
		}
	}

	@Override
	public double precio(String tipoNafta, String cantidadLitros, LocalDateTime fecha) throws DomainExceptionIn {
		Objects.requireNonNull(cantidadLitros);
		this.validarLitros(cantidadLitros);
		double total = 0;
		for (Nafta nafta : naftas) {
			total = calcularPrecio(tipoNafta, cantidadLitros, total, nafta, fecha);
		}
		return total;
	}

	private double calcularPrecio(String tipoNafta, String cantidadLitros, double total, Nafta nafta,
			LocalDateTime fecha) {
		if (nafta.esDeEsteTipo(tipoNafta)) {
			total = nafta.calcularPrecio(Integer.parseInt(cantidadLitros), fecha);
		}
		return total;
	}

	@Override
	public void confirmarCompra(String tipoNafta, String cantidadLitros, LocalDateTime fecha) throws DomainExceptionIn {
		double total = this.precio(tipoNafta, cantidadLitros, fecha);

		try {

			registrarCompra.registro(Integer.parseInt(cantidadLitros), total);
		} catch (DomainExceptionOut e) {
			throw new DomainExceptionIn(e.getMessage());
		}
	}

	@Override
	public List<NaftaRecord> tiposDeNafta() {
		List<NaftaRecord> tipos = new ArrayList<NaftaRecord>();
		for (Nafta nafta : naftas) {
			tipos.add(new NaftaRecord(nafta.tipo()));
		}
		return tipos;
	}

	@Override
	public List<VentasRecord> ventas() throws DomainExceptionIn {
		try {
			List<VentasRecord> lista = new ArrayList<>();
			List<VentasRecordPortOut> listPortsOut = this.recuperarVenta.recuperarVentas();

			for (VentasRecordPortOut unaVenta : listPortsOut) {
				VentasRecord venta = new VentasRecord(unaVenta.fecha(), unaVenta.litros(), unaVenta.total(),
						esImportante(unaVenta.total()));
				lista.add(venta);
			}

			return lista;
		} catch (

		Exception e) {
			throw new DomainExceptionIn(e.getMessage());
		}
	}

	private String esImportante(String total) {
		String esImportante = "NO";
		if (Double.parseDouble(total) > 100000) {
			return esImportante = "SI";
		}
		return esImportante;
	}

}
