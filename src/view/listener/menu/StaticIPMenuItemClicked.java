package view.listener.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.ConfigurationController;

import application.Launcher;
import application.RuntimeVariables;
import application.RuntimeVariables.Variable;

public class StaticIPMenuItemClicked implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			((ConfigurationController) RuntimeVariables.getVariable(Variable.CONFIGURATION_CONTROLLER)).setStaticIPAddress();
		} catch (IOException | InterruptedException e1) {
			Launcher.showErrorMessageDialog(e1);
			e1.printStackTrace();
		}
	}

}
