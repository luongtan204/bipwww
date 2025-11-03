package iuh.fit.stt_luongminhtan_22677941_may20.controllers;

import iuh.fit.stt_luongminhtan_22677941_may20.entities.BusRoute;
import iuh.fit.stt_luongminhtan_22677941_may20.services.BusRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/busroute")
public class BusRouteController {

    @Autowired
    private final BusRouteService busRouteService;

    public BusRouteController(BusRouteService busRouteService) {
        this.busRouteService = busRouteService;
    }

    // ✅ Trang xem danh sách tuyến
    @GetMapping
    public String showAllRoutes(Model model) {
        List<BusRoute> routeList = busRouteService.findAll();
        model.addAttribute("routes", routeList);
        return "busroute/list";   // Trỏ tới file templates/busroute/list.html
    }

    // ✅ Trang xem chi tiết tuyến theo ID
    @GetMapping("/{id}")
    public String showRouteDetail(@PathVariable int id, Model model) {
        BusRoute route = busRouteService.findById(id);
        model.addAttribute("route", route);
        return "busroute/detail"; // Trỏ tới file templates/busroute/detail.html
    }

    // ====== CREATE ======
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("route", new BusRoute());
        return "busroute/add";
    }

    @PostMapping("/add")
    public String handleAdd(@ModelAttribute("route") BusRoute route) {
        busRouteService.save(route);
        return "redirect:/busroute";
    }

    // ====== UPDATE ======
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("route", busRouteService.findById(id));
        return "busroute/edit";
    }

    @PostMapping("/{id}/edit")
    public String handleEdit(@PathVariable int id,
                             @ModelAttribute("route") BusRoute route) {
        route.setId(id);                // đảm bảo đúng id
        busRouteService.save(route);    // save = insert/update
        return "redirect:/busroute/" + id;
    }

    // ====== DELETE ======
    @PostMapping("/{id}/delete")
    public String handleDelete(@PathVariable int id) {
        busRouteService.deleteById(id);
        return "redirect:/busroute";
    }

}
