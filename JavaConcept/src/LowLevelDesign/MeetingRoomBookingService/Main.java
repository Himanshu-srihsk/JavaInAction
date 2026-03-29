package LowLevelDesign.MeetingRoomBookingService;

import java.time.*;
import java.util.*;

/*
Design a meeting room booking system (like Microsoft Outlook) where users can search rooms by time,
 capacity, and amenities, and book or cancel meetings.
 The system must prevent overlapping bookings and handle concurrent requests safely.
 It should also support recurring meetings (daily/weekly) and allow listing bookings for a room on a given date.
 The design should be scalable, extensible, and follow SOLID principles.
 */
public class Main {

    public static void main(String[] args) {

        BookingRepository bookingRepo = new InMemoryBookingRepository();
        RoomRepository roomRepo = new InMemoryRoomRepository();
        LockManager lockManager = new LockManager();
        AvailabilityService availabilityService = new AvailabilityService(bookingRepo);

        BookingService service = new BookingService(bookingRepo, roomRepo, availabilityService, lockManager);

        System.out.println("===== MEETING ROOM BOOKING SYSTEM =====");

        Room r1 = new Room("R1", 10, Set.of(Amenity.TV, Amenity.WHITEBOARD,Amenity.VIDEO_CONFERENCING));
        Room r2 = new Room("R2", 5, Set.of(Amenity.VIDEO_CONFERENCING));
        Room r3 = new Room("R3", 20, Set.of(Amenity.TV, Amenity.VIDEO_CONFERENCING));
        Room r4 = new Room("R4", 10, Set.of(Amenity.WHITEBOARD, Amenity.VIDEO_CONFERENCING));
        Room r5 = new Room("R5", 2, Set.of(Amenity.TV,Amenity.WHITEBOARD));
        Room r6 = new Room("R6", 12, Set.of(Amenity.TV, Amenity.VIDEO_CONFERENCING,Amenity.WHITEBOARD));

        roomRepo.save(r1);
        roomRepo.save(r2);
        roomRepo.save(r3);
        roomRepo.save(r4);
        roomRepo.save(r5);
        roomRepo.save(r6);

        System.out.println("\nRooms Added:");
        printRooms(roomRepo.findAll());

        System.out.println("\n--- Rooms with WHITEBOARD and FREE (10-11) ---");

        RoomFilter filter = new CompositeFilter(List.of(
                new AmenityFilter(Amenity.WHITEBOARD),
                new AvailabilityFilter(availabilityService,
                        LocalDateTime.of(2026, 3, 29, 10, 0),
                        LocalDateTime.of(2026, 3, 29, 11, 0))
        ));

        List<Room> result = service.searchRooms(filter);

        printRooms(result);


        System.out.println("\n--- Booking Room R1 (10:00 - 11:00) ---");

        boolean booking1 = service.book(
                "R1",
                "user1",
                LocalDateTime.of(2026, 3, 29, 10, 0),
                LocalDateTime.of(2026, 3, 29, 11, 0)
        );

        System.out.println("Booking Success: " + booking1);



        // ------------------ CONFLICT BOOKING ------------------
        System.out.println("\n--- Trying conflicting booking (same slot) ---");

        boolean conflictBooking = service.book(
                "R1",
                "user2",
                LocalDateTime.of(2026, 3, 29, 10, 30),
                LocalDateTime.of(2026, 3, 29, 11, 30)
        );

        System.out.println("Booking Success : " + conflictBooking); // as same slot already booked

        System.out.println("\n--- Rooms with WHITEBOARD & VIDEO_CONFERENCING and FREE (10-11) ---");

        RoomFilter filter1 = new CompositeFilter(List.of(
                new MultiAmenityFilter(Set.of(
                        Amenity.WHITEBOARD,
                        Amenity.VIDEO_CONFERENCING
                )),
                new AvailabilityFilter(availabilityService,
                        LocalDateTime.of(2026, 3, 29, 10, 0),
                        LocalDateTime.of(2026, 3, 29, 11, 0))
        ));

        List<Room> result1 = service.searchRooms(filter1);

        printRooms(result1);

        System.out.println("\n--- Available Rooms (10:00 - 11:00, capacity >= 6) ---");

        List<Room> availableRooms = service.searchAvailableRooms(
                LocalDateTime.of(2026, 3, 29, 10, 0),
                LocalDateTime.of(2026, 3, 29, 11, 0),
                6
        );

        printRooms(availableRooms);

        System.out.println("\n--- Bookings for Room R1 on 2026-03-29 ---");

        List<Booking> bookings = service.getBookings(
                "R1",
                LocalDate.of(2026, 3, 29)
        );

        printBookings(bookings);

        System.out.println("\n--- Rooms with WHITEBOARD & VIDEO_CONFERENCING and FREE (10-11) and Have capacity ≥ 10 ---");

        RoomFilter filter2 = new CompositeFilter(List.of(
                new MultiAmenityFilter(Set.of(
                        Amenity.WHITEBOARD,
                        Amenity.VIDEO_CONFERENCING
                )),
                new AvailabilityFilter(
                        availabilityService,
                        LocalDateTime.of(2026, 3, 29, 10, 0),
                        LocalDateTime.of(2026, 3, 29, 11, 0)
                ),
                new CapacityFilter(10)
        ));

        List<Room> result2 = service.searchRooms(filter2);

        printRooms(result2);

        System.out.println("\n--- Recurring Booking (Weekly) for R2 ---");

        RecurrenceStrategy weekly = new WeeklyRecurrence(1, LocalDateTime.of(2026, 4, 30, 10, 0)
        );

        List<Boolean> recurringResults = service.bookRecurring(
                "R2",
                "user3",
                LocalDateTime.of(2026, 3, 29, 9, 0),
                Duration.ofHours(1),
                weekly
        );

        System.out.println("Recurring Booking Results:");
        for (int i = 0; i < recurringResults.size(); i++) {
            System.out.println("Occurrence " + (i + 1) + ": " + recurringResults.get(i));
        }

        System.out.println("\n--- All Bookings for Room R2 ---");

        List<Booking> r2Bookings = bookingRepo.findByRoom("R2");
        printBookings(r2Bookings);

        System.out.println("\n===== END =====");
    }

    private static void printRooms(List<Room> rooms) {
        for (Room r : rooms) {
            System.out.println("Room ID: " + r.getId()
                    + ", Capacity: " + r.getCapacity() + ", Amenities: "+ r.getAmenities());
        }
    }

    private static void printBookings(List<Booking> bookings) {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Booking b : bookings) {
            System.out.println("Room: " + b.getRoomId() + ", Start: " + b.getStart() + ", End: " + b.getEnd() + ", Status: " + b.getStatus());
        }
    }
}
/*
===== MEETING ROOM BOOKING SYSTEM =====

Rooms Added:
Room ID: R2, Capacity: 5, Amenities: [VIDEO_CONFERENCING]
Room ID: R3, Capacity: 20, Amenities: [VIDEO_CONFERENCING, TV]
Room ID: R4, Capacity: 10, Amenities: [VIDEO_CONFERENCING, WHITEBOARD]
Room ID: R5, Capacity: 2, Amenities: [WHITEBOARD, TV]
Room ID: R6, Capacity: 12, Amenities: [VIDEO_CONFERENCING, TV, WHITEBOARD]
Room ID: R1, Capacity: 10, Amenities: [VIDEO_CONFERENCING, TV, WHITEBOARD]

--- Rooms with WHITEBOARD and FREE (10-11) ---
Room ID: R4, Capacity: 10, Amenities: [VIDEO_CONFERENCING, WHITEBOARD]
Room ID: R5, Capacity: 2, Amenities: [WHITEBOARD, TV]
Room ID: R6, Capacity: 12, Amenities: [VIDEO_CONFERENCING, TV, WHITEBOARD]
Room ID: R1, Capacity: 10, Amenities: [VIDEO_CONFERENCING, TV, WHITEBOARD]

--- Booking Room R1 (10:00 - 11:00) ---
Booking Success: true

--- Trying conflicting booking (same slot) ---
Booking Success : false

--- Rooms with WHITEBOARD & VIDEO_CONFERENCING and FREE (10-11) ---
Room ID: R4, Capacity: 10, Amenities: [VIDEO_CONFERENCING, WHITEBOARD]
Room ID: R6, Capacity: 12, Amenities: [VIDEO_CONFERENCING, TV, WHITEBOARD]

--- Available Rooms (10:00 - 11:00, capacity >= 6) ---
Room ID: R3, Capacity: 20, Amenities: [VIDEO_CONFERENCING, TV]
Room ID: R4, Capacity: 10, Amenities: [VIDEO_CONFERENCING, WHITEBOARD]
Room ID: R6, Capacity: 12, Amenities: [VIDEO_CONFERENCING, TV, WHITEBOARD]

--- Bookings for Room R1 on 2026-03-29 ---
Room: R1, Start: 2026-03-29T10:00, End: 2026-03-29T11:00, Status: BOOKED

--- Rooms with WHITEBOARD & VIDEO_CONFERENCING and FREE (10-11) and Have capacity ≥ 10 ---
Room ID: R4, Capacity: 10, Amenities: [VIDEO_CONFERENCING, WHITEBOARD]
Room ID: R6, Capacity: 12, Amenities: [VIDEO_CONFERENCING, TV, WHITEBOARD]

--- Recurring Booking (Weekly) for R2 ---
Recurring Booking Results:
Occurrence 1: true
Occurrence 2: true
Occurrence 3: true
Occurrence 4: true
Occurrence 5: true

--- All Bookings for Room R2 ---
Room: R2, Start: 2026-03-29T09:00, End: 2026-03-29T10:00, Status: BOOKED
Room: R2, Start: 2026-04-05T09:00, End: 2026-04-05T10:00, Status: BOOKED
Room: R2, Start: 2026-04-12T09:00, End: 2026-04-12T10:00, Status: BOOKED
Room: R2, Start: 2026-04-19T09:00, End: 2026-04-19T10:00, Status: BOOKED
Room: R2, Start: 2026-04-26T09:00, End: 2026-04-26T10:00, Status: BOOKED

===== END =====
 */