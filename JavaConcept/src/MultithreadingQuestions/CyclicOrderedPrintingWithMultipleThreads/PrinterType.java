package MultithreadingQuestions.CyclicOrderedPrintingWithMultipleThreads;

public enum PrinterType {
    ONE,
    TWO,
    THREE;
    PrinterType type;
    PrinterType() {
        this.type = this;
    }
    PrinterType getPrinterType(){
        return type;
    }
}
