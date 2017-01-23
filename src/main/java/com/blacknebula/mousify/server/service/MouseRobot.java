package com.blacknebula.mousify.server.service;

import com.blacknebula.mousify.server.dto.MotionHistory;
import com.blacknebula.mousify.server.dto.ScreenSize;
import com.blacknebula.mousify.server.utils.Logger;

import java.awt.*;

import static com.blacknebula.mousify.server.utils.Logger.Type.MOUSIFY;


/**
 * @author hazem
 */
public class MouseRobot {

    private static MouseRobot instance;
    private Robot robot;
    private ScreenSize screenSize;


    private MouseRobot(Robot robot) {
        this.robot = robot;
    }

    public static MouseRobot getInstance() {
        if (instance == null) {
            try {
                instance = new MouseRobot(new Robot());
                instance.screenSize = getDeviceSize();
            } catch (AWTException e) {
                Logger.error(MOUSIFY, "Error while Controlling mouse", e);
                return null;
            }
        }
        return instance;
    }

    private static ScreenSize getDeviceSize() {
        final GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice[] devices = g.getScreenDevices();
        final ScreenSize screenSize = new ScreenSize();

        for (GraphicsDevice device : devices) {
            screenSize.setWidth(screenSize.getWidth() + device.getDisplayMode().getWidth());
            screenSize.setHeight(screenSize.getHeight() + device.getDisplayMode().getHeight());
        }
        Logger.info(MOUSIFY, "Screen size: %s x %s", screenSize.getWidth(), screenSize.getHeight());
        return screenSize;
    }

    public void leftButtonClick() {
        final MouseEvent mouseEvent = new MouseEvent(robot);
        mouseEvent.leftButtonClick();
    }

    public void leftButtonDown() {
        final MouseEvent mouseEvent = new MouseEvent(robot);
        mouseEvent.leftButtonDown();
    }

    public void leftButtonUp() {
        final MouseEvent mouseEvent = new MouseEvent(robot);
        mouseEvent.leftButtonUp();
    }

    public void rightButtonClick() {
        final MouseEvent mouseEvent = new MouseEvent(robot);
        mouseEvent.rightButtonClick();
    }

    public void move(int dx, int dy) {
        final MouseEvent mouseEvent = new MouseEvent(robot);

        final int distanceX = getDistance(MotionHistory.getInstance().getX(), dx, screenSize.getWidth());
        MotionHistory.getInstance().incrementX(distanceX);

        final int distanceY = getDistance(MotionHistory.getInstance().getY(), dy, screenSize.getHeight());
        MotionHistory.getInstance().incrementY(distanceY);
        Logger.info(MOUSIFY, "effective move %s; %s", distanceX, distanceY);

        mouseEvent.moveMouse(MotionHistory.getInstance().getX(), MotionHistory.getInstance().getY(), 0, 0, 0);
    }

    public void scrollWheel(int wheelAmt) {
        final MouseEvent mouseEvent = new MouseEvent(robot);
        mouseEvent.scrollWheel(wheelAmt);
    }

    private int getDistance(int current, int distance, int screenSize) {
        if (distance > 0) {
            return screenSize >= distance + current ? distance : screenSize - current;
        }
        return distance + current >= 0 ? distance : (-1) * current;
    }
}





