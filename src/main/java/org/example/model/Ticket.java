package org.example.model;

public class Ticket {
    private final int id;
    private final Event event;
    private final Customer customer;
    private boolean cancelled;

    public Ticket(int id, Event event, Customer customer) {
        this.id = id;
        this.event = event;
        this.customer = customer;
        this.cancelled = false;
    }

    public int getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void cancel() {
        if (cancelled) {
            throw new IllegalStateException("Ticket is already cancelled");
        }
        cancelled = true;
    }
}
