package org.tour.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tour.services.TourApiItem;
import org.tour.services.TourInfoService;

@RestController
public class TourInfoController {
	
	@Autowired
	private TourInfoService service;
	
	@GetMapping("/tour_info")
	public List<TourApiItem> process(double lng, double lat, int radius) {
		List<TourApiItem> items = service.process(lng, lat, radius);
		
		return items;
	}
}
