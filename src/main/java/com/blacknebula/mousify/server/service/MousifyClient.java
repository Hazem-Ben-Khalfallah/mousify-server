package com.blacknebula.mousify.server.service;

import com.blacknebula.mousify.server.dto.MotionRequest;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.minlog.Log;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @author hazem
 */
public class MousifyClient {

    public static void main(String[] args) {
        Client client = new Client();
        Kryo kryo = client.getKryo();
        kryo.register(MotionRequest.class);
        new Thread(client).start();
        try {
            client.connect(5000, InetAddress.getLocalHost(), MousifyServer.TCP_PORT, MousifyServer.UDP_PORT);
        } catch (IOException e) {
            Log.error("Moudify-Client", "Error while connecting", e);
        }


        final MotionRequest request = new MotionRequest(0, 0);
        client.sendTCP(request);
    }
}
