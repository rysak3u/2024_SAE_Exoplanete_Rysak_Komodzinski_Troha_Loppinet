import java.awt.*;

public class ImageMaine {
    public static void main(String[] args) {
        ImageCopy.copyImage("./img/stanislas.png","./img/stanislas_basic_copy.png");
        ImageCopy.copyPixelbPixel("./img/stanislas.png","./img/stanislas_pixel_copy.png");
        ImageCopy.convertToBlackAndWhite("./img/stanislas.png","./img/stanislas_black_and_white_copy.png");
        ImageCopy.convertToRed("./img/stanislas.png","./img/stanislas_red_copy.png");
        ImageCopy.convertToGreenAndBlue("./img/stanislas.png","./img/stanislas_green_blue_copy.png");
        ImageCopy.replaceByNearestColor("./img/fleur.jpg","./img/fleur_green_yellow_copy.jpg", Color.GREEN,Color.YELLOW);
        ImageCopy.replaceByNearestColor("./img/stanislas.png","./img/stanislas_black_and_white_test.png",Color.black,Color.white);
        Palette p = new Palette(new Color[]{Color.BLACK,Color.BLUE,Color.RED,Color.CYAN,Color.YELLOW,Color.LIGHT_GRAY,Color.GREEN,Color.MAGENTA,Color.ORANGE,Color.pink,new Color(128,0,0),new Color(192,192,192),new Color(0,0,128),new Color(0,255,255),new Color(0,255,255),new Color(0,128,128),new Color(128,128,0),new Color(255,215,0),new Color(184,134,11),new Color(218,165,32),new Color(238,232,170),new Color(189,183,107),new Color(240,230,140),new Color(154,205,50),new Color(85,107,47),new Color(0,250,154),new Color(64,224,208),new Color(25,25,112),new Color(255,20,147)},new NormeRedMean());
        ImageCopy.nearestPaletteColor("./img/stanislas.png","./img/stanislas_palette_color.png",p);
        ImageCopy.nearestPaletteColor("./img/fleur.jpg","./img/fleur_palette_color.png",p);
    }
}
