package Reflection_and_Annotations.AnnotationbasedSQLQueryBuilder;

@Table(name = "User_Profile_Table")
public class Profile {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "user_id")
    String userId;

    public Profile(String id, String username, String email, String userId) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.userId = userId;
    }
}
