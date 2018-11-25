package data_model;

public class TypeAvion {

    private String type;

    /**
     * Constructeur pour unTypeAvion
     * @param type
     */
    public TypeAvion(String type){
        this.type = type;
    }

    /**
     * Getter du type
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Setter du type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
}
