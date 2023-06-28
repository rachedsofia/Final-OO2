package main;

import java.awt.EventQueue;

import data.RecuperarVentaArchivo;
import data.RegistrarVentaArchivoPlano;
import modelo.EstacionESHOY;
import ui.CargarCombustible;

public class Main2 {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CargarCombustible cargarCombustibleArchivo = new CargarCombustible(new EstacionESHOY(
							new RegistrarVentaArchivoPlano("registro"), new RecuperarVentaArchivo("registro")));
					cargarCombustibleArchivo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
