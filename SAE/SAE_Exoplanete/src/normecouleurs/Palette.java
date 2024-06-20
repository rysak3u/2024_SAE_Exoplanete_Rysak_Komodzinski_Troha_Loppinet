package normecouleurs; // Déclaration du package 'normecouleurs'

import java.awt.*; // Importation de la bibliothèque AWT pour la gestion des couleurs
import java.util.HashMap; // Importation de la classe HashMap pour stocker les couleurs
import java.util.Map; // Importation de la classe Map pour les opérations de mappage

public class Palette {
    private Map<String, Integer> colors; // Déclaration d'une map pour stocker les noms de couleur et leurs valeurs RGB
    private NormeCouleurs norme; // Déclaration d'un objet de type NormeCouleurs pour la gestion des distances de couleur

    // Constructeur de la classe Palette
    public Palette(NormeCouleurs norme) {
        this.norme = norme; // Initialisation de l'objet norme
        colors = new HashMap<>(); // Initialisation de la map colors
        // Ajout de différentes couleurs à la map avec leurs valeurs RGB
        colors.put("Black", new Color(0, 0, 0).getRGB());
        colors.put("White", new Color(255, 255, 255).getRGB());
        colors.put("Red", new Color(255, 0, 0).getRGB());
        colors.put("Lime", new Color(0, 255, 0).getRGB());
        colors.put("Blue", new Color(0, 0, 255).getRGB());
        colors.put("Yellow", new Color(255, 255, 0).getRGB());
        colors.put("Cyan", new Color(0, 255, 255).getRGB());
        colors.put("Magenta", new Color(255, 0, 255).getRGB());
        colors.put("Silver", new Color(192, 192, 192).getRGB());
        colors.put("Gray", new Color(128, 128, 128).getRGB());
        colors.put("Maroon", new Color(128, 0, 0).getRGB());
        colors.put("Olive", new Color(128, 128, 0).getRGB());
        colors.put("Green", new Color(0, 128, 0).getRGB());
        colors.put("Purple", new Color(128, 0, 128).getRGB());
        colors.put("Teal", new Color(0, 128, 128).getRGB());
        colors.put("Navy", new Color(0, 0, 128).getRGB());
    }

    // Méthode pour trouver la couleur la plus proche d'une couleur donnée
    public String getPlusProche(int color) {
        String closestColor = null; // Initialisation de la variable pour stocker la couleur la plus proche
        double minDistance = Double.MAX_VALUE; // Initialisation de la distance minimale à une valeur maximale
        // Parcours de toutes les couleurs dans la map
        for (Map.Entry<String, Integer> entry : colors.entrySet()) {
            // Calcul de la distance entre la couleur donnée et la couleur actuelle de la map
            double distance = norme.distanceCouleur(color, entry.getValue());
            // Si la distance calculée est inférieure à la distance minimale actuelle
            if (distance < minDistance) {
                minDistance = distance; // Mise à jour de la distance minimale
                closestColor = entry.getKey(); // Mise à jour de la couleur la plus proche
            }
        }
        return closestColor; // Retourne la couleur la plus proche
    }

    // Getter pour obtenir l'objet norme
    public NormeCouleurs getNorme() {
        return norme;
    }

    // Getter pour obtenir la map des couleurs
    public Map<String, Integer> getColors() {
        return colors;
    }
}
