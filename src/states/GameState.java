package states;

import assets.GameAsset;
import assets.Reader;
import assets.StillAsset;
import game.*;
import server.GameClient;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import server.GameServer;

import java.util.ArrayList;
import java.util.Hashtable;

public class GameState extends BasicGameState {

    private StateBasedGame game;
    private StillAsset background;
    private Hashtable<Short, StillAsset> players;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        game = stateBasedGame;
        players = new Hashtable<>();
        Reader.init();
        background = new StillAsset("res/images/TESTmapV1.png",0,0,true,null);
        // gameContainer.setShowFPS(false);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        int delta = i;
        GameClient.update(gameContainer);
        if(GameServer.initalized)
            GameServer.update();

        GameClient.getShipList().forEach((id,math) ->
        {
            if(players.get(math.getId()) == null)
            {
                try {
                    players.put(math.getId(), new StillAsset("res/images/FederationSmall1V2.png", math.getX(), math.getY(),true,null));
                } catch (SlickException e) {
                    e.printStackTrace();
                }
                System.out.println("created ship");
            }
            else
            {
                players.get(math.getId()).setX(math.getX());
                players.get(math.getId()).setY(math.getY());
            }
        });
        players.forEach((id,s) -> s.update());
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setBackground(new Color(24,20,37));
        graphics.setAntiAlias(true);
        background.render(graphics);
        players.forEach((id,s) -> s.render(graphics));
    }

    @Override
    public int getID() {
        return 0;
    }
}

