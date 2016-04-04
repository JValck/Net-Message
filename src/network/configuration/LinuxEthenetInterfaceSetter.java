package network.configuration;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import application.Launcher;
import application.RuntimeVariables;
import application.RuntimeVariables.Variable;
import domain.JarExtractor;

public class LinuxEthenetInterfaceSetter implements EthernetInterfaceIPSetter {

	@Override
	public void setStaticIP() throws IOException, InterruptedException {
		String path = Launcher.class.getResource("/scripts/setIpAddress.pl").toString().replace("file:/", "");
		if(runningFromJar()){
			JarExtractor extractor = new JarExtractor();
			extractor.extract("/scripts");
			path = RuntimeVariables.getVariable(Variable.SCRIPTS_FOLDER)+File.separator+"scripts"+File.separator+"setIpAddress.pl";
		}				
		Process p = Runtime.getRuntime().exec(new String[] {"perl", path});
		p.waitFor();
	}

	@Override
	public void setDynamicIP() throws IOException, InterruptedException {
		JOptionPane.showMessageDialog(null, "Momenteel is er geen ingebouwde mogelijkheid om DHCP in te stellen.\n\n"
				+ "Ga naar de instellingen van uw besturingssysteem om DHCP in te schakelen.");
	}

	@Override
	public boolean runningFromJar() {
		return RuntimeVariables.isRunningFromJar();
	}

}
