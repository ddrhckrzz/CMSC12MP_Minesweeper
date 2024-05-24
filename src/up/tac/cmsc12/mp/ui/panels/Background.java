package up.tac.cmsc12.mp.ui.panels;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import up.tac.cmsc12.mp.minesweeper.Minesweeper;

public class Background extends JPanel{
    private Image backGroundImage;
    private static final String BACKGROUND_PATH = Minesweeper.ASSETS_PATH + "backgrounds\\";
    public Background(){
        backGroundImage = new ImageIcon(BACKGROUND_PATH + "desert.png").getImage();
    }

    public Background(CardLayout cardLayout){
        super(cardLayout);
        backGroundImage = new ImageIcon(BACKGROUND_PATH + "desert.png").getImage();
    }
    public void paintComponent(Graphics g){
        g.drawImage(backGroundImage, 0, 0, null);
    }

}
