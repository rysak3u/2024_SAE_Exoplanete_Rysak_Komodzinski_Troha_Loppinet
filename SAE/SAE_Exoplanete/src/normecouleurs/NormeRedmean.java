package normecouleurs;

public class NormeRedmean implements NormeCouleurs {
    @Override
    public double distanceCouleur(int couleur1, int couleur2) {
        int[] rgb1 = OutilCouleur.getTabColor(couleur1);
        int[] rgb2 = OutilCouleur.getTabColor(couleur2);

        double r = (rgb1[0] + rgb2[0]) / 2.0;
        double deltaR = rgb1[0] - rgb2[0];
        double deltaG = rgb1[1] - rgb2[1];
        double deltaB = rgb1[2] - rgb2[2];

        return Math.sqrt((2 + r / 256) * deltaR * deltaR + 4 * deltaG * deltaG + (2 + (255 - r) / 256) * deltaB * deltaB);
    }
}
