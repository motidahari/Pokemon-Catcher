package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import api.*;
import gameClient.*;
import gameClient.util.Point3D;
import gameClient.util.Range;
import gameClient.util.Range2D;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *This class represents the main panel containing the game
 */
public class MyPanel extends JPanel{

    JLabel t = new JLabel();
    private final JCheckBox show_edges = new JCheckBox("edges");
    private final JCheckBox show_nodes = new JCheckBox("nodes");
    private final JCheckBox show_nodes_numbers = new JCheckBox("nodes numbers");
    private final JCheckBox show_pokemons_values = new JCheckBox("pokemons values");
    int time, duration = -1, grade, moves, level;
    private Arena _arena;
    static gameClient.util.Range2Range _w2f;
    private final Color[] _agents_colors = new Color[]{
            new Color(255, 0, 221),
            new Color(27, 255, 0),
            new Color(0, 255, 247),
            new Color(255, 0, 0),
            new Color(23,4,68),
            new Color(23,4,68)
    };
    private final Image[] _pokemons_images = new Image[]{
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/pika.png")),
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/poke1.png")),
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/poke2.png")),
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/poke3.png")),
            Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/poke4.png"))
    };

    MyPanel() {
        super();
        add(t);
        show_nodes.setSelected(true);
        show_edges.setSelected(true);
        show_nodes_numbers.setSelected(true);
        show_pokemons_values.setSelected(true);

        show_nodes.setOpaque(false);
        show_edges.setOpaque(false);
        show_nodes_numbers.setOpaque(false);
        show_pokemons_values.setOpaque(false);

        add(show_edges);
        add(show_nodes);
        add(show_nodes_numbers);
        add(show_pokemons_values);
        setOpaque(true);
    }
    public void update(Arena ar) {
        this._arena = ar;
        updateFrame();
    }

    private void updateFrame() {
        time = (int) (Ex2._game.timeToEnd()/1000);
        Range rx = new Range(50, this.getWidth()-50);
        Range ry = new Range(this.getHeight()-30, 80);
        Range2D frame = new Range2D(rx,ry);
        directed_weighted_graph g = _arena.getGraph();
        _w2f = Arena.w2f(g, frame);
    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g);
        int w = this.getWidth();
        int h = this.getHeight();
        Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/background.png"));
        g2.drawImage(img, 0,0, w, h, this);

        fetchData();
        t.setText("Level: "+ level +" Timer: "+ time+"/"+duration+" Grade: "+grade+" Moves: "+moves+"/"+duration*10+"     Display:");

        updateFrame();
        drawGraph(g2);
        drawAgents(g2);
        drawPokemons(g2);
    }

    /**
     * Fetching all the necessary information about the game from a json string from the server
     */
    private void fetchData() {
        try {
            JSONObject line = new JSONObject(Ex2._game.toString());
            JSONObject ttt = line.getJSONObject("GameServer");
            grade = (int) ttt.getDouble("grade");
            moves = (int) ttt.getDouble("moves");
            level = ttt.getInt("game_level");
            time = (int) (Ex2._game.timeToEnd() / 1000);
            if(duration == -1)
                duration = time+1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws all the graph's nodes and edges.
     */
    private void drawGraph(Graphics2D g) {
        directed_weighted_graph gg = _arena.getGraph();
        g.setStroke(new BasicStroke(2));
        if(show_edges.isSelected()) {
            for (node_data n : gg.getV()) {
                for (edge_data e : gg.getE(n.getKey())) {
                    g.setColor(new Color(0, 0, 0, 32));
                    drawEdge(e, g);
                }
            }
        }
        g.setColor(Color.blue);
        for (node_data n : gg.getV()) {
            drawNode(n, g);
        }
    }

    /**
     *Draws all the pokemons on the screen
     */
    private void drawPokemons(Graphics2D g) {
        List<Pokemon> pokemons = _arena.getPokemons();
        if(pokemons != null) {
            for (Pokemon f : pokemons) {
                int val = (int)f.getValue();
                Point3D c = f.getLocation();
                int r = (int) (0.03 * this.getHeight());
                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.BOLD, 16));
                if (c != null) {
                    geo_location fp = _w2f.world2frame(c);
                    Image img1 = getPokemonIconByValue(f.getValue());
                    g.drawImage(img1, (int) fp.x() - r, (int) fp.y() - r, 2 * r, 2 * r, this);
                    if(show_pokemons_values.isSelected())
                        g.drawString(""+val, (int)fp.x()-2*r, (int)fp.y()-2*r);
                }
            }
        }
    }

    private Image getPokemonIconByValue(double value) {
        if(value <= 5)  return _pokemons_images[0];
        if(value <= 10) return _pokemons_images[1];
        if(value <= 12) return _pokemons_images[2];
        if(value <= 14) return _pokemons_images[3];
        else            return _pokemons_images[4];
    }

    /**
     *Draws all the agents on the screen
     */
    private void drawAgents(Graphics2D g) {
        List<Agent> rs = _arena.JsonToAgents();
        g.setStroke(new BasicStroke(3));
        for(Agent ag: rs){
            g.setColor(_agents_colors[ag.getID()]);
            List<node_data> path = Arena.paths.get(ag.getID());
            for(int i=0; i< path.size()-1; i++){
                edge_data e = new EdgeData(path.get(i).getKey(), path.get(i+1).getKey(),0);
                drawEdge(e, g);
            }
            geo_location c = ag.getLocation();
            int r = (int)(0.03 * this.getHeight());
            if(c!=null) {
                geo_location fp = _w2f.world2frame(c);
                Image img1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/POKEBALL.gif"));
                g.drawImage(img1, (int)fp.x()-r, (int)fp.y()-r, 2*r, 2*r,this);
                g.drawString(""+ag.getID(), (int)fp.x()-2*r, (int)fp.y()-2*r);
            }
        }
    }

    private void drawNode(node_data n, Graphics2D g) {
        geo_location pos = n.getLocation();
        geo_location fp = _w2f.world2frame(pos);
        int r = (int)(0.009 * this.getHeight());
        if(show_nodes.isSelected()) {
            g.fillOval((int)fp.x()-r, (int)fp.y()-r, 2*r, 2*r);
            g.fill3DRect((int)fp.x()-r, (int)fp.y()-r, 2*r, 2*r, true);
        }
        if(show_nodes_numbers.isSelected())
            g.drawString(""+n.getKey(), (int)fp.x()-2*r, (int)fp.y()-2*r);
    }

    private void drawEdge(edge_data e, Graphics2D g) {
        directed_weighted_graph gg = _arena.getGraph();
        geo_location s = gg.getNode(e.getSrc()).getLocation();
        geo_location d = gg.getNode(e.getDest()).getLocation();
        geo_location s0 = _w2f.world2frame(s);
        geo_location d0 = _w2f.world2frame(d);

        g.drawLine((int)s0.x(), (int)s0.y(), (int)d0.x(), (int)d0.y());
    }

}
