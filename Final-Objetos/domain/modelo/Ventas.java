package modelo;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ventas {
	private LocalDateTime fechaCompra;
	private int cantidadLitros;
	private double total;

	public Ventas(LocalDateTime fechaCompra, int cantidadLitros, double total) {
		super();
		Objects.requireNonNull(fechaCompra);
		Objects.requireNonNull(cantidadLitros);
		Objects.requireNonNull(total);

		this.fechaCompra = fechaCompra;
		this.cantidadLitros = cantidadLitros;
		this.total = total;
	}

	public String fecha() {
		return this.fechaCompra.toString();
	}

	public String CantidadLitros() {
		return String.valueOf(cantidadLitros);
	}

	public String total() {
		return String.valueOf(total);

	}

	public boolean isEsImportante() {
		return total > 100000;
	}

}
