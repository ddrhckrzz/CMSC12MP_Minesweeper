package up.tac.cmsc12.mp.ui.buttons;

import static java.awt.Color.white;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public final class CustomButton extends JPanel{

    private int r=198,g=188,b=108, grade=30,border_width=5,fontSize=50;
    //196, 188, 108
    private Color main_color = new Color(r,g,b);
    private Color top_color = convertTopColor(r,g,b);
    private Color bottom_color = convertBotColor(r,g,b);
    private Color clicked_color = convertColor(r,g,b,20);
    //private Color text_color = convertColor(r,g,b,-50);
    private Color text_shadow = convertColor(r,g,b,50);
    private Font f = new Font("Impact", Font.BOLD, fontSize);
    private String text;
    
    public CustomButton(String name){
        this.text = name;
        initButtonDesign();
        setDefaultMouseListeners();
    }
    
    public CustomButton(String name, int border_width, int fontSize){
        this.text = name;
        this.border_width = border_width;
        this.fontSize = fontSize;
        initButtonDesign();
        setDefaultMouseListeners();
    }
    
    private void setDefaultMouseListeners(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                push();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                unpush();
            }
        });
    }

    public void setColor(int r, int g, int b){
        this.main_color = new Color(r,g,b);
        this.top_color = convertTopColor(r,g,b);
        this.bottom_color = convertBotColor(r,g,b);
        this.clicked_color = convertColor(r,g,b,20);
        initButtonDesign(); 
    }
    
    public void initButtonDesign(){
        setBackground(main_color);
        setForeGround();
        repaint();
    }
    
    public Color convertTopColor(int r,int g,int b){
        return new Color(r+grade,g+grade,b+grade);
    }
    
    public Color convertBotColor(int r,int g,int b){
        return new Color(r-grade,g-grade,b-grade);
    }
    
    public Color convertColor(int r,int g,int b,int custom_grade){
        return new Color(r-custom_grade,g-custom_grade,b-custom_grade);
    }
    
    public void paint(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(1));
        
        g2D.setColor(bottom_color);
        
        int[] xcoord = {0,border_width,getWidth()-border_width,getWidth()-border_width,getWidth(),getWidth(),0};
        int[] ycoord = {getHeight(),getHeight()-border_width,getHeight()-border_width,border_width,0,getHeight(),getHeight()};
        
        Polygon Bot_Border = new Polygon(xcoord,ycoord,xcoord.length);
        
        int[] xcoord2 = {0,border_width,border_width,getWidth()-border_width,getWidth(),0};
        int[] ycoord2 = {getHeight(),getHeight()-border_width,border_width,border_width,0,0};
        
        Polygon Top_Border = new Polygon(xcoord2,ycoord2,xcoord2.length);
        
        g2D.fillPolygon(Bot_Border);
        
        g2D.setColor(top_color);
        
        g2D.fillPolygon(Top_Border);
        
        
        g2D.setColor(text_shadow);
          
        FontMetrics metrics = g.getFontMetrics(f);
        
        int x = ((getWidth() - metrics.stringWidth(text))/2)+4;
        int y = ((getHeight()-metrics.getHeight())/2)+metrics.getAscent()+4;

        g2D.drawString(text,x,y);

        g2D.setColor(white);

        int x2 = (getWidth() - metrics.stringWidth(text))/2;
        int y2 = ((getHeight()-metrics.getHeight())/2)+metrics.getAscent();
        g2D.drawString(text, x2,y2);

    }
    
    public void setForeGround(){
        f = new Font("Impact", Font.BOLD, fontSize); 
        setFont(f);
    }
    
    public void push(){
        Color tcolor = this.bottom_color;
        this.bottom_color = this.top_color;
        this.top_color = tcolor;
        setBackground(clicked_color);
        repaint();
        updateFrameUI();

    }
    
    public void unpush(){
        Color tcolor = this.bottom_color;
        this.bottom_color = this.top_color;
        this.top_color = tcolor;
        setBackground(main_color);
        repaint();
        updateFrameUI();
    }

    private void updateFrameUI() {
        SwingUtilities.updateComponentTreeUI(SwingUtilities.getWindowAncestor(this));
    }
}
