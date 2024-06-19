package normecouleurs;

public class OutilCouleur {

    /**
     * Méthode qui retourne un tableau d'entier comportant les trois composantes d'une couleur :
     * Rouge, vert, et bleu.
     * Prend en entrée une couleur sous forme d'octets (précisément 3 octets), et la convertit donc en tableau d'int
     * chaque entier étant compris entre 0 et 255.
     * @param c
     * @return
     */
    public static int[] getTabColor(int c){
        int bleu = c & 0xFF;
        int vert = (c & 0xFF00) >> 8;
        int rouge = (c & 0xFF0000) >> 16;

        return new int[]{rouge, vert, bleu};
    }
}
