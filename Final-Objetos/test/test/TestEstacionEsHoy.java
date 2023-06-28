package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import modelo.EstacionESHOY;
import modelo.ExtraSuper;
import modelo.Nafta;
import modelo.Super;

public class TestEstacionEsHoy {
	/******* SIN DESCUENTO ******/

	@Test
	void calcularPrecioSuper() {

		String fecha = "2023-06-22T15:30:15";
		LocalDateTime fechaP = LocalDateTime.parse(fecha);

		EstacionESHOY miEstacion = new EstacionESHOY(new FakeRegistrarEnArchivoDeTexto(),
				new FakeRecuperarVentaArchivo());

		assertEquals(294840.0, miEstacion.precio("Super", "1092", fechaP));
		// calcula el precio SIN DESCUENTO de la nafta SUPER
	}

	@Test
	void calcularPrecioExtraSuper() {
		String fecha = "2023-06-22T15:30:15";
		LocalDateTime fechaP = LocalDateTime.parse(fecha);

		EstacionESHOY miEstacion = new EstacionESHOY(new FakeRegistrarEnArchivoDeTexto(),
				new FakeRecuperarVentaArchivo());

		assertEquals(730080.0, miEstacion.precio("Extra Super", "1872", fechaP));
		// calcula el precio SIN DESCUENTO de la nafta EXTRA SUPER
	}

	@Test
	void calcularPrecioSuperSabadoSin20Descuento() { // calcula el precio el sabado con menos de 20 litros por ende no
														// hay descuento
		String fecha = "2023-06-24T11:30:15";
		LocalDateTime fechaP = LocalDateTime.parse(fecha);

		EstacionESHOY miEstacion = new EstacionESHOY(new FakeRegistrarEnArchivoDeTexto(),
				new FakeRecuperarVentaArchivo());

		Nafta naftaSuper = new Super();
		double precio = naftaSuper.calcularPrecio(10, fechaP);
		assertEquals(2700.0, precio);

	}

	/******* CON DESCUENTO ******/

	@Test
	void calcularPrecioSuperSabadoHoraDescuento() { // calcula el precio el sabado a las 9:30 (tiene doble descuento,
													// 12%
		// del sabado
		// y 5% del horario);
		String fecha = "2023-06-24T09:30:15";
		LocalDateTime fechaP = LocalDateTime.parse(fecha);

		EstacionESHOY miEstacion = new EstacionESHOY(new FakeRegistrarEnArchivoDeTexto(),
				new FakeRecuperarVentaArchivo());

		Nafta naftaSuper = new Super();
		double precio = naftaSuper.calcularPrecio(100, fechaP);
		assertEquals(22572.0, precio);

	}

	@Test
	void calcularPrecioSuperHoraDescuento() { // calcula el precio a las 9:30

		String fecha = "2023-06-21T09:30:15";
		LocalDateTime fechaP = LocalDateTime.parse(fecha);

		EstacionESHOY miEstacion = new EstacionESHOY(new FakeRegistrarEnArchivoDeTexto(),
				new FakeRecuperarVentaArchivo());

		Nafta naftaSuper = new Super();
		double precio = naftaSuper.calcularPrecio(50, fechaP);
		assertEquals(12825.0, precio);

	}

	@Test
	void calcularPrecioSuperSabadoDescuento() { // calcula el precio el sabado a las 11 (tiene descuento, 12%
												// del sabado

		String fecha = "2023-06-24T11:30:15";
		LocalDateTime fechaP = LocalDateTime.parse(fecha);

		EstacionESHOY miEstacion = new EstacionESHOY(new FakeRegistrarEnArchivoDeTexto(),
				new FakeRecuperarVentaArchivo());

		Nafta naftaSuper = new Super();
		double precio = naftaSuper.calcularPrecio(80, fechaP);
		assertEquals(19008.0, precio);

	}

	@Test
	void calcularPrecioExtraSuperDomingoDescuento() { // calcula el precio de la nafta Extra Super el domingo a las
														// 15:30, con
														// un descuento del 10%
		String fecha = "2023-06-25T15:30:15";
		LocalDateTime fechaP = LocalDateTime.parse(fecha);

		EstacionESHOY miEstacion = new EstacionESHOY(new FakeRegistrarEnArchivoDeTexto(),
				new FakeRecuperarVentaArchivo());

		Nafta naftaExtraSuper = new ExtraSuper();
		double precio = naftaExtraSuper.calcularPrecio(100, fechaP);
		assertEquals(35100.0, precio);

	}

	/******* FAKES ******/
	@Test
	void entroFake1() {

		FakeRegistrarEnArchivoDeTexto fake1 = new FakeRegistrarEnArchivoDeTexto();
		EstacionESHOY miEstacion = new EstacionESHOY(fake1, new FakeRecuperarVentaArchivo());
		miEstacion.confirmarCompra("Super", "10", LocalDateTime.now());

		assertTrue(fake1.confirmacion(10, 2700.0));

	}

	@Test
	void entroFake2() {

		FakeRegistrarEnBD fake2 = new FakeRegistrarEnBD();
		EstacionESHOY miEstacion = new EstacionESHOY(fake2, new FakeRecuperarVentaArchivo());
		miEstacion.confirmarCompra("Super", "10", LocalDateTime.now());

		assertTrue(fake2.confirmacion(10, 2700.0));

	}

}
