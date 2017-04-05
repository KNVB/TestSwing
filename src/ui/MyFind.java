package ui;

import static javax.swing.GroupLayout.Alignment.*;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class MyFind extends JFrame{

	public MyFind() 
	{
        JLabel label = new JLabel("Find What:");
        JTextField textField = new JTextField();
        JButton findButton = new JButton("Find");
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(label)
                .addComponent(textField));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                    .addComponent(label)
                    .addComponent(textField)) 
                .addGroup(layout.createParallelGroup(LEADING)));
        
        setTitle("Find");
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
	public static void main(String[] args) 
	{
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(
                                  "javax.swing.plaf.metal.MetalLookAndFeel");
                                //  "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                                //UIManager.getCrossPlatformLookAndFeelClassName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                new MyFind().setVisible(true);
            }
        });

	}

}
