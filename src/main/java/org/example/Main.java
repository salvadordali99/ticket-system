package org.example;

import org.example.model.Customer;
import org.example.model.Event;
import org.example.model.Ticket;
import org.example.repository.InMemoryTicketRepository;
import org.example.repository.TicketRepository;
import org.example.service.TicketService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TicketRepository repository = new InMemoryTicketRepository();
        TicketService ticketService = new TicketService(repository);

        Event event = new Event(1, "Concert", 3);
        Customer customer = new Customer(1, "Somov");
        Ticket ticket = ticketService.buyTicket(1, event, customer);
        System.out.println("after purchase: " + event.getAvailableSeats());
        ticketService.cancelTicket(1);
        System.out.println("after refund: " + event.getAvailableSeats());


    }
}