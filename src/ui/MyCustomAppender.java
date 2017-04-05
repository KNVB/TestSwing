package ui;
import java.io.Serializable;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;

import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

public class MyCustomAppender extends AbstractAppender {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected MyCustomAppender(String name, Filter filter,
            Layout<? extends Serializable> layout) {
        super(name, filter, layout);
        // TODO Auto-generated constructor stub
    }

    @PluginFactory
    public static MyCustomAppender createAppender(
            @PluginAttribute("name") String name) {
        // note: in this example the class name matches the @Plugin name,
        // but this is not required.
        return new MyCustomAppender(name, null, null);
    }
	@Override
	public void append(LogEvent event) {
		// TODO Auto-generated method stub
		System.out.println("event message:"+event.getMessage());
	}

}
