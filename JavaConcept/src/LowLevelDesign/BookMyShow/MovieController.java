package LowLevelDesign.BookMyShow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieController{
    Map<City, List<Movie>> cityVsMovies;
    List<Movie> allMovies;
    public MovieController(){
        cityVsMovies = new HashMap<>();
        allMovies = new ArrayList<>();
    }
    void addMovie(Movie movie,City c){
        allMovies.add(movie);
        List<Movie> movies = cityVsMovies.getOrDefault(c, new ArrayList<>());
        movies.add(movie);
        cityVsMovies.put(c,movies);
    }
    Movie getMovieByName(String name){
        for(Movie m:allMovies){
            if(m.getMovieName().equals(name)){
                return m;
            }
        }
        return null;
    }

    List<Movie> getMoviesByCity(City city) {
        return cityVsMovies.get(city);
    }

}
