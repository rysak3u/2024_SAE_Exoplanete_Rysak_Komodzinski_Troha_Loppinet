package normecouleurs;

public class NormeBasique implements NormeCouleurs {
    @Override
    public double distanceCouleur(int couleur1, int couleur2) {
        int[] targetColor = OutilCouleur.getTabColor(couleur1);
        int[] paletteColor = OutilCouleur.getTabColor(couleur2);
        return Math.sqrt(
                Math.pow(targetColor[0] - paletteColor[0], 2) +
                Math.pow(targetColor[1] - paletteColor[1], 2) +
                Math.pow(targetColor[2] - paletteColor[2], 2)
        );
    }
}
