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
		InputStream is = null;
		
		try {
			is = new FileInputStream(this.fileName);
			this.properties.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	public void write() {
		OutputStream os = null;
		
		try {
			os = new FileOutputStream(this.fileName);
			this.properties.store(os, null);
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	public String get(String property) {
		return this.properties.getProperty(property);
	}

	public void set(String property, String value) {
		this.properties.setProperty(property, value);
	}
	
	public void create() {
		PrintWriter writer = null;
		
		try{
		    writer = new PrintWriter(this.fileName, "UTF-8");
		    writer.println("placeStrategyAI=random");
		    writer.println("attackStrategyAI=random");
		} catch (Exception e) {
		   e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
}
