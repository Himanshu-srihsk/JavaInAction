package Stream;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class DataObj{
    int index;
    int val;
    int count;
    DataObj(int index,int val,int count){
        this.index = index;
        this.val = val;
        this.count = count;
    }
    public void setCount(int count){
        this.count = count;
    }
}
class Employee{
    int id,age;
    String name;
    Employee(int id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString(){
        return "ID: "+id+", Name: "+name+", Age: "+age;
    }
}


 class Flight {
    private String flightNumber;
    private String source;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public Flight(String flightNumber, String source, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(flightNumber, flight.flightNumber) &&
                Objects.equals(source, flight.source) &&
                Objects.equals(destination, flight.destination) &&
                Objects.equals(departureTime, flight.departureTime) &&
                Objects.equals(arrivalTime, flight.arrivalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, source, destination, departureTime, arrivalTime);
    }
}

 class Seat {
    private int seatNumber;
    private double price;
    private SeatStatus status;

    // Constructor
    public Seat(int seatNumber, double price, SeatStatus status) {
        this.seatNumber = seatNumber;
        this.price = price;
        this.status = status;
    }

    // Getters and Setters
    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    // Override toString for better debugging
    @Override
    public String toString() {
        return "Seat{" +
                "seatNumber=" + seatNumber +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}

 enum SeatStatus {
    AVAILABLE,
    BOOKED,
    RESERVED;
}


 class Concert {
    private String concertName;
    private List<Seat> seats;
    private List<Integer> ratings;

    // Constructor
    public Concert(String concertName, List<Seat> seats) {
        this.concertName = concertName;
        this.seats = seats;
        this.ratings = new ArrayList<>();
    }

    // Add a rating
    public void addRating(int rating) {
        if (rating >= 1 && rating <= 5) { // Assuming ratings are between 1 and 5
            ratings.add(rating);
        } else {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
    }

    // Get average rating
    public double getAverageRating() {
        return ratings.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    // Getters and Setters
    public String getConcertName() {
        return concertName;
    }

    public void setConcertName(String concertName) {
        this.concertName = concertName;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    // ToString for debugging
    @Override
    public String toString() {
        return "Concert{" +
                "concertName='" + concertName + '\'' +
                ", seats=" + seats +
                ", ratings=" + ratings +
                '}';
    }
}




public class JavaStream {
    public static void main(String[] args) {
        int[] nums = {1,3,2,0,1,0,4,0,5,0,4,0,5,-1,-3,4,5,0,4,5};
        //sort by stream
        System.out.println("Stream Sorted array is"+ Arrays.toString(Arrays.stream(nums.clone()).sorted().toArray()));

        String str = "Hello World";

        System.out.println("Duplicate Characters in String is: ");

       Map<Character, Long> charVsCountMap = str.chars().mapToObj(e->(char)e).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

        List<Character> duplicates =  charVsCountMap.entrySet().stream().filter(e -> e.getValue() > 1).map(e -> e.getKey()).collect(Collectors.toList());
        System.out.println("Duplicate keys in str :" + str +" is "+ duplicates);

        int[] arr= {23,24,12,33,45};
        System.out.println("2nd Largest keys in arr :" + Arrays.toString(arr) +" is "+ Arrays.stream(arr).boxed().sorted((a,b) -> b-a).skip(1).limit(1).findFirst().get());

        List<String> words = Arrays.asList("CARS", "REPAID", "DUES", "NOSE", "SIGNED",
                "LANE", "PAIRED", "ARCS", "GRAB", "USED", "ONES", "BRAG",
                "SUED", "LEAN", "SCAR", "DESIGN");

        List<String> sortedWords = words.stream().sorted().collect(Collectors.toUnmodifiableList());
        System.out.println("Sorted Words is"+ sortedWords);

        List<String> sortedWordsandChars = words.stream().map(s -> Stream.of(s.split("")).sorted().collect(Collectors.joining())).collect(Collectors.toUnmodifiableList());
        System.out.println("Sorted Words and by chars is"+ sortedWordsandChars);

        List<String> sortedWordsandChars2 = words.stream().map(s -> Stream.of(s.toCharArray()).map(e-> {
            Arrays.sort(e);
            return new String(e);
        }).collect(Collectors.joining())).collect(Collectors.toUnmodifiableList());
        System.out.println("Sorted Words and by chars is"+ sortedWordsandChars2);

        List<String> strings = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");

       //Filter the list based on custom index-based criteria (e.g., even indices).
        List<String> sublist = IntStream.range(0, strings.size())
                .filter(i -> i % 2 == 0) // Even indices
                .mapToObj(strings::get)
                .collect(Collectors.toList());
        System.out.println(sublist); // Output: [apple, cherry, elderberry]

        List<String> sublist1 = strings.stream()
                .filter(s -> s.matches(".*rr.*")) // Strings containing "rr"
                .collect(Collectors.toList());
        System.out.println(sublist1); // Output: [cherry, elderberry]

        List<String> sublist2 = strings.stream()
                .limit(3) // Take first 3 elements
                .collect(Collectors.toList());
        System.out.println(sublist2); // Output: [apple, banana, cherry]

        int n = 2;
        List<String> sublist4 = strings.stream()
                .skip(strings.size() - n) // Skip all but the last 2 elements
                .collect(Collectors.toList());
        System.out.println(sublist4); // Output: [date, elderberry]

        List<String> sublist5 = strings.stream()
                .filter(s -> s.length() > 5 && s.contains("e")) // Length > 5 and contains 'e'
                .collect(Collectors.toList());
        System.out.println(sublist5); // Output: [elderberry]
        char[] charr = { 'g', 'e', 'e', 'k', 's', 'f', 'o',
        'r', 'g', 'e', 'e', 'k', 's' };

        String s = Stream.of(charr).map(c -> String.valueOf(c)).collect(Collectors.joining());
        System.out.println("String formed using STring is"+ s);
        StringBuilder stringBuilder = new StringBuilder();
        for(char c: charr){
            stringBuilder.append(c);
        }
        System.out.println("String formed using StringBuilder is"+ stringBuilder.toString());
        String ss = new String(charr);
        System.out.println("String formed using String is"+ ss);

        int[] arr1 = { -8, -3, -6, -2, -5, -4 };
        int maxElem = Arrays.stream(arr1).max().getAsInt();
        System.out.println("Maxium ELemenet in arrr:"+ Arrays.toString(arr1) + " is "+ maxElem);

        Set<String> word = Stream.of("STAR", "NOTE", "SAND", "STONE").collect(Collectors.toSet());
        List<String> reverseSortedWord = word.stream().sorted((p,q) -> q.compareTo(p)).collect(Collectors.toList());
        Set<String> reverseSortedWord1 = word.stream().sorted((p,q) -> q.compareTo(p)).collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println("reverseSortedWord = "+ reverseSortedWord);
        System.out.println("reverseSortedWord1 = "+ reverseSortedWord1);

        int[] arr23 = {1,5,2,3,2,3,3,3,3,4,4,4};
        //sort the data based on freq Descending order i.e data with highestFrequency First then...

        Map<Integer, DataObj> map = new HashMap<>();
        int index =0;
        for(int x:arr23){
            index++;
            map.putIfAbsent(x,new DataObj(index,x,0));
            map.get(x).count++;
        }
        List<DataObj> DataObjs = map.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        List<DataObj> sortedByFreqDataObjs = DataObjs.stream().sorted((p,q) -> {
            if(q.count!=p.count){
                return q.count - p.count;
            }
            return p.index - q.index;
        }).collect(Collectors.toList());

        for(DataObj d:  sortedByFreqDataObjs){
            System.out.print(String.valueOf(d.val+" ").repeat(d.count));
        }

        int[] freq = { 25, 10, 20 };
        int sum = IntStream.rangeClosed(1,2).map(r -> freq[r]).sum();
        System.out.println("Sum for Range 1-2 "+ sum);

        String[][] input = new String[][]{
                {"LAX", "DXB"},
                {"DFW", "JFK"},
                {"LHR", "DFW"},
                {"JFK", "LAX"}
        };

        Map<String,String> inputMap = Arrays.stream(input).collect(Collectors.toMap(p-> p[0], p -> p[1]));
        inputMap.forEach((k,v) -> System.out.println("Key is "+ k+" val is "+v));

        String[] chars = { "A", "B", "C", "A", "C", "A" };
        Map<String,Long> charsMap = Stream.of(chars).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("CharsMap is "+ charsMap);


         List<Employee> employees = Arrays.asList(
                 new Employee(1,"Sonu", 30),
         new Employee(2,"Monu", 30),
         new Employee(3,"Amit", 34) );

         System.out.println("List of Employees:");
         for(Employee emp : employees){
             System.out.println("ID: "+emp.id+", Name: "+emp.name+", Age: "+emp.age);
         }

         System.out.println("\nEmployees with age 30:");

         Map<Integer, List<Employee>> mp = employees.stream().collect(Collectors.groupingBy((Employee emp) -> emp.age));

         mp.forEach((key,value) -> {
             System.out.println("age =" + key);
             System.out.println("Value: "+value);
         });

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> reveresedList = IntStream.rangeClosed(1,list.size()).mapToObj(i-> list.get(list.size()-i)).collect(Collectors.toList());
        System.out.println("reveresedList is "+ reveresedList);

        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("AI101", "New York", "London",
                LocalDateTime.of(2024, 12, 1, 10, 30),
                LocalDateTime.of(2024, 12, 1, 20, 15)));
        flights.add(new Flight("BA202", "New York", "Paris",
                LocalDateTime.of(2024, 12, 1, 12, 00),
                LocalDateTime.of(2024, 12, 1, 21, 00)));
        // Flight search example
        String source = "New York";
        String destination = "London";
        LocalDateTime searchDate = LocalDateTime.of(2024, 12, 1, 0, 0);

        List<Flight> searchedFlightResults = flights.stream().filter(f -> f.getSource().equalsIgnoreCase(source)
                && f.getDestination().equalsIgnoreCase(destination) && f.getDepartureTime().toLocalDate().equals(searchDate.toLocalDate())
        ).collect(Collectors.toList());
        System.out.println("Matching Flights: " + searchedFlightResults);


        List<Seat> seats = new ArrayList<>();
        seats.add(new Seat(1, 50.0, SeatStatus.AVAILABLE));
        seats.add(new Seat(2, 50.0, SeatStatus.BOOKED));
        seats.add(new Seat(3, 60.0, SeatStatus.AVAILABLE));
        seats.add(new Seat(4, 60.0, SeatStatus.AVAILABLE));
        seats.add(new Seat(5, 70.0, SeatStatus.RESERVED));

        // Create Concert
        Concert concert = new Concert("Rock Night", seats);

        // Select Seats
        List<Seat> selectedSeats = selectSeats(concert, 2);
        System.out.println("Selected Seats: " + selectedSeats);

        // Calculate Total Price of Selected Seats
        double totalPrice = selectedSeats.stream().mapToDouble(Seat::getPrice).sum();
        System.out.println("Total Price: " + totalPrice);

        // Add Ratings
        concert.addRating(5);
        concert.addRating(4);
        concert.addRating(3);

        // Get Average Rating
        System.out.println("Average Rating: " + concert.getAverageRating());
        /*
        Selected Seats: [Seat{seatNumber=1, price=50.0, status=AVAILABLE}, Seat{seatNumber=3, price=60.0, status=AVAILABLE}]
        Total Price: 110.0
        Average Rating: 4.0
         */

    }
    private static List<Seat> selectSeats(Concert concert, int numberOfSeats) {
        return concert.getSeats().stream()
                .filter(seat -> seat.getStatus() == SeatStatus.AVAILABLE)
                .limit(numberOfSeats)
                .toList();
    }
}
