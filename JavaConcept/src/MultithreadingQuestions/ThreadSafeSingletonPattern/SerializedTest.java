package MultithreadingQuestions.ThreadSafeSingletonPattern;

import java.io.*;

public class SerializedTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BillplughSingleton instance1 = BillplughSingleton.getInstance();

        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("filename.txt"));
        out.writeObject(instance1);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("filename.txt"));
        BillplughSingleton instance2 = (BillplughSingleton) in.readObject();
        in.close();
        System.out.println(" with BillplughSingleton instance1 and instance2 are the same: " + (instance1 == instance2));
        System.out.println(" with BillplughSingleton Instance1 hashcode " + instance1.hashCode());
        System.out.println(" with BillplughSingleton  Instance2 hashcode " + instance2.hashCode());

        DoubleCheckLocking instance3 = DoubleCheckLocking.getInstance();
        out = new ObjectOutputStream(new FileOutputStream("filename.txt"));
        out.writeObject(instance3);
        out.close();

        in = new ObjectInputStream(new FileInputStream("filename.txt"));
        DoubleCheckLocking instance4 = (DoubleCheckLocking) in.readObject();
        in.close();
       System.out.println(" with DoubleCheckLocking instance3 and instance4 are the same: " + (instance3 == instance4));
        System.out.println(" with DoubleCheckLocking Instance3 hashcode " + instance3.hashCode());
        System.out.println(" with DoubleCheckLocking  Instance4 hashcode " + instance4.hashCode());

    }
}

/*
 with BillplughSingleton instance1 and instance2 are the same: true
 with BillplughSingleton Instance1 hashcode 1856056345
 with BillplughSingleton  Instance2 hashcode 1856056345
 with DoubleCheckLocking instance3 and instance4 are the same: false
 with DoubleCheckLocking Instance3 hashcode 329645619
 with DoubleCheckLocking  Instance4 hashcode 3213500
* */