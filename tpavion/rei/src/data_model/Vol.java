package data_model;

public class Vol {

    private int id;
    private int frequence;
    private String uniteFrequence;

    /**
     * Constructeur pour un vol
     * @param id
     * @param frequence
     */
    public Vol(int id,int frequence, String uniteFrequence){
        this.id = id;
        this.frequence = frequence;
        this.uniteFrequence = uniteFrequence;
    }

    /**
     * Constructeur pour un vol
     * @param frequence
     */
    public Vol(int frequence, String uniteFrequence){
        this.frequence = frequence;
        this.uniteFrequence = uniteFrequence;
    }

    /**
     * Getter de l'id
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Setter de l'id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter de la frequence
     * @return int
     */
    public int getFrequence() {
        return frequence;
    }

    /**
     * Setter de la frequence
     * @param frequence
     */
    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }
    
    /**
    * Getter de la frequence
    * @return String
    */
    public String getUniteFrequence(){
        return uniteFrequence;
    }

/**
 * Setter de la frequence
 * @param uniteFrequence
 */
    public void setUniteFrequence(String uniteFrequence) {
        this.uniteFrequence = uniteFrequence;
    }
    
        @Override
    public String toString() {
        return "Vol [id=" + id + ", frequence=" + frequence + ", unite=" + uniteFrequence+" ]";
    }
}
