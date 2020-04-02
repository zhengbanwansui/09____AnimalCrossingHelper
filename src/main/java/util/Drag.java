package util;

import java.awt.*;

import static java.awt.event.InputEvent.BUTTON1_MASK;
import static java.lang.Math.abs;

public class Drag {
    public static void drag(int x1, int y1, int x2,int y2) throws AWTException, InterruptedException {
        Robot robot = new Robot();

        robot.mouseMove(x1,y1);
        Thread.sleep(200);
        robot.mousePress(BUTTON1_MASK);
        Thread.sleep(100);
        robot.mouseMove(x2,y2);
        Thread.sleep(100);
        robot.mouseRelease(BUTTON1_MASK);
        Thread.sleep(100);
    }
}
