package com.blacknebula.mousify.server.service;

import com.esotericsoftware.minlog.Log;

import java.awt.*;


/**
 * @author hazem
 */
public class MouseRobot {
    public static void main(String[] args) {
        test();
    }

    static void test() {
        try {
            final Robot robot = new Robot();
            final MouseEvent mouseEvent = new MouseEvent(robot);
            mouseEvent.moveMouse(10, 740, 0, 0, 0);
            Log.info("Mousify", mouseEvent.getMouseX() + ":" + mouseEvent.getMouseY());
        } catch (AWTException e) {
            Log.error("Mousify", "Error while Controlling mouse", e);
        }
    }
}





