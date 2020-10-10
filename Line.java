
/**
 * Write a description of class Line here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Line
{
    // instance variables - replace the example below with your own
    private String color;
    private int xPos1;
    private int yPos1;
    private int xPos2;
    private int yPos2;
    private boolean isVisible;

    /**
     * Constructor 
     */
    public Line(int x1, int y1, int x2, int y2)
    {
        color = "#000000";
        xPos1 = x1;
        yPos1 = y1;
        xPos2 = x2;
        yPos2 = y2;
        isVisible = false;
    }
    
    /**
     * retorna lo pocision del circulo en x
     */
    public int getLocationX1()
    {
        return xPos1;
    }
    
    /**
     * retorna lo pocision del circulo en x
     */
    public int getLocationX2()
    {
        return xPos2;
    }
    
    /**
     * retorna lo pocision del circulo en x
     */
    public int getLocationY2()
    {
        return yPos2;
    }
    
    /**
     * retorna lo pocision del circulo en x
     */
    public int getLocationY1()
    {
        return yPos1;
    }
    
    /**
     * Make this rectangle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this rectangle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }

    /*
     * Draw the rectangle with current specifications on screen.
     */

    private void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                new java.awt.geom.Line2D.Double(xPos1, yPos1, xPos2, yPos2));
            canvas.wait(10);
        }
    }

    /*
     * Erase the rectangle on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}
