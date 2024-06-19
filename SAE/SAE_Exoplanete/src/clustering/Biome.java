package clustering;

import normecouleurs.NormeCouleurs;

import java.awt.*;

public class Biome implements Clustering{
    private int nbBiomes;
    private NormeCouleurs norme;
    public Biome(int nbBiomes, NormeCouleurs norme){
        this.nbBiomes = nbBiomes;
        this.norme = norme;
    }
    @Override
    public int[] clusteriser(double[][] objets) {
        int index = 0;
        int[] centroides = new int[nbBiomes];
        int[] groupes = new int[objets.length];
        for(int i=0;i<centroides.length;i++){
            int bleu = (int)Math.round(Math.random()*255);
            int rouge = (int)Math.round(Math.random()*255);
            int vert = (int)Math.round(Math.random()*255);
            centroides[i] = new Color(bleu,vert,rouge).getRGB();
        }
        boolean finito = false;
        while (!finito){
            for( int i=0; i< groupes.length;i++){
                groupes[i] = -1;
            }
            for(int i = 0;i< objets.length;i++){
                int indexCentroidePlusProche = -1;
                double valCentroidePlusProche = Double.MAX_VALUE;
                for (int j = 0; j < centroides.length; j ++){
                    //System.out.println(objets[i][1]+" "+objets[i][2]+" "+objets[i][3]);
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
