package com.dev.chitdanai.handytools;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class HandytoolController {
    private final StorageRepository repository;

    HandytoolController(StorageRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/handytools")
    List<Storage> findAll() {
        return repository.findAll();
    }

    @GetMapping("/handytools/{id}")
    Storage findOne(@PathVariable Long id) {
        Optional<Storage> handytool = repository.findById(id);
        if (handytool.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no handy tool by given id");
        }
        return handytool.get();
    }

    @PostMapping("/handytools")
    Storage newHandyTool(@RequestBody Storage handytool) {
        // Validate borrowed state and borrower name consistency
        if (handytool.getBorrowed() && handytool.getBorrowerName() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Cannot create tool as borrowed without specifying a borrower"
            );
        }
    
        // Validate that borrower is null when tool is not borrowed
        if (!handytool.getBorrowed() && handytool.getBorrowerName() != null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Borrower name must be null when tool is not borrowed"
            );
        }
    
        // Ensure borrowerName is null when not borrowed
        if (!handytool.getBorrowed()) {
            handytool.setBorrowerName(null);
        }
    
        return repository.save(handytool);
    }

        @PutMapping("/handytools/{id}")
    Storage saveHandyTool(@RequestBody Storage newHandyTool, @PathVariable Long id) {
        Optional<Storage> existingTool = repository.findById(id);
    
        if (existingTool.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Cannot borrow - Tool with id %d does not exist", id)
            );
        }
    
        // Validate borrowed state and borrower name consistency
        if (newHandyTool.getBorrowed() && newHandyTool.getBorrowerName() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Cannot set tool as borrowed without specifying a borrower"
            );
        }
    
        // Validate that borrower is null when tool is not borrowed
        if (!newHandyTool.getBorrowed() && newHandyTool.getBorrowerName() != null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Borrower name must be null when tool is not borrowed"
            );
        }
    
        return existingTool.map(handytool -> {
            // Prevent owner change
            if (!handytool.getOwnerName().equals(newHandyTool.getOwnerName())) {
                throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Changing the owner of a tool is not allowed"
                );
            }
    
            // Check if tool is already borrowed and attempting to borrow again
            if (handytool.getBorrowed() && newHandyTool.getBorrowed()) {
                throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("Tool is currently unavailable - borrowed by %s", handytool.getBorrowerName())
                );
            }
    
            handytool.setBorrowed(newHandyTool.getBorrowed());
            handytool.setBorrowerName(newHandyTool.getBorrowed() ? newHandyTool.getBorrowerName() : null);
            handytool.setLocationName(newHandyTool.getLocationName());
            handytool.setToolDetail(newHandyTool.getToolDetail());
            return repository.save(handytool);
        }).get();
    }

    @ResponseStatus(HttpStatus.OK) // Changed from NO_CONTENT to OK to allow response body
    @DeleteMapping("/handytools/{id}")
    Storage deleteHandyTool(@PathVariable Long id) {
        Optional<Storage> tool = repository.findById(id);
        if (tool.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Cannot Delete - Tool with id %d does not exist", id));
        }
        // Get the tool details before deletion
        Storage deletedTool = tool.get();
        // Perform the deletion
        repository.deleteById(id);
        // Return the deleted tool details
        return deletedTool;
    }

}