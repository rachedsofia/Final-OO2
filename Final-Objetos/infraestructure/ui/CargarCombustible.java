package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import portsin.DomainExceptionIn;
import portsin.ES_HOY_Estacion;
import portsin.NaftaRecord;

public class CargarCombustible extends JFrame {

	private JPanel contentPane;
	private JTextField cantidadDeLitros;
	private ES_HOY_Estacion miEstacion;

	public CargarCombustible(ES_HOY_Estacion miEstacion) {
		this.miEstacion = miEstacion;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 307);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cantidad de litros a cargar:");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 15));
		lblNewLabel.setBounds(29, 112, 185, 55);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Tipo de Nafta");
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(29, 36, 136, 34);
		contentPane.add(lblNewLabel_1);

		JComboBox<String> tipoDeNafta = new JComboBox<String>();
		tipoDeNafta.setBounds(246, 44, 118, 22);
		contentPane.add(tipoDeNafta);
		cargarComboBox(tipoDeNafta);

		cantidadDeLitros = new JTextField();
		cantidadDeLitros.setBounds(246, 131, 118, 22);
		contentPane.add(cantidadDeLitros);
		cantidadDeLitros.setColumns(10);

		JButton consultarPrecio = new JButton("Consultar Precio");
		consultarPrecio.setFont(new Font("Serif", Font.PLAIN, 14));
		consultarPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarPrecio.setEnabled(false);
				try {
					String tipo = (String) tipoDeNafta.getSelectedItem();
					double precio = miEstacion.precio(tipo, cantidadDeLitros.getText(), LocalDateTime.now());
					JOptionPane.showMessageDialog(null, precio);
				} catch (DomainExceptionIn e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());

				}

				consultarPrecio.setEnabled(true);
			}
		});
		consultarPrecio.setBounds(51, 203, 151, 34);
		contentPane.add(consultarPrecio);

		JButton confirmarCompra = new JButton("Confirmar compra");
		confirmarCompra.setFont(new Font("Serif", Font.PLAIN, 14));
		confirmarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmarCompra.setEnabled(false);

				String tipo = (String) tipoDeNafta.getSelectedItem();
				try {
					miEstacion.confirmarCompra(tipo, cantidadDeLitros.getText(), LocalDateTime.now());
					JOptionPane.showMessageDialog(null, "La compra se ha realizado correctamente.");
				} catch (DomainExceptionIn e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());

				}

				confirmarCompra.setEnabled(true);
			}
		});
		confirmarCompra.setBounds(246, 203, 163, 34);
		contentPane.add(confirmarCompra);

		JButton btnNewButton = new JButton("Listar ventas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaVentas unaLista = new ListaVentas(miEstacion.ventas());
				unaLista.setVisible(true);
			}
		});
		btnNewButton.setBounds(457, 203, 126, 34);
		contentPane.add(btnNewButton);
	}

	private void cargarComboBox(JComboBox<String> comboBox) {

		List<NaftaRecord> tipos = new ArrayList<NaftaRecord>();

		tipos = miEstacion.tiposDeNafta();

		for (NaftaRecord unTipo : tipos) {
			comboBox.addItem(unTipo.tipo());

		}
	}
}
