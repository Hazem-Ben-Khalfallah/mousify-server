package com.blacknebula.mousify.server.service;

import com.blacknebula.mousify.server.dto.MotionHistory;
import com.blacknebula.mousify.server.dto.MotionRequest;
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
        kryo.register(MotionRequest.class);
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
                if (object instanceof MotionRequest) {
                    MotionRequest motionRequest = (MotionRequest) object;
                    if (MotionHistory.getInstance().shouldIgnoreMove(motionRequest)) {
                        Log.info("Mousify", "Ignore : " + motionRequest.getX() + ", " + motionRequest.getY());
                        return;
                    }
                    MotionHistory.getInstance().updateHistory(motionRequest.getX(), motionRequest.getY());
                    Log.info("Mousify", "Move to: " + motionRequest.getX() + ", " + motionRequest.getY());
                    MouseRobot.move(motionRequest.getX(), motionRequest.getY());
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
