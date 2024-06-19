package clustering;

import normecouleurs.NormeCouleurs;

import java.awt.*;

/**
 * Classe qui propose une méthode permettant d'obtenir pour chaque pixel d'une image, le biome
 * auquel il appartient
 */
public class Biome implements Clustering{

    /**
     * Nombre de barycentres/biomes pour l'algorithme KMeans
     */
    private int nbBiomes;

    /**
     * Métrique de couleur utilisée pour calculer la distance entre deux couleurs
     */
    private NormeCouleurs norme;

    /**
     * Constructeur d'un objet Biome, prenant un nombre de barycentres/biomes et la métrique de couleur utilisée
     * @param nbBiomes
     * @param norme
     */
    public Biome(int nbBiomes, NormeCouleurs norme){
        this.nbBiomes = nbBiomes;
        this.norme = norme;
    }


    /**
     * Retourne une liste de taille de tous les pixels d'une image.
     * Chaque indice représentant un pixel, et à chaque indice la valeur associée et l'identifiant (int)
     * du cluster auquel appartient le pixel
     * @param objets
     * @return
     */
    @Override
    public int[] clusteriser(double[][] objets) {
        int index = 0;
        int[] centroides = new int[nbBiomes]; // On crée le tableau vide qui comportera les valeurs rgb de nos centroides
        int[] groupes = new int[objets.length]; // On crée le tableau qu'on retournera, comportant pour chaque pixel le biome auquel il appartient

        // On instancie notre tableau de centroides avec des couleurs aléatoires en nuances de rouge bleu et vert
        for(int i=0;i<centroides.length;i++){
            int bleu = (int)Math.round(Math.random()*255);
            int rouge = (int)Math.round(Math.random()*255);
            int vert = (int)Math.round(Math.random()*255);
            centroides[i] = new Color(bleu,vert,rouge).getRGB();
        }

        // On instancie la condition d'arrêt à false
        boolean finito = false;

        // Puis on itère sur l'algorithme KMeans tant que la condition est à false
        while (!finito){
            // On réinitialise le biome auquel appartient chaque pixel en début d'itération
            for( int i=0; i< groupes.length;i++){
                groupes[i] = -1; // par forcément utile imo
            }
            // On parcourt maintenant le tableau qui contient les pixels
            // ainsi que leur valeur rgb
            // afin de déterminer, pour chaque pixel, de quelle couleur de centroide il est le plus proche
            // et ainsi pouvoir ajuster la valeur RGB du centroide dont il est le plus proche
            for(int i = 0;i< objets.length;i++){
                int indexCentroidePlusProche = -1;
                double valCentroidePlusProche = Double.MAX_VALUE;
                for (int j = 0; j < centroides.length; j ++){
                    double distance = norme.distanceCouleur(new Color(centroides[j]).getRGB(),new Color((int)objets[i][1],(int)objets[i][2],(int)objets[i][3]).getRGB());
                    if(distance < valCentroidePlusProche){
                        valCentroidePlusProche = distance;
                        indexCentroidePlusProche = j;
                    }
                }
                groupes[i]=indexCentroidePlusProche;
            }
            index++;
            finito = true;
            // Puis
            for (int i = 0; i < centroides.length; i++) {
                int sumR = 0, sumG = 0, sumB = 0, count = 0;
                for (int j = 0; j < groupes.length; j++) {
                    if (groupes[j] == i) {
                        Color color = new Color((int)objets[j][1], (int)objets[j][2], (int)objets[j][3]);
                        sumR += color.getRed();
                        sumG += color.getGreen();
                        sumB += color.getBlue();
                        count++;
                    }
                }
                if (count > 0) {
                    finito = false;
                    int avgR = sumR / count;
                    int avgG = sumG / count;
                    int avgB = sumB / count;
                    centroides[i] = new Color(avgR, avgG, avgB).getRGB();
                }
            }

            if(index>=200){
                finito = true;
            }
        }
        return groupes;
    }
}
