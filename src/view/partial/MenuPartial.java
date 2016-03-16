package view.partial;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import view.listener.menu.CloseMenuItemClicked;
import view.listener.menu.StaticIPMenuItemClicked;

public class MenuPartial extends JMenuBar{
	private static final long serialVersionUID = 1L;
	private JMenu configurationMenu, fileMenu;
	
	public MenuPartial() {
		createFileMenu();
		createConfigurationMenu();
	}

	private void createFileMenu() {
		fileMenu = new JMenu("Bestand");
		fileMenu.setMnemonic(KeyEvent.VK_B);
		fileMenu.getAccessibleContext().setAccessibleDescription("Net-Message venster");		
		addCloseMenuItem();		
		this.add(fileMenu);
	}

	private void addCloseMenuItem() {
		JMenuItem closeMenuItem = new JMenuItem("Venster sluiten");
		closeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		closeMenuItem.getAccessibleContext().setAccessibleDescription("Venster sluiten");
		closeMenuItem.addActionListener(new CloseMenuItemClicked());
		fileMenu.add(closeMenuItem);
	}

	private void createConfigurationMenu() {
		configurationMenu = new JMenu("Configuratie");
		configurationMenu.setMnemonic(KeyEvent.VK_C);
		configurationMenu.getAccessibleContext().setAccessibleDescription("Configureer de Ethernet adapter");
		
		addStaticIPMenuItem();
		
		this.add(configurationMenu);
	}

	private void addStaticIPMenuItem() {
		JMenuItem staticIPEthernetMenuItem = new JMenuItem("Statisch IP-adres");
		staticIPEthernetMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		staticIPEthernetMenuItem.getAccessibleContext().setAccessibleDescription("Statisch IP-adres instellen");
		staticIPEthernetMenuItem.addActionListener(new StaticIPMenuItemClicked());
		configurationMenu.add(staticIPEthernetMenuItem);
	}

}
