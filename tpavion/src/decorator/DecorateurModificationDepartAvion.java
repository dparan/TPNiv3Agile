package decorator;


import data_model.DepartAvion;

public class DecorateurModificationDepartAvion extends DecorateurMenuDepart {

    private DepartAvion departAvion = null;

    /**
     * Constructeur d'un DecorateurModificationDepartAvion
     * @param departAvion
     */
    public DecorateurModificationDepartAvion(Abstraction a) {
        super(a);       
    }
    
    public void setDepart(DepartAvion depart){
         this.departAvion = depart;
    }

    /**
     * Méthode permettant l'affichage du menu
     */
    @Override
    public void affichage() {
        a.affichage();
        System.out.println(" 3 --> Modifier l'immatriculation du départ (immatriculation actuelle : "+departAvion.getImmatriculation()+" )");
        System.out.println(" 4 --> Modifier la quantité de carburant du départ (quantité actuelle : "+departAvion.getQteCarburant()+" )");
    }
}