package ui;
import java.io.*;
import javax.swing.*;
import java.net.Socket;
import java.awt.GridLayout;
import java.net.InetSocketAddress;

public class Client 
{	
	public Client()
	{
        JTextField adminServerIpAddress = new JTextField();
        JTextField adminServerPort = new JTextField();
        JPanel panel = new JPanel(new GridLayout(2, 2));
       
        panel.add(new JLabel("User Name:"));
        panel.add(adminServerIpAddress);
        panel.add(new JLabel("Password:"));
        panel.add(adminServerPort);
       /* UIManager.put("OptionPane.cancelButtonText", "Cancel");
        UIManager.put("OptionPane.okButtonText", "Connect");*/
        int result = JOptionPane.showConfirmDialog(null, panel, "Login",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION)
        {
        	 InetSocketAddress isa = null; 
        	 Socket client = new Socket();
             try
             {
            	 isa = new InetSocketAddress(adminServerIpAddress.getText(),Integer.valueOf(adminServerPort.getText()));
            	 client.connect(isa);
            	 BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream());
            	 out.flush();
                 out.close();
            	 client.close();
             }
             catch (IOException|NumberFormatException e) 
             {
            	 JOptionPane.showMessageDialog(null, "hello");
            	 System.out.println("Socket連線有問題 !");
                 System.out.println("Exception :" + e.toString());
                 panel.setEnabled(true);
                 panel.setVisible(true);
             }
        }
        
        	
        /*if (result == JOptionPane.OK_OPTION) {
            System.out.println(
                 " " + adminServerIpAddress.getText()
                + " " + adminServerPort.getText());
        } else {
            System.out.println("Cancelled");
        }
        panel=null;*/
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
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
                new Client();
            }
        });		
	}
}