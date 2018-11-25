package data_model;

public enum TypeRole {
    NAVIGANT ("Navigant"),
    NONNAVIGANT ("NonNavigant");

    private String type="";
    private TypeRole(String type){
        this.type = type;
      }

    /**
     * Getter du type
     * @return String
     */
    public String getType(){
        return type;
    }

    /**
     * Méthode statique permettant de créer un type de rôle
     * @param typePersonnel
     * @return
     */
    public static TypeRole getTypePossible(String typePersonnel) {
        switch(typePersonnel) {
            case "Navigant":
                return TypeRole.NAVIGANT;
            case "NonNavigant":
                return TypeRole.NONNAVIGANT;
            default:
                return null;
        }
    }
}
