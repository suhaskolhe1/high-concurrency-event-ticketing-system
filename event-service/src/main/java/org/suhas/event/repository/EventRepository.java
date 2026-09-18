package org.suhas.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.suhas.event.domain.Event;

public interface EventRepository extends JpaRepository<Event, String> {}

