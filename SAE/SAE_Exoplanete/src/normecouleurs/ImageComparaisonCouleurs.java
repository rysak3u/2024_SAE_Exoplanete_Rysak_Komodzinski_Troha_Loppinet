package normecouleurs;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageComparaisonCouleurs {

    public static boolean sauverImageComparaisonCouleurs(String cheminFichierOriginal, String cheminCopie, Palette palette) throws Exception {
        // On charge notre image pour la manipuler en java
        File f = new File(cheminFichierOriginal);
        BufferedImage img = ImageIO.read(f);

        // La copie pixel par pixel
        // Donc on la parcourt en largeur et en hauteur, double boucle
        int largeur = img.getWidth();
        int hauteur = img.getHeight();

        // On instancie notre image copiée, à partir des dimensions de notre image de base
        BufferedImage img_copie = new BufferedImage(largeur, hauteur, BufferedImage.TYPE_3BYTE_BGR);

        // Puis on parcourt toutes les coordonnées (pixel par pixel) de l'image, et à chaque fois
        // on prend la couleur de l'image de base à tel pixel, pour la transformer en gris et
        // l'ajouter au pixel de même coordonée de l'image copiée
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                // On récupère la couleur
                int couleur = img.getRGB(i, j);

                // On obtient la couleur la plus proche dans la palette
                String closestColorName = palette.getPlusProche(couleur);
                int closestColor = palette.getColors().get(closestColorName);

                // Puis on ajoute le rgb nuancé en gris à l'image aux coordonnées
                img_copie.setRGB(i, j, closestColor);
            }
        }
        // Et on finit par écrire le flux de notre image convertie en noir et blanc sur le pc
        return ImageIO.write(img_copie, "jpg", new File(cheminCopie));
    }



    public static void main(String[] args) throws Exception {
        // tableau des couleurs souahitées pour le comparatif1
        // CHANGER NORME PAR LA DESIRÉE
        Palette palette = new Palette(new NormeCielab());
        palette.getColors().put("Green", Color.GREEN.getRGB());
        palette.getColors().put("Yellow", Color.YELLOW.getRGB());
        boolean ecrit = sauverImageComparaisonCouleurs("img/Planete 2.jpg", "img/post_traitement/Planete 2.jpg", palette);
        System.out.println("Succès de l'écriture de l'image ? : " + ecrit);
    }
}
