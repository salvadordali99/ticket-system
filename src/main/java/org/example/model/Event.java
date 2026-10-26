package org.example.model;

public class Event {
    private final int id;
    private final String name;
    private int availableSeats;

    public Event(int id, String name, int availableSeats) {
        this.id = id;
        this.name = name;
        this.availableSeats = availableSeats;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void increaseAvailableSeats() {
        availableSeats++;
    }

    public void decreaseAvailableSeats() {
        if (availableSeats <= 0) {
            throw new IllegalStateException("No available seats");
        }
        availableSeats--;
    }
}
