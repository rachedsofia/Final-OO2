package modelo;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Super extends Nafta {
	private static double PRECIO = 270;

	public static double DESCUENTO_DIARIO = 0.95;
	public static double DESCUENTO_SABADO = 0.88;

	public Super() {
		super("Super");
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calcularPrecio(int cantidadLitros, LocalDateTime hoy) {

//		LocalDateTime hoy = LocalDateTime.now();
		double total = cantidadLitros * PRECIO;

		if (this.horarioDescuento(hoy))
			total = total * DESCUENTO_DIARIO;

		if (this.esSabado(hoy, cantidadLitros))
			total = total * DESCUENTO_SABADO;

		return total;

	}

	public boolean horarioDescuento(LocalDateTime fecha) {
		LocalTime horaInicio = LocalTime.of(8, 0); // 8:00 AM
		LocalTime horaFin = LocalTime.of(10, 0); // 10:00 AM

		LocalTime horaFecha = fecha.toLocalTime();

		return horaFecha.isAfter(horaInicio) && horaFecha.isBefore(horaFin);
	}

	public boolean esSabado(LocalDateTime fecha, int cantidadLitros) {

		if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY && cantidadLitros > 20)
			return true;
		return false;

	}

	@Override
	public String tipo() {
		// TODO Auto-generated method stub
		return this.nombre;
	}

}
