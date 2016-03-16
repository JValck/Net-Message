package network.configuration;

import java.io.IOException;

public interface EthernetInterfaceIPSetter {
	void setStaticIP() throws IOException, InterruptedException;
}
