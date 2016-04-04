package network.configuration;

import java.io.IOException;

import application.Launcher;

public class WindowsEthernetInterfaceIPSetter implements
EthernetInterfaceIPSetter {

	@Override
	public void setStaticIP() throws IOException, InterruptedException {
		//remove the /scripts prefix, as this part will be ignored in the jar
		String path = Launcher.class.getResource("/toStatic.bat").toString().replace("file:/", "");	
		System.out.println(path);
		Process p = Runtime.getRuntime().exec(new String[] {"cmd.exe", "/c", path});
		p.waitFor();
		/*BufferedReader stdInput = new BufferedReader(new 
				InputStreamReader(p.getInputStream()));

		BufferedReader stdError = new BufferedReader(new 
				InputStreamReader(p.getErrorStream()));

		// read the output from the command
		System.out.println("Here is the standard output of the command:\n");
		String s = null;
		while ((s = stdInput.readLine()) != null) {
			System.out.println(s);
		}

		// read any errors from the attempted command
		System.out.println("Here is the standard error of the command (if any):\n");
		while ((s = stdError.readLine()) != null) {
			System.out.println(s);
		}*/


	}

	@Override
	public void setDynamicIP() throws IOException, InterruptedException {
		//remove the /scripts prefix, as this part will be ignored in the jar
				String path = getClass().getResource("/toDHCP.bat").toString().replace("file:/", "");		
				Process p = Runtime.getRuntime().exec(new String[] {"cmd.exe", "/c", path});
				p.waitFor();
	}

}
