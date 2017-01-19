package com.blacknebula.mousify.server.service;

import com.blacknebula.mousify.server.dto.MotionHistory;
import com.blacknebula.mousify.server.event.ClickEvent;
import com.blacknebula.mousify.server.event.MotionEvent;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import java.io.IOException;

/**
 * @author hazem
 */
public class MousifyServer {
    public static int TCP_PORT = 18181;
    public static int UDP_PORT = 28282;

    public static void main(String[] args) {
        Server server = new Server();
        Kryo kryo = server.getKryo();
        kryo.register(MotionEvent.class);
        kryo.register(ClickEvent.class);
        server.start();

        try {
            Log.info("Mousify", "Listening on port TCP: " + TCP_PORT + ", UDP: " + UDP_PORT);
            server.bind(TCP_PORT, UDP_PORT);

        } catch (IOException e) {
            Log.error("Mousify", "Error while starting server", e);
        }

        server.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof MotionEvent) {
                    MotionEvent motionEvent = (MotionEvent) object;
                    if (MotionHistory.getInstance().shouldIgnoreMove(motionEvent)) {
                        Log.info("Mousify", "Ignore : " + motionEvent.getDx() + ", " + motionEvent.getDy());
                        return;
                    }
                    Log.info("Mousify", "Move by: " + motionEvent.getDx() + " , " + motionEvent.getDy());
                    MotionHistory.getInstance().updateHistory(motionEvent.getDx(), motionEvent.getDy());
                    final MouseRobot mouseRobot = MouseRobot.getInstance();
                    if (mouseRobot != null) {
                        mouseRobot.move(MotionHistory.getInstance().getX(), MotionHistory.getInstance().getY());
                    }
                } else if (object instanceof ClickEvent) {
                    Log.info("Mousify", "--> Click");
                    final MouseRobot mouseRobot = MouseRobot.getInstance();
                    if (mouseRobot != null) {
                        mouseRobot.leftClick();
                    }
                }
            }

            @Override
            public void connected(Connection connection) {
                super.connected(connection);
                Log.info("Mousify", "Connected");
            }

            @Override
            public void disconnected(Connection connection) {
                super.disconnected(connection);
                Log.info("Mousify", "Disconnected");
            }

            @Override
            public void idle(Connection connection) {
                super.idle(connection);
                //Log.info("Mousify", "Idle");
            }
        });
    }
}
