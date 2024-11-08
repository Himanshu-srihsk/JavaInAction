package Reflection_and_Annotations.AnnotationbasedSQLQueryBuilder;

/*
*
* Annotation-based SQL Query Builder
Define annotations like @Table, @Column, and @Id for a simple ORM-like setup.
Write a query builder that:
Generates a SQL CRUD statement by reading the annotations from a given entity class.
* it can contain @OneToOne,@oneToMany, @ManyToMany Mapping
This exercise demonstrates how annotations can simplify mapping database tables to Java classes.
* It also demonstrates Spring Data JPA
* */
public class AnnotationbasedSQLQueryBuilder {
    public static void main(String[] args) {
        SqlQueryBinderUtil.SelectQueryBuilder(User.class);
//        SqlQueryBinderUtil.InsertQueryBuilder(User.class);
//        SqlQueryBinderUtil.UpdateQueryBuilder(User.class);
//        SqlQueryBinderUtil.DeleteQueryBuilder(User.class);

    }
}

/*
Connected to the target VM, address: '127.0.0.1:56205', transport: 'socket'
SELECT u.id AS u_id, profile.id AS profile_id, profile.username AS profile_username, profile.email AS profile_email,
profile.user_id AS profile_user_id, profile.id AS profile_id, profile.username AS profile_username, profile.email AS profile_email,
 profile.user_id AS profile_user_id, posts.post_id AS posts_post_id, posts.post_content AS posts_post_content, posts.user_id AS posts_user_id,
  posts.post_id AS posts_post_id, posts.post_content AS posts_post_content, posts.user_id AS posts_user_id, role.role_id AS role_role_id,
  role.role_name AS role_role_name, role.role_id AS role_role_id, role.role_name AS role_role_name FROM User_Table u
  LEFT JOIN User_Profile_Table profile ON u.profile_id = profile.id LEFT JOIN User_Post_Table posts ON u.id = posts.user_id
  JOIN user_roles roles ON u.id = roles.user_id JOIN User_Role_Table role ON roles.role_id = role.id;

Disconnected from the target VM, address: '127.0.0.1:56205', transport: 'socket'

MYSQL CLIENT : ---------------------------------------------
mysql> show tables;
+----------------+
| Tables_in_temp |
+----------------+
| role           |
+----------------+
1 row in set (0.00 sec)

mysql> INSERT INTO Role (role_id, role_name) VALUES
    -> (1, 'Admin'),
    -> (2, 'User');
Query OK, 2 rows affected (0.01 sec)
Records: 2  Duplicates: 0  Warnings: 0

mysql> CREATE TABLE User_Table (
    ->     id VARCHAR(255) PRIMARY KEY,
    ->     profile_id VARCHAR(255) NOT NULL,
    ->     FOREIGN KEY (profile_id) REFERENCES User_Profile_Table(id)
    -> );
ERROR 1824 (HY000): Failed to open the referenced table 'user_profile_table'
mysql> CREATE TABLE User_Profile_Table (
    ->     id VARCHAR(255) PRIMARY KEY,
    ->     username VARCHAR(255),
    ->     email VARCHAR(255),
    ->     user_id VARCHAR(255) NOT NULL,
    ->     FOREIGN KEY (user_id) REFERENCES User_Table(id)
    -> );
ERROR 1824 (HY000): Failed to open the referenced table 'user_table'
mysql> CREATE TABLE User_Profile_Table (
    ->     id VARCHAR(255) PRIMARY KEY,
    ->     username VARCHAR(255),
    ->     email VARCHAR(255),
    ->     user_id VARCHAR(255) NOT NULL
    -> );
Query OK, 0 rows affected (0.04 sec)

mysql> CREATE TABLE User_Table (
    ->     id VARCHAR(255) PRIMARY KEY,
    ->     profile_id VARCHAR(255) NOT NULL,
    ->     FOREIGN KEY (profile_id) REFERENCES User_Profile_Table(id)
    -> );
Query OK, 0 rows affected (0.03 sec)

mysql> CREATE TABLE User_Role_Table (
    ->     user_id VARCHAR(255),
    ->     role_id INT,
    ->     PRIMARY KEY (user_id, role_id),
    ->     FOREIGN KEY (user_id) REFERENCES User_Table(id),
    ->     FOREIGN KEY (role_id) REFERENCES Role(role_id)
    -> );
Query OK, 0 rows affected (0.03 sec)

mysql> CREATE TABLE User_Post_Table (
    ->     post_id VARCHAR(255) PRIMARY KEY,
    ->     post_content TEXT,
    ->     user_id VARCHAR(255),
    ->     FOREIGN KEY (user_id) REFERENCES User_Table(id)
    -> );
Query OK, 0 rows affected (0.02 sec)

mysql> -- Insert dummy data into User_Profile_Table
Query OK, 0 rows affected (0.00 sec)

mysql> INSERT INTO User_Profile_Table (id, username, email, user_id) VALUES
    -> ('P1', 'user1', 'user1@example.com', 'U1'),
    -> ('P2', 'user2', 'user2@example.com', 'U2');
Query OK, 2 rows affected (0.01 sec)
Records: 2  Duplicates: 0  Warnings: 0

mysql>
mysql> -- Insert dummy data into User_Table
Query OK, 0 rows affected (0.00 sec)

mysql> INSERT INTO User_Table (id, profile_id) VALUES
    -> ('U1', 'P1'),
    -> ('U2', 'P2');
Query OK, 2 rows affected (0.00 sec)
Records: 2  Duplicates: 0  Warnings: 0

mysql>
mysql> -- Insert dummy data into User_Role_Table (Many-to-Many mapping between User and Role)
Query OK, 0 rows affected (0.00 sec)

mysql> INSERT INTO User_Role_Table (user_id, role_id) VALUES
    -> ('U1', 1),
    -> ('U1', 2),
    -> ('U2', 2);
Query OK, 3 rows affected (0.00 sec)
Records: 3  Duplicates: 0  Warnings: 0

mysql>
mysql> -- Insert dummy data into User_Post_Table
Query OK, 0 rows affected (0.00 sec)

mysql> INSERT INTO User_Post_Table (post_id, post_content, user_id) VALUES
    -> ('P1', 'Post content 1', 'U1'),
    -> ('P2', 'Post content 2', 'U1'),
    -> ('P3', 'Post content 3', 'U2');
Query OK, 3 rows affected (0.01 sec)
Records: 3  Duplicates: 0  Warnings: 0

mysql> show tables;
+--------------------+
| Tables_in_temp     |
+--------------------+
| role               |
| user_post_table    |
| user_profile_table |
| user_role_table    |
| user_table         |
+--------------------+
5 rows in set (0.00 sec)

mysql> select * from tole;
ERROR 1146 (42S02): Table 'temp.tole' doesn't exist
mysql> select * from role;
+---------+-----------+
| role_id | role_name |
+---------+-----------+
|       1 | Admin     |
|       2 | User      |
+---------+-----------+
2 rows in set (0.00 sec)

mysql> select * from usr_post_table;
ERROR 1146 (42S02): Table 'temp.usr_post_table' doesn't exist
mysql> select * from user_post_table;
+---------+----------------+---------+
| post_id | post_content   | user_id |
+---------+----------------+---------+
| P1      | Post content 1 | U1      |
| P2      | Post content 2 | U1      |
| P3      | Post content 3 | U2      |
+---------+----------------+---------+
3 rows in set (0.00 sec)

mysql> select * from user_profile_table;
+----+----------+-------------------+---------+
| id | username | email             | user_id |
+----+----------+-------------------+---------+
| P1 | user1    | user1@example.com | U1      |
| P2 | user2    | user2@example.com | U2      |
+----+----------+-------------------+---------+
2 rows in set (0.00 sec)

mysql> select * from user_role_table;
+---------+---------+
| user_id | role_id |
+---------+---------+
| U1      |       1 |
| U1      |       2 |
| U2      |       2 |
+---------+---------+
3 rows in set (0.00 sec)

mysql> select * from user_table;
+----+------------+
| id | profile_id |
+----+------------+
| U1 | P1         |
| U2 | P2         |
+----+------------+
2 rows in set (0.00 sec)

mysql> SELECT u.id AS u_id, p.id AS profile_id, p.username AS profile_username, p.email AS profile_email,
    ->        p.user_id AS profile_user_id, po.post_id AS posts_post_id, po.post_content AS posts_post_content,
    ->        po.user_id AS posts_user_id, r.role_id AS role_role_id, r.role_name AS role_role_name
    -> FROM User_Table u
    -> LEFT JOIN User_Profile_Table p ON u.profile_id = p.id
    -> LEFT JOIN User_Post_Table po ON u.id = po.user_id
    -> JOIN User_Role_Table ur ON u.id = ur.user_id
    -> JOIN Role r ON ur.role_id = r.role_id;
+------+------------+------------------+-------------------+-----------------+---------------+--------------------+---------------+--------------+----------------+
| u_id | profile_id | profile_username | profile_email     | profile_user_id | posts_post_id | posts_post_content | posts_user_id | role_role_id | role_role_name |
+------+------------+------------------+-------------------+-----------------+---------------+--------------------+---------------+--------------+----------------+
| U1   | P1         | user1            | user1@example.com | U1              | P1            | Post content 1     | U1            |            1 | Admin          |
| U1   | P1         | user1            | user1@example.com | U1              | P2            | Post content 2     | U1            |            1 | Admin          |
| U2   | P2         | user2            | user2@example.com | U2              | P3            | Post content 3     | U2            |            2 | User           |
| U1   | P1         | user1            | user1@example.com | U1              | P1            | Post content 1     | U1            |            2 | User           |
| U1   | P1         | user1            | user1@example.com | U1              | P2            | Post content 2     | U1            |            2 | User           |
+------+------------+------------------+-------------------+-----------------+---------------+--------------------+---------------+--------------+----------------+
5 rows in set (0.00 sec)

mysql>




 */