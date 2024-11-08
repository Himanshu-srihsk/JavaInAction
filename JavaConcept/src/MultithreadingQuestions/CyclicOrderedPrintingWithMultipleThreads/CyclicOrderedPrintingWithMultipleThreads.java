package MultithreadingQuestions.CyclicOrderedPrintingWithMultipleThreads;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/*
*
* Implement Cyclic Ordered Printing with Multiple Threads IPC
* */
public class CyclicOrderedPrintingWithMultipleThreads {
    public static void main(String[] args) {
        int numThreads = 3;
        int stepIncrement = numThreads; // increment each thread by stepIncrement given start point for each thread
        int end = 100;
        Thread[] threads = new Thread[numThreads];
        Printer[] printers = new Printer[numThreads];
        AtomicReference<PrinterType> printerTypeRef = new AtomicReference<>(PrinterType.ONE);
        // Shared lock object for synchronization
        Object lock = new Object();

        for(int i=0; i<numThreads; i++){
            int now =i;
            int next = (now+1)%numThreads;
            threads[i] = new Thread(new Printer(stepIncrement, i+1, end, PrinterType.values()[now], PrinterType.values()[next], printerTypeRef,lock));
            threads[i].start();
        }
        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("All threads have finished executing");
    }
}
/*ONE printing: 1
TWO printing: 2
THREE printing: 3
ONE printing: 4
TWO printing: 5
THREE printing: 6
ONE printing: 7
TWO printing: 8
THREE printing: 9
ONE printing: 10
TWO printing: 11
THREE printing: 12
ONE printing: 13
TWO printing: 14
THREE printing: 15
ONE printing: 16
TWO printing: 17
THREE printing: 18
ONE printing: 19
TWO printing: 20
THREE printing: 21
ONE printing: 22
TWO printing: 23
THREE printing: 24
ONE printing: 25
TWO printing: 26
THREE printing: 27
ONE printing: 28
TWO printing: 29
THREE printing: 30
ONE printing: 31
TWO printing: 32
THREE printing: 33
ONE printing: 34
TWO printing: 35
THREE printing: 36
ONE printing: 37
TWO printing: 38
THREE printing: 39
ONE printing: 40
TWO printing: 41
THREE printing: 42
ONE printing: 43
TWO printing: 44
THREE printing: 45
ONE printing: 46
TWO printing: 47
THREE printing: 48
ONE printing: 49
TWO printing: 50
THREE printing: 51
ONE printing: 52
TWO printing: 53
THREE printing: 54
ONE printing: 55
TWO printing: 56
THREE printing: 57
ONE printing: 58
TWO printing: 59
THREE printing: 60
ONE printing: 61
TWO printing: 62
THREE printing: 63
ONE printing: 64
TWO printing: 65
THREE printing: 66
ONE printing: 67
TWO printing: 68
THREE printing: 69
ONE printing: 70
TWO printing: 71
THREE printing: 72
ONE printing: 73
TWO printing: 74
THREE printing: 75
ONE printing: 76
TWO printing: 77
THREE printing: 78
ONE printing: 79
TWO printing: 80
THREE printing: 81
ONE printing: 82
TWO printing: 83
THREE printing: 84
ONE printing: 85
TWO printing: 86
THREE printing: 87
ONE printing: 88
TWO printing: 89
THREE printing: 90
ONE printing: 91
TWO printing: 92
THREE printing: 93
ONE printing: 94
TWO printing: 95
THREE printing: 96
ONE printing: 97
TWO printing: 98
THREE printing: 99
ONE printing: 100*/