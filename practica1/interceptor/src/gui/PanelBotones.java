package gui;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelBotones extends JPanel {
	private JTextField txtState;
	JToggleButton tglbtnOnOff;
	JButton btnSpeed;
	
	

	/**
	 * Create the panel.
	 */
	public PanelBotones() {
		setLayout(null);
		
		
		JPanel panel = new JPanel();
		//panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBorder(BorderFactory.createLoweredBevelBorder());
		panel.setBounds(10, 161, 430, 128);
		add(panel);
		panel.setLayout(null);
		
		btnSpeed = new JButton("Acelerar");
		btnSpeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonAcelerarActionPerformed(e);
			}
		});
		btnSpeed.setBounds(210, 48, 112, 30);
		panel.add(btnSpeed);
		
		tglbtnOnOff = new JToggleButton("Encender");
		tglbtnOnOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BotonEncenderActionPerformed(e);
			}
		});
		tglbtnOnOff.setBackground(SystemColor.inactiveCaption);
		tglbtnOnOff.setBounds(76, 48, 124, 30);
		tglbtnOnOff.setForeground(Color.RED);
		panel.add(tglbtnOnOff);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(BorderFactory.createLoweredBevelBorder());
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
	
	synchronized private void BotonAcelerarActionPerformed (java.awt.event.ActionEvent evt){
		
		JButton button = (JButton)evt.getSource();
		// If engine is on
		if(tglbtnOnOff.isSelected()){
			
			txtState.setText("ACELERANDO");
			
			// TODO
			//aumentar velocidad
		}
		
	}
	
	synchronized private void BotonEncenderActionPerformed(java.awt.event.ActionEvent evt){
		
		JToggleButton button = (JToggleButton)evt.getSource();
		
		
		if(button.isSelected()){
			button.setForeground(Color.BLUE);
			button.setBackground(Color.GRAY);
			button.setText("Apagar");
			
			txtState.setText("Encendido");
		}
		else if(!button.isSelected()){
			button.setForeground(Color.RED);
			button.setBackground(Color.CYAN);
			button.setText("Encender");
			
			
			txtState.setText("Apagado");
			
			//TODO
			// Apagar velocidad
		}
	}
	
}
