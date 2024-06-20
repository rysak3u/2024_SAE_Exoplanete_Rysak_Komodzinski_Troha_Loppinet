package normecouleurs; // Déclaration du package 'normecouleurs'

// Déclaration de la classe NormeBasique qui implémente l'interface NormeCouleurs
public class NormeBasique implements NormeCouleurs {
    // Implémentation de la méthode distanceCouleur définie dans l'interface NormeCouleurs
    @Override
    public double distanceCouleur(int couleur1, int couleur2) {
        // Conversion des couleurs de format entier en tableau RGB
        int[] targetColor = OutilCouleur.getTabColor(couleur1);
        int[] paletteColor = OutilCouleur.getTabColor(couleur2);

        // Retourne la distance de couleur en utilisant la formule Euclidienne dans l'espace RGB
        return Math.sqrt(
                Math.pow(targetColor[0] - paletteColor[0], 2) + // Différence des composantes rouges au carré
                        Math.pow(targetColor[1] - paletteColor[1], 2) + // Différence des composantes vertes au carré
                        Math.pow(targetColor[2] - paletteColor[2], 2)   // Différence des composantes bleues au carré
        );
    }
}
