package test;

import portsout.DomainExceptionOut;
import portsout.RegistrarCompra;

public class FakeRegistrarEnArchivoDeTexto implements RegistrarCompra {

	private int litros;
	private double total;

	@Override
	public void registro(int litros, double total) throws DomainExceptionOut {
		// TODO Auto-generated method stub
		this.litros = litros;
		this.total = total;

	}

	public boolean confirmacion(int litros, double total) {
		if (this.litros == litros && this.total == total) {
			return true;
		}
		return false;
	}

}
