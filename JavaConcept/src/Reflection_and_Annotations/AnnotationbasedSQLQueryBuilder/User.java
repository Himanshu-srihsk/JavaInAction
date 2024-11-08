package Reflection_and_Annotations.AnnotationbasedSQLQueryBuilder;

import java.util.List;

@Table(name = "User_Table")
public class User {
   @Id
   @Column(name = "id")
   private String id;
   @OneToOne(joinColumn = "profile_id", referencedColumn = "id")
   private Profile profile;
   @OneToMany(mappedBy = "user_id")
   private List<Post> posts;
   @ManyToMany(joinTable = "user_roles", joinColumn = "user_id", inverseJoinColumn = "role_id")
   private List<Role> roles;

   public User(String id, Profile profile, List<Post> posts, List<Role> roles) {
      this.id = id;
      this.profile = profile;
      this.posts = posts;
      this.roles = roles;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public Profile getProfile() {
      return profile;
   }

   public void setProfile(Profile profile) {
      this.profile = profile;
   }

   public List<Post> getPosts() {
      return posts;
   }

   public void setPosts(List<Post> posts) {
      this.posts = posts;
   }

   public List<Role> getRoles() {
      return roles;
   }

   public void setRoles(List<Role> roles) {
      this.roles = roles;
   }
}


/*


*/