package tic;

import javax.swing.*;
import java.awt.*;

public class Renderer extends JPanel {

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Tic.tic.render((Graphics2D) g);
    }
}
