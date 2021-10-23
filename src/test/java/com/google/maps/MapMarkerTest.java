package com.google.maps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import static com.google.maps.TestUtils.retrieveBody;

import map.directions.*;
import com.google.maps.model.LatLng;


public class MapMarkerTest {
	
	  private final String getDirectionsResponse;

	  public MapMarkerTest() {
	    getDirectionsResponse = retrieveBody("GetDirectionsResponse1.json");
	  }
	  
	@Test
	  public void testGetDirections() throws Exception {
	    try {
	      LocalTestServerContext sc = new LocalTestServerContext(getDirectionsResponse);
	      MapMarker marker = new MapMarker(sc.context, new LatLng(21.207341635612213, 79.06475912399799), new LatLng(21.18534274586657, 79.08316889885823));
	      List<LatLng> resultCoordinates = marker.getResults(50);
	      sc.close();
	      assertNotNull(resultCoordinates);
	      assertEquals(82, resultCoordinates.size());
	      assertEquals(21.20616014, resultCoordinates.get(5).lat, 0.0001);
	      assertEquals(79.06602963, resultCoordinates.get(5).lng, 0.0001);
	    }
	    finally {}
	  }
}
