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
