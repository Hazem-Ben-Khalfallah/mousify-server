package com.blacknebula.mousify.server.service;

import java.awt.*;

public class MouseEvent {
    private Robot robot;

    public MouseEvent(Robot robot) {
        this.robot = robot;
    }

    public int getMouseX() {
        return MouseInfo.getPointerInfo().getLocation().x;
    }

    public int getMouseY() {
        return MouseInfo.getPointerInfo().getLocation().y;
    }

    public void moveMouse(int speed, int destX, int destY, int ranX, int ranY) {
        Mouse.moveMouse(robot, new Point(getMouseX(), getMouseY()), new Point(destX, destY), speed, ranX, ranY);
    }
}