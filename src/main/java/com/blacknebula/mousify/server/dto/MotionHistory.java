package com.blacknebula.mousify.server.dto;

import com.blacknebula.mousify.server.event.MotionEvent;
import com.blacknebula.mousify.server.service.MouseEvent;

/**
 * @author hazem
 */

public class MotionHistory {
    private static final int Y_THRESHOLD = 10;
    private static final int X_THRESHOLD = 10;

    public static MotionHistory motionHistory;
    private int x;
    private int y;

    private MotionHistory(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static MotionHistory getInstance() {
        if (motionHistory == null) {
            motionHistory = new MotionHistory(MouseEvent.getCurrentMouseX(), MouseEvent.getCurrentMouseY());
        }
        return motionHistory;
    }

    public void incrementX(int dx) {
        this.x += dx;
    }

    public void incrementY(int dy) {
        this.y += dy;
    }

    public boolean shouldIgnoreMove(MotionEvent motionEvent) {
        return Math.abs(motionEvent.getDx()) < X_THRESHOLD && Math.abs(motionEvent.getDy()) < Y_THRESHOLD;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
