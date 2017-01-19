package com.blacknebula.mousify.server.service;

import com.esotericsoftware.minlog.Log;

import java.awt.*;


/**
 * @author hazem
 */
public class MouseRobot {
    public static void main(String[] args) {
        move(0, 740);
    }

    static void move(int x, int y) {
        try {
            final Robot robot = new Robot();
            final MouseEvent mouseEvent = new MouseEvent(robot);
            mouseEvent.moveMouse(x, y, 0, 0, 0);
        } catch (AWTException e) {
            Log.error("Mousify", "Error while Controlling mouse", e);
        }
    }
}





