package com.blacknebula.mousify.server.service;

import com.blacknebula.mousify.server.dto.MotionHistory;
import com.blacknebula.mousify.server.event.ClickEvent;
import com.blacknebula.mousify.server.event.MotionEvent;
import com.blacknebula.mousify.server.event.ScrollEvent;
import com.blacknebula.mousify.server.utils.Logger;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import static com.blacknebula.mousify.server.utils.Logger.Type.MOUSIFY;

/**
 * @author hazem
 */
public class ServerListener extends Listener {

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof MotionEvent) {
            MotionEvent motionEvent = (MotionEvent) object;
            if (MotionHistory.getInstance().shouldIgnoreMove(motionEvent)) {
                Logger.info(MOUSIFY, "Ignore : %s, %s", motionEvent.getDx(), motionEvent.getDy());
                return;
            }
            Logger.info(MOUSIFY, "Move by: %s, %s", motionEvent.getDx(), motionEvent.getDy());
            final MouseRobot mouseRobot = MouseRobot.getInstance();
            if (mouseRobot != null) {
                mouseRobot.move(motionEvent.getDx(), motionEvent.getDy());
            }
        } else if (object instanceof ClickEvent) {
            final ClickEvent clickEvent = (ClickEvent) object;
            Logger.info(MOUSIFY, "%s %s", (clickEvent.isLeft() ? "Left" : "Right"), clickEvent.getAction());
            final MouseRobot mouseRobot = MouseRobot.getInstance();
            if (mouseRobot != null) {
                if (clickEvent.isLeft()) {
                    if (ClickEvent.ACTION_CLICK.equals(clickEvent.getAction())) {
                        mouseRobot.leftButtonClick();
                    } else if (ClickEvent.ACTION_DOWN.equals(clickEvent.getAction())) {
                        mouseRobot.leftButtonDown();
                    } else if (ClickEvent.ACTION_UP.equals(clickEvent.getAction())) {
                        mouseRobot.leftButtonUp();
                    }
                } else {
                    mouseRobot.rightButtonClick();
                }
            }
        } else if (object instanceof ScrollEvent) {
            final ScrollEvent scrollEvent = (ScrollEvent) object;
            Logger.info(MOUSIFY, "scroll by %s", scrollEvent.getAmount());
            final MouseRobot mouseRobot = MouseRobot.getInstance();
            if (mouseRobot != null) {
                mouseRobot.scrollWheel(scrollEvent.getAmount());
            }
        }
    }

    @Override
    public void connected(Connection connection) {
        super.connected(connection);
        Logger.info(MOUSIFY, "Connected");
    }

    @Override
    public void disconnected(Connection connection) {
        super.disconnected(connection);
        Logger.info(MOUSIFY, "Disconnected");
    }
}
