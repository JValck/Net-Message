package domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import application.RuntimeVariables;
import application.RuntimeVariables.Variable;

/**
 * Extracts files from the jar files and
 * puts them in the system temp folder
 * @author JValck
 *
 */
public class JarExtractor {
	
	/**
	 * Extracts all the files under the path specified by the 
	 * first parameter.
	 * It will extract them in %TEMP%/pathInJar/
	 * @param pathInJar The path in the jar file to extract
	 * @throws IOException 
	 * @throws URISyntaxException 
	 */
	public void extract(String pathInJar) throws IOException{
		if(pathInJar.startsWith("/")) pathInJar = pathInJar.substring(1);//remove leading pathSeparator		
		String path = Files.createTempDirectory(pathInJar).toString();
		System.out.println("CREATED:\t"+path);
		RuntimeVariables.saveVariable(Variable.SCRIPTS_FOLDER, path);
		extractJar(pathInJar, path);
	}

	private void extractJar(String pathInJar, String path) throws IOException, FileNotFoundException {
		JarFile jar = getCurrentJarFile();
		Enumeration<JarEntry> enumEntries = jar.entries();
		while (enumEntries.hasMoreElements()) {
		    java.util.jar.JarEntry entry = (java.util.jar.JarEntry) enumEntries.nextElement();
		    if(!entry.getName().contains(pathInJar)) continue;		    
		    java.io.File f = new java.io.File(path + java.io.File.separator + entry.getName());
		    System.out.println("File: "+entry.getName());
		    if (entry.isDirectory()) { // if its a directory, create it
		        f.mkdir();
		        continue;
		    }
		    java.io.InputStream is = jar.getInputStream(entry); // get the input stream
		    java.io.FileOutputStream fos = new java.io.FileOutputStream(f);
		    while (is.available() > 0) {  // write contents of 'is' to 'fos'
		        fos.write(is.read());
		    }
		    fos.close();
		    is.close();
		}
		jar.close();
	}

	private JarFile getCurrentJarFile() throws IOException {
		File classPath = new File(System.getProperty("java.class.path"));
		File dir = classPath.getAbsoluteFile();
		String jarFile = dir.toString();
		System.out.println("Jar file:\t"+jarFile);
		return new java.util.jar.JarFile(jarFile);
	}
}
