package up.tac.cmsc12.mp.ui.frames;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Background extends JPanel{
    Image backGroundImage;
    public Background(){
        backGroundImage = new ImageIcon("src\\up\\tac\\cmsc12\\mp\\841032800_preview_Desert 1.png").getImage();
    }

    public Background(CardLayout cardLayout){
        super(cardLayout);
        backGroundImage = new ImageIcon("src\\up\\tac\\cmsc12\\mp\\841032800_preview_Desert 1.png").getImage();
    }
    public void paintComponent(Graphics g){
        g.drawImage(backGroundImage, 0, 0, null);
    }

}
