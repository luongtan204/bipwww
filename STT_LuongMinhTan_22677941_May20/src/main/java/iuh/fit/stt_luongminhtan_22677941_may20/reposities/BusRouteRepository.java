package iuh.fit.stt_luongminhtan_22677941_may20.reposities;

import iuh.fit.stt_luongminhtan_22677941_may20.entities.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusRouteRepository extends JpaRepository<BusRoute, Integer> {
    List<BusRoute> findByNameContainingIgnoreCase(String name);
}
