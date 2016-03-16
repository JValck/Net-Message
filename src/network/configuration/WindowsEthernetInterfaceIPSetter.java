package network.configuration;

import java.io.IOException;

public class WindowsEthernetInterfaceIPSetter implements
		EthernetInterfaceIPSetter {

	@Override
	public void setStaticIP() throws IOException, InterruptedException {
		String str1="192.168.0.1";
		String str2="255.255.255.0";
		String[] command1 = { "netsh", "interface", "ipv4", "set", "address",
		"name=", "\"Local Area Connection\"" ,"source=static", "addr=",str1,
		"mask=", str2};
		Process pp = java.lang.Runtime.getRuntime().exec(command1);
		int result = pp.waitFor();
	}

}
