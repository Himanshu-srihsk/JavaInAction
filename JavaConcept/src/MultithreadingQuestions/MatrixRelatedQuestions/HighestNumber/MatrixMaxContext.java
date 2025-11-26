package MultithreadingQuestions.MatrixRelatedQuestions.HighestNumber;

public class MatrixMaxContext {
    MatrixMaxStrategy matrixMaxStrategy;

    public MatrixMaxContext(MatrixMaxStrategy matrixMaxStrategy) {
        this.matrixMaxStrategy = matrixMaxStrategy;
    }
    public int executeStrategy(int[][] matrix,int numThreads) throws Exception {
        if (matrixMaxStrategy==null){
            throw new IllegalStateException("Strategy not set");
        }
        return matrixMaxStrategy.findMax(matrix,numThreads);
    }
}
