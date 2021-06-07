package server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import game.ShipMath;
import server.packets.InputPacket;
import server.packets.PlayerPacket;
import server.packets.RotationPointPacket;

import java.io.IOException;
import java.util.Hashtable;

public class GameServer extends Listener {

    private static Server server;
    private static int tcpPort = 54555, udpPort = 54777;
    private static boolean logMessages = true;
    private static Hashtable<Short,ShipMath> shipList;
    private static short id;
    public static boolean initalized = false;


    public static void init()
    {
        try {
            if(logMessages)
                System.out.println("---------- Starting Kryonet Server ---------- ");
            server = new Server();
            server.getKryo().register(double[].class);
            server.getKryo().register(PlayerPacket.class);
            server.getKryo().register(InputPacket.class);
            server.getKryo().register(RotationPointPacket.class);
            server.bind(tcpPort, udpPort);
            server.start();
            server.addListener(new GameServer());
            shipList = new Hashtable<>();
            id = 0;
            if(logMessages)
                System.out.println("---------- Kryonet Server Configuration Complete ---------- ");
            initalized = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void update()
    {
        for(short i = 0; i < shipList.size(); i++)
        {
            shipList.get(i).update();
            PlayerPacket packet = new PlayerPacket(i,shipList.get(i).getX(),shipList.get(i).getY(),shipList.get(i).getAngles());
            server.sendToAllTCP(packet);
        }
    }

    public void connected(Connection c) {
        if(logMessages)
            System.out.println("---------- " + c.getRemoteAddressTCP().getHostString() + " Client Connected To Game ----------");
        //handle adding off ships here
        ShipMath ship;
        ship = new ShipMath(id,200f * (id+1),200f);
        shipList.put(id,ship);
        PlayerPacket packet = new PlayerPacket(id,shipList.get(id).getX(),shipList.get(id).getY(), ship.getAngles());
        c.sendTCP(packet);
        id++;
    }

    public void received(Connection c, Object o)
    {
        if(o instanceof InputPacket)
        {
            InputPacket packet = (InputPacket) o;
            shipList.get(packet.getId()).handleInput(packet);
//            if (logMessages) {
//               System.out.println("---------- Received Packet From Client ----------");
//                System.out.println("client " + packet.getId() + " sent input");
//            }
        }

        if(o instanceof RotationPointPacket)
        {
            RotationPointPacket packet = (RotationPointPacket) o;
            ShipMath ship = shipList.get(packet.getShipId());
            System.out.println("adding rotation point to ship id " + packet.getShipId());
            ship.addPoint(packet.getId(),packet.getX(),packet.getY());
        }
    }

    public void disconnected(Connection c)
    {
        if(logMessages)
            System.out.println("---------- " + c.getRemoteAddressTCP().getHostString() + " Client Disconnected From Game ----------");
    }
}
