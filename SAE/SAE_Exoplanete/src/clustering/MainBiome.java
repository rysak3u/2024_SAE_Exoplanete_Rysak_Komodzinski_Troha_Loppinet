package clustering;

import filtre.Filtre;
import filtre.FiltreGaussien;
import filtre.FiltreMoyenne;
import normecouleurs.NormeBasique;
import normecouleurs.NormeCielab;
import normecouleurs.NormeRedmean;
import normecouleurs.OutilCouleur;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Cette classe permet, pour une carte donnée, d'en ressortir sous formes d'autres images
 * les différents biomes, en utilisant un algorithme (actuellement KMeans) et une métrique de couleur
 */
public class MainBiome {
    public static void main(String[] args) throws IOException {
        File sourceFile = new File("img/Planete 2.jpg");
        BufferedImage imageSource = ImageIO.read(sourceFile);
        int height = imageSource.getHeight();
        int width = imageSource.getWidth();
        /*
        * On applique un flou gaussien à l'image
        * */

        //on recupére la matrice des couleurs de chaque pixel de l'image
        int[][] matrice = new int[width][height];

        for(int x = 0 ; x < width ; x++){
            for(int y = 0 ; y<height ; y++){
                matrice[x][y]=imageSource.getRGB(x,y);
            }
        }
        //on lui applique le Filtre Gaussien
        Filtre f = new FiltreGaussien(9,1.5);
        matrice = f.appliquerFiltre(matrice);

        //on créer une nouvelle image en appliquant la matrice des couleurs renvoyé par le filtre gaussien
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        for(int x = 0 ; x < width ; x++){
            for(int y = 0 ; y<height ; y++){
                image.setRGB(x,y,matrice[x][y]);
            }
        }


        // On aura un tableau qui ressemble à
        /*
        0 | 1 | 2 | 3 | 4 | 5 | 6 | 7
        R | R | R | R | R | R | R | R
        G | G | G | G | G | G | G | G
        B | B | B | B | B | B | B | B

        avec R, G et B indiquant le niveau de Rouge Vert et Bleu pour chaque pixel à tel indice
         */
        double[][] objets = new double[height * width][4];
        int nom = 0;
        // On parcourt notre tableau d'objets pour leur donner un nom qui se situe sur la première ligne
        // leur nom correspond à la valeur numérique de l'indice auquel il se situe
        // et on récupère également les couleurs RGB de l'image pour les mettres dans le tableau sur la colonne de chaque pixel
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                objets[nom][0]=nom;
                int[] couleurs = OutilCouleur.getTabColor(image.getRGB(x,y));
                objets[nom][1] = couleurs[0];
                objets[nom][2] = couleurs[1];
                objets[nom][3] = couleurs[2];
                nom++;
            }
        }

        // ON crée notre objet permettant d'utiliser l'algorithme de Clustering
        Clustering biome = new Biome(7,new NormeCielab());

        // et on fait appel à sa méthode pour nous retourner un tableau d'entiers où à chaque indice, se situe un pixel
        // le pixel de coordonnées 0:0 à l'indice 0 du tableau, le pixel de coordonnées 0:1 à l'indice 1 du tableau etc.
        // et la valeur dans le tableau associée à chaque indice correspond à l'indice de la palette auquel
        // se situe la moyenne des couleurs d'un cluster (biome)
        int[] groupes = biome.clusteriser(objets);
        System.out.println("GROUPES RECUPERES");
       // System.out.println(Arrays.toString(groupes));

        // Pour chaque pixel, on regarde à quel biome sa couleur est identifiée
        // et pour chaque biome (indice du tableau palettes), on fait la moyenne de tous les
        // pixels qui appartiennent à ce biome, pour avoir la couleur du biome et pouvoir l'afficher
        // sur l'image lors de la réécriture de la copie.
        int[] palettes = new int[7];
        for(int i =0;i<palettes.length;i++){
            int sumR = 0, sumG = 0, sumB = 0, count = 0;
            for(int j = 0;j<groupes.length;j++){
                if(groupes[j]==i){
                    Color color = new Color((int)objets[j][1], (int)objets[j][2], (int)objets[j][3]);
                    sumR += color.getRed();
                    sumG += color.getGreen();
                    sumB += color.getBlue();
                    count++;
                }
            }
            if(count !=0) {
                int avgR = sumR / count;
                int avgG = sumG / count;
                int avgB = sumB / count;
                palettes[i] = new Color(avgR, avgG, avgB).getRGB();
            }
        }
        System.out.println(Arrays.toString(palettes));



        // ON parcourt la palette puis chaque pixel pour vérifier à quelle cluster le pixel appartient
        // si on tombe sur un pixel qui appartient au cluster actuel
        // alors on set le rgb de l'image à la couleur du cluster
        for(int i = 0; i < palettes.length; i++) {
            if(!(palettes[i] == 0)){
                // On prépare notre fond d'image clair en prennat la carte et en augmentant les canaux RGB d'un certaine pourcentage
                BufferedImage biomeImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
                for(int a = 0 ; a<width;a++){
                    for(int b = 0 ; b<height;b++){
                        int couleur = image.getRGB(a, b);
                        int[] couleurs = OutilCouleur.getTabColor(couleur);


                        couleurs[0] = Math.round((couleurs[0] + (float) 75 /100 * (255 - couleurs[0])));

                        couleurs[1] = Math.round((couleurs[1] + (float) 75 /100 * (255 - couleurs[1])));
                        couleurs[2] = Math.round((couleurs[2] + (float) 75 /100 * (255 - couleurs[2])));


                        biomeImage.setRGB(a, b, new Color(couleurs[0], couleurs[1], couleurs[2]).getRGB());
                    }
                }

                int indexPixel=0; // indice du pixel dans le tableau de pixels renvoyés par l'algo
                // parcourt des pixels de l'image copiée au fond clair
                for(int j = 0; j < width; j++){

                    for(int k = 0; k < height; k++){
                        if(groupes[indexPixel] == i){
                            biomeImage.setRGB(j, k, palettes[i]);
                        }
                        indexPixel++;
                    }
                }
                // Puis on écrit l'image du biome sur le disque dur
                ImageIO.write(biomeImage, getFileExtension(sourceFile.getPath()), new File("img/Biomes/Planete2/CieLab/biomeClusterPlanete" + i + ".png"));
            }
        }
    }

    /**
     * Méthode qui retourne l'extension du fichier
     * @param filePath
     * @return
     */
    public static String getFileExtension(String filePath) {
        int dotIndex = filePath.lastIndexOf('.');
        if (dotIndex >= 0 && dotIndex < filePath.length() - 1) {
            return filePath.substring(dotIndex + 1);
        } else {
            return "";
        }
    }
}
