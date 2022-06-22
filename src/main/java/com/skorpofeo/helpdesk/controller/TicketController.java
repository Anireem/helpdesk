package com.skorpofeo.helpdesk.controller;

import com.skorpofeo.helpdesk.exception.ResourceNotFoundException;
import com.skorpofeo.helpdesk.model.Ticket;
import com.skorpofeo.helpdesk.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("tickets")
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @GetMapping("tickets/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable(value = "id") Long ticketId)
        throws ResourceNotFoundException {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with this id ::" + ticketId));
        // TODO: 6/22/2022 what is it or else thorw
        return ResponseEntity.ok().body(ticket); // TODO: 6/22/2022 what is it: ResponseEntity
    }
}
