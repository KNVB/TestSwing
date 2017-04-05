package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class MyTimer extends JFrame implements ActionListener 
{
	private JPanel topPanel;
	private JTextPane tPane = new JTextPane();
	public MyTimer()
	{
		topPanel = new JPanel();        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);            

        EmptyBorder eb = new EmptyBorder(new Insets(10, 10, 10, 10));

        tPane = new JTextPane();                
        tPane.setBorder(eb);
        //tPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        tPane.setMargin(new Insets(5, 5, 5, 5));
        tPane.setFont(tPane.getFont().deriveFont(Font.ITALIC));
        tPane.setFont(tPane.getFont().deriveFont(20f));	
        topPanel.add(tPane);
		
        getContentPane().add(topPanel);

        pack();
        setVisible(true);  
		
		Timer timer = new Timer(1000, this);
		/*JFrame topFrame=new JFrame();
		JPanel topPanel = new JPanel(); 
		                
		EmptyBorder eb = new EmptyBorder(new Insets(10, 10, 10, 10));
		
		tPane.setMargin(new Insets(5, 5, 5, 5));
        tPane.setFont(tPane.getFont().deriveFont(20f));
        tPane.setBorder(eb);
        
        topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        topFrame.setLocationRelativeTo(null);    
        topPanel.add(tPane);
        topFrame.getContentPane().add(topPanel);

        topFrame.pack();
        topFrame.setVisible(true);*/
    
        timer.start(); 
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Calendar now=new GregorianCalendar ();
		SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		fmt.setCalendar(now);
		String dateFormatted = fmt.format(now.getTime());
		String msg="Time now is " + dateFormatted+"\n";
		if ((now.get(Calendar.SECOND)%2)==0)
		{
			 appendToPane(tPane,msg,Color.RED,"Times New Roma");
		}
		else
			appendToPane(tPane,msg,Color.MAGENTA,"Vivaldi Italic");
		this.pack();
	}
	private void appendToPane(JTextPane tp,String msg, Color c,String font)
   {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, font);
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new MyTimer();
            }
        });
	}

	

}
