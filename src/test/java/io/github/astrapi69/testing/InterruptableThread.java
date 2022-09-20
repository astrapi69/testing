package io.github.astrapi69.testing;

public abstract class InterruptableThread extends Thread {
	private boolean interrupted = false;
	@Override
	public void run() {
		while (!interrupted) {
			process();
		}
	}

	@Override
	public void interrupt() {
		super.interrupt();
		interrupted = true;
	}

	protected abstract void process();
}
