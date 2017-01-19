package com.blacknebula.mousify.server.dto;

import com.blacknebula.mousify.server.service.MouseEvent;

/**
 * @author hazem
 */

public class MotionHistory {
    private static final int Y_THRESHOLD = 20;
    private static final int X_THRESHOLD = 20;

    public static MotionHistory motionHistory;
    private int previousX;
    private int previousY;

    private MotionHistory(int x, int y) {
        this.previousX = x;
        this.previousY = y;
    }

    public static MotionHistory getInstance() {
        if (motionHistory == null) {
            motionHistory = new MotionHistory(MouseEvent.getCurrentMouseX(), MouseEvent.getCurrentMouseY());
        }
        return motionHistory;
    }

    public void updateHistory(int x, int y) {
        this.previousX = x;
        this.previousY = y;
    }

    public boolean shouldIgnoreMove(MotionRequest motionRequest) {
        return Math.abs(motionRequest.getX() - previousX) < X_THRESHOLD ||
                Math.abs(motionRequest.getY() - previousY) < Y_THRESHOLD;
    }
}
