package ui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.UIManager;


public class MyTree 
{
	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() 
    {
    	try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Couldn't use system look and feel.");
        }
    	//Create and set up the window.
        JFrame frame = new JFrame("TreeDemo");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create a toolbar and give it an etched border.
        JToolBar toolBar = new JToolBar();
 		//toolBar.setBorder(new EtchedBorder());
 		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
 		
 		//Create the menu bar.
 		JMenuBar menuBar = new JMenuBar();
 			
		// Build the first menu.
 		JMenu menu = new JMenu("Server");
		menu.setMnemonic(KeyEvent.VK_S);
		menu.getAccessibleContext().setAccessibleDescription("Server related function.");

		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
		frame.add(new TreePanel());
		//Display the window.
        frame.pack();
        frame.setVisible(true);
        
    }
	public static void main(String[] args) {
		 //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

	}

}
