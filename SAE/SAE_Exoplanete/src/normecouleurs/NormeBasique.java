package normecouleurs;

import java.awt.*;

public class NormeBasique implements NormeCouleurs {
    @Override
    public double distanceCouleur(Color c1, Color c2) {
        int[] targetColor = OutilCouleur.getTabColor(c1.getRGB());
        int[] paletteColor = OutilCouleur.getTabColor(c2.getRGB());
        return Math.sqrt(
                Math.pow(targetColor[0] - paletteColor[0], 2) +
                        Math.pow(targetColor[1] - paletteColor[1], 2) +
                        Math.pow(targetColor[2] - paletteColor[2], 2)
        );
    }
}