import java.awt.image.BufferedImage;

public class Main {
	
	public static void main(String[] args) {

		Camera camera = new Camera();
		Frame frame = new Frame(camera);
		Renderer renderer = new Renderer(frame);
		
		BufferedImage lastImage;
		BufferedImage image = camera.getImage();
		
		while(true) {
			
			lastImage = image;
			image = camera.getImage();
			renderer.drawImage(image, 0, 0);
			renderer.drawDifferences(image, lastImage);
			frame.update();
			renderer.clear();
			
		}
		
	}

}
