package LowLevelDesign.FileAndDirectorySystem;

import java.util.Collections;
import java.util.List;

/*
Design and implement a file search system that can search files within a hierarchical directory structure using multiple filters.
The system should support files and directories, where directories can contain files and other directories,
forming a tree structure similar to a real file system.
 */
public class Main {
    public static void main(String[] args) {
        List<Entry> entries = buildFileSystem();
        SearchRequest searchRequest = new SearchRequest(31d,"F", "xlsx");

        SearchCriteria applySearchFilter = new ApplySearchFilter();
        SearchResponse searchResponse = applySearchFilter.search(searchRequest, entries);
        System.out.println(searchResponse);
    }
    private static List<Entry> buildFileSystem() {
        File F1 = new File("F1", 10d, "csv");
        File F2 = new File("F2", 20d, "ppt");
        File F3 = new File("F3", 30d, "txt");
        File F4 = new File("F4", 40d, "txt");
        File F5 = new File("F5", 50d, "xlsx");
        File F7 = new File("F6", 50d, "xlsx");
        File F6 = new File("D6", 50d, "xlsx");

        Directory d2 = new Directory();
        d2.add(F4);
        d2.add(F5);

        d2.add(F6);
        d2.add(F7);


        Directory d1 = new Directory();
        d1.add(F1);
        d1.add(F2);
        d1.add(F3);
        d1.add(d2);

        return Collections.singletonList(d1);
        // return Arrays.asList(d1);
    }
}
/*
input:
d1 -> F1 , F2 , F3, d2
d2 -> F4, F5, F6, F7
Search in d1 directory
SearchRequest(31d,"F", "xlsx");
i.e. all the files whose size >=31d , startwith F, and extension end with .xlsx
 */


/*
SearchResponse{result=[Entry{name='F5'}, Entry{name='F6'}]}
 */