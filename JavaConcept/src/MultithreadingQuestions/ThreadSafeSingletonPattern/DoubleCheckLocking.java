package MultithreadingQuestions.ThreadSafeSingletonPattern;


import java.io.Serializable;

public class DoubleCheckLocking implements Serializable {
    private static final long serialVersionUID = 1L;
    private static volatile DoubleCheckLocking instance;

    private DoubleCheckLocking() {
    }

    public static DoubleCheckLocking getInstance() {
        if(instance == null){
            synchronized (DoubleCheckLocking.class){
                if(instance == null){
                    instance = new DoubleCheckLocking();
                }
            }
        }
        return instance;
    }
}
