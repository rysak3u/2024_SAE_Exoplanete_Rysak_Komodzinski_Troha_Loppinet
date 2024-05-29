import java.awt.*;

public class Palette {
   Color[] colors;

   public Palette(Color[] c){
       colors = c;
   }

   public Color getPlusProche(Color colorCompared){
       double valPlusProche = Double.MAX_VALUE;
       Color colorPlusProche = null;
       for(Color c  : colors){
           double distance = ImageCopy.distanceBetweenColor(c,colorCompared);
           if(distance <valPlusProche){
               valPlusProche = distance;
               colorPlusProche = c;
           }
       }
       return colorPlusProche;
   }

}
