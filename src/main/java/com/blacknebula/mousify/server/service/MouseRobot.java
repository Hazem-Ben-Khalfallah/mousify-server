package com.blacknebula.mousify.server.service;

import com.esotericsoftware.minlog.Log;

import java.awt.*;


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
                Log.error("Mousify", "Error while Controlling mouse", e);
                return null;
            }
        }
        return instance;
    }

    public void leftClick() {
        final MouseEvent mouseEvent = new MouseEvent(robot);
        mouseEvent.leftClick();
    }

    public void rightClick() {
        final MouseEvent mouseEvent = new MouseEvent(robot);
        mouseEvent.rightClick();
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





