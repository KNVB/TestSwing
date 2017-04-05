package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class LoginBox implements ActionListener 
{
	JFrame frame=new JFrame("Login Form");
	JButton loginButton=new JButton("Login");
    JButton cancelButton=new JButton("Cancel");
	public LoginBox()
	{
		JTextField userName = new JTextField();
        JTextField password = new JTextField();
        JPanel panel = new JPanel(new GridLayout(3, 2));
        Border padding = BorderFactory.createEmptyBorder(10,10,10,10);
        panel.setBorder(padding);
        panel.add(new JLabel("User Name:"));
        panel.add(userName);
        panel.add(new JLabel("Password:"));
        panel.add(password);
        panel.add(loginButton);
        panel.add(cancelButton);
        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(loginButton))
		{
			JOptionPane.showMessageDialog(frame, "Eggs are not supposed to be green.");
		}
		else
		{
			frame.dispose();
		}
		
	}
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(
                                 // "javax.swing.plaf.metal.MetalLookAndFeel");
                                 // "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                                //UIManager.getCrossPlatformLookAndFeelClassName());
                            UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                new LoginBox();
            }
        });		
	}
}
