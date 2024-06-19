package normecouleurs;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Palette {
    private Map<String, Integer> colors;
    private NormeCouleurs norme;

    public Palette(NormeCouleurs norme) {
        this.norme = norme;
        colors = new HashMap<>();
        colors.put("Black", new Color(0, 0, 0).getRGB());
        colors.put("White", new Color(255, 255, 255).getRGB());
        colors.put("Red", new Color(255, 0, 0).getRGB());
        colors.put("Lime", new Color(0, 255, 0).getRGB());
        colors.put("Blue", new Color(0, 0, 255).getRGB());
        colors.put("Yellow", new Color(255, 255, 0).getRGB());
        colors.put("Cyan", new Color(0, 255, 255).getRGB());
        colors.put("Magenta", new Color(255, 0, 255).getRGB());
        colors.put("Silver", new Color(192, 192, 192).getRGB());
        colors.put("Gray", new Color(128, 128, 128).getRGB());
        colors.put("Maroon", new Color(128, 0, 0).getRGB());
        colors.put("Olive", new Color(128, 128, 0).getRGB());
        colors.put("Green", new Color(0, 128, 0).getRGB());
        colors.put("Purple", new Color(128, 0, 128).getRGB());
        colors.put("Teal", new Color(0, 128, 128).getRGB());
        colors.put("Navy", new Color(0, 0, 128).getRGB());
    }

    public String getPlusProche(int color) {
        String closestColor = null;
        double minDistance = Double.MAX_VALUE;
        for (Map.Entry<String, Integer> entry : colors.entrySet()) {
            double distance = norme.distanceCouleur(color, entry.getValue());
            if (distance < minDistance) {
                minDistance = distance;
                closestColor = entry.getKey();
            }
        }
        return closestColor;
    }

    public NormeCouleurs getNorme() {
        return norme;
    }

    public Map<String, Integer> getColors() {
        return colors;
    }
}