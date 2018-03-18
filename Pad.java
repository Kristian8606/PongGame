package tic;

import java.awt.*;

public class Pad {


    public int number;
    public int x,y,width= 16,height=100;
   public int speed=20;



    public  Pad(Tic tic, int number){
        this.number = number;
    if (number == 1){
        this.x = 0;
    }
    if (number == 2){
        this.x = tic.width-width;
    }
    this.y = tic.height/2-height/2;

    }

    public void render(Graphics g){
        g.setColor(Color.lightGray);
        g.fillRect(x,y,width,height);
    }

    public void move(boolean up) {
        if (up){
            if (y > 0 ){
                this.y -=speed;
            }else {
                y = 0;
            }
        }else {
            if (this.y + height < Tic.tic.height){
                this.y += speed;
            }
        }
    }
}
