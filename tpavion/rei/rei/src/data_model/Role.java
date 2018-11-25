package data_model;

public class Role {

    private String role; 
    private TypeRole type;

    /**
     * Constructeur pour un rôle
     * @param type
     * @param role
     */
    public Role(String type,String role){
        this.role = role;
        this.type = TypeRole.getTypePossible(type);
    }

    /**
     * Getter d'un type de rôle
     * @return TypeRole
     */
    public TypeRole getType() {
        return type;
    }

    /**
     * Setter du type de rôle
     * @param type
     */
    public void setType(String type) {
        this.type = TypeRole.getTypePossible(type);
    }

    /**
     * Getter du rôle
     * @return String
     */
    public String getRole() {
        return role;
    }

    /**
     * Setter du rôle
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

}
