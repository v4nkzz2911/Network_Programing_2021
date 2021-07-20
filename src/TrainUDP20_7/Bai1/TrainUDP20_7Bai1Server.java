/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainUDP20_7.Bai1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.Buffer;

/**
 *
 * @author er1nzz
 */
public class TrainUDP20_7Bai1Server {

    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket server = new DatagramSocket(10);
        byte[] buffer = new byte[1024];
        while (true) {

            buffer = new byte[1024];
            DatagramPacket sqrNumPacket = new DatagramPacket(buffer, buffer.length);
            server.receive(sqrNumPacket);

            String revNum = new String(sqrNumPacket.getData(), 0, sqrNumPacket.getLength());
            int n = Integer.parseInt(revNum);

            int result = n * n;

            buffer = new byte[1024];
            buffer = (result + "").getBytes();
            DatagramPacket resultPacket = new DatagramPacket(buffer, buffer.length, sqrNumPacket.getAddress(), sqrNumPacket.getPort());
            server.send(resultPacket);
        }

    }

}
