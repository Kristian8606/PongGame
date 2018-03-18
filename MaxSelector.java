package tic;

import java.awt.*;

public class MaxSelector {
    public int x, y, width = 100, height = 100, scoreX, scoreY;
    public int num;
        public String  score ;
 public  int maxScore = 0 ;
    public MaxSelector(Tic tic, int num) {
        this.num = num;

        this.x = tic.width / 4;
        this.y = (tic.height / 4) * 3;

        if (num == 2) {
            this.scoreX = this.x + 8;
            this.score = "15";

        }
        if (num == 3) {
            this.scoreX = this.x + width + 30;
            this.score = "25";
        }
        if (num == 4) {
            this.scoreX = this.x + width * 2 + 48;
            this.score = "35";
        }
        this.scoreY = this.y + height - 29;



    }

    public void Colision() {


    }


    public void render(Graphics g) {
        g.setColor(Color.red);
        g.drawRoundRect(x, y, width, height, 30, 30);
    }

    public void rend(Graphics g) {
        g.setColor(Color.white);
        g.drawString(score, scoreX, scoreY);



    }

}
