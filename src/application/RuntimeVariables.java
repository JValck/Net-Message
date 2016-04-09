package application;

import java.util.HashMap;
import java.util.Map;

public class RuntimeVariables {
	private static Map<Variable ,Object> variables = new HashMap<>();
	
	public enum Variable{
		INTERFACE_NAME, MAIN_CONTROLLER, NETWORK_CONTROLLER, DESTINATION_ADDRESS, RECEIVING_IP_ADDRESS, RECEIVED_MESSAGE, CONFIGURATION_CONTROLLER, SCRIPTS_FOLDER;
	}
	
	public static void saveVariable(Variable variable, Object value) {
		variables.put(variable, value);
	}
	
	public static Object getVariable(Variable variable){
		return variables.get(variable);
	}
	
	public static boolean isRunningFromJar(){
		return RuntimeVariables.class.getResource("/icon.png").toString().contains("rsrc:");
	}
}
