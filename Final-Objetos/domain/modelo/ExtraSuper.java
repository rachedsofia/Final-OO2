package modelo;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ExtraSuper extends Nafta {
	private static double PRECIO = 390;
	public static double DESCUENTO_DOMINGO = 0.90;

	public ExtraSuper() {
		super("Extra Super");
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calcularPrecio(int cantidadLitros, LocalDateTime hoy) {

//		LocalDate hoy = LocalDate.now();
		double total = PRECIO * cantidadLitros;
		if (esDomingo(hoy)) {
			total = total * DESCUENTO_DOMINGO;
		}
		return total;
	}

	public boolean esDomingo(LocalDateTime fecha) {

		if (fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
			return true;
		}
		return false;

	}

	@Override
	public String tipo() {
		// TODO Auto-generated method stub
		return this.nombre;
	}

}
