package normecouleurs; // Déclaration du package 'normecouleurs'

// Déclaration de la classe NormeRedmean qui implémente l'interface NormeCouleurs
public class NormeRedmean implements NormeCouleurs {
    // Implémentation de la méthode distanceCouleur définie dans l'interface NormeCouleurs
    @Override
    public double distanceCouleur(int couleur1, int couleur2) {
        // Conversion des couleurs de format entier en tableau RGB
        int[] rgb1 = OutilCouleur.getTabColor(couleur1);
        int[] rgb2 = OutilCouleur.getTabColor(couleur2);

        // Calcul de la moyenne des valeurs rouges des deux couleurs
        double r = (rgb1[0] + rgb2[0]) / 2.0;
        // Calcul de la différence des valeurs rouges, vertes et bleues des deux couleurs
        double deltaR = rgb1[0] - rgb2[0];
        double deltaG = rgb1[1] - rgb2[1];
        double deltaB = rgb1[2] - rgb2[2];

        // Retourne la distance de couleur en utilisant la formule Redmean
        return Math.sqrt(
                (2 + r / 256) * deltaR * deltaR +
                        4 * deltaG * deltaG +
                        (2 + (255 - r) / 256) * deltaB * deltaB
        );
    }
}
