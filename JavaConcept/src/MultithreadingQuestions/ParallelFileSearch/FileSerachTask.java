package MultithreadingQuestions.ParallelFileSearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.Callable;

public class FileSerachTask implements Callable<String> {
    String filePath;
    String searchTerm;
    boolean isCaseSensitive = true;

    public FileSerachTask(String filePath, String searchTerm, boolean isCaseSensitive) {
        this.filePath = filePath;
        this.filePath = filePath;
        this.searchTerm = searchTerm;
        this.isCaseSensitive = isCaseSensitive;
    }
    @Override
    public String call() throws Exception {
        FileReader fileReader = new FileReader(filePath);
        searchTerm = isCaseSensitive ? searchTerm : searchTerm.toLowerCase();
        boolean isFound = true;
        try(BufferedReader br = new BufferedReader(fileReader)) {
             System.out.println("Started Reading " + filePath + " Reader name is "+ Thread.currentThread().getName() + " Search query is is "+ searchTerm);
          String line;
          while ((line = br.readLine())!=null){
            //  System.out.println(" " + line);
              Thread.sleep(100);
            if(isCaseSensitive && line.contains(searchTerm)){
                System.out.println("Found: " + searchTerm + " in file: " + filePath);
                return filePath;
            }else if(!isCaseSensitive && line.toLowerCase().contains(searchTerm)){
                System.out.println("Found: " + searchTerm + " in file: " + filePath);
                return filePath;
            }

          }
        }catch (Exception e) {

        }finally {
            System.out.println("completed Reading " + filePath + " Reader name is "+ Thread.currentThread().getName() + " Search query is is "+ searchTerm);
            fileReader.close();
        }

        return null;
    }
}
