import java.awt.*;

public class NormeNewCieLab implements NormeCouleurs{
    public double distanceCouleur(Color couleur1, Color couleur2){
        int[] lab1=CieLab.rgb2lab(couleur1.getRed(), couleur1.getGreen(), couleur1.getBlue());
        int[] lab2=CieLab.rgb2lab(couleur2.getRed(), couleur2.getGreen(), couleur2.getBlue());
        double c1 = Math.sqrt(Math.pow(lab1[1],2)+Math.pow(lab1[2],2));
        double c2 = Math.sqrt(Math.pow(lab2[1],2)+Math.pow(lab2[2],2));
        int deltaL = lab1[0]-lab2[0];
        double deltaC = c1-c2;
        double deltaH = Math.sqrt(Math.pow(lab1[1]-lab2[1],2)+Math.pow(lab1[2]-lab2[2],2)-Math.pow(deltaC,2));
        double sC = 1+0.045*c1;
        double sH = 1+0.015*c1;
        return Math.sqrt(Math.pow(deltaL/1,2)+ Math.pow(deltaC/sC,2)+Math.pow(deltaH/sH,2));
    }
}
