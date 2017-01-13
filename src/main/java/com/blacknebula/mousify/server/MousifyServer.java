package com.blacknebula.mousify.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import java.io.IOException;

/**
 * @author hazem
 */
public class MousifyServer {
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
        try {
            server.bind(54555);
        } catch (IOException e) {
            Log.error("Mousify", "Error while starting server", e);
        }

        server.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof String) {
                    Log.info("Mousify", "Received data: " + object);
                }
            }
        });
    }
}
