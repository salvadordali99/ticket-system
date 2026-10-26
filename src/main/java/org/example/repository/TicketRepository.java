package org.example.repository;

import org.example.model.Ticket;

public interface TicketRepository {
    void save(Ticket ticket);
    Ticket findById(int id);
}
