package org.suhas.inventory.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class SeatLockService {

    private final StringRedisTemplate redisTemplate;

    public boolean lockSeat(String eventId, String seatId, String userId){
        String lockKey="lock:event:"+eventId+":seat:"+seatId;

        Boolean acquired  = redisTemplate.opsForValue().setIfAbsent(lockKey,userId, Duration.ofMinutes(10));

        if (Boolean.TRUE.equals(acquired)) {
            log.info("Lock acquired by user {} for seat {} in event {}", userId, seatId, eventId);
            return true;
        } else {
            log.warn("Lock failed! Seat {} already locked.", seatId);
            return false;
        }
    }

    public void unlockSeat(String eventId, String seatId, String userId) {
        String lockKey = "lock:event:" + eventId + ":seat:" + seatId;

        // Before unlocking, we MUST verify the user requesting the unlock actually owns it!
        String owner = redisTemplate.opsForValue().get(lockKey);

        if (userId.equals(owner)) {
            redisTemplate.delete(lockKey);
            log.info("Lock released for seat {}", seatId);
        }
    }
}
