package ui;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Calendar;
import java.util.GregorianCalendar;


import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.core.config.AbstractConfiguration;

public class Log4jTest implements ActionListener {
	private JFrame topFrame=new JFrame ();
	private JPanel topPanel= new JPanel(); 
	private JTextPane tPane = new JTextPane();
	private static final Logger logger = LogManager.getLogger(Log4jTest.class);
	
	public Log4jTest()
	{
		topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		topFrame.setLocationRelativeTo(null);            

        EmptyBorder eb = new EmptyBorder(new Insets(10, 10, 10, 10));

        tPane = new JTextPane();                
        tPane.setBorder(eb);
        //tPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        tPane.setMargin(new Insets(5, 5, 5, 5));
        tPane.setFont(tPane.getFont().deriveFont(Font.BOLD));
        tPane.setFont(tPane.getFont().deriveFont(30f));	
        topPanel.add(tPane);
		
        topFrame.getContentPane().add(topPanel);

        topFrame.pack();
        topFrame.setVisible(true);  
    	configure();

		Timer timer = new Timer(1000, this);
		timer.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Calendar now=new GregorianCalendar ();
		/*SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		fmt.setCalendar(now);
		String dateFormatted = fmt.format(now.getTime());
		String msg="Time now is " + dateFormatted;*/
		//logger.error(msg);
		//logger.warn(msg);
		//logger.warn(now.get(Calendar.SECOND));
		logger.warn("中文");
	}
	private void configure() 
	{
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        AbstractConfiguration config = (AbstractConfiguration) ctx.getConfiguration();
        MyCustomImpl appender = MyCustomImpl.createAppender("abc",PatternLayout.createDefaultLayout(),null,null,tPane,topFrame);
        appender.start();
        config.addAppender(appender);
        AppenderRef[] refs = new AppenderRef[] { AppenderRef.createAppenderRef(appender.getName(), null, null) };
        LoggerConfig loggerConfig = LoggerConfig.createLogger("false", Level.ALL, LogManager.ROOT_LOGGER_NAME, "true", refs, null, config, null);
        loggerConfig.addAppender(appender, null, null);
        config.addLogger(LogManager.ROOT_LOGGER_NAME, loggerConfig);
        ctx.updateLoggers();
    }
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
            	Log4jTest log4jTest=new Log4jTest();
            }
        });
	}
}
