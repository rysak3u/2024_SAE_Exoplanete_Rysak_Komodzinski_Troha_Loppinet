import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageCopy {

    public static void copyImage(String sourcePath, String destinationPath) {
        try {
            File sourceFile = new File(sourcePath);
            BufferedImage image = ImageIO.read(sourceFile);


            String formatName = getFileExtension(destinationPath);


            File destinationFile = new File(destinationPath);
            System.out.println(ImageIO.write(image, formatName, destinationFile));
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

    public static void copyPixelbPixel(String imgPath, String destinationPath) {
        try {
            File sourceFile = new File(imgPath);
            BufferedImage image = ImageIO.read(sourceFile);
            int height = image.getHeight();
            int width = image.getWidth();
            BufferedImage copyImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int rgb = image.getRGB(x, y);
                    copyImage.setRGB(x, y, rgb);
                }
            }
            ImageIO.write(copyImage, ImageCopy.getFileExtension(imgPath), new File(destinationPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void convertToBlackAndWhite(String imgPath, String destinationPath) {
        try {
            File sourceFile = new File(imgPath);
            BufferedImage image = ImageIO.read(sourceFile);
            int height = image.getHeight();
            int width = image.getWidth();
            BufferedImage copyImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int rgb = image.getRGB(x, y);
                    int[] rgbArray = OutilCouleur.getTabColor(rgb);
                    int rgbMoyenne = (rgbArray[0] + rgbArray[1] + rgbArray[2]) / 3;
                    rgb =(int)Math.floor(rgbMoyenne + rgbMoyenne * 256 + rgbMoyenne * Math.pow(256,2));
                    copyImage.setRGB(x, y, rgb);
                }
            }
            ImageIO.write(copyImage, ImageCopy.getFileExtension(imgPath), new File(destinationPath));
        } catch (IOException e) {

        }
    }

    public static void convertToRed(String imgPath, String destinationPath) {
        BufferedImage image = loadImage(imgPath);
        int height = image.getHeight();
        int width = image.getWidth();
        BufferedImage copyImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = image.getRGB(x, y);
                rgb = rgb & 0xff0000;
                copyImage.setRGB(x, y, rgb);
            }
        }
        try {
            ImageIO.write(copyImage, ImageCopy.getFileExtension(imgPath), new File(destinationPath));
        }catch (IOException e){

        }
    }

    public static void convertToGreenAndBlue(String imgPath, String destinationPath) {
        BufferedImage image = loadImage(imgPath);
        int height = image.getHeight();
        int width = image.getWidth();
        BufferedImage copyImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = image.getRGB(x, y);
                rgb = rgb & 0x00ffff;
                copyImage.setRGB(x, y, rgb);
            }
        }
        try {
            ImageIO.write(copyImage, ImageCopy.getFileExtension(imgPath), new File(destinationPath));
        } catch (IOException e) {

        }
    }

        private static BufferedImage loadImage(String imgPath) {
        try {
            File sourceFile = new File(imgPath);
            return ImageIO.read(sourceFile);
        } catch (IOException e) {
            return null;
        }
    }

    public static void replaceByNearestColor(String imgPath, String destinationPath,Color c1, Color c2){
        BufferedImage image = loadImage(imgPath);
        int height = image.getHeight();
        int width = image.getWidth();
        BufferedImage copyImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = image.getRGB(x, y);
                if (distanceBetweenColor(c1, new Color(rgb)) < distanceBetweenColor(c2, new Color(rgb))) {
                    copyImage.setRGB(x, y, c1.getRGB());
                } else {
                    copyImage.setRGB(x, y, c2.getRGB());
                }
            }
        }
        try {
            ImageIO.write(copyImage, ImageCopy.getFileExtension(imgPath), new File(destinationPath));
        }catch (IOException e){

        }
    }
    public static double distanceBetweenColor(Color c1, Color c2){

        return Math.sqrt(Math.pow(c1.getRed()-c2.getRed(),2)+ Math.pow(c1.getGreen()-c2.getGreen(),2)+Math.pow(c1.getBlue()-c2.getBlue(),2));
    }
}