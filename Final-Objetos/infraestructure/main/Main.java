package main;

import java.awt.EventQueue;

import data.RecuperarVentaBD;
import data.RegistrarCompraBD;
import modelo.EstacionESHOY;
import ui.CargarCombustible;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CargarCombustible cargarCombustibleBD = new CargarCombustible(
							new EstacionESHOY(new RegistrarCompraBD("jdbc:mysql://localhost:3306/finaloo2"),
									new RecuperarVentaBD("jdbc:mysql://localhost:3306/finaloo2")));
					cargarCombustibleBD.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
