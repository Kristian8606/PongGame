package tic;

import java.awt.*;
import java.util.Random;

public class Ball {

    public int x;
    public int y;
    public int width = 18;
    public int height = 18;
    boolean nul = true;
    boolean up = true;
    public int speed = 5;
  //  public int speedY = 10;
    public int score1;
    public int score2;
    int motionX, motionY;
    public Random random;
    //  public boolean rights = true;
    // public int right;
    // public int left;

    public Ball(Tic tic) {
        this.random=new Random();
        this.x = tic.width / 2 - width / 2;
        this.y = tic.height / 2 - height / 2;
        this.motionY= -2 + random.nextInt(4);
      // this.motionX= -5 + random.nextInt(13);
        // this.x;
        // this.left ;
        // move();
    }

    public void update(Pad pad1, Pad pad2) {
        this.x += motionX;
        this.y += motionY;
        if (nul) {

            if (!(pad1.x + pad1.width > this.x && this.y > pad1.y-10 && this.y < pad1.y + pad1.height+10)) {
                this.x -= speed ;
                if (pad1.x > this.x) {
                    score1++;
                    this.x = Tic.tic.width / 2 - width / 2;
                    this.y = Tic.tic.height / 2 - height / 2;
                    up=random.nextBoolean();

                }

            } else {
                nul = false;
            }

        } else {
            if (!(pad2.x - pad2.width < this.x && this.y > pad2.y-10 && this.y < pad2.y + pad2.height+10)) {
                this.x += speed;

                if (pad2.x < this.x) {
                    score2++;
                    this.x = Tic.tic.width / 2 - width / 2;
                    this.y = Tic.tic.height / 2 - height / 2;
                    up=random.nextBoolean();

                }
            } else {
                nul = true;
            }
        }

        if (up) {
            this.y -= speed;

            if (this.y < 0) {
                up = false;

            }
        } else {
            this.y += speed;

            if (this.y > Tic.tic.height - this.width) {
                up = true;

            }

        }


    }


    public void render(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(x, y, width, height);
    }


}
