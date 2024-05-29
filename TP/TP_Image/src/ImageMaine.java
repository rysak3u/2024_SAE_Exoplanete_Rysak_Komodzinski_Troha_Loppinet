import java.awt.*;

public class ImageMaine {
    public static void main(String[] args) {
        ImageCopy.copyImage("./img/stanislas.png","./img/stanislas_basic_copy.png");
        ImageCopy.copyPixelbPixel("./img/stanislas.png","./img/stanislas_pixel_copy.png");
        ImageCopy.convertToBlackAndWhite("./img/stanislas.png","./img/stanislas_black_and_white_copy.png");
        ImageCopy.convertToRed("./img/stanislas.png","./img/stanislas_red_copy.png");
        ImageCopy.convertToGreenAndBlue("./img/stanislas.png","./img/stanislas_green_blue_copy.png");
        ImageCopy.replaceByNearestColor("./img/fleur.jpg","./img/fleur_green_yellow_copy.jpg", Color.GREEN,Color.YELLOW);

    }
}
