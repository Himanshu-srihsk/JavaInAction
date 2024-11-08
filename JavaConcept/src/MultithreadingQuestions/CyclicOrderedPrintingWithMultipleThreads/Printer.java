package MultithreadingQuestions.CyclicOrderedPrintingWithMultipleThreads;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Printer implements Runnable{
    private final int step;
    private  int start;
    private final int end;
    private final PrinterType currentPrinter;
    private final PrinterType nextPrinter;
    private  AtomicReference<PrinterType> sharedPrinterType;
    private final Object lock;

    public Printer(int step, int start, int end, PrinterType currentPrinter, PrinterType nextPrinter, AtomicReference<PrinterType> printerType, Object lock) {
        this.step = step;
        this.start = start;
        this.end = end;
        this.currentPrinter = currentPrinter;
        this.nextPrinter = nextPrinter;
        this.sharedPrinterType = printerType;
        this.lock = lock;
    }
    public void run() {
        while(this.start <= this.end){
            synchronized (lock){
                //System.out.println(nextPrinter.getPrinterType() + " to printing: " + " currentPrinter = " + currentPrinter.getPrinterType());
                while(this.currentPrinter.getPrinterType() != this.sharedPrinterType.get()){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
                System.out.println(currentPrinter.getPrinterType() + " printing: " + this.start);
                //System.out.println(nextPrinter + " to printing: ");
                this.sharedPrinterType.set(this.nextPrinter.getPrinterType());
                start += step;
                lock.notifyAll();
            }

        }
    }
}
