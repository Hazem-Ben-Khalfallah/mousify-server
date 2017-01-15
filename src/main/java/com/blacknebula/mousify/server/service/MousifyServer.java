package com.blacknebula.mousify.server.service;

import com.blacknebula.mousify.server.dto.SomeRequest;
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
    private static int port = 18080;

    public static void main(String[] args) {
        Server server = new Server();
        Kryo kryo = server.getKryo();
        kryo.register(SomeRequest.class);
        server.start();

        try {
            Log.info("Mousify", "Listening on port " + port);
            server.bind(port);

        } catch (IOException e) {
            Log.error("Mousify", "Error while starting server", e);
        }

        server.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof SomeRequest) {
                    SomeRequest someRequest = (SomeRequest) object;
                    Log.info("Mousify", "Received data: " + someRequest.text);
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
                Log.info("Mousify", "Idle");
            }
        });
    }
}
