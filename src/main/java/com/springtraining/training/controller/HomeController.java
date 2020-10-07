package com.springtraining.training.controller;

import com.springtraining.training.model.LocationStats;
import com.springtraining.training.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> locationStats = coronaVirusDataService.getLocationStats();
        int totalReportedCases = locationStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = locationStats.stream().mapToInt(stat -> stat.getDiffFromPreviousDay()).sum();
        model.addAttribute("locationSats", locationStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);


        return "home";
    }
}
