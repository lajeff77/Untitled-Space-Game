package states;

import assets.Reader;
import assets.StillAsset;
import server.GameClient;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import server.GameServer;
import java.util.Hashtable;

public class GameState extends BasicGameState {

    private StateBasedGame game;
    private StillAsset background;
    private Hashtable<Short, StillAsset> players;
    public static Input input;
    private boolean logMessages = true;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        game = stateBasedGame;
        players = new Hashtable<>();
        Reader.init();
        background = new StillAsset("res/images/TESTmapV1.png",0,0,true,null, GameClient.getId());
        // gameContainer.setShowFPS(false);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        int delta = i;
        input = gameContainer.getInput();
        GameClient.update(gameContainer);
        if(GameServer.initalized)
            GameServer.update();

        GameClient.getShipList().forEach((id,math) ->
        {
            if(players.get(math.getId()) == null)
            {
                try {
                    players.put(math.getId(), new StillAsset("res/images/FederationSmall1V2.png", math.getPlayerX(), math.getPlayerY(),true,null, math.getId()));

                } catch (SlickException e) {
                    e.printStackTrace();
                }
                if(logMessages)
                    System.out.println("created ship");
            }
            else
            {
                players.get(math.getId()).setX(math.getPlayerX());
                players.get(math.getId()).setY(math.getPlayerY());
                players.get(math.getId()).processAngles(math.getAngles());
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

