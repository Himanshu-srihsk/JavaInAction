package LowLevelDesign.FileAndDirectorySystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchByFileName implements SearchCriteria {

    @Override
    public SearchResponse search(SearchRequest searchRequest , List<Entry> entries) {
        SearchResponse searchResponse = new SearchResponse(new ArrayList<>());
        if(searchRequest.getPattern()==null)
            return searchResponse;

        search(searchRequest.getPattern(), entries, searchResponse);
        return searchResponse;
    }

    public void search(String fileName, List<Entry> entries, SearchResponse searchResponse) {

        for (Entry e : entries) {
//            if (e instanceof Directory) {
//                for(Entry en : ((Directory) e).getEntries()){
//                    if(en instanceof File){
//                        if(en.getName().startsWith(fileName)){
//                            searchResponse.getResult().add(en);
//                        }
//                    }
//                    else{
//                        search(fileName, Collections.singletonList(en), searchResponse);
//                    }
//                }
//            }
//            else{
//                if(e.getName().equalsIgnoreCase(fileName)){
//                    searchResponse.getResult().add(e);
//                }
//            }

            if (e instanceof Directory) {
                search(fileName, ((Directory) e).getEntries(), searchResponse); // recursive
            } else {
                if (e.getName().startsWith(fileName)) {
                    searchResponse.getResult().add(e);
                }
            }
        }
    }
}
