package filtre;

public class FiltreMoyenne implements Filtre{
    private double[][] filtre;
    private int taille;

    public FiltreMoyenne(int taille) {
        this.filtre = new double[taille][taille];
        this.taille = taille;

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                this.filtre[i][j] = 1.0 / (taille * taille);
            }
        }
    }

    @Override
    public int[][] appliquerFiltre(int[][] matrice) {
        int hauteur = matrice.length;
        int largeur = matrice[0].length;
        int half = taille / 2;

        int[][] resultat = new int[hauteur][largeur];

        for (int i = half; i < hauteur - half; i++) {
            for (int j = half; j < largeur - half; j++) {
                double sommeR = 0, sommeG = 0, sommeB = 0;

                for (int k = -half; k <= half; k++) {
                    for (int l = -half; l <= half; l++) {
                        int rgb = matrice[i + k][j + l];
                        double filtreValue = this.filtre[k + half][l + half];

                        sommeR += filtreValue * ((rgb >> 16) & 0xFF);
                        sommeG += filtreValue * ((rgb >> 8) & 0xFF);
                        sommeB += filtreValue * (rgb & 0xFF);
                    }
                }

                int moyenneR = (int) Math.round(sommeR);
                int moyenneG = (int) Math.round(sommeG);
                int moyenneB = (int) Math.round(sommeB);

                resultat[i][j] = (moyenneR << 16) | (moyenneG << 8) | moyenneB;
            }
        }

        return resultat;
    }
}
