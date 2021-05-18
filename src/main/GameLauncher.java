package main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import server.GameClient;
import server.GameServer;
import states.GameState;

public class GameLauncher extends StateBasedGame {

    public static int screenWidth = 800;
    public static int screenHeight = 600;

    public GameLauncher()
    {
        super("Untitled Space Game");
    }

    public static void main(String[] args) throws Exception {
        //init server
        GameServer.init();
        GameClient.init();
        try
        {
            AppGameContainer app = new AppGameContainer(new GameLauncher());
            app.setDisplayMode(screenWidth,screenHeight, false);
            app.setAlwaysRender(true);
            app.setTargetFrameRate(60);
            app.start();

        }catch(SlickException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new GameState());
    }
}
