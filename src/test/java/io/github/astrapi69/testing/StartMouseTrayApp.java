package io.github.astrapi69.testing;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Robot;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import io.github.astrapi69.icon.ImageIconFactory;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.swing.dialog.JOptionPaneExtensions;
import io.github.astrapi69.swing.robot.MouseExtensions;

public class StartMouseTrayApp {
	static InterruptableThread currentExecutionThread;

	static SettingsModelBean settingsModelBean = SettingsModelBean.builder().build();
	static MouseMoveSettingsPanel panel = new MouseMoveSettingsPanel(BaseModel.of(settingsModelBean));
	static Robot robot;

	static Robot getRobot() {
		if (robot == null) {
			try {
				robot = new Robot();
			} catch (AWTException e) {
				throw new RuntimeException(e);
			}
		}
		return robot;
	}

	/**
	 * Start the Application
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {
		final JFrame frame = new JFrame("MouseTrayApp");
		initializeComponents();
		frame.setExtendedState(JFrame.ICONIFIED);
		frame.pack();
		frame.setVisible(true);
	}

	static SystemTray newSystemTray(final TrayIcon trayIcon, final PopupMenu popup) throws AWTException {
		final SystemTray systemTray = SystemTray.getSystemTray();
		trayIcon.setPopupMenu(popup);
		systemTray.add(trayIcon);
		return systemTray;
	}

	static void initializeComponents() {

		//Check the SystemTray is supported
		if (!SystemTray.isSupported()) {
			System.out.println("SystemTray is not supported");
		} else {
			final PopupMenu popup = new PopupMenu();
			ImageIcon trayImageIcon = ImageIconFactory.newImageIcon("io/github/astrapi69/silk/icons/anchor.png"
					//							, "Keep moving"
			);
			Image image = trayImageIcon.getImage();
			final TrayIcon trayIcon = new TrayIcon(image);
			final SystemTray tray = SystemTray.getSystemTray();

			// Create a pop-up menu components
			MenuItem aboutItem = new MenuItem("About");

			MenuItem startItem = new MenuItem("Start");
			MenuItem stopItem = new MenuItem("Stop");
			MenuItem exitItem = new MenuItem("Exit");

			exitItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tray.remove(trayIcon);
					System.exit(0);
				}
			});

			aboutItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int option = JOptionPaneExtensions.getInfoDialogWithOkCancelButton(panel, "Settings", panel.getCmbVariableX());
					if (option == JOptionPane.OK_OPTION) {
						final String text = panel.getTxtIntervalOfSeconds().getText();
						if (text != null) {
							settingsModelBean.setIntervalOfSeconds(Integer.valueOf(text));
						}
						settingsModelBean = panel.getModelObject();
						if (currentExecutionThread != null && currentExecutionThread.isAlive()) {
							stopMoving(stopItem, startItem);
							startMoving(stopItem, startItem);
						}
					}
				}
			});
			stopItem.setEnabled(currentExecutionThread != null && !currentExecutionThread.isInterrupted());
			stopItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					stopMoving(stopItem, startItem);
				}
			});

			startItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					startMoving(stopItem, startItem);
				}

			});
			//Add components to pop-up menu
			popup.add(aboutItem);
			popup.addSeparator();
			popup.add(startItem);
			popup.add(stopItem);
			popup.addSeparator();
			popup.add(exitItem);

			try {
				newSystemTray(trayIcon, popup);
			} catch (AWTException e) {
				System.out.println("TrayIcon could not be added.");
			}
		}
	}

	private static void startMoving(MenuItem stopItem, MenuItem startItem) {
		if (currentExecutionThread != null) {
			currentExecutionThread.interrupt();
		}
		currentExecutionThread = new InterruptableThread() {
			@Override protected void process() {
				while (!isInterrupted()) {
					try {
						MouseExtensions.setMousePosition(getRobot(), MouseExtensions.getMousePosition().x + settingsModelBean.getXAxis(),
								MouseExtensions.getMousePosition().y + settingsModelBean.getYAxis());
						Thread.sleep(settingsModelBean.getIntervalOfSeconds() * 1000);
					} catch (InterruptedException ex) {
						throw new RuntimeException(ex);
					}
				}
			}
		};
		currentExecutionThread.start();
		stopItem.setEnabled(true);
		startItem.setEnabled(false);
	}

	private static void stopMoving(MenuItem stopItem, MenuItem startItem) {
		if (currentExecutionThread != null) {
			currentExecutionThread.setPriority(Thread.MIN_PRIORITY);
			currentExecutionThread.interrupt();
			while (!currentExecutionThread.isInterrupted()) {
				currentExecutionThread.interrupt();
			}
			stopItem.setEnabled(false);
			startItem.setEnabled(true);
		}
	}

	//Obtain the image URL
	protected static Image createImage(String path, String description) {
		URL imageURL = StartMouseTrayApp.class.getResource(path);

		if (imageURL == null) {
			System.err.println("Resource not found: " + path);
			return null;
		} else {
			return (new ImageIcon(imageURL, description)).getImage();
		}
	}

}
