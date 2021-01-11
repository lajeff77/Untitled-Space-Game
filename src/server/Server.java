package server;

import java.io.IOException;
import java.net.*;

public class Server extends Thread{

    private DatagramSocket socket;
    private boolean running;
    private byte[] buffer = new byte[1024];

    public Server()
    {
        try {
            socket = new DatagramSocket(1331);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run()
    {
        running = true;
        while(running)
        {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            packet = new DatagramPacket(buffer, buffer.length, packet.getAddress(),packet.getPort());
            String message = new String(packet.getData(),0,packet.getLength());

            if(message.equalsIgnoreCase("end"))
            {
                running = false;
            }
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }

   /* public void sendData(byte[] data, InetAddress ipAddress, int port)
    {
        DatagramPacket packet = new DatagramPacket(data,data.length,ipAddress,port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
}
