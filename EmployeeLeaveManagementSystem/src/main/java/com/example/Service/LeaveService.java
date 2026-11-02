package com.example.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.example.Exception.LeaveNotFoundException;
import com.example.Model.Leave;

@Service
public class LeaveService {

    private final ArrayList<Leave> leaves = new ArrayList<>();

    private long nextId = 1L;

    private Leave copyLeave(Leave leave) {

        return new Leave(
                leave.getEmployeeId(),
                leave.getEmployeeName(),
                leave.getLeaveType(),
                leave.getNumberOfDays(),
                leave.getReason()
        );
    }

    // Apply leave
    public Leave applyLeave(Leave request) {

        checkNotBlank(request.getEmployeeName(),
                "Employee name is required");

        checkNotBlank(request.getLeaveType(),
                "Leave type is mandatory");

        Leave leave = new Leave(
                nextId++,
                request.getEmployeeName().trim(),
                request.getLeaveType().trim(),
                request.getNumberOfDays(),
                request.getReason()
        );

        leaves.add(leave);

        return copyLeave(leave);
    }

    // View all leave requests
    public List<Leave> getAllLeaves() {

        return leaves.stream()
                .map(this::copyLeave)
                .toList();
    }

    // Find by employee ID
    public Leave getLeaveById(Long id) {

        Leave leave = leaves.stream()
                .filter(item -> item.getEmployeeId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new LeaveNotFoundException(
                                "Leave record not found with ID: " + id
                        ));

        return copyLeave(leave);
    }

    // Delete leave request
    public String deleteLeave(Long id) {

        Leave leave = leaves.stream()
                .filter(item -> item.getEmployeeId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new LeaveNotFoundException(
                                "Leave record not found with ID: " + id
                        ));

        leaves.remove(leave);

        return "Leave request deleted successfully";
    }

    // Check blank values
    private void checkNotBlank(String value, String message) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    // Search by employee name
    public List<Leave> searchByName(String keyword) {

        checkNotBlank(keyword,
                "Search keyword must not be blank");

        String searchText = keyword.trim()
                .toLowerCase(Locale.ROOT);

        return leaves.stream()
                .filter(leave ->
                        leave.getEmployeeName()
                                .toLowerCase(Locale.ROOT)
                                .contains(searchText))
                .map(this::copyLeave)
                .toList();
    }

    // Search by leave type
    public List<Leave> getByLeaveType(String leaveType) {

        checkNotBlank(leaveType,
                "Leave type must not be blank");

        return leaves.stream()
                .filter(leave ->
                        leave.getLeaveType()
                                .equalsIgnoreCase(leaveType.trim()))
                .map(this::copyLeave)
                .toList();
    }
}