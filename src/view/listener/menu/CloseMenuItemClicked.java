package view.listener.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseMenuItemClicked implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	}

}
