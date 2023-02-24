package io.github.astrapi69.testing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import javax.swing.*;

import dorkbox.systemTray.MenuItem;
import dorkbox.systemTray.Separator;
import dorkbox.systemTray.SystemTray;
import io.github.astrapi69.icon.ImageIconFactory;
import io.github.astrapi69.model.BaseModel;
import io.github.astrapi69.swing.dialog.JOptionPaneExtensions;
import io.github.astrapi69.swing.robot.MouseExtensions;

public class PureSwingSystemTray
{

	static InterruptableThread currentExecutionThread;
	static InterruptableThread mouseTrackThread;

	static NavigableMap<LocalDateTime, Point> mouseTracks = new TreeMap<>();

	static SettingsModelBean settingsModelBean = SettingsModelBean.builder().build();
	static MouseMoveSettingsPanel panel = new MouseMoveSettingsPanel(
		BaseModel.of(settingsModelBean));
	static Robot robot;

	static Robot getRobot()
	{
		if (robot == null)
		{
			try
			{
				robot = new Robot();
			}
			catch (AWTException e)
			{
				throw new RuntimeException(e);
			}
		}
		return robot;
	}

	public static void main(final String[] args)
	{
		final JFrame frame = new JFrame("MouseTrayApp");
		initializeComponents();
		frame.setExtendedState(JFrame.ICONIFIED);
		frame.pack();
		frame.setVisible(false);
	}

	private static void initializeComponents()
	{
		SystemTray systemTray = SystemTray.get();
		if (systemTray == null)
		{
			throw new RuntimeException("Unable to load SystemTray!");
		}

		systemTray.installShutdownHook();
		ImageIcon trayImageIcon = ImageIconFactory
			.newImageIcon("io/github/astrapi69/silk/icons/anchor.png", "Keep moving");
		Image image = trayImageIcon.getImage();
		systemTray.setImage(image);

		systemTray.setStatus("Not Started");

		MenuItem startItem = new MenuItem("Start");
		MenuItem stopItem = new MenuItem("Stop");
		MenuItem exitItem = new MenuItem("Exit");
		MenuItem aboutItem = new MenuItem("About");

		exitItem.setCallback(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				systemTray.shutdown();
				System.exit(0);
			}
		});

		aboutItem.setCallback(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int option = JOptionPaneExtensions.getInfoDialogWithOkCancelButton(panel,
					"Settings", panel.getCmbVariableX());
				if (option == JOptionPane.OK_OPTION)
				{
					final String text = panel.getTxtIntervalOfSeconds().getText();
					if (text != null)
					{
						settingsModelBean.setIntervalOfSeconds(Integer.valueOf(text));
					}
					settingsModelBean = panel.getModelObject();
					if (currentExecutionThread != null && currentExecutionThread.isAlive())
					{
						stopMoving(stopItem, startItem);
						startMoving(stopItem, startItem);
					}
				}
			}
		});
		stopItem
			.setEnabled(currentExecutionThread != null && !currentExecutionThread.isInterrupted());
		stopItem.setCallback(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				stopMoving(stopItem, startItem);
				systemTray.setStatus("Stopped Moving");
			}
		});

		startItem.setCallback(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				startMoving(stopItem, startItem);
				systemTray.setStatus("Moving around");
			}

		});
		// Add components to pop-up menu
		systemTray.getMenu().add(aboutItem).setShortcut('q');
		systemTray.getMenu().add(new Separator());
		systemTray.getMenu().add(startItem);
		systemTray.getMenu().add(stopItem);
		systemTray.getMenu().add(new Separator());
		systemTray.getMenu().add(exitItem);
	}

	private static void startMoving(MenuItem stopItem, MenuItem startItem)
	{
		if (currentExecutionThread != null)
		{
			currentExecutionThread.interrupt();
		}
		if (mouseTrackThread != null)
		{
			mouseTrackThread.interrupt();
		}
		mouseTrackThread = new InterruptableThread()
		{
			@Override
			protected void process()
			{
				while (!isInterrupted())
				{
					try
					{
						mouseTracks.put(LocalDateTime.now(), MouseExtensions.getMousePosition());
						Thread.sleep(
							settingsModelBean.getIntervalOfMouseMovementsCheckInSeconds() * 1000);
					}
					catch (InterruptedException ex)
					{
						throw new RuntimeException(ex);
					}
				}
			}
		};
		currentExecutionThread = new InterruptableThread()
		{
			@Override
			protected void process()
			{
				while (!isInterrupted())
				{
					try
					{
						final Map.Entry<LocalDateTime, Point> lastTrackedMousePointEntry = mouseTracks.lastEntry();
						final Point lastTrackedMousePoint = lastTrackedMousePointEntry.getValue();
						final Point currentMousePosition = MouseExtensions.getMousePosition();
						// mouse not moved
						if(lastTrackedMousePoint.equals(currentMousePosition)) {
							MouseExtensions.setMousePosition(getRobot(),
									currentMousePosition.x + settingsModelBean.getXAxis(),
									currentMousePosition.y + settingsModelBean.getYAxis());
							Thread.sleep(settingsModelBean.getIntervalOfSeconds() * 1000);
						} else {

						}
					}
					catch (InterruptedException ex)
					{
						throw new RuntimeException(ex);
					}
				}
			}
		};
		mouseTrackThread.start();
		mouseTrackThread.setPriority(Thread.MIN_PRIORITY);
		currentExecutionThread.start();
		currentExecutionThread.setPriority(Thread.MIN_PRIORITY);
		stopItem.setEnabled(true);
		startItem.setEnabled(false);
	}

	private static void stopMoving(MenuItem stopItem, MenuItem startItem)
	{
		if (currentExecutionThread != null)
		{
			currentExecutionThread.setPriority(Thread.MIN_PRIORITY);
			currentExecutionThread.interrupt();
			while (!currentExecutionThread.isInterrupted())
			{
				currentExecutionThread.interrupt();
			}
			stopItem.setEnabled(false);
			startItem.setEnabled(true);
		}

		if (mouseTrackThread != null)
		{
			mouseTrackThread.setPriority(Thread.MIN_PRIORITY);
			mouseTrackThread.interrupt();
			while (!mouseTrackThread.isInterrupted())
			{
				mouseTrackThread.interrupt();
			}
		}
	}
}
