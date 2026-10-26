package org.example;

import org.example.model.Customer;
import org.example.model.Event;
import org.example.model.Ticket;
import org.example.repository.InMemoryTicketRepository;
import org.example.repository.TicketRepository;
import org.example.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicketServiceTest {
    private TicketRepository repository;
    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTicketRepository();
        ticketService = new TicketService(repository);
    }
    @Test
    void shouldBuyTicketSuccessfully() {


        Event event = new Event(1, "Concert", 3);
        Customer customer = new Customer(1, "Somov");
        Ticket ticket = ticketService.buyTicket(1, event, customer);
        assertNotNull(ticket);
        assertEquals(2, event.getAvailableSeats());
        assertEquals(customer, ticket.getCustomer());
    }

    @Test
    void shouldNotBuyTicketWhenNoSeatsAvailable() {


        Event event = new Event(1, "Concert", 0);
        Customer customer = new Customer(1, "Dmitriy");

        assertThrows(
                IllegalStateException.class,
                () -> ticketService.buyTicket(1, event, customer)
        );
    }

    @Test
    void shouldIncreaseAvailableSeatsAfterCancellation() {


        Event event = new Event(1, "Concert", 5);
        Customer customer = new Customer(1, "Dmitriy");

        Ticket ticket = ticketService.buyTicket(1, event, customer);

        assertEquals(4, event.getAvailableSeats());

        ticketService.cancelTicket(ticket.getId());

        assertTrue(ticket.isCancelled());
        assertEquals(5, event.getAvailableSeats());
    }
}
