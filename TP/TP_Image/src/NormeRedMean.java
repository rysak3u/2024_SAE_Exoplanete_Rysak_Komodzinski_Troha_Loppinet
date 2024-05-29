import java.awt.*;

public class NormeRedMean implements NormeCouleurs{
    @Override
    public double distanceCouleur(Color c1, Color c2) {
        int deltaR = c1.getRed() - c2.getRed();
        int deltaG = c1.getGreen() - c2.getGreen();
        int deltaB = c1.getBlue() - c2.getBlue();
        double rBizarre = 0.5*(c1.getRed()+c2.getRed());
        return Math.sqrt((2+(rBizarre/256))*Math.pow(deltaR,2)+ 4*Math.pow(deltaG,2)+(2+((255-rBizarre)/256))*Math.pow(deltaB,2));
    }
}
