public class FiltreMoyenne implements Filtre{
    private double[][] filtre;
    private int taille;
    public FiltreMoyenne(int taille) {
        this.filtre=new double[taille][taille];
        this.taille=taille;

        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                this.filtre[i][j] = 1.0 / (taille * taille);
            }
        }
    }

    @Override
    public int appliquerFiltre(int[][] matrice) {
        // On vérifie que les dimensions de la matrice sont correcte
        if (matrice.length != this.taille || matrice[0].length != this.taille) {
            return -1;
        }

        double sommeR = 0, sommeG = 0, sommeB = 0;
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                int rgb = matrice[i][j];
                double filtreValue = this.filtre[i][j];

                sommeR += filtreValue * ((rgb >> 16) & 0xFF);
                sommeG += filtreValue * ((rgb >> 8) & 0xFF);
                sommeB += filtreValue * (rgb & 0xFF);
            }
        }

        // Arrondissement des moyennes
        int moyenneR = (int) Math.round(sommeR);
        int moyenneG = (int) Math.round(sommeG);
        int moyenneB = (int) Math.round(sommeB);

        // Recomposition de la valeur RGB
        return (moyenneR << 16) | (moyenneG << 8) | moyenneB;
    }
}
