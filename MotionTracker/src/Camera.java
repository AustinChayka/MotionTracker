import java.awt.image.BufferedImage;
import com.github.sarxos.webcam.Webcam;

public class Camera {
	
	private Webcam camera;
	private int width, height;
	
	public Camera() {
		
		camera = Webcam.getDefault();
		camera.open();
		
		width = (int)camera.getViewSize().getWidth();
		height = (int)camera.getViewSize().getHeight();
	
	}
	
	public int getWidth() {
		
		return width;
		
	}
	
	public int getHeight() {
		
		return height;
		
	}

	public BufferedImage getImage() {
		
		return camera.getImage();
		
	}

}
