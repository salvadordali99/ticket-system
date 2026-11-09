package org.example.service;

import org.example.model.Customer;
import org.example.model.Event;
import org.example.model.Ticket;
import org.example.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicketService {
    private static final Logger logger =
            LoggerFactory.getLogger(TicketService.class);
    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public Ticket buyTicket(int ticketId, Event event, Customer customer) {
        if (event.getAvailableSeats() <= 0) {
            logger.warn("No available seats for event {}", event.getId());
            throw new IllegalStateException("No available seats");
        }

        event.decreaseAvailableSeats();
        Ticket ticket = new Ticket(ticketId, event, customer);

        repository.save(ticket);
        logger.info("Ticket {} purchased for event {}",
                ticket.getId(), event.getId());
        return ticket;

    }

    public void cancelTicket(int ticketId) {
        Ticket ticket = repository.findById(ticketId);

        if (ticket == null) {
            logger.error("Ticket {} not found", ticketId);
            throw new IllegalArgumentException("No ticket found");
        }
        ticket.cancel();
        ticket.getEvent().increaseAvailableSeats();

    }

}
