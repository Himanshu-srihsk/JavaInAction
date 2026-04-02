package LowLevelDesign.BookMyShow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreController {
    Map<City, List<Threatre>> cityVsTheatre;
    List<Threatre> allTheatre;



    public TheatreController() {
        cityVsTheatre = new HashMap<>();
        allTheatre = new ArrayList<>();
    }

    void addTheatre(Threatre theatre, City city) {

        allTheatre.add(theatre);

        List<Threatre> theatres = cityVsTheatre.getOrDefault(city, new ArrayList<>());
        theatres.add(theatre);
        cityVsTheatre.put(city, theatres);
    }
    Map<Threatre,List<Show>> getAllShow(Movie m,City c){
        List<Threatre> threatres = cityVsTheatre.getOrDefault(c, new ArrayList<>());
        Map<Threatre, List<Show>> theatreVsShows = new HashMap<>();

        for(Threatre t:threatres){
            List<Show> showList = new ArrayList<>();
            for(Show s:t.shows){
                if(s.movie.getMovieId() == m.getMovieId()){
                    showList.add(s);
                }
            }
            if(!showList.isEmpty()){
                theatreVsShows.put(t,showList);
            }

        }
        return theatreVsShows;
    }

}
