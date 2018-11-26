package nam.concertsintown.concertsintown.events.repository;

import nam.concertsintown.concertsintown.events.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    Optional<Event> findByUuid(String uuid);

    Page<Event> findByCityIgnoreCase(String city, Pageable pageable);

    void deleteByUuid(String uuid);
}
