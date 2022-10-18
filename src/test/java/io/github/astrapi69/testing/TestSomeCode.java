package io.github.astrapi69.testing;

import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class TestSomeCode {

    public static void main(String[] args) throws AWTException, NoSuchAlgorithmException, InterruptedException {
        infiniteMoveMouse(new Robot(), (int) getMousePosition().getX(),
                (int) getMousePosition().getY(), 300000, 1, true );
    }

    public static Point getMousePosition()
    {
        return MouseInfo.getPointerInfo().getLocation();
    }
    public static void infiniteMoveMouse(final Robot robot, int x, int y, long everyMilliSeconds,
                                         int threadPriority, boolean considerMousePosition) throws InterruptedException, NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstanceStrong();
        if (threadPriority >= 1 && threadPriority <= 10)
        {
            Thread.currentThread().setPriority(threadPriority);
        }
        else
        {
            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        }
        Point nextRandomPoint = null;
        while (true)
        {
            if (considerMousePosition)
            {
                nextRandomPoint = new Point(secureRandom.nextInt(), secureRandom.nextInt());
                robot.mouseMove((int) nextRandomPoint.getX(), (int) nextRandomPoint.getY());
                Thread.sleep(everyMilliSeconds);
            }
            else
            {
                robot.mouseMove(secureRandom.nextInt(x),
                        secureRandom.nextInt(y));
                Thread.sleep(everyMilliSeconds);
            }
        }
    }
}
