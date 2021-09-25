package gameClient;
import GUI.MyFrame;
import Server.Game_Server_Ex2;
import api.*;

import static java.lang.Thread.sleep;

/**
 * This is the engine class of the game.
 */
public class Ex2 implements Runnable{

    public static game_service _game;
    static Arena _ar;
    static MyFrame _mainFrame;
    public static long id = -1;
    public static int level = 0;
    public static boolean playButton = true;

    public static void main(String[] args) {
        try {
            id = Integer.parseInt(args[0]);
            level = Integer.parseInt(args[1]);
        }
        catch(Exception e) {
            id = -1;
            level = 0;
            playButton = false;
        }
        Thread client = new Thread(new Ex2());
        client.start();
    }

    @Override
    public void run() {

        _mainFrame = new MyFrame();
        _mainFrame.showPanel(0);
        _mainFrame.pack();

        while (!playButton) {
            Thread.onSpinWait();
        }

        _game = Game_Server_Ex2.getServer(level);
        _ar = new Arena(_game);
        _mainFrame.InitGamePanel(_ar);
        _mainFrame.showPanel(1);
        _mainFrame.pack();
        _game.login(id);
        _game.startGame();
        int dt;
        while(_game.isRunning()) {
                _ar.moveAgents();
                _mainFrame.repaint();
                dt = isCloseToPokemon()? 20 : 120;
            try {
                sleep(dt);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(_game.toString());
    }

    /**
     * Checks if one of the agents is closed to some pokemon,
     * if so, than we want to increase the amount of movements in order
     * to make sure that this agent wont miss the pokemon.
     * @return if one of the agents is near a pokemon
     */
    private boolean isCloseToPokemon() {
        for(Agent ag : _ar.JsonToAgents()){
            for(Pokemon p : _ar.getPokemons()) {
                if (ag.get_curr_edge() == p.get_edge() &&
                        ag.getLocation().distance(p.getLocation()) < 0.001 * ag.getSpeed())
                    return true;
            }
        }
        return false;
    }
}
