package com.blacknebula.mousify.server.service;

import java.awt.*;

public class MouseEvent {
    private Robot robot;

    MouseEvent(Robot robot) {
        this.robot = robot;
    }

    public static int getCurrentMouseX() {
        return MouseInfo.getPointerInfo().getLocation().x;
    }

    public static int getCurrentMouseY() {
        return MouseInfo.getPointerInfo().getLocation().y;
    }

    void moveMouse(int destX, int destY, int ranX, int ranY, int speed) {
        Mouse.moveMouse(robot, new Point(getCurrentMouseX(), getCurrentMouseY()), new Point(destX, destY), speed, ranX, ranY);
    }

    void leftClick() {
        Mouse.leftClick(robot);
    }
}