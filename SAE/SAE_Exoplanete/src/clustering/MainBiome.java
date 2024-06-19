package clustering;

import normecouleurs.NormeBasique;
import normecouleurs.OutilCouleur;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MainBiome {
    public static void main(String[] args) throws IOException {
        File sourceFile = new File("img/Planete 3.jpg");
        BufferedImage image = ImageIO.read(sourceFile);
        int height = image.getHeight();
        int width = image.getWidth();
        double[][] objets = new double[height * width][4];
        int nom = 0;
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
        Clustering biome = new Biome(10,new NormeBasique());
        int[] groupes = biome.clusteriser(objets);
       // System.out.println(Arrays.toString(groupes));
        int[] palettes = new int[10];
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
        int index=0;
        BufferedImage copyImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for(int x = 0 ; x<width;x++){
            for(int y = 0 ; y<height;y++){
                copyImage.setRGB(x,y,palettes[groupes[index]]);
                index++;
            }
        }
        ImageIO.write(copyImage, getFileExtension(sourceFile.getPath()), new File("img/biomeClusterPlanete3.jpg"));
    }
    public static String getFileExtension(String filePath) {
        int dotIndex = filePath.lastIndexOf('.');
        if (dotIndex >= 0 && dotIndex < filePath.length() - 1) {
            return filePath.substring(dotIndex + 1);
        } else {
            return "";
        }
    }
}
