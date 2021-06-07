package server;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import server.packets.InputPacket;
import server.packets.PlayerPacket;
import server.packets.RotationPointPacket;

import java.util.Hashtable;

public class GameClient extends Listener {
    private static Client client;
    private static int tcpPort = 54555, udpPort = 54777;
    private static String ip = "localhost";
    private static boolean logMessages = true;
    private static Connection connection;
    private static short id;
    private static Hashtable<Short,PlayerPacket> shipList;

    public static void init() throws Exception
    {
        if(logMessages)
            System.out.println("---------- Connecting Client To Server ---------- ");
        client = new Client();
        client.getKryo().register(double[].class);
        client.getKryo().register(PlayerPacket.class);
        client.getKryo().register(InputPacket.class);
        client.getKryo().register(RotationPointPacket.class);


        client.start();
        client.connect(5000, ip,tcpPort,udpPort);
        client.addListener(new GameClient());
        shipList = new Hashtable<>();
        id = -1;
        if(logMessages)
            System.out.println("---------- Connection Success. Client Waiting To Receive Packet ---------- ");
    }

    public void connected(Connection c)
    {
        connection = c;
        //send packet of ship data here
    }

    public void received(Connection c, Object o)
    {
        if(o instanceof PlayerPacket)
        {
            PlayerPacket packet = (PlayerPacket)o;
            if(id == -1)
            {
                id = packet.getId();
            }

            shipList.put(packet.getId(), packet);

//            if(logMessages)
//            {
//                System.out.println("---------- Received Packet From Sever ----------");
//                System.out.println("id:" +  packet.getId() + "(" + packet.getPlayerX()+ "," + packet.getPlayerY() + ")");
//            }
        }

    }

    public static Hashtable<Short,PlayerPacket> getShipList()
    {
        return (Hashtable<Short,PlayerPacket>)shipList;
    }

    public static void update(GameContainer gameContainer)
    {
        Input input = gameContainer.getInput();
        connection.sendTCP(new InputPacket(id, input.isMousePressed(Input.MOUSE_LEFT_BUTTON), input.getMouseX(), input.getMouseY()));
    }

    public static void sendRotationPacket(short clientID, short id, float x, float y)
    {
        connection.sendTCP(new RotationPointPacket(clientID,id,x,y));
    }

    public static short getId()
    {
        return id;
    }
}
