package com.test.server.services.rest;


import com.test.server.entity.Manager;
import com.test.server.repositories.ManagersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ManagersRestController {
    @Autowired
    private ManagersRepository managersRepository;

    @GetMapping("/managers")
    public List<Manager> all() {
        return managersRepository.findAll();
    }
    @GetMapping("/managers/{id}")
    public Manager getManagerByid(@PathVariable String id) {
        return managersRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        ));
    }
    @PostMapping("/managers")
    public Manager newManager(@RequestBody Manager newManager) {
        return managersRepository.save(newManager);
    }

   @PostMapping("/managers/{id}")
    public Manager editManager(@PathVariable String id, @RequestBody Manager newManager) {
        return managersRepository.save(newManager);
    }
    @DeleteMapping("/managers/{id}")
    public Manager deleteManager(@PathVariable String id, @RequestBody Manager newManager) {
        return managersRepository.save(newManager);
    }
}
