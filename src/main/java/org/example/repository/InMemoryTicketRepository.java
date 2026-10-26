package org.example.repository;

import org.example.model.Ticket;

import java.util.HashMap;
import java.util.Map;

public class InMemoryTicketRepository implements TicketRepository {

    private final Map<Integer, Ticket> tickets = new HashMap<>();

    @Override
    public void save(Ticket ticket) {
        tickets.put(ticket.getId(), ticket);
    }

    @Override
    public Ticket findById(int id) {
        return tickets.get(id);
    }

}
