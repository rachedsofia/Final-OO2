package ui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import portsin.VentasRecord;

public class ListaVentas extends JFrame {

	private JTable ventasTable;

	public ListaVentas(List<VentasRecord> lista) {

		// Configuración básica del JFrame
		setTitle("Lista de Ventas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);

		// Creación de la JTable
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Fecha Venta");
		model.addColumn("Total Litros Cargados");
		model.addColumn("Monto Facturado");
		model.addColumn("Es Importante");

		for (VentasRecord venta : lista) {
			Object[] rowData = { venta.fecha(), venta.litros(), venta.monto(), venta.importante() };
			model.addRow(rowData);
		}

		ventasTable = new JTable(model);

		// Agregar la JTable a un JScrollPane para permitir el desplazamiento de los
		// datos
		JScrollPane scrollPane = new JScrollPane(ventasTable);
		add(scrollPane, BorderLayout.CENTER);
		setVisible(true);
	}

}
