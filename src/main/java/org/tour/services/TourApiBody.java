package org.tour.services;

import java.util.List;

import lombok.*;

@Getter @Setter @ToString
public class TourApiBody {
	
	private TourApiItems items;
	private int numOfRows;
	private int pageNo;
	private int totalCount;
}
