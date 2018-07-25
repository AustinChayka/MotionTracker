import java.awt.Point;
import java.util.ArrayList;

public class TrackedObject {
	
	private int minX, minY, maxX, maxY;
	
	ArrayList<Point> points;
	
	public TrackedObject(int x, int y) {
		
		points = new ArrayList<Point>();
		points.add(new Point(x, y));
		
		minX = x;
		minY = y;
		maxX = x;
		maxY = y;
		
	}
	
	public void include(int x, int  y) {
		
		points.add(new Point(x, y));
		if(x < minX) minX = x;
		if(x > maxX) maxX = x;
		if(y < minY) minY  = y;
		if(y > maxY) maxY = y;
		
	}
	
	public int minDistanceToSqrd(int pX, int pY) {
		
		int minDistance = Integer.MAX_VALUE;
		
		for(Point p : points) if((pX - (int)p.getX()) * (pX - (int)p.getX()) + 
			(pY - (int)p.getY()) * (pY - (int)p.getY()) < minDistance) 
				minDistance = (pX - (int)p.getX()) * (pX - (int)p.getX()) + 
				(pY - (int)p.getY()) * (pY - (int)p.getY());
		
		return minDistance;
		
	}
	
	public void draw(Renderer r) {
		
		r.drawRect(minX, minY, maxX - minX, maxY - minY, 0x00ff00);
		r.setPixel((minX + maxX) / 2, (minY + maxY) / 2, 0x00ff00);
						
	}
	
	public int size() {
		
		return (maxX - minX) * (maxY - minY);
		
	}
	
}
