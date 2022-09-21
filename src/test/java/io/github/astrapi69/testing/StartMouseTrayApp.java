package io.github.astrapi69.testing;

import io.github.astrapi69.icon.ImageIconFactory;
import io.github.astrapi69.swing.dialog.JOptionPaneExtensions;
import io.github.astrapi69.swing.robot.MouseExtensions;
import io.github.astrapi69.swing.robot.RobotExtensions;

import javax.swing.*;
import java.awt.AWTException;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Robot;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class StartMouseTrayApp {
	static InterruptableThread currentExecutionThread;

	static SettingsModelBean settingsModelBean = SettingsModelBean.builder().build();

	static Robot robot;

	static Robot getRobot() {
		if(robot == null) {
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
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args)
	{
		final Frame frame = new Frame("MouseTrayApp");
		initializeComponents();
		frame.pack();
		frame.setVisible(false);
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
			ImageIcon trayImageIcon = ImageIconFactory
					.newImageIcon("io/github/astrapi69/silk/icons/anchor.png"
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
					MouseMoveSettingsPanel panel = new MouseMoveSettingsPanel();
					JOptionPane pane = new JOptionPane(panel, JOptionPane.INFORMATION_MESSAGE,
							JOptionPane.OK_CANCEL_OPTION);
					int option = JOptionPaneExtensions.getInfoDialogWithOkCancelButton(panel, "Settings",
							panel.getCmbVariableX());
					if (option == JOptionPane.OK_OPTION)
					{
						settingsModelBean = panel.getModelObject();
					}
				}
			});
			stopItem.setEnabled(currentExecutionThread != null && !currentExecutionThread.isInterrupted());
			stopItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(currentExecutionThread != null) {
						currentExecutionThread.setPriority(Thread.MIN_PRIORITY);
						currentExecutionThread.interrupt();
						while (!currentExecutionThread.isInterrupted()) {
							currentExecutionThread.interrupt();
						}
						stopItem.setEnabled(false);
						startItem.setEnabled(true);
					}
				}
			});

			startItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(currentExecutionThread != null) {
						currentExecutionThread.interrupt();
					}
					currentExecutionThread = new InterruptableThread() {
						@Override protected void process() {
							if(!isInterrupted()) {
								try {
									RobotExtensions
											.infiniteMoveMouse(
													getRobot(),
													MouseExtensions.getMousePosition().x + settingsModelBean.getXAxis(),
													MouseExtensions.getMousePosition().y + settingsModelBean.getYAxis(),
													settingsModelBean.getIntervalOfSeconds() * 1000);
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
