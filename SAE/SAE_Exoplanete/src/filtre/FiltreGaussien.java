package filtre;

public class FiltreGaussien implements Filtre {
    // Matrice du filtre moyenne
    private double[][] filtre;

    // taille de la matrice
    private int taille;

    /**
     * Constructeur qui construit la matrice selon la taille passée en paramètre et le sigma
     * @param taille de la matrice
     * @param sigma ecart-type de la distribution gaussienne. Un grand sigma donne un flou beaucoup plus prononcé et inversement. Plus de 2.0 est un grand sigma.
     */
    public FiltreGaussien(int taille, double sigma) {
        // On initialise les attributs
        this.taille = taille;
        this.filtre = new double[taille][taille];

        // On initialise la somme et le centre
        double sum = 0.0;
        int centre = taille / 2;

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                // On récupère la distance au centre
                int x = i - centre;
                int y = j - centre;
                // On applique la formule de Gauss
                this.filtre[i][j] = Math.exp(-(x * x + y * y) / (2 * sigma * sigma)) / (2 * Math.PI * sigma * sigma);
                // On additionne
                sum += this.filtre[i][j];
            }
        }

        // Normalisation : pour que la somme de toutes les valeurs du filtre est égale à 1
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                this.filtre[i][j] /= sum;
            }
        }
    }

    /**
     *
     * @param matrice Contient les valeurs rgb de chaque pixels de l'image qu'on souhaite flouter
     * @return les valeurs avec le flou appliqué
     */
    @Override
    public int[][] appliquerFiltre(int[][] matrice) {
        // On initialise la taille et on prend la moitié de la taille de la matrice du filtre
        int hauteur = matrice.length;
        int largeur = matrice[0].length;
        int moitie = taille / 2;

        // On initialise la matrice de résultat
        int[][] resultat = new int[hauteur][largeur];

        // On commence à (i = moitié) et (j = moitié) car on évite les bords
        // De même pour le fait qu'on finit par (hauteur - moitié) et (largeur - moitié)
        for (int i = moitie; i < hauteur - moitie; i++) {
            for (int j = moitie; j < largeur - moitie; j++) {
                // On initialise les sommes pour le Rouge, le Vert et le Bleu
                double sommeR = 0, sommeG = 0, sommeB = 0;
                // On applique le filtre sur chaque pixel (sauf les bords)
                for (int k = -moitie; k <= moitie; k++) {
                    for (int l = -moitie; l <= moitie; l++) {
                        // On récupère la valeur rgb du pixel de l'image
                        int rgb = matrice[i + k][j + l];
                        double filtreValue = this.filtre[k + moitie][l + moitie];

                        // On fait la somme des produits des coefficients
                        sommeR += filtreValue * ((rgb >> 16) & 0xFF);
                        sommeG += filtreValue * ((rgb >> 8) & 0xFF);
                        sommeB += filtreValue * (rgb & 0xFF);
                    }
                }

                // On arrondit les moyennes
                int moyenneR = (int) Math.round(sommeR);
                int moyenneG = (int) Math.round(sommeG);
                int moyenneB = (int) Math.round(sommeB);

                // On affecte le résultat pour le rouge, vert et bleu
                resultat[i][j] = (moyenneR << 16) | (moyenneG << 8) | moyenneB;
            }
        }

        // On retourne les valeurs de l'image flouté
        return resultat;
    }

}
