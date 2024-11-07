package Reflection_and_Annotations.CustomAccessControl;

import java.util.Arrays;

/*
* Define an annotation @Role that takes a String parameter (e.g., "ADMIN", "USER").
Annotate methods in a class with different roles.
Write a method that:
Checks if a user’s role matches the required role for the method and grants or denies access accordingly.
Simulate different role-based access by changing the user’s role and invoking methods dynamically.
* */
public class CustomAccessControl {
    public static void main(String[] args) {
       DocumentService documentService = DocumentService.getInstance();

       Thread t1 = new Thread(()->{
           try {
              UserContext.setUserRole(RoleType.USER);
               RoleBasedAccessControl.invoke(documentService,"addDocument",1);
              RoleBasedAccessControl.invoke(documentService,"viewDocument",1);
              RoleBasedAccessControl.invoke(documentService,"deleteDocument",1);
               RoleBasedAccessControl.invoke(documentService,"searchDocuments", Arrays.asList("Document1", "Document2", "Document3"));
               RoleBasedAccessControl.invoke(documentService,"updateDocument",1);
           }finally {
               UserContext.clearUserRole();
           }
       });

        Thread t2 = new Thread(()->{
            try {
                UserContext.setUserRole(RoleType.ADMIN);
                RoleBasedAccessControl.invoke(documentService,"addDocument",2);
                RoleBasedAccessControl.invoke(documentService,"deleteDocument",2);
                RoleBasedAccessControl.invoke(documentService,"searchDocuments", Arrays.asList("Document1", "Document2", "Document3"));
                RoleBasedAccessControl.invoke(documentService,"viewDocument",2);
                RoleBasedAccessControl.invoke(documentService,"updateDocument",2);
            }finally {
                UserContext.clearUserRole();
            }
        });


        Thread t3 = new Thread(()->{
            try {
                UserContext.setUserRole(RoleType.GUEST);
                RoleBasedAccessControl.invoke(documentService,"addDocument",3);
                RoleBasedAccessControl.invoke(documentService,"deleteDocument",3);
                RoleBasedAccessControl.invoke(documentService,"searchDocuments", Arrays.asList("Document1", "Document2", "Document3"));
                RoleBasedAccessControl.invoke(documentService,"viewDocument",3);
                RoleBasedAccessControl.invoke(documentService,"updateDocument",3);
            }finally {
                UserContext.clearUserRole();
            }
        });

        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
* Document 2 added By Thread-1
Access denied. Customer role: GUEST is not allowed to invoke addDocument
Document 1 added By Thread-0
Access denied. Customer role: GUEST is not allowed to invoke deleteDocument
Searching for documents containing keyword: [Document1, Document2, Document3]By Thread-2
Document 2 deleted By Thread-1
Viewing Document 3 By Thread-2
Viewing Document 1 By Thread-0
Searching for documents containing keyword: [Document1, Document2, Document3]By Thread-1
Access denied. Customer role: USER is not allowed to invoke deleteDocument
Viewing Document 2 By Thread-1
Searching for documents containing keyword: [Document1, Document2, Document3]By Thread-0
Access denied. Customer role: GUEST is not allowed to invoke updateDocument
Document 1 updated By Thread-0
Document 2 updated By Thread-1*/