package com.blacknebula.mousify.server.service;

import com.blacknebula.mousify.server.event.ClickEvent;
import com.blacknebula.mousify.server.event.MotionEvent;
import com.blacknebula.mousify.server.event.ScrollEvent;
import com.blacknebula.mousify.server.utils.Logger;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

import static com.blacknebula.mousify.server.utils.Logger.Type.MOUSIFY;

/**
 * @author hazem
 */
public class MousifyServer {
    final static int TCP_PORT = 18181;
    final static int UDP_PORT = 28282;

    public static void main(String[] args) {
        final Server server = new Server();
        Kryo kryo = server.getKryo();
        kryo.register(MotionEvent.class);
        kryo.register(ClickEvent.class);
        kryo.register(ScrollEvent.class);
        server.start();

        try {
            Logger.info(MOUSIFY, "Listening on port TCP: %s, UDP: %s", TCP_PORT, UDP_PORT);
            server.bind(TCP_PORT, UDP_PORT);

        } catch (IOException e) {
            Logger.error(MOUSIFY, "Error while starting server", e);
        }

        server.addListener(new ServerListener());
    }
}
