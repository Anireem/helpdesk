package com.skorpofeo.helpdesk.controller;

import com.skorpofeo.helpdesk.exception.ResourceNotFoundException;
import com.skorpofeo.helpdesk.model.Ticket;
import com.skorpofeo.helpdesk.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class TicketController {

    public String ticketNotFoundMessage = "Ticket not found with this id ::";

    // TODO: 6/22/2022 comment 
    @Autowired
    private TicketRepository ticketRepository;

    // TODO: 6/22/2022 comment 
    @GetMapping("tickets")
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // TODO: 6/22/2022 comment 
    @GetMapping("tickets/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable(value = "id") Long ticketId)
        throws ResourceNotFoundException {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException(ticketNotFoundMessage + ticketId));
        // TODO: 6/22/2022 what is it or else thorw
        // TODO: 6/22/2022 what is it: ResponseEntity
        return ResponseEntity.ok().body(ticket);
    }

    // TODO: 6/22/2022 comment 
    @PostMapping("tickets")
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    // TODO: 6/22/2022 comment
    // TODO: 6/22/2022 why here ResponseEntity and just Ticket above
    @PostMapping("tickets/{id}")
    public ResponseEntity<Ticket> updateTicket(
            @PathVariable(value = "id") Long ticketId,
            @RequestBody Ticket ticketDetails
    ) throws ResourceNotFoundException {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException(ticketNotFoundMessage + ticketId));
        ticket.setCustomer(ticketDetails.getCustomer());
        ticket.setDescription(ticketDetails.getDescription());
        ticket.setName(ticketDetails.getName());
        ticket.setResponsible(ticketDetails.getResponsible());
        // TODO: 6/22/2022 what is it or else throw
        // TODO: 6/22/2022 what is it: ResponseEntity
        // TODO: 6/22/2022 what is it "ok"
        return ResponseEntity.ok(ticketRepository.save(ticket));
    }

    @DeleteMapping("tickets/{id}")
    public Map<String, Boolean> deleteTicket(
            @PathVariable(value = "id") Long ticketId
    ) throws ResourceNotFoundException {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException(ticketNotFoundMessage + ticketId));
        ticketRepository.delete(ticket);
        return Map.of("Deleted", true);
    }
}
