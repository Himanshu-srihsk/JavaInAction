package MultithreadingQuestions.ThreadSafeSingletonPattern;



import java.io.Serializable;
import java.lang.annotation.Annotation;

public class BillplughSingleton implements Serializable{
    private static final long serialVersionUID = 1L;
    private User user;
    private BillplughSingleton() {
    }


    private static class SingletonHelper{
        private static BillplughSingleton instance = new BillplughSingleton();
    }
    public static BillplughSingleton getInstance() {
        return SingletonHelper.instance;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
    protected  Object readResolve(){
        return getInstance();
    }

}

/*
private static final long serialVersionUID = 1L;
This acts as a Version Stamp for the class.
when we Serialize an object (convert it to bytes to save to a file or send over a network),
Java attaches this ID to the data. and when Deserialize it later, Java compares the ID in the data with the ID in current class.

What happens if we don't provide it?
The JVM generates one automatically based on  class's fields, methods, etc. If we change anything in your class (like adding a new variable), the generated ID changes.
The Problem: If we saved a Singleton object to a file, changed any code, and then tried to read that file back, the JVM would throw an InvalidClassException because the IDs don't match.
The Singleton Context: By hardcoding 1L, we are telling Java: "Even if I change some code later, consider this the same version of the class so I can still read my old data.
 */