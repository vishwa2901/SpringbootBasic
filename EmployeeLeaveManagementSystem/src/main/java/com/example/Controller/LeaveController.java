package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Model.Leave;
import com.example.Service.LeaveService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    LeaveService service;

    // Apply leave
    @PostMapping("/save")
    public Leave applyLeave(@Valid @RequestBody Leave leave) {

        return service.applyLeave(leave);
    }

    // View all leaves
    @GetMapping("/")
    public List<Leave> getAllLeaves() {

        return service.getAllLeaves();
    }

    // Find by ID
    @GetMapping("/{id}")
    public Leave getLeaveById(@PathVariable Long id) {

        return service.getLeaveById(id);
    }

    // Delete leave
    @DeleteMapping("/{id}")
    public String deleteLeave(@PathVariable Long id) {

        return service.deleteLeave(id);
    }

    // Search by employee name
    @GetMapping("/search/name")
    public List<Leave> searchByName(@RequestParam String keyword) {

        return service.searchByName(keyword);
    }

    // Search by leave type
    @GetMapping("/search/type")
    public List<Leave> getByLeaveType(@RequestParam String leaveType) {

        return service.getByLeaveType(leaveType);
    }
}