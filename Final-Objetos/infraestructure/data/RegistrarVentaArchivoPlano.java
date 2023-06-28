package data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import portsout.DomainExceptionOut;
import portsout.RegistrarCompra;

public class RegistrarVentaArchivoPlano implements RegistrarCompra {
	private File direccion;
	private FileWriter escribir;
	private PrintWriter lineaEscritura;
	private static final String pipes = " | ";

	public RegistrarVentaArchivoPlano(String direccion) {
		super();
		this.direccion = new File(direccion);
	}

	@Override
	public void registro(int litros, double monto) {

		String registroDeCompra = LocalDate.now() + pipes + Integer.toString(litros) + pipes + Double.toString(monto);

		try {
			if (this.direccion.exists())
				this.direccion.createNewFile();

			this.escribir = new FileWriter(direccion, true);
			this.lineaEscritura = new PrintWriter(this.escribir);
			this.lineaEscritura.println(registroDeCompra);

		} catch (IOException e) {
			throw new DomainExceptionOut("No se pudo registrar el participante en el archivo.", e);
		} finally {
			try {
				this.escribir.close();
				this.lineaEscritura.close();
			} catch (IOException e) {
				throw new DomainExceptionOut("Error al cerrar el archivo de registro.", e);
			}

		}

	}

}
