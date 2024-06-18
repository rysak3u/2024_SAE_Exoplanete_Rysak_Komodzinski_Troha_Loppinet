import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FiltreMoyenne fM = new FiltreMoyenne(10); // Filtre de taille 100x100
            File sourceFile = new File("img/Planete 3.jpg");
            BufferedImage image = ImageIO.read(sourceFile);
            int height = image.getHeight();
            int width = image.getWidth();
            BufferedImage copyImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int[][] matrice = new int[10][10];
                    for (int dy = -5; dy < 4; dy++) {
                        for (int dx = -5; dx < 4; dx++) {
                            int nx = Math.min(Math.max(x + dx, 0), width - 1);
                            int ny = Math.min(Math.max(y + dy, 0), height - 1);
                            matrice[dy + 5][dx + 5] = image.getRGB(nx, ny);
                        }
                    }
                    int filteredRgb = fM.appliquerFiltre(matrice);
                    copyImage.setRGB(x, y, filteredRgb);
                }
            }

            ImageIO.write(copyImage, getFileExtension(sourceFile.getPath()), new File("img/pFlou.jpg"));
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
