package LowLevelDesign.TrafficSignalSystem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Cars (threads) wait at red light
Controller periodically gives green signal to specific directions.

NORTH -> goes straight to SOUTH
SOUTH -> goes straight to NORTH
EAST -> goes straight to WEST
WEST -> goes straight to EAST

 */
public class Main {
    public static void main(String[] args) {
        TrafficSignal signal = new TrafficSignal();
        new Thread(new TrafficLightController(signal)).start();
        ExecutorService executor = Executors.newFixedThreadPool(12);
        int id = 1;
        for (Direction dir : Direction.values()) {
            for (int i = 0; i < 5; i++) {
                executor.submit(new Car(id++, dir, signal));
            }
        }
    }
}
/*

 GREEN: NORTH & SOUTH
Car 8 arrived from SOUTH
Car 11 arrived from EAST
Car 8 passing from SOUTH
Car 3 arrived from NORTH
Car 3 passing from NORTH
Car 2 arrived from NORTH
Car 2 passing from NORTH
Car 5 arrived from NORTH
Car 1 arrived from NORTH
Car 7 arrived from SOUTH
Car 6 arrived from SOUTH
Car 6 passing from SOUTH
Car 4 arrived from NORTH
Car 4 passing from NORTH
Car 12 arrived from EAST
Car 5 passing from NORTH
Car 9 arrived from SOUTH
Car 9 passing from SOUTH
Car 1 passing from NORTH
Car 10 arrived from SOUTH
Car 7 passing from SOUTH
Car 10 passing from SOUTH
Car 1 crossed from NORTH
Car 3 crossed from NORTH
Car 4 crossed from NORTH
Car 6 crossed from SOUTH
Car 8 crossed from SOUTH
Car 2 crossed from NORTH
Car 10 crossed from SOUTH
Car 5 crossed from NORTH
Car 9 crossed from SOUTH
Car 7 crossed from SOUTH
Car 1 arrived from NORTH
Car 5 arrived from NORTH
Car 5 passing from NORTH
Car 3 arrived from NORTH
Car 4 arrived from NORTH
Car 4 passing from NORTH
Car 8 arrived from SOUTH
Car 2 arrived from NORTH
Car 2 passing from NORTH
Car 6 arrived from SOUTH
Car 6 passing from SOUTH
Car 10 arrived from SOUTH
Car 10 passing from SOUTH
Car 9 arrived from SOUTH
Car 9 passing from SOUTH
Car 1 passing from NORTH
Car 7 arrived from SOUTH
Car 7 passing from SOUTH
Car 3 passing from NORTH
Car 8 passing from SOUTH
Car 9 crossed from SOUTH
Car 1 crossed from NORTH
Car 7 crossed from SOUTH
Car 3 crossed from NORTH
Car 8 crossed from SOUTH
Car 10 crossed from SOUTH
Car 6 crossed from SOUTH
Car 2 crossed from NORTH
Car 5 crossed from NORTH
Car 4 crossed from NORTH
Car 1 arrived from NORTH
Car 1 passing from NORTH
Car 3 arrived from NORTH
Car 8 arrived from SOUTH
Car 8 passing from SOUTH
Car 9 arrived from SOUTH
Car 9 passing from SOUTH
Car 2 arrived from NORTH
Car 7 arrived from SOUTH
Car 7 passing from SOUTH
Car 4 arrived from NORTH
Car 4 passing from NORTH
Car 10 arrived from SOUTH
Car 10 passing from SOUTH
Car 5 arrived from NORTH
Car 5 passing from NORTH
Car 6 arrived from SOUTH
Car 6 passing from SOUTH
Car 3 passing from NORTH
Car 2 passing from NORTH
Car 2 crossed from NORTH
Car 5 crossed from NORTH
Car 3 crossed from NORTH
Car 6 crossed from SOUTH
Car 4 crossed from NORTH
Car 7 crossed from SOUTH
Car 10 crossed from SOUTH
Car 9 crossed from SOUTH
Car 8 crossed from SOUTH
Car 1 crossed from NORTH
Car 9 arrived from SOUTH
Car 10 arrived from SOUTH
Car 4 arrived from NORTH
Car 9 passing from SOUTH
Car 1 arrived from NORTH
Car 1 passing from NORTH
Car 10 passing from SOUTH
Car 4 passing from NORTH
Car 2 arrived from NORTH
Car 2 passing from NORTH
Car 3 arrived from NORTH
Car 8 arrived from SOUTH
Car 8 passing from SOUTH
Car 7 arrived from SOUTH
Car 7 passing from SOUTH
Car 6 arrived from SOUTH
Car 6 passing from SOUTH
Car 5 arrived from NORTH
Car 3 passing from NORTH
Car 5 passing from NORTH
 SWITCHING...

 GREEN: EAST & WEST
Car 11 passing from EAST
Car 12 passing from EAST
Car 3 crossed from NORTH
Car 1 crossed from NORTH
Car 9 crossed from SOUTH
Car 5 crossed from NORTH
Car 10 crossed from SOUTH
Car 4 crossed from NORTH
Car 2 crossed from NORTH
Car 8 crossed from SOUTH
Car 7 crossed from SOUTH
Car 6 crossed from SOUTH
Car 12 crossed from EAST
Car 11 crossed from EAST
Car 8 arrived from SOUTH
Car 7 arrived from SOUTH
Car 5 arrived from NORTH
Car 4 arrived from NORTH
Car 2 arrived from NORTH
Car 10 arrived from SOUTH
Car 9 arrived from SOUTH
Car 1 arrived from NORTH
Car 3 arrived from NORTH
Car 6 arrived from SOUTH
Car 11 arrived from EAST
Car 11 passing from EAST
Car 12 arrived from EAST
Car 12 passing from EAST
Car 11 crossed from EAST
Car 12 crossed from EAST
Car 12 arrived from EAST
Car 12 passing from EAST
Car 11 arrived from EAST
Car 11 passing from EAST
Car 12 crossed from EAST
Car 11 crossed from EAST
Car 11 arrived from EAST
Car 11 passing from EAST
Car 12 arrived from EAST
Car 12 passing from EAST

 GREEN: NORTH & SOUTH
Car 8 passing from SOUTH
Car 7 passing from SOUTH
Car 5 passing from NORTH
Car 2 passing from NORTH
Car 10 passing from SOUTH
Car 4 passing from NORTH
Car 3 passing from NORTH
Car 9 passing from SOUTH
Car 1 passing from NORTH
Car 6 passing from SOUTH
Car 12 crossed from EAST
Car 11 crossed from EAST
Car 8 crossed from SOUTH
Car 7 crossed from SOUTH
Car 5 crossed from NORTH
Car 2 crossed from NORTH
Car 10 crossed from SOUTH
Car 4 crossed from NORTH
Car 9 crossed from SOUTH
Car 1 crossed from NORTH
Car 6 crossed from SOUTH
Car 3 crossed from NORTH
Car 11 arrived from EAST
Car 12 arrived from EAST
Car 3 arrived from NORTH
Car 9 arrived from SOUTH
Car 6 arrived from SOUTH
Car 1 arrived from NORTH
Car 1 passing from NORTH
Car 4 arrived from NORTH
Car 4 passing from NORTH
Car 3 passing from NORTH
Car 10 arrived from SOUTH
Car 10 passing from SOUTH
Car 2 arrived from NORTH
Car 2 passing from NORTH
Car 5 arrived from NORTH
Car 5 passing from NORTH
Car 8 arrived from SOUTH
Car 8 passing from SOUTH
Car 7 arrived from SOUTH
Car 7 passing from SOUTH
Car 9 passing from SOUTH
Car 6 passing from SOUTH
Car 6 crossed from SOUTH
Car 3 crossed from NORTH
Car 4 crossed from NORTH
Car 2 crossed from NORTH
Car 9 crossed from SOUTH
Car 5 crossed from NORTH
Car 8 crossed from SOUTH
Car 1 crossed from NORTH
Car 7 crossed from SOUTH
Car 10 crossed from SOUTH
Car 9 arrived from SOUTH
Car 1 arrived from NORTH
Car 5 arrived from NORTH
Car 4 arrived from NORTH
Car 4 passing from NORTH
Car 3 arrived from NORTH
Car 3 passing from NORTH
Car 2 arrived from NORTH
Car 6 arrived from SOUTH
Car 6 passing from SOUTH
Car 10 arrived from SOUTH
Car 10 passing from SOUTH
Car 8 arrived from SOUTH
Car 9 passing from SOUTH
Car 7 arrived from SOUTH
Car 1 passing from NORTH
Car 5 passing from NORTH
Car 2 passing from NORTH
Car 8 passing from SOUTH
Car 7 passing from SOUTH
Car 8 crossed from SOUTH
Car 9 crossed from SOUTH
Car 7 crossed from SOUTH
Car 2 crossed from NORTH
Car 1 crossed from NORTH
Car 5 crossed from NORTH
Car 10 crossed from SOUTH
Car 6 crossed from SOUTH
Car 3 crossed from NORTH
Car 4 crossed from NORTH
Car 4 arrived from NORTH
Car 4 passing from NORTH
Car 7 arrived from SOUTH
Car 7 passing from SOUTH
Car 2 arrived from NORTH
Car 2 passing from NORTH
Car 1 arrived from NORTH
Car 1 passing from NORTH
Car 10 arrived from SOUTH
Car 10 passing from SOUTH
Car 6 arrived from SOUTH
Car 6 passing from SOUTH
Car 3 arrived from NORTH
Car 3 passing from NORTH
Car 8 arrived from SOUTH
Car 9 arrived from SOUTH
Car 9 passing from SOUTH
Car 5 arrived from NORTH
Car 5 passing from NORTH
Car 8 passing from SOUTH
 SWITCHING...

 GREEN: EAST & WEST
Car 11 passing from EAST
Car 12 passing from EAST
Car 3 crossed from NORTH
Car 4 crossed from NORTH
Car 6 crossed from SOUTH
Car 5 crossed from NORTH
Car 10 crossed from SOUTH
Car 1 crossed from NORTH
Car 9 crossed from SOUTH
Car 8 crossed from SOUTH
Car 2 crossed from NORTH
Car 7 crossed from SOUTH
Car 11 crossed from EAST
Car 12 crossed from EAST
Car 3 arrived from NORTH
Car 4 arrived from NORTH
Car 6 arrived from SOUTH
Car 5 arrived from NORTH
Car 10 arrived from SOUTH
Car 1 arrived from NORTH
Car 9 arrived from SOUTH
Car 8 arrived from SOUTH
Car 2 arrived from NORTH
Car 7 arrived from SOUTH
Car 12 arrived from EAST
Car 12 passing from EAST
Car 11 arrived from EAST
Car 11 passing from EAST
 */