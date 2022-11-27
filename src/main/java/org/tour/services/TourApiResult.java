package org.tour.services;

import java.util.List;
import lombok.*;

@Getter @Setter @ToString
public class TourApiResult {
	private TourApiHeader header;
	private TourApiBody body;
}
