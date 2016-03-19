package view.listener.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import application.Launcher;
import application.RuntimeVariables;
import application.RuntimeVariables.Variable;
import controller.ConfigurationController;

public class DynamicIPMenuItemClicked implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			((ConfigurationController) RuntimeVariables.getVariable(Variable.CONFIGURATION_CONTROLLER)).setDynamicIPAddress();
		} catch (IOException | InterruptedException e1) {
			Launcher.showErrorMessageDialog(e1);
			e1.printStackTrace();
		}
	}

}
