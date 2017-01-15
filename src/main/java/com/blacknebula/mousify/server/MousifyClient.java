package com.blacknebula.mousify.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.minlog.Log;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @author hazem
 */
public class MousifyClient {
    private static int port = 18080;

    public static void main(String[] args) {
        Client client = new Client();
        Kryo kryo = client.getKryo();
        kryo.register(SomeRequest.class);
        new Thread(client).start();
        try {
            client.connect(5000, InetAddress.getLocalHost(), port);
        } catch (IOException e) {
            Log.error("Moudify-Client", "Error while connecting", e);
        }

        SomeRequest request = new SomeRequest();
        request.text = "Here is the request";
        client.sendTCP(request);
    }
}
