import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.HashMap;
import java.util.Arrays; 
import java.lang.Math;
/**
 * Crea el mapa y pone los objetos solicitados
 * 
 * @author Juan Cadavid, Camila Fetecua 
 * @version 2.0 version
 */
public class Town
{
    // instance variables - replace the example below with your own
    private Rectangle rectangle;
    private String col;
    private Line line;
    private Triangle triangle;
    HashMap<String, Circle> location = new HashMap<String, Circle>();
    HashMap<ArrayList<String>, Line> street = new HashMap<ArrayList<String>, Line>();
    HashMap<ArrayList<String>, Triangle> sign = new HashMap<ArrayList<String>, Triangle>();
    Stack<HashMap<String, Circle>> undoloc = new Stack<HashMap<String, Circle>>();
    Stack<HashMap<ArrayList<String>, Line>> undostre = new Stack<HashMap<ArrayList<String>, Line>>();
    Stack<HashMap<ArrayList<String>, Triangle>> undosig = new Stack<HashMap<ArrayList<String>, Triangle>>();
    Stack<HashMap<String, Circle>> redoloc = new Stack<HashMap<String, Circle>>();
    Stack<HashMap<ArrayList<String>, Line>> redostre = new Stack<HashMap<ArrayList<String>, Line>>();
    Stack<HashMap<ArrayList<String>, Triangle>> redosig = new Stack<HashMap<ArrayList<String>, Triangle>>();
    private int lenghtMap;
    private int widthMap;
    private boolean ok = true;

    /**
     * El tamaño maximo del mapa es de 600 x 600.
     */
    public Town(int lenght, int width)
    {
        lenghtMap = lenght;
        widthMap = width;
        borde(lenght, width);
        AdminColor.generarColores(60);
    }
    
    /*
     * Genera rectangulos negros en lo que no hace parte del mapa 
     */
    private void borde(int x, int y)
    {
        //añadir rectan para guardar el borde del mpa
        if ((600 >= x) && (600 >= y)){
            rectangle = new Rectangle(0, y, 600-x, 600);
            rectangle.makeVisible();
            rectangle = new Rectangle(x, 0, 600, 600-y);
            rectangle.makeVisible();
            ok = true;
        }
        else{
            ok = false;
        }
        
    }
    
    /**
     * E
     */
    public Town(int lenght, int width, int streets, int signs)
    {
        
    }
    
 public Casa(int lenght, int width, int streets, int signs)
    {
        
    }
    
    /**
     * Entrada arena 
     */
    public Town(String [] input)
    {
        lenghtMap = 600;
        widthMap = 600;
        borde(600, 600);
        AdminColor.generarColores(60);
        ArrayList<String> al = new ArrayList<String>();
        for (int i = 0; input.length > i; i++){
            for (String s: input[i].split(" ")){
                al.add(s);
            }
        }
        for (int i = 0; Integer.parseInt(al.get(0)) > i; i++){
            int [] coor = randomLocation();
            addLocation(i+1+"", coor[0], coor[1]);
        }
        for (int i = 2; Integer.parseInt(al.get(1))*2 >= i; i+= 2){
            addStreet(al.get(i), al.get(i+1));
        }       
    }
   
    /**
     * Genera coordenadas aleatorias.
     * @return
     */
    public int[] randomLocation(){
        int [] coor = new int[2];
        coor[0] = (int)(Math.floor(Math.random()*30))*20;
        coor[1] = (int)(Math.floor(Math.random()*30))*20;
        return coor;
    }
    
    
    /**
     * Pinta y crea el objetos de location
     * 
     * @param  el color de la location, las coordenadas en x y y.
     */
    public void addLocation(String color, int x, int y)
    {
        if (validarLocation(color, x, (widthMap-20)-y)){
            saveundo();
            String num = color;
            color = AdminColor.retornaColor(Integer.parseInt(color));
            Circle circulo = new Circle(x, (widthMap-20)-y, color);
            location.put(num, circulo);
            makeVisible();
            ok = true;
        }
        else {
            ok = false;
        }
        
    }
    
    /*
     * Valida si es posible poner lo location
     * 
     * @param  el color de la location, las coordenadas en x y y.
     */
    private boolean validarLocation(String color, int x, int y)
    {
        boolean val = true;
        for (HashMap.Entry<String, Circle> entry : location.entrySet()){
            if ((((int)(entry.getValue()).getLocationX()) == x) && ((int)(entry.getValue()).getLocationY() == y)){
                val = false;
            }
        }
        return (!location.containsKey(color) && val);
    }
        
    /**
     * Pinta y crea la calle, la cual une la location
     * 
     * @param la union de color de donde se une las location.
     */
    public void addStreet(String locationA, String locationB)
    {
        ArrayList<String> lista = new ArrayList<String>();lista.add(locationA);lista.add(locationB);
        Collections.sort(lista);
        if (((location.containsKey(locationA)) && (location.containsKey(locationB))) && (!street.containsKey(lista))){
            saveundo();
            line = new Line(location.get(locationA).getLocationX()+ 9, location.get(locationA).getLocationY()+ 9, location.get(locationB).getLocationX()+ 9, location.get(locationB).getLocationY()+ 9);
            street.put(lista, line);
            makeVisible();
            ok = true;
        }
        else{
            ok = false;
        }
    }
    
    /**
     * Pinta y crea la señal, pone la señal ensima de las calles.
     * 
     * @param la union de color de donde se une las location.
     */
    public void addSing(String locationA, String locationB)
    {
        ArrayList<String> lista = new ArrayList<String>();lista.add(locationA);lista.add(locationB);
        Collections.sort(lista);
        int [] coor;
        if ((street.containsKey(lista)) && (!sign.containsKey(lista))){
            saveundo();
            coor = coorTriangle(location.get(locationA).getLocationX()+ 9, location.get(locationA).getLocationY()+ 9, location.get(locationB).getLocationX()+ 9, location.get(locationB).getLocationY()+ 9);
            triangle = new Triangle(coor[0], coor[1]);
            sign.put(lista, triangle);
            makeVisible();
            ok = true;
        }
        else{
            ok = false;
        }
    }
    
    /**
     * Saca las coordenadas de ubicacion del triangulo
     * 
     * @param la union de color de donde se une las location.
     */
    private int [] coorTriangle(int x1, int y1, int x2, int y2)
    {
        int r = 50, j = y1, k = x1;
        int [] lis = new int [2];
        if (x1 == x2){
            if (y1 > y2){
                lis[0] = x1;lis[1] = (int)-Math.sqrt(Math.pow(r,2)-Math.pow(x1,2)+2*k*x1-Math.pow(k,2))+j;
                return lis;
            }
            else {
                lis[0] = x1;lis[1] = (int)Math.sqrt(Math.pow(r,2)-Math.pow(x1,2)+2*k*x1-Math.pow(k,2))+j;
                return lis;
            } 
        }
        else{
            lis[0] = posXsize(x1, y1, x2, y2, lis[0]);
            lis[1] = posYsize(x1, y1, x2, y2, lis[0]);
            return lis;
        }
    }
    
    /**
     * Calcula el eje y de la señal
     * 
     * @param  el color de la location que quiere elimnar.
     */
    private int posYsize(int x1, int y1, int x2, int y2, int lis)
    {
        float r = (float)50, j = (float)y1, k = (float)x1;
        float m;
        m = ((float)(y1-y2)/(float)(x1-x2));
        if(x1>x2){
            return (int)(((float)j+(float)Math.pow((float)m,2)*(float)j-(float)m*(float)r*(float)Math.sqrt((float)Math.pow((float)m,2)+1))/(1+(float)Math.pow((float)m,2)));
        }
        else{
            return (int)((j+Math.pow(m,2)*j+m*r*Math.sqrt(Math.pow(m,2)+1))/(1+Math.pow(m,2)));
        }
    }
    
    /**
     * Calcula el eje x de la señal
     * 
     * @param  el color de la location que quiere elimnar.
     */
    private int posXsize(int x1, int y1, int x2, int y2, int lis)
    {
        float r = (float)50, j = (float)y1, k = (float)x1;
        double m;
        m = ((float)(y1-y2)/(float)(x1-x2));
        if(x1>x2){
            return (int)(((float)Math.pow((float)m,2)*(float)k+(float)k-(float)r*(float)Math.sqrt((float)Math.pow((float)m,2)+1))/((float)Math.pow((float)m,2)+1));
        }
        else{
            return (int)((Math.pow(m,2)*k+k+r*Math.sqrt(Math.pow(m,2)+1))/(Math.pow(m,2)+1));
        }
    }
    
    /**
     * Elimina la location
     * 
     * @param  el color de la location que quiere elimnar.
     */
    public void delLocation(String color)
    {
        if (location.containsKey(color)){
            makeInvisible();
            saveundo();
            location.remove(color);
            makeVisible();
            delStreet();
            delSing();
            ok = true;
        }
        else{
            ok = false;
        }
    }
    
    /**
     * Elimina la location con parametros
     * 
     * @param la ubicacion de donde se ubica la calle
     */
    public void delStreet(String locationA, String locationB)
    {
        ArrayList<String> lista = new ArrayList<String>();lista.add(locationA);lista.add(locationB);Collections.sort(lista);
        if (street.containsKey(lista)){
            saveundo();
            makeInvisible();
            street.remove(lista);
            makeVisible();
            delSing();
            delStreet();
            ok = true;
        }
        else{
            ok = false;
        }
    }
    
    /**
     * Elimina la calle
     * 
     */
    public void delStreet()
    {
        makeInvisible();
        for (int i = 0; street.size() > i; i++){
            for (HashMap.Entry<ArrayList<String>, Line> entry : street.entrySet()){
                if (!location.containsKey(entry.getKey().get(0)) || !location.containsKey(entry.getKey().get(1))){
                    ArrayList<String> lista = entry.getKey();
                    street.remove(lista);
                    break;
                }
            }
        }
        makeVisible();
    }
    
    /**
     * Elimina la señal
     * 
     * @param la ubicacion de donde se ubica la señal
     */
    public void delSing(String locationA, String locationB)
    {
        ArrayList<String> lista = new ArrayList<String>();lista.add(locationA);lista.add(locationB);Collections.sort(lista);
        if (sign.containsKey(lista)){
            makeInvisible();
            saveundo();
            sign.remove(lista);
            makeVisible();
            ok = true;
        }
        else{
            ok = false;
        }
    }
    
    /**
     * Elimina la señal 
     * 
     */
    public void delSing()
    {
        makeInvisible();
        for (int i = 0; sign.size() > i; i++){
            for (HashMap.Entry<ArrayList<String>, Triangle> entry : sign.entrySet()){
                if (!street.containsKey(entry.getKey())){
                    ArrayList<String> lista = entry.getKey();
                    sign.remove(lista);
                    break;
                }
            }
        }
        makeVisible();
    }
    
    /**
     * Rehacer los objetos 
     */
    public void redo(){
        if (redoloc.size() != 0){
            System.out.println(location.size());
            makeInvisible();
            location = redoloc.pop();
            System.out.println(location.size());
            street = redostre.pop();
            sign = redosig.pop();
            makeVisible();
            ok = true;
        }
        else{
            ok = false;
        }
    }
    
    /**
     * Guarda el mapa actual 
     */
    private void saveredo(){
        HashMap<String, Circle> l = new HashMap<String, Circle>(location);
        redoloc.add(l);
        HashMap<ArrayList<String>, Line> s = new HashMap<ArrayList<String>, Line>(street);
        redostre.add(s);
        HashMap<ArrayList<String>, Triangle> si = new HashMap<ArrayList<String>, Triangle>(sign);
        redosig.add(si);
    }
    
    /**
     * Deshace los objetos 
     */
    public void undo(){
        if (undoloc.size() != 0){
            saveredo();
            makeInvisible();
            location = undoloc.pop();
            street = undostre.pop();
            sign= undosig.pop();
            makeVisible();
            ok = true;
        }
        else{
            ok = false;
        }
        
    }
    
    /**
     * Guarda el mapa antes de modificar 
     */
    private void saveundo(){
        System.out.println(location.size());
        HashMap<String, Circle> l = new HashMap<String, Circle>(location);
        undoloc.add(l);
        HashMap<ArrayList<String>, Line> s = new HashMap<ArrayList<String>, Line>(street);
        undostre.add(s);
        HashMap<ArrayList<String>, Triangle> si = new HashMap<ArrayList<String>, Triangle>(sign);
        undosig.add(si);
    }
    
    /**
     * Da informacion del mapa.
     * 
     * @return Devulve la informacion del mapa en forma de matriz.
     */
    public String [][] roadMap()
    {
        String [][] map = {{"Location", location.size()+""}, {"Street",street.size()+""}, {"Sign",sign.size()+""}};
        return map; 
    }
    
    /**
     * Da informacion de las location.
     * @return devuelve una matriz con la informacion
     */
    public String [][] allLocation(){
        String [][] lis = new String[location.size()][3];
        ArrayList <String> lista = new ArrayList<String>();
        for (HashMap.Entry<String, Circle> entry : location.entrySet()){
            lista.add(entry.getKey().toLowerCase());
        }
        Collections.sort(lista);
        for (int i = 0; lista.size() > i; i++){
            lis [i][0] = "color"+lista.get(i);
            lis [i][1] = "x"+Math.abs(location.get(lista.get(i)).getLocationX());
            lis [i][2] = "y" +Math.abs(location.get(lista.get(i)).getLocationY()-580);
        }
        return lis;
    }
    
    /**
     * Da informacion de las calles.
     * @return devuelve una matriz con la informacion
     */
    public String [][] allStreets(){
        String [][] lis = new String[street.size()][2];
        ArrayList <String> lista = new ArrayList<String>();
        ArrayList <List<String>> lista1 = new ArrayList<List<String>>();
        
        for (HashMap.Entry<ArrayList<String>, Line> entry : street.entrySet()){
            lista.add(entry.getKey().get(0)+","+entry.getKey().get(1));
        }
        Collections.sort(lista);
        for (int i = 0; lista.size() > i; i++){
            ArrayList<String> al = new ArrayList<String>();
            for (String s: lista.get(i).split(",")){al.add(s);}
            lista1.add(al);
        }
        for (int i = 0; lista.size() > i; i++){
            lis [i][0] = "color"+lista1.get(i).get(0);
            lis [i][1] = "color"+lista1.get(i).get(1);
        }
        return lis;
    }
    
    /**
     * 
     */
    public void unnecessary(){
    
    }
    
    /**
     * Da informacion de las señales
     * @return devuelve una matriz con la informacion
     */
    public String [][] allSigns(){
        String [][] lis = new String[sign.size()][2];
        ArrayList <String> lista = new ArrayList<String>();
        ArrayList <List<String>> lista1 = new ArrayList<List<String>>();
        
        for (HashMap.Entry<ArrayList<String>, Triangle> entry : sign.entrySet()){
            lista.add(entry.getKey().get(0)+","+entry.getKey().get(1));
        }
        Collections.sort(lista);
        for (int i = 0; lista.size() > i; i++){
            ArrayList<String> al = new ArrayList<String>();
            for (String s: lista.get(i).split(",")){al.add(s);}
            lista1.add(al);
        }
        for (int i = 0; lista.size() > i; i++){
            lis [i][0] = "color"+lista1.get(i).get(0);
            lis [i][1] = "color"+lista1.get(i).get(1);
        }
        return lis;
    }
    
    /**
     * 
     */
    public void wrongSignals(){
    
    }
    
    /**
     * Muestra todos los objetos
     * 
     */
    public void makeVisible()
    {
        for (HashMap.Entry<ArrayList<String>, Line> entry : street.entrySet()){
            entry.getValue().makeVisible();
        }
        for (HashMap.Entry<String, Circle> entry : location.entrySet()){
            entry.getValue().makeVisible();
        }
        for (HashMap.Entry<ArrayList<String>, Triangle> entry : sign.entrySet()){
            entry.getValue().makeVisible();
        }
    }
    
    /**
     * Hace invisible todos los objetos
     * 
     */
    public void makeInvisible()
    {
        for (HashMap.Entry<String, Circle> entry : location.entrySet()){
            entry.getValue().makeInvisible();
        }
        for (HashMap.Entry<ArrayList<String>, Line> entry : street.entrySet()){
            entry.getValue().makeInvisible();
        }
        for (HashMap.Entry<ArrayList<String>, Triangle> entry : sign.entrySet()){
            entry.getValue().makeInvisible();
        }
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void finish()
    {
        // put your code here
    }
    
    /**
     * Valida si lo ultimo indicado fue posible realizar. 
     * 
     * @return retorna tru o false, dependiendo si se realizo o no.
     */
    public boolean ok()
    {
        return ok;
    }
}
