import java.awt.*;

public class Palette {
   Color[] colors;
   NormeCouleurs distanceCouleur;

   public Palette(Color[] c, NormeCouleurs dC){
       colors = c;
       distanceCouleur = dC;
   }

   public Color getPlusProche(Color colorCompared){
       double valPlusProche = Double.MAX_VALUE;
       Color colorPlusProche = null;
       for(Color c  : colors){
           double distance = distanceCouleur.distanceCouleur(c,colorCompared);
           if(distance <valPlusProche){
               valPlusProche = distance;
               colorPlusProche = c;
           }
       }
       return colorPlusProche;
   }

}
