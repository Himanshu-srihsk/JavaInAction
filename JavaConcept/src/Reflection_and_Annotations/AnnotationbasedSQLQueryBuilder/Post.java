package Reflection_and_Annotations.AnnotationbasedSQLQueryBuilder;

@Table(name = "User_Post_Table")
public class Post {
    @Id
    @Column(name = "post_id")
    private String id;
    @Column(name = "post_content")
    private String content;
    @Column(name = "user_id")
    private String userId;

    public Post(String id, String content, String userId) {
        this.id = id;
        this.content = content;
        this.userId = userId;
    }
}
