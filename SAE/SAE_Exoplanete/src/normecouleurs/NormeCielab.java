package normecouleurs;

public class NormeCielab implements NormeCouleurs {
    @Override
    public double distanceCouleur(int couleur1, int couleur2) {

        int[] rgb1 = OutilCouleur.getTabColor(couleur1);
        int[] rgb2 = OutilCouleur.getTabColor(couleur2);

        int[] lab1 = RGB2LAB.rgb2lab(rgb1[0], rgb1[1], rgb1[2]);
        int[] lab2 = RGB2LAB.rgb2lab(rgb2[0], rgb2[1], rgb2[2]);

        double deltaL = lab1[0] - lab2[0];
        double deltaA = lab1[1] - lab2[1];
        double deltaB = lab1[2] - lab2[2];

        return Math.sqrt(deltaL * deltaL + deltaA * deltaA + deltaB * deltaB);
    }

}
