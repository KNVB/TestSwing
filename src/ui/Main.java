package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class Main {
	private JDialog dialog = new JDialog();
	private String colorNames[] = { "Red: ", "Green: ", "Blue: " };
	private Label labels[] = new Label[3];
	private JSlider slider[] = new JSlider[3];
	private Label lb;

	public Main() {
		JPanel sliderPanel = new JPanel();
		sliderPanel.setLayout(new GridLayout(0, 1));
		for (int i = 0; i < slider.length; i++) {
			labels[i] = new Label(colorNames[i] + 255);
			sliderPanel.add(labels[i]);
			slider[i] = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 255);
			slider[i].setMinorTickSpacing(10);
			slider[i].setMajorTickSpacing(50);
			slider[i].setPaintTicks(true);
			slider[i].setPaintLabels(true);
			sliderPanel.add(slider[i]);
		}
		lb = new Label("Colour");
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setModal(true);
		dialog.add(sliderPanel, BorderLayout.CENTER);
		dialog.add(lb, BorderLayout.SOUTH);
		dialog.pack();
		dialog.setLocation(200, 200);
		dialog.setTitle("Colour Dialog");
		dialog.setVisible(true);
	}

	public static void main(String args[]) {
		Main colourDialog = new Main();
	}
}
