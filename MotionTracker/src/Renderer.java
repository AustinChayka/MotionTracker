import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

public class Renderer {
    
    private int pW, pH;
    private int[] p, p2;
    private static final int COLOR_THRESHOLD = 40, DISTANCE_THRESHOLD = 10;
    
    ArrayList<TrackedObject> objects = new ArrayList<TrackedObject>();
    
    public Renderer(Frame f) {
        
        pW = f.getWidth();
        pH = f.getHeight();
        
        p = ((DataBufferInt)f.getImage().getRaster().getDataBuffer()).getData();
        p2 = ((DataBufferInt)f.getImage2().getRaster().getDataBuffer()).getData();
        
    }
    
    public void clear() {
        
        for(int i = 0; i < p.length; i++) {
            
            p[i] = 0xff000000;
            
        }
        for(int i = 0; i < p2.length; i++) {
            
            p2[i] = 0xff000000;
            
        }
        
    }

	public void drawDifferences(BufferedImage image1, BufferedImage image2) {
    	
    	int[] pixels1 = image1.getRGB(0, 0, image1.getWidth(),
    			image1.getHeight(), null, 0, image1.getWidth()),
    		pixels2 = image2.getRGB(0, 0, image2.getWidth(),
    	    	image2.getHeight(), null, 0, image2.getWidth());
    	    	
    	for(int i = 0; i < p.length; i++) if(colorDistanceSqrd(pixels1[i], pixels2[i]) > 
    		COLOR_THRESHOLD * COLOR_THRESHOLD) {

    		p2[i] = 0xffffff;
    		
    		boolean found = false;
    		
    		if(objects.size() == 0) objects.add(new TrackedObject(i % pW, i / pW));
    		for(TrackedObject to : objects) {
    			
    			if(to.minDistanceToSqrd(i % pW, i / pW) < DISTANCE_THRESHOLD * DISTANCE_THRESHOLD) {
    				to.include(i % pW, i / pW);
    				found = true;
    				break;
    			}
    			
    		}
    		
    		if(!found) objects.add(new TrackedObject(i % pW, i / pW));
    		    		
    	}
    	
    	for(TrackedObject to : objects) if(to.size() > 400) to.draw(this);
    	objects.clear();
    	    
    }
    
    public static double colorDistanceSqrd(int color1, int color2) {
    	
    	int red1 = (color1 >> 16) & 0xff,
    		green1 = (color1 >> 8) & 0xff,
    		blue1 = color1 & 0xff,
    		red2 = (color2 >> 16) & 0xff,
    		green2 = (color2 >> 8) & 0xff,
    		blue2 = color2 & 0xff;
    	
    	return (red2 - red1) * (red2 - red1) + (green2 - green1) * (green2 - green1) +
    		(blue2 - blue1) * (blue2 - blue1);
    		
    }
    
    public void setPixel(int x, int y, int value) {
        
        if((x < 0 || x >= pW || y < 0 || y >= pH) || value == 0xffff00ff) {
            
            return;
            
        }
                
        p[x + y * pW] = value;
        
    }
    
    public void setPixel2(int x, int y, int value) {
        
        if((x < 0 || x >= pW || y < 0 || y >= pH) || value == 0xffff00ff) {
            
            return;
            
        }
                
        p2[x + y * pW] = value;
        
    }
    
    public void drawImage(BufferedImage image, int offX, int offY) {
    	
    	int[] pixels = image.getRGB(0, 0, image.getWidth(),
    			image.getHeight(), null, 0, image.getWidth());
        
        for(int y = 0; y < image.getHeight(); y++) {
            
            for(int x = 0; x < image.getWidth(); x++) {
                
                setPixel(x + offX, y + offY, pixels[x + y * image.getWidth()]);
                
            }
            
        }
        
    }
    
    public void drawRect(int offX, int offY, int width, int height, int color) {
        
        for(int y = 0; y <= height; y++) {
            
            setPixel(offX, y + offY, color);
            setPixel(offX + width, y + offY, color);
            
        }
        for(int x = 0; x < width; x++) {
            
            setPixel(x + offX, offY, color);
            setPixel(x + offX, offY + height, color);
            
        }
        
    }
    
}