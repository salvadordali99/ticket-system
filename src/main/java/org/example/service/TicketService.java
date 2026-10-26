package org.example.service;

import org.example.model.Customer;
import org.example.model.Event;
import org.example.model.Ticket;
import org.example.repository.TicketRepository;

public class TicketService {
    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public Ticket buyTicket(int ticketId, Event event, Customer customer) {
        if (event.getAvailableSeats() <= 0) {
            throw new IllegalStateException("No available seats");
        }

        event.decreaseAvailableSeats();
        Ticket ticket = new Ticket(ticketId, event, customer);

        repository.save(ticket);
        return ticket;

    }

    public void cancelTicket(int ticketId) {
        Ticket ticket = repository.findById(ticketId);

        if (ticket == null) {
            throw new IllegalArgumentException("No ticket found");
        }
        ticket.cancel();
        ticket.getEvent().increaseAvailableSeats();

    }

}
