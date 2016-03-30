package network.configuration;

import java.io.IOException;

import javax.swing.JOptionPane;

public class LinuxEthenetInterfaceSetter implements EthernetInterfaceIPSetter {

	@Override
	public void setStaticIP() throws IOException, InterruptedException {
		String path = getClass().getResource("/setIpAddress.pl").toString().replace("file:/", "");		
		Process p = Runtime.getRuntime().exec(new String[] {"perl", path});
		p.waitFor();
	}

	@Override
	public void setDynamicIP() throws IOException, InterruptedException {
		JOptionPane.showMessageDialog(null, "Momenteel is er geen ingebouwde mogelijkheid om DHCP in te stellen.\n\n"
				+ "Ga naar de instellingen van uw besturingssysteem om DHCP in te schakelen.");
	}

}
