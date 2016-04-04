package network.configuration;

import java.io.File;
import java.io.IOException;

import application.Launcher;
import application.RuntimeVariables;
import application.RuntimeVariables.Variable;
import domain.JarExtractor;

public class WindowsEthernetInterfaceIPSetter implements
EthernetInterfaceIPSetter {

	@Override
	public void setStaticIP() throws IOException, InterruptedException {
		String path = Launcher.class.getResource("/scripts/toStatic.bat").toString().replace("file:/", "");;
		if(runningFromJar()){
			JarExtractor extractor = new JarExtractor();
			extractor.extract("/scripts");
			path = RuntimeVariables.getVariable(Variable.SCRIPTS_FOLDER)+File.separator+"scripts"+File.separator+"toStatic.bat";
		}
		Process p = Runtime.getRuntime().exec(new String[] {"cmd.exe", "/c", path});
		p.waitFor();
	}

	@Override
	public void setDynamicIP() throws IOException, InterruptedException {	
		String path = getClass().getResource("/scripts/toDHCP.bat").toString().replace("file:/", "");
		if(runningFromJar()){
			JarExtractor extractor = new JarExtractor();
			extractor.extract("/scripts");
			path = RuntimeVariables.getVariable(Variable.SCRIPTS_FOLDER)+File.separator+"scripts"+File.separator+"toDHCP.bat";
		}		
		System.out.println(path);
		Process p = Runtime.getRuntime().exec(new String[] {"cmd.exe", "/c", path});
		p.waitFor();
	}

	@Override
	public boolean runningFromJar(){
		return RuntimeVariables.isRunningFromJar();
	}
}
