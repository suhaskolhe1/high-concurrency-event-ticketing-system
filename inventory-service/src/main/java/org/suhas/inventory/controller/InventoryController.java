package org.suhas.inventory.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.suhas.inventory.service.SeatLockService;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final SeatLockService seatLockService;


    @PostMapping("/lock/{eventId}/{seatId}")
    public ResponseEntity<String> lockSeat(
            @RequestHeader("X-User-Id") String userId,
            @PathVariable String eventId,
            @PathVariable String seatId) {

        boolean success = seatLockService.lockSeat(eventId, seatId, userId);

        if (success) {
            return ResponseEntity.ok("Seat locked successfully for 10 minutes.");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Seat is already locked by someone else!");
        }
    }
    @PostMapping("/unlock/{eventId}/{seatId}")
    public ResponseEntity<Void> unlockSeat(
            @RequestHeader("X-User-Id") String userId,
            @PathVariable String eventId,
            @PathVariable String seatId) {

        seatLockService.unlockSeat(eventId, seatId, userId);
        return ResponseEntity.ok().build();
    }
}
