import java.awt.*;

/**
 * A triangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Triangle{
    private int height;
    private int width;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;

    /**
     * Create a new triangle at default position with default color.
     */
    public Triangle(int x, int y){
        height = 15;
        width = 15;
        xPosition = x;
        yPosition = y;
        color = "#00bb2d";
        isVisible = false;
    }

    /**
     * Make this triangle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this triangle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    

 
    
    /*
	 * Draw the triangle with current specifications on screen.
	 */
	private void draw(){
		if(isVisible) {
			Canvas canvas = Canvas.getCanvas();
			int[] xpoints = { xPosition, xPosition + (width/2), xPosition - (width/2) };
			int[] ypoints = { yPosition, yPosition + height, yPosition + height };
			canvas.draw(this, color, new Polygon(xpoints, ypoints, 3));
			canvas.wait(10);
		}
	}

    /*
	 * Erase the triangle on screen.
	 */
	private void erase(){
		if(isVisible) {
			Canvas canvas = Canvas.getCanvas();
			canvas.erase(this);
		}
	}
}
