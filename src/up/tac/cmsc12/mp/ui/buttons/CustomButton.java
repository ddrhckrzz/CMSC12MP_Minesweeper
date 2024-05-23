package up.tac.cmsc12.mp.ui.buttons;

import java.awt.BasicStroke;
import java.awt.Color;
import static java.awt.Color.*;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public final class CustomButton extends JPanel implements MouseListener{

    private int r=80,g=80,b=200, grade=30,border_width=5,fontSize=50;
    private Color main_color = new Color(r,g,b);
    private Color top_color = convertTopColor(r,g,b);
    private Color bottom_color = convertBotColor(r,g,b);
    private Color clicked_color = convertColor(r,g,b,20);
    private Font f = new Font("Times New Roman", Font.PLAIN, fontSize);
    private String text;
    
    public CustomButton(String name){
        this.text = name;
        initButtonDesign();
    }
    
    public CustomButton(String name, int border_width, int fontSize){
        this.text = name;
        this.border_width = border_width;
        this.fontSize = fontSize;
        initButtonDesign(); 
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
        
        
        g2D.setColor(white);
          
        FontMetrics metrics = g.getFontMetrics(f);
        
        int x = (getWidth() - metrics.stringWidth(text))/2;
        int y = ((getHeight()-metrics.getHeight())/2)+metrics.getAscent();
        g2D.drawString(text, x,y);
    }
    
    public void setForeGround(){
        setFont(f);  
    }
    
    public void push(){
        Color tcolor = this.bottom_color;
        this.bottom_color = this.top_color;
        this.top_color = tcolor;
        setBackground(clicked_color);
        repaint();
        SwingUtilities.updateComponentTreeUI(this);

    }
    
    public void unpush(){
        Color tcolor = this.bottom_color;
        this.bottom_color = this.top_color;
        this.top_color = tcolor;
        setBackground(main_color);
        repaint();
        updateUI();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        unpush();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
}
