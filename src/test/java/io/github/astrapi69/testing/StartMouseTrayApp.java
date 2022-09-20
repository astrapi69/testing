package io.github.astrapi69.testing;

import io.github.astrapi69.icon.ImageIconFactory;
import io.github.astrapi69.swing.robot.MouseExtensions;
import io.github.astrapi69.swing.robot.RobotExtensions;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMouseTrayApp {
	static InterruptableThread currentExecutionThread;

	static Robot robot;

	static int xVariable = 1;

	static int yVariable = 1;

	static int intervalMilliseconds = 1000;

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
			final TrayIcon trayIcon =
					new TrayIcon(ImageIconFactory
							.newImageIcon("io/github/astrapi69/silk/icons/anchor.png").getImage());
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
					// TODO show dialog
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
													MouseExtensions.getMousePosition().x + xVariable,
													MouseExtensions.getMousePosition().y + yVariable,
													intervalMilliseconds);
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

}
