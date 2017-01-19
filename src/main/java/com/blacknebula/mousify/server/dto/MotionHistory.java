package com.blacknebula.mousify.server.dto;

import com.blacknebula.mousify.server.service.MouseEvent;

/**
 * @author hazem
 */

public class MotionHistory {
    private static final int Y_THRESHOLD = 20;
    private static final int X_THRESHOLD = 20;

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

    public void updateHistory(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public boolean shouldIgnoreMove(MotionRequest motionRequest) {
        return Math.abs(motionRequest.getDx()) < X_THRESHOLD && Math.abs(motionRequest.getDy()) < Y_THRESHOLD;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
