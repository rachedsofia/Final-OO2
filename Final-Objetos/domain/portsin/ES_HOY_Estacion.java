package portsin;

import java.time.LocalDateTime;
import java.util.List;

public interface ES_HOY_Estacion {
	public double precio(String tipoNafta, String cantidadLitros, LocalDateTime fecha) throws DomainExceptionIn;

	public void confirmarCompra(String tipoNafta, String cantidadLitros, LocalDateTime fecha) throws DomainExceptionIn;

	public List<NaftaRecord> tiposDeNafta();

	public List<VentasRecord> ventas() throws DomainExceptionIn;

}
