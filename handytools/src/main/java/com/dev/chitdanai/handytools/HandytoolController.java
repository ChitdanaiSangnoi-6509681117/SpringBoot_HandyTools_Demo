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
        Optional<Storage> handytool =  repository.findById(id);
        if (handytool.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no handy tool by given id");
        }
        return handytool.get();
    }
    
    @PostMapping("/handytools")
    Storage newHandyTool(@RequestBody Storage handytool) {        
        return repository.save(handytool);
    }
    
    @PutMapping("/handytools/{id}")
    Storage saveHandyTool(@RequestBody Storage newHandyTool, @PathVariable Long id) {
        return repository.findById(id).map(handytool -> {
            handytool.setBorrowed(newHandyTool.getBorrowed());
            handytool.setBorrowerName(newHandyTool.getBorrowerName());
            handytool.setLocationName(newHandyTool.getLocationName());
            handytool.setOwnerName(newHandyTool.getOwnerName());
            handytool.setToolDetail(newHandyTool.getToolDetail());
            return repository.save(handytool);
        }).orElseGet(() -> {
            return repository.save(newHandyTool);
        });
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/handytools/{id}")
    void deleteHandyTool(@PathVariable Long id) {
        repository.deleteById(id);
    }

}