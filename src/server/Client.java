package server;

import java.io.IOException;
import java.net.*;

public class Client extends Thread
{
    private InetAddress ipAddress;
    private DatagramSocket socket;

    public Client(String ipAddress)
    {
        try {
            this.ipAddress = InetAddress.getByName(ipAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run()
    {
        while(true)
        {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data,data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String message = new String(packet.getData());
            if(message.length() > 0)
              System.out.println("SERVER >" + message);
        }
    }

    public void sendData(byte[] data)
    {
        DatagramPacket packet = new DatagramPacket(data,data.length,ipAddress,1331);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
