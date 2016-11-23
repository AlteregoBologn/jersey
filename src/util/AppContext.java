package util;

import java.util.logging.Logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * AppContext
 * 
 * @author andrea
 */
public class AppContext {

    static Logger log = Logger.getAnonymousLogger();
    public static String CONFIG_FILE = "applicationContext.xml";
    public static ClassPathXmlApplicationContext ctx = null;
    static private AppContext instance = null;

    static synchronized public AppContext getInstance() {
        if (instance == null) {
            log.info("Loading SPRING CONTEXT :" + CONFIG_FILE);
            ctx = new ClassPathXmlApplicationContext(CONFIG_FILE);
            log.info("Loaded SPRING CONTEXT :" + CONFIG_FILE);
            instance = new AppContext();
            // ctx.registerShutdownHook();
        }
        return instance;
    }

    private AppContext() {
    }

    public Object getBean(String name) {
        return ctx.getBean(name);
    }

    public String getMyName(Object o) {
        //
        return "";
        
    }

    public void refresh() {
        ctx.refresh();
    }

    public void start() {
    }

    public void stop() {
        log.info("Inizio");
        ctx.destroy();
        log.info("Fine");
        ctx = null;
    }
    
    public static void main(String s[]) {
    	// Manager man=(Manager) AppContext.getInstance().getBean("manager");
    }
    
}
