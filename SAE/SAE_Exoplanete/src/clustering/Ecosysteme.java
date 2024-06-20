package clustering;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Ecosysteme implements Clustering {

    // Epsilon : la distance maximale pour considérer un point comme voisin
    private double eps;

    // Le nombre de points minimum pour former un cluster
    private int minPts;

    public Ecosysteme(double eps, int minPts) {
        this.eps = eps;
        this.minPts = minPts;
    }

    /**
     * Méthode qui implémente DBScan
     * @param objets tableau des pixels à clusteriser
     * @return tableau des identifiants de clusters pour chaque pixels
     */
    @Override
    public int[] clusteriser(double[][] objets) {
//        System.out.println("Début de DBScan");
        // Tableau pour stocker les identifiants de cluster de chaque pixels
        int[] groupes = new int[objets.length];
        // Initialisation de l'ID de cluster
        int clusterId = 0;
//        System.out.println("Nombre de pixels: " + objets.length);

        // Parcours de tous les pixels
        for (int i = 0; i < objets.length; i++) {
            // Si le pixel est déjà assigné à un cluster, on passe au suivant
            if (groupes[i] != 0) {
                continue;
            }
            // Trouver les voisins du point actuel
            Set<Integer> voisins = regionQuery(objets, i);
            // Si le nombre de voisins est suffisant pour former un cluster
            if (voisins.size() >= minPts) {
//                System.out.println("Expension du cluster " + i);
                // Incrémentation de l'ID de cluster et expansion du cluster
                clusterId++;
                expandCluster(objets, groupes, i, voisins, clusterId);
            } else {
                // Si le point ne peut pas former un cluster, il est marqué comme noise point
                groupes[i] = -1;
            }
        }
        System.out.println("Fin DBScan.");
        return groupes;
    }

    /**
     * Expansion d'un cluster
     * @param objets tableau de pixels
     * @param groupes tableau des identifiants de clusters
     * @param pointIdx index du point actuel
     * @param voisins ensemble des voisins du point actuel
     * @param clusterId identifiant du cluster
     */
    private void expandCluster(double[][] objets, int[] groupes, int pointIdx, Set<Integer> voisins, int clusterId) {
        // Assigner le point actuel au cluster
        groupes[pointIdx] = clusterId;
        // Liste des voisins à explorer
        Set<Integer> ListeVoisins = new HashSet<>(voisins);
        // Ensemble des pixels visités
        Set<Integer> visite = new HashSet<>();

        // Tant qu'il y a des voisins à explorer
        while (!ListeVoisins.isEmpty()) {
            // Itérateur pour parcourir les voisins
            Iterator<Integer> iterator = ListeVoisins.iterator();
            // Ensemble pour les nouveaux voisins trouvés
            Set<Integer> nouveauxVoisins = new HashSet<>();

            // Parcours des voisins
            while (iterator.hasNext()) {
                int idVoisin = iterator.next();
                // Si le voisin n'est pas encore assigné à un cluster
                if (!visite.contains(idVoisin)) {
                    // Ajouter le voisin aux visités
                    visite.add(idVoisin);
                    // Si le voisin n'est pas encore assigné à un cluster
                    if (groupes[idVoisin] == 0) {
                        // Assigner le voisin au cluster actuel
                        groupes[idVoisin] = clusterId;
//                        System.out.println("Calculs des voisins pour "+idVoisin);
                        // Trouver les voisins du voisin actuel
                        Set<Integer> nouveauVoisin = regionQuery(objets, idVoisin);
                        // Si le nombre de nouveaux voisins est suffisant, les ajouter à la liste
                        if (nouveauVoisin.size() >= minPts) {
                            nouveauxVoisins.addAll(nouveauVoisin);
                        }
                    }
                }
                // On retire le voisin actuel de la liste
                iterator.remove();
            }
            // On ajoute les nouveaux voisins trouvés à la liste des voisins à explorer
            ListeVoisins.addAll(nouveauxVoisins);
        }
    }

    /**
     * Méthode pour trouver les voisins d'un pixel donné
     * @param objets tableau de pixels
     * @param idx index du pixel pour lequel on cherche les voisins
     * @return ensemble des indices des voisins
     */
    private Set<Integer> regionQuery(double[][] objets, int idx) {
        Set<Integer> voisins = new HashSet<>();
        // Parcours de tous les points pour trouver les voisins
        for (int i = 0; i < objets.length; i++) {
            // Si la distance entre le point actuel et le point idx est inférieure ou égale à epsilon
            if (distance(objets[idx], objets[i]) <= eps) {
                // Alors on l'ajoute à l'ensemble des voisins
                voisins.add(i);
            }
        }
        return voisins;
    }

    /**
     * Méthode pour calculer la distance euclidienne entre deux pixels
     * @param point1 premier pixel
     * @param point2 deuxième pixel
     * @return distance euclidienne entre les deux pixels
     */
    private double distance(double[] point1, double[] point2) {
        double sum = 0;
        // Somme des carrés des différences des coordonnées
        for (int i = 0; i < point1.length; i++) {
            sum += Math.pow(point1[i] - point2[i], 2);
        }
        // Retour de la racine carrée de la somme des carrés
        return Math.sqrt(sum);
    }
}
