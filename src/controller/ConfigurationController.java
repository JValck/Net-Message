package controller;

import java.io.IOException;

import network.configuration.EthernetInterfaceIPSetter;
import network.configuration.LinuxEthenetInterfaceSetter;
import network.configuration.WindowsEthernetInterfaceIPSetter;
import domain.OSType;

public class ConfigurationController {

	/**
	 * Sets a static IP-address to the Ethernet controller
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public void setStaticIPAddress() throws IOException, InterruptedException {
		switch (determineOs()) {
		case WINDOWS:
			EthernetInterfaceIPSetter iSetter = new WindowsEthernetInterfaceIPSetter();
			iSetter.setStaticIP();
			break;
		case LINUX:
			EthernetInterfaceIPSetter linux = new LinuxEthenetInterfaceSetter();
			linux.setStaticIP();
			break;
		default:
			//TODO: mac
			break;
		}
	}

	/** 
	 * As each OS-type has a separate manner of configuring
	 * the network interface, we have to determine the OS-type.
	 * @return The type of the OS.
	 */
	private OSType determineOs() {
		String osName = System.getProperty("os.name");
		if(osName.contains("Windows")){
			return OSType.WINDOWS;
		}else if(osName.contains("Mac")){
			return OSType.MAC;
		}else{
			return OSType.LINUX;
		}
	}

	public void setDynamicIPAddress() throws IOException, InterruptedException {
		switch (determineOs()) {
		case WINDOWS:
			EthernetInterfaceIPSetter iSetter = new WindowsEthernetInterfaceIPSetter();
			iSetter.setDynamicIP();
			break;
		case LINUX:
	
			break;
		default:
			//TODO: mac
			break;
		}
	}

}
