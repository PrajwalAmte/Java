package designPatterns;


public class SingletonLoggerDemo {
	public static void main(String[] args) {
		Logger logger1 = Logger.getInstance();
		logger1.log("Application started");
		
		Logger logger2 = Logger.getInstance();
		logger2.log("User Logged In");
		
		System.out.println(logger1 == logger2);
	}
}

class Logger {
	private static Logger instance;
	
	private Logger() {
		System.out.println("Logger initialised");
	}
	
	public static Logger getInstance() {
		if(instance == null) {
			synchronized (Logger.class) {
				if (instance == null) {
					instance = new Logger();
				}
			}
		}
		return instance;
	}
	public void log(String message) {
		System.out.println("[LOG]: " + message);
	}
}

