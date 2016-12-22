package properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * @author Mathias
 */
public class PropertiesFile {
	private Properties properties;
	private String fileName = "zeeslag.properties";
	private volatile static PropertiesFile uniqueInstance;
	
	private PropertiesFile() {
		this.properties = new Properties();
		
		File f = new File(this.fileName);
		if(!f.exists() || f.isDirectory()) { 
		    this.create();
		}
		read();
	}
	
	public static PropertiesFile getInstance() {
		if (uniqueInstance == null) {
			synchronized (PropertiesFile.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new PropertiesFile();
				}
			}
		}
		return uniqueInstance;
	}
	
	public void read() {
		try {
			InputStream is = new FileInputStream(this.fileName);
			this.properties.load(is);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write() {
		try {
			OutputStream os = new FileOutputStream(this.fileName);
			this.properties.store(os, null);
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String get(String property) {
		return this.properties.getProperty(property);
	}

	public void set(String property, String value) {
		this.properties.setProperty(property, value);
	}
	
	public void create() {
		try{
		    PrintWriter writer = new PrintWriter(this.fileName, "UTF-8");
		    writer.println("placeStrategyAI=random");
		    writer.println("attackStrategyAI=random");
		    writer.close();
		} catch (Exception e) {
		   e.printStackTrace();
		}
	}
}
