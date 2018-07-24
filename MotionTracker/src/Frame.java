import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class Frame {
    
    private JFrame frame;
    private BufferedImage image, image2;
    private Canvas canvas;
    private Graphics g;
    private BufferStrategy bs;
    private Camera camera;
    
    public Frame(Camera c) {
        
    	camera = c;
        image = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_RGB);
        image2 = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_RGB);
        canvas = new Canvas();
        Dimension d = new Dimension(c.getWidth() * 8, c.getHeight() * 4);
        canvas.setPreferredSize(d);
        canvas.setMaximumSize(d);
        canvas.setMinimumSize(d);
        
        frame = new JFrame("Motion Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
        frame.toFront();
        frame.requestFocus();
        
        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();
        g = bs.getDrawGraphics();
        
    }
       
    public Canvas getCanvas() {
        
        return canvas;
        
    }
    
    public BufferedImage getImage() {
        
        return image;
        
    }
 
    public BufferedImage getImage2() {
        
        return image2;
        
    }
    
    public JFrame getFrame() {
        
        return frame;
        
    }
    
    public void update() {
        
        g.drawImage(image, 0, 0, canvas.getWidth() / 2, canvas.getHeight(), null);
        g.drawImage(image2, canvas.getWidth() / 2, 0, canvas.getWidth() / 2, canvas.getHeight(), null);
        bs.show();
        
    }
    
    public void setTitle(String newTitle) {
        
        frame.setTitle(newTitle);
        
    }
    
    public void close() {
        
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        
    }
    
    public int getWidth() {
    	
    	return camera.getWidth();
    	
    }
    
    public int getHeight() {
    	
    	return camera.getHeight();
    	
    }
           
}