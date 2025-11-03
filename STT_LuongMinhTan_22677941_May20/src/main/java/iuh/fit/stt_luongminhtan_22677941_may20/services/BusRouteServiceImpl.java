package iuh.fit.stt_luongminhtan_22677941_may20.services;

import iuh.fit.stt_luongminhtan_22677941_may20.entities.BusRoute;
import iuh.fit.stt_luongminhtan_22677941_may20.reposities.BusRouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusRouteServiceImpl implements BusRouteService {

    private final BusRouteRepository repo;
    public BusRouteServiceImpl(BusRouteRepository repo) { this.repo = repo; }

    public List<BusRoute> findAll() { return repo.findAll(); }
    public BusRoute findById(int id) { return repo.findById(id).orElse(null); }
    public BusRoute save(BusRoute route) { return repo.save(route); }        // ⬅️
    public void deleteById(int id) { repo.deleteById(id); }
}
