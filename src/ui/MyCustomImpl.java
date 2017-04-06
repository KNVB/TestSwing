package ui;

import java.awt.Color;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.appender.AppenderLoggingException;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

@Plugin(name = "MyCustomAppender", category = "Core", elementType = "appender", printObject = true)
public class MyCustomImpl extends AbstractAppender {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
	private final Lock readLock = rwLock.readLock();
	private JTextPane tp;
	private JFrame topFrame;

	protected MyCustomImpl(String name, Filter filter,
			Layout<? extends Serializable> layout,
			final boolean ignoreExceptions, JTextPane tp, JFrame topFrame) {
		super(name, filter, layout, ignoreExceptions);
		this.tp = tp;
		this.topFrame = topFrame;
	}

	@PluginFactory
	public static MyCustomImpl createAppender(
			@PluginAttribute("name") String name,
			@PluginElement("Layout") Layout<? extends Serializable> layout,
			@PluginElement("Filter") final Filter filter,
			@PluginAttribute("otherAttribute") String otherAttribute,
			JTextPane tp, JFrame topFrame) {
		if (name == null) {
			LOGGER.error("No name provided for MyCustomAppenderImp");
			return null;
		}
		if (layout == null) {
			layout = PatternLayout.createDefaultLayout();
		}
		return new MyCustomImpl(name, filter, layout, true, tp, topFrame);

	}
	@Override
	public void append(LogEvent event) {
		readLock.lock();
		try {
			Calendar now = new GregorianCalendar();
			SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			fmt.setCalendar(now);
			String dateFormatted = fmt.format(now.getTime());
			String msg = event.getMessage().getFormattedMessage()
					+ " Time now is " + dateFormatted + "\n";
			// System.out.println(msg);
			if ((now.get(Calendar.SECOND) % 2) == 0) {
				appendToPane(tp, msg, Color.RED, "標楷體");
			} else
				appendToPane(tp, msg, Color.MAGENTA, "Vivaldi Italic");

			topFrame.pack();
		} catch (Exception ex) {
			// if (!ignoreExceptions()) {
			throw new AppenderLoggingException(ex);
			// }
		} finally {
			readLock.unlock();
		}
	}
	private void appendToPane(JTextPane tp, String msg, Color c, String font) {
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
				StyleConstants.Foreground, c);

		aset = sc.addAttribute(aset, StyleConstants.FontFamily, font);
		aset = sc.addAttribute(aset, StyleConstants.Alignment,
				StyleConstants.ALIGN_JUSTIFIED);

		int len = tp.getDocument().getLength();
		tp.setCaretPosition(len);
		tp.setCharacterAttributes(aset, false);
		tp.replaceSelection(msg);
	}
}
