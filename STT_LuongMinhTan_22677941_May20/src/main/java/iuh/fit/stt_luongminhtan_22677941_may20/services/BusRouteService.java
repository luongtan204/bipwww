package iuh.fit.stt_luongminhtan_22677941_may20.services;

import iuh.fit.stt_luongminhtan_22677941_may20.entities.BusRoute;

import java.util.List;

public interface BusRouteService {
    List<BusRoute> findAll();
    BusRoute findById(int id);
    BusRoute save(BusRoute route);       // ⬅️ thêm
    void deleteById(int id);             // ⬅️ thêm
    // Thêm phương thức tìm kiếm theo tên
    List<BusRoute> findByNameContaining(String name);
}
