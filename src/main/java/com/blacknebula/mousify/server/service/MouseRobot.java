package com.blacknebula.mousify.server.service;

import com.blacknebula.mousify.server.utils.Logger;

import java.awt.*;

import static com.blacknebula.mousify.server.utils.Logger.Type.MOUSIFY;


/**
 * @author hazem
 */
public class MouseRobot {

    private static MouseRobot instance;
    private Robot robot;


    private MouseRobot(Robot robot) {
        this.robot = robot;
    }

    public static MouseRobot getInstance() {
        if (instance == null) {
            try {
                instance = new MouseRobot(new Robot());
            } catch (AWTException e) {
                Logger.error(MOUSIFY, "Error while Controlling mouse", e);
                return null;
            }
        }
        return instance;
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

    public void move(int x, int y) {
        final MouseEvent mouseEvent = new MouseEvent(robot);
        mouseEvent.moveMouse(x, y, 0, 0, 0);
    }

    public void scrollWheel(int wheelAmt) {
        final MouseEvent mouseEvent = new MouseEvent(robot);
        mouseEvent.scrollWheel(wheelAmt);
    }
}





