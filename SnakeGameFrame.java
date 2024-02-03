

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class SnakeGameFrame extends JPanel implements ActionListener {
    static GameFrame frame;
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 75;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyparts = 6;
    int applesEaten;
    int applex;
    int appley;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    SnakeGameFrame(Object ob) {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter(this));
        startGame();
    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                //g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                //g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }
            g.setColor(Color.red);
            g.fillOval(applex, appley, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyparts; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 75));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score:" + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score:" + applesEaten)) / 2, g.getFont().getSize());
        } else {
            gameOver(g);
        }
    }

    public void newApple() {
        applex = random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        appley = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;

    }

    public void move() {
        for (int i = bodyparts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U' ->
                y[0] -= UNIT_SIZE;
            case 'D' ->
                y[0] += UNIT_SIZE;
            case 'L' ->
                x[0] -= UNIT_SIZE;
            case 'R' ->
                x[0] += UNIT_SIZE;
        }
    }

    public void checkApple() {
        if ((x[0] == applex) && (y[0] == appley)) {
            bodyparts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisons() {
        //checks if head collide with body
        for (int i = bodyparts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        //checks if head touches left border
        if (x[0] < 0) {
            running = false;
        }
        //checks if head touches right border
        if (x[0] > SCREEN_WIDTH) {
            running = false;
        }
        //checks if head touches bottom border
        if (y[0] < 0) {
            running = false;
        }
        //checks if head touches top border
        if (y[0] > SCREEN_HEIGHT) {
            running = false;
        }
        if (!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {

        //score 
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score:" + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score:" + applesEaten)) / 2, g.getFont().getSize());

        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);

        JButton button1 = new JButton();
        button1.addActionListener(new EvtRestat());
        button1.setSize(75, 50);
        button1.setVisible(true);
        button1.setLocation(100, 400);
        button1.setText("Restat");
        this.add(button1);

        JButton button2 = new JButton();
        button2.addActionListener(new EvtExit());
        button2.setSize(75, 50);
        button2.setVisible(true);
        button2.setLocation(425, 400);
        button2.setText("Exit");
        this.add(button2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisons();
        }
        repaint();
    }

    public class EvtRestat implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            frame.dispose();
            frame = new GameFrame(this);
        }
    }

    private class EvtExit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            frame.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            frame = new GameFrame(frame);
        });
    }
}

@SuppressWarnings("serial")
class GameFrame extends JFrame {

    @SuppressWarnings("OverridableMethodCallInConstructor")
    GameFrame(Object ob) {
        SnakeGameFrame GamePanel = new SnakeGameFrame(ob);
        this.add(GamePanel);
        this.setTitle("snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
