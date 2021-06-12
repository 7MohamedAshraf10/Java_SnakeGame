package game;

import javax.swing.JFrame;

/**
 *
 * @author MohamedAshraf
 */
public class Game {
 public Game(){
     JFrame frame= new JFrame();
     Gamepanel gamepanel = new Gamepanel();
     
     frame.add(gamepanel);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setTitle("SnakeGame");
     frame.pack();
     frame.setVisible(true);
     frame.setLocationRelativeTo(null);
 }
    public static void main(String[] args) {
    new Game();
    }
    
}
