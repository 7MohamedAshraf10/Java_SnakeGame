package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author MohamedAshraf
 */
public class Gamepanel extends JPanel implements Runnable, KeyListener{
    private static final long serialVersionUID=1L;
    public static final int WIDTH =700, HEIGHT = 700;
    private Thread thread;
    private boolean running;
    private boolean right = true, left=false, up= false, down=false;
    private BodyPart b;
    private ArrayList<BodyPart> snake;
    private Apple apple;
    private ArrayList<Apple> apples;
    private Random r;
    private int xCoor=10, yCoor=10, size=3;
    private int ticks=0;
    
    public Gamepanel(){
            setFocusable(true);
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
            addKeyListener(this);
            
            snake= new ArrayList<BodyPart> ();
            apples = new ArrayList<Apple>();
            r= new Random();
            start();
                
                
        }
    
   
    public void start(){
        running = true;
        thread = new Thread(this);
        thread.start();     
    }
    
    
    public void stop(){
        
        running = false;
        
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
    
            
    
    
    public void tick(){
        if(snake.size()==0){
            b= new BodyPart (xCoor,yCoor,10);
            snake.add(b);
        }
        ticks++;
        if(ticks>500000){
            if(right) xCoor++;
            if(left) xCoor--;
            if (up) yCoor--;
            if(down)yCoor++;
            
            ticks=0;
            b=new BodyPart(xCoor,yCoor,10);
            snake.add(b);
            
            if(snake.size()>size){
                snake.remove(0);
            }
        }
             if(apples.size()==0){
                  int xCoor=r.nextInt(69);
                  int yCoor=r.nextInt(69);
            
                 apple=new Apple(xCoor,yCoor,10);
                 apples.add(apple);
        }

             for(int i =0;i<apples.size();i++){
                 if(xCoor== apples.get(i).getxCoor() && yCoor==apples.get(i).getyCoor()){
                     size++;
                     System.out.println("Score: "+ (size-3));
                     apples.remove(i);
                     i++;
                     
                     
                      
                 }
             }
             
             for(int i=0; i <snake.size(); i++){
                 if(xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()){
                     if(i!=snake.size()-1){
                          System.out.println("Game Over. U did a GoodJob WP ;), Your score: " + (size-3));
                         stop();
                     }
                 }
             }
             
             if(xCoor<0 || xCoor>69 || yCoor<0 || yCoor>69){
                 System.out.println("Game Over. U did a GoodJob WP ;), Your score: " + (size-3));
                 stop();
             }
             
             
    }
    
    
    
    
    
    public void paint(Graphics g) {
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        
        for(int i=0; i<WIDTH/10;i++)
        {
            g.drawLine(i*10 , 0 , i*10 , HEIGHT);
        }
        
        for(int i=0; i<HEIGHT/10;i++)
        {
            g.drawLine(0 , i*10 , HEIGHT , i*10);
        }
        
        for(int i =0; i< snake.size();i++){
            snake.get(i).draw(g);
        }
        
        for( int i=0; i< apples.size();i++)
        {
            apples.get(i).draw(g);
        }
        
    }

    @Override
    public void run() {
        while (running){
            tick();
            repaint();
                 
        }
        
    }
    
       @Override
        public  void keyPressed(KeyEvent e){
        int key=e.getKeyCode();
        
        if(key == KeyEvent.VK_RIGHT && !left){
              right=true;
              up = false;
              down= false;
                        
            }
        
           if(key == KeyEvent.VK_LEFT && !right){
             left=true;
              up = false;
              down= false;
                        
            }
           
              if(key == KeyEvent.VK_UP && !down){
              up=true;
              left = false;
              right= false;
                        
            }
              
                 if(key == KeyEvent.VK_DOWN && !up){
              right=false;
              left = false;;
              down= true;
                        
            }
         
         
         
         
    }
         
            @Override
        public void keyReleased(KeyEvent e){
        
    }
         
            @Override
       public  void keyTyped(KeyEvent e){
        
    }

  
}
