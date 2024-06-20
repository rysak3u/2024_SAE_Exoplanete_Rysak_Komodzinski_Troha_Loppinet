package normecouleurs; // Déclaration du package 'normecouleurs'

// Déclaration de la classe NormeCielab qui implémente l'interface NormeCouleurs
public class NormeCielab implements NormeCouleurs {
    // Implémentation de la méthode distanceCouleur définie dans l'interface NormeCouleurs
    @Override
    public double distanceCouleur(int couleur1, int couleur2) {
        // Conversion des couleurs de format entier en tableau RGB
        int[] rgb1 = OutilCouleur.getTabColor(couleur1);
        int[] rgb2 = OutilCouleur.getTabColor(couleur2);

        // Conversion des couleurs RGB en LAB
        int[] lab1 = RGB2LAB.rgb2lab(rgb1[0], rgb1[1], rgb1[2]);
        int[] lab2 = RGB2LAB.rgb2lab(rgb2[0], rgb2[1], rgb2[2]);

        // Calcul des différences des composantes LAB entre les deux couleurs
        double deltaL = lab1[0] - lab2[0];
        double deltaA = lab1[1] - lab2[1];
        double deltaB = lab1[2] - lab2[2];

        // Retourne la distance de couleur en utilisant la formule Euclidienne dans l'espace CIE LAB
        return Math.sqrt(deltaL * deltaL + deltaA * deltaA + deltaB * deltaB);
    }
}
