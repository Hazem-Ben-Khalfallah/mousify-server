package com.blacknebula.mousify.server.service;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Random;

class Mouse {
    static void moveMouse(Robot robot, Point s, Point e, int speed, int ranX, int ranY) {
        if (Math.abs(e.x - s.x) <= ranX && Math.abs(e.y - s.y) <= ranY)
            return;

        Point[] cooardList;
        double t;    //the time interval
        double k = .025;
        cooardList = new Point[4];

        //set the beginning and end points
        cooardList[0] = s;
        cooardList[3] = new Point(e.x + random(-ranX, ranX), e.y + (random(-ranY, ranY)));

        int xout = (int) (Math.abs(e.x - s.x) / 10);
        int yout = (int) (Math.abs(e.y - s.y) / 10);

        int x = 0, y = 0;

        x = s.x < e.x
                ? s.x + ((xout > 0) ? random(1, xout) : 1)
                : s.x - ((xout > 0) ? random(1, xout) : 1);
        y = s.y < e.y
                ? s.y + ((yout > 0) ? random(1, yout) : 1)
                : s.y - ((yout > 0) ? random(1, yout) : 1);
        cooardList[1] = new Point(x, y);

        x = e.x < s.x
                ? e.x + ((xout > 0) ? random(1, xout) : 1)
                : e.x - ((xout > 0) ? random(1, xout) : 1);
        y = e.y < s.y
                ? e.y + ((yout > 0) ? random(1, yout) : 1)
                : e.y - ((yout > 0) ? random(1, yout) : 1);
        cooardList[2] = new Point(x, y);

        double px = 0, py = 0;
        for (t = k; t <= 1 + k; t += k) {
            //use Berstein polynomials
            px = (cooardList[0].x + t * (-cooardList[0].x * 3 + t * (3 * cooardList[0].x -
                    cooardList[0].x * t))) + t * (3 * cooardList[1].x + t * (-6 * cooardList[1].x +
                    cooardList[1].x * 3 * t)) + t * t * (cooardList[2].x * 3 - cooardList[2].x * 3 * t) +
                    cooardList[3].x * t * t * t;
            py = (cooardList[0].y + t * (-cooardList[0].y * 3 + t * (3 * cooardList[0].y -
                    cooardList[0].y * t))) + t * (3 * cooardList[1].y + t * (-6 * cooardList[1].y +
                    cooardList[1].y * 3 * t)) + t * t * (cooardList[2].y * 3 - cooardList[2].y * 3 * t) +
                    cooardList[3].y * t * t * t;
            robot.mouseMove((int) px, (int) py);
            robot.delay(random(speed, speed * 2));
        }
    }

    static void leftClick(Robot robot) {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    private static int random(int min, int max) {

        if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        if (min == max) {
            return max;
        }

        final Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


}