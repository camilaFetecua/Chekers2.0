
/**
 * Write a description of class AdminColor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.HashMap;
import java.util.Map;

/**
 * Write a description of class AdminColor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AdminColor
{
    // instance variables - replace the example below with your own
    private static Map<Integer,String> colores = new HashMap<Integer,String>();
    private static final String[] numAle = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
    /**
     * Constructor for objects of class AdminColor
     */
    public static void generarColores(int cantColores)
    {
        colores= new HashMap<Integer,String>();
        for(int i = 0;i<cantColores ; i++ ) {
               colores.put(i+1,generaHexa());
        }
    }
     /**
     * Genera color hexagecimal aleatorio
     * @param  milliseconds  the number 
     */
    private static String generaHexa(){
     String color = "#";
    for (int i = 0; i < 6; i++ ) {
        color += numAle[(int) Math.round(Math.random() * 15)];
    }
    return color;
    }
        /**
     * Retorna el color 
     * @param  milliseconds  the number 
     */
    public static String retornaColor(Integer selColor){
     return colores.get(selColor);
    }

}
