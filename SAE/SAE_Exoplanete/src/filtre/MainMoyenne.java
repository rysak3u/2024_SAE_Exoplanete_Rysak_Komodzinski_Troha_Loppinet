package filtre;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMoyenne {
    public static void main(String[] args) {
        try {
            FiltreMoyenne fM = new FiltreMoyenne(9); // Filtre de taille 5x5
            File sourceFile = new File("img/Planete 3.jpg");
            BufferedImage image = ImageIO.read(sourceFile);
            int height = image.getHeight();
            int width = image.getWidth();
            BufferedImage copyImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

            int[][] pixels = new int[height][width];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    pixels[y][x] = image.getRGB(x, y);
                }
            }

            int[][] filteredPixels = fM.appliquerFiltre(pixels);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    copyImage.setRGB(x, y, filteredPixels[y][x]);
                }
            }

            ImageIO.write(copyImage, getFileExtension(sourceFile.getPath()), new File("img/pFlouMoyenne.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
