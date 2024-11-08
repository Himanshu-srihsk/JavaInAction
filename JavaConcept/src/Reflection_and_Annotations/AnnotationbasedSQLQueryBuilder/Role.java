package Reflection_and_Annotations.AnnotationbasedSQLQueryBuilder;

@Table(name = "User_Role_Table")
public class Role {
    @Id
    @Column(name = "role_id")
    private int id;
    @Column(name = "role_name")
    private String name;

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
