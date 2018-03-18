package tic;

import javax.swing.*;
import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tic implements ActionListener, KeyListener {
    public static Tic tic;
    public boolean w, s, up, down;
    public Renderer renderer;
    public Pad Pad;
    public Pad player1;
    public Pad player2;
    public static MaxSelector max;
    public static MaxSelector Max2;
    public static MaxSelector Max3;
    public static MaxSelector Max4;
    public static Ball ball;
    public boolean ballRight, isBallLeft;
    int width = 800, height = 600;

    public boolean pause = true;
    public boolean enter = true;
    public boolean escape = false;
    public boolean right = false;
    public boolean left = false;
    public boolean typeRight = true;
    public int maxScore;

    //public Ball score1;
    //  public Ball score2;
    // jhjhjhjhjh

    public Tic() {
        JFrame j = new JFrame("Tic");
        Timer timer = new Timer(20, this);
        renderer = new Renderer();

        j.setSize(width, height + 22);
        j.setLocation(250, 0);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setResizable(true);
        j.addKeyListener(this);


        start();
        j.add(renderer);
        timer.start();
        j.setVisible(true);

    }

    public void start() {
        player1 = new Pad(this, 1);
        player2 = new Pad(this, 2);
        ball = new Ball(this);
        max = new MaxSelector(this, 0);
        Max2 = new MaxSelector(this, 2);
        Max3 = new MaxSelector(this, 3);
        Max4 = new MaxSelector(this, 4);
    }

    public void update() {
        if (up) {
            player2.move(true);
        }
        if (down) {
            player2.move(false);
        }
        if (w) {
            player1.move(true);
        }
        if (s) {
            player1.move(false);
        }
        if (!enter) {

            ball.update(player1, player2);
        }

    }

    public void render(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.lightGray);
        g.setStroke(new BasicStroke(3.5f));
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 30));

        if (max.x > 150 && max.x < 250 && (ball.score1 == 15 || ball.score2 == 15)) {
            System.exit(0);
        } else if (max.x > 250 && max.x < 350 && (ball.score1 == 25 || ball.score2 == 25)) {
            System.exit(0);
        }else if (max.x > 350 && max.x < 460 && (ball.score1 == 35 || ball.score2 == 35)) {
            System.exit(0);
        }

        if (!enter) {
            if (escape) {
                System.exit(0);
            }
            if (ball.score2 > 9) {
                g.drawString(String.valueOf(ball.score1), width / 2 + 30, 30);
                g.drawString(String.valueOf(ball.score2), width / 2 - 60, 30);
            } else {
                g.drawString(String.valueOf(ball.score1), width / 2 + 30, 30);
                g.drawString(String.valueOf(ball.score2), width / 2 - 45, 30);
            }
            if (!pause) {
                g.setFont(new Font("Arial", 1, 100));
                g.drawString("PAUSE", width / 2 - 180, height / 2 - 20);
                g.setFont(new Font("Arial", 1, 30));
                g.drawString("Press Esc to Exit", width / 2 - 140, height / 4 - 20);
            } else {
                g.drawLine(width / 2, 0, width / 2, height);
                g.drawOval(width / 2 - 125, height / 2 - 125, 250, 250);
                player1.render(g);
                player2.render(g);
                ball.render(g);


            }
        } else {
            g.setFont(new Font("Arial", 1, 30));
            g.drawString("Press Enter to Start Game", width / 2 - 220, height / 2 - 20);
            g.setFont(new Font("Arial", 1, 30));
            g.drawString("Press Space to Pause", width / 2 - 180, height / 2 + 40);
            g.setFont(new Font("Arial", 1, 30));
            g.drawString("Choose score:", width / 2 - 140, height / 2 + 80);
            g.setFont(new Font("Arial", 1, 70));
         //   System.out.println(max.x);

            max.render(g);
            Max2.rend(g);
            Max3.rend(g);
            Max4.rend(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (pause) {
            update();
        }
        renderer.repaint();
    }

    public static void main(String[] args) {
        tic = new Tic();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int id = e.getKeyCode();
        if (id == KeyEvent.VK_UP) {
            up = true;
        }
        if (id == KeyEvent.VK_DOWN) {
            down = true;
        }
        if (id == KeyEvent.VK_W) {
            w = true;
        }
        if (id == KeyEvent.VK_S) {
            s = true;
        }
        if (id == KeyEvent.VK_SPACE && pause) {
            pause = false;
        } else {
            pause = true;
        }
        if (id == KeyEvent.VK_EQUALS) {
            ball.speed++;
            //  ball.speedY++;
        }
        if (id == KeyEvent.VK_MINUS && ball.speed != 2) {
            ball.speed--;
        }
        if (id == KeyEvent.VK_ENTER) {
            enter = false;
        }
        if (id == KeyEvent.VK_ESCAPE) {

            escape = true;
        }

        if (id == KeyEvent.VK_RIGHT && max.x < tic.width / 4 + max.width + 20 * 2) {
            max.x += max.width + 20;
            this.maxScore = max.x;

        }
        if (id == KeyEvent.VK_LEFT && max.x > tic.width / 4) {
            max.x -= max.width + 20;
        }


    }


    @Override
    public void keyReleased(KeyEvent e) {
        int id = e.getKeyCode();
        if (id == KeyEvent.VK_UP) {
            up = false;
        }
        if (id == KeyEvent.VK_DOWN) {
            down = false;
        }
        if (id == KeyEvent.VK_W) {
            w = false;
        }
        if (id == KeyEvent.VK_S) {
            s = false;
        }
        if (id == KeyEvent.VK_SPACE && !pause) {
            pause = false;
        } else {
            pause = true;
        }
        if (id == KeyEvent.VK_EQUALS) {
            ball.speed++;
        }
        if (id == KeyEvent.VK_MINUS && ball.speed != 2) {
            ball.speed--;
        }
        if (id == KeyEvent.VK_ENTER) {
            enter = false;
        }
        if (id == KeyEvent.VK_ESCAPE) {
            escape = true;
        }
        if (id == KeyEvent.VK_RIGHT) {

        }

    }
}
