package Reflection_and_Annotations.CustomAccessControl;

import java.util.List;

public class DocumentService {
    private static DocumentService instance;

    // Private constructor to prevent instantiation
    private DocumentService() {}

    // Thread-safe singleton initialization
    public static synchronized DocumentService getInstance() {
        if (instance == null) {
            instance = new DocumentService();
        }
        return instance;
    }

    @Role(values = {RoleType.USER, RoleType.ADMIN})
    public void addDocument(Integer documentId) {
        System.out.println("Document " + documentId + " added " + "By "+ Thread.currentThread().getName());
    }
    @Role(values = {RoleType.ADMIN, RoleType.USER,RoleType.GUEST})
    public void searchDocuments(List<String> keywords) {
        System.out.println("Searching for documents containing keyword: " + keywords+ "By "+ Thread.currentThread().getName());
    }
    @Role(values = {RoleType.ADMIN})
    public void deleteDocument(int documentId) {
        System.out.println("Document " + documentId + " deleted "+ "By "+ Thread.currentThread().getName());
    }
    @Role(values = {RoleType.USER, RoleType.ADMIN})
    public void updateDocument(int documentId) {
        System.out.println("Document " + documentId + " updated "+ "By "+ Thread.currentThread().getName());
    }
    public void viewDocument(int documentId) {
        System.out.println("Viewing Document " + documentId+ " By "+ Thread.currentThread().getName());
    }
}
