package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import portsout.DomainExceptionOut;
import portsout.RecuperarVentas;
import portsout.VentasRecordPortOut;

public class RecuperarVentaArchivo implements RecuperarVentas {
	private String archivoDeVentas;
	private FileReader lector;
	private BufferedReader buffer;

	public RecuperarVentaArchivo(String archivoDeVentas) throws DomainExceptionOut {
		Objects.requireNonNull(archivoDeVentas);

		if (archivoDeVentas.isEmpty()) {
			throw new DomainExceptionOut("El archivo esta vacio", null);
		}

		this.archivoDeVentas = archivoDeVentas;
	}

	@Override
	public List<VentasRecordPortOut> recuperarVentas() throws DomainExceptionOut {
		List<VentasRecordPortOut> ventas = new ArrayList<VentasRecordPortOut>();

		try {
			File archivo = new File(archivoDeVentas);
			lector = new FileReader(archivo);
			buffer = new BufferedReader(lector);
			String linea;

			while ((linea = buffer.readLine()) != null) {
				String[] partes = linea.split("\\|");

				ventas.add(new VentasRecordPortOut(partes[0], partes[1], partes[2]));

			}
		} catch (

		IOException e) {
			throw new DomainExceptionOut("Hubo un error al abrir el archivo de peliculas.", e);
		} finally {

			try {
				buffer.close();
				lector.close();
			} catch (IOException e) {
				throw new DomainExceptionOut("Error al cerrar el archivo de peliculas", e);
			}

		}

		return ventas;

	}

}
