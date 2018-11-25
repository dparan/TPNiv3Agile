package decorator;


/** commentaires auto-générés par éclipse
 * The Class Decorateur.
 */
public abstract class Decorateur implements Abstraction{
    /** The a. */
    Abstraction a;

    /**
     * Constructeur de la méthode abstraite "Decorateur".
     *
     * @param a the a
     */
    public Decorateur(Abstraction a){
          this.a = a;
    }
}
