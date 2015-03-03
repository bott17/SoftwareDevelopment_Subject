package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JToggleButton;

public class PanelBotones extends JPanel {
	private JTextField txtState;

	/**
	 * Create the panel.
	 */
	public PanelBotones() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 161, 430, 128);
		add(panel);
		panel.setLayout(null);
		
		JButton btnOnOff = new JButton("Encender");
		btnOnOff.setBounds(88, 48, 112, 30);
		panel.add(btnOnOff);
		
		JButton btnSpeed = new JButton("Acelerar");
		btnSpeed.setBounds(210, 48, 112, 30);
		panel.add(btnSpeed);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("New toggle button");
		tglbtnNewToggleButton.setBounds(21, 94, 121, 23);
		panel.add(tglbtnNewToggleButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 11, 430, 139);
		add(panel_1);
		panel_1.setLayout(null);
		
		txtState = new JTextField();
		txtState.setBackground(SystemColor.control);
		txtState.setForeground(Color.RED);
		txtState.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtState.setHorizontalAlignment(SwingConstants.CENTER);
		txtState.setText("APAGADO");
		txtState.setBounds(90, 30, 246, 53);
		panel_1.add(txtState);
		txtState.setColumns(10);

	}
	
}
