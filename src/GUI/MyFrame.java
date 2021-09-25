package GUI;

import gameClient.Arena;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *This class represents the main window of the game
 */
public class MyFrame extends JFrame {

    int px, py;
    Arena _ar;
    MyPanel gamePanel = new MyPanel();
    EntrancePanel entrancePanel = new EntrancePanel();
    private static final Color color_header = new Color(219, 4, 4);
    private static final Color color_hoverHeader = new Color(42, 196, 176, 255);
    private final JPanel Header = new JPanel();
    private final JPanel closePNL = new JPanel();
    private final JLabel closeWindowBTN = new JLabel();
    private final JPanel MaxClosePanel = new JPanel();
    private final JLabel maxWindowBTN = new JLabel();
    private final JPanel maximizePNL = new JPanel();

    /**
     * Creates new form MainMenu
     */
    public MyFrame() {
        super("PokemonCatcher v1.0");
        this.initComponents();
    }

    public void InitGamePanel(Arena ar){
        _ar = ar;
        gamePanel.update(_ar);
        this.add(gamePanel, BorderLayout.CENTER);
    }

    private void initComponents() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(900,500));
        this.setLocationByPlatform(true);
        this.setUndecorated(true);
        this.setVisible(true);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/GUI/Icons/pokeball.png")));
        this.setLayout(new BorderLayout());
        this.addOtherComponents();
        this.getContentPane().add(entrancePanel, BorderLayout.CENTER);
        this.pack();
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }

    private void addOtherComponents() {
        Header.setBackground(color_header);
        Header.setMinimumSize(new Dimension(150, 20));
        Header.setPreferredSize(new Dimension(800, 30));
        Header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                px = e.getX();
                py = e.getY();
            }
        });
        Header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
                setLocation(e.getXOnScreen()-px, e.getYOnScreen()-py);
            }
        });
        Header.setLayout(new BorderLayout());

        JLabel title = new JLabel("  PokemonCatcher v1.0");
        title.setFont(new Font("Arial", Font.ITALIC, 15));
        title.setForeground(Color.white);

        closePNL.setBackground(color_header);
        closePNL.setLayout(new BorderLayout());

        closeWindowBTN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        closeWindowBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icons/icons8_close_window_18px.png"))); // NOI18N
        closeWindowBTN.setAlignmentY(0.0F);
        closeWindowBTN.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        closeWindowBTN.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        closeWindowBTN.setMaximumSize(new Dimension(30, 30));
        closeWindowBTN.setPreferredSize(new Dimension(30, 30));
        closeWindowBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.exit(0);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closePNL.setBackground(color_hoverHeader);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closePNL.setBackground(color_header);
            }
        });
        closePNL.add(closeWindowBTN, BorderLayout.PAGE_START);

        MaxClosePanel.add(closePNL, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 50, 50));

        maximizePNL.setBackground(color_header);
        maximizePNL.setLayout(new BorderLayout());
        maxWindowBTN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        maxWindowBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icons/icons8_full_screen_18px.png"))); // NOI18N
        maxWindowBTN.setPreferredSize(new Dimension(30, 30));
        maxWindowBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                maxWindowBTNMouseClicked();
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                maximizePNL.setBackground(color_hoverHeader);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                maximizePNL.setBackground(color_header);
            }
        });
        maximizePNL.add(maxWindowBTN, BorderLayout.PAGE_START);

        MaxClosePanel.setLayout(new GridBagLayout());
        MaxClosePanel.add(maximizePNL);
        MaxClosePanel.add(closePNL);

        Header.add(title, BorderLayout.WEST);
        Header.add(MaxClosePanel, BorderLayout.LINE_END);
        getContentPane().add(Header, BorderLayout.PAGE_START);
    }

    private void maxWindowBTNMouseClicked() {
        if(this.getExtendedState() != MyFrame.MAXIMIZED_BOTH){
            this.setExtendedState(MyFrame.MAXIMIZED_BOTH);
        }
        else{
            this.setExtendedState(MyFrame.NORMAL);
        }
    }

    public void showPanel(int p) {
        switch (p) {
            case 0 -> {
                entrancePanel.setVisible(true);
                gamePanel.setVisible(false);
                this.setWindowResizable(false);
            }
            case 1 -> {
                entrancePanel.setVisible(false);
                gamePanel.setVisible(true);
                this.setWindowResizable(true);
            }
        }
    }

    public void setWindowResizable(boolean b){
        if(!b) return;
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 4, 4, color_header));
        ComponentResizer cr = new ComponentResizer();
        cr.registerComponent(this);
    }
}
