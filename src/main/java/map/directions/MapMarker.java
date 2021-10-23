package map.directions;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

import utility.Coordinates;

public class MapMarker {
	
	private LatLng source;
	private LatLng destination;
	private GeoApiContext context;
	
	public MapMarker(GeoApiContext context, LatLng source, LatLng destination) {
		this.context = context;
		this.source = source;
		this.destination = destination;
	}
	
	private DirectionsApiRequest configureApi() {
		DirectionsApiRequest apiRequest = DirectionsApi.newRequest(context);
		apiRequest.origin(source);
		apiRequest.destination(destination);

		apiRequest.mode(TravelMode.WALKING); //setting traveling mode
		return apiRequest;
	}
	
	public List<LatLng> getResults(double markingInterval) throws ApiException{

		DirectionsResult result=new DirectionsResult();
		try {
			result = configureApi().await();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<LatLng> resultCoordinates = new LinkedList<LatLng>();
		
		double intervalDist = markingInterval;
		
		resultCoordinates.add(result.routes[0].legs[0].startLocation);
	
		for(DirectionsStep step: result.routes[0].legs[0].steps) {
			List<LatLng> decodedPath = PolylineEncoding.decode(step.polyline.getEncodedPath());
			double dist = step.distance.inMeters;
			if(dist < intervalDist) {
				intervalDist -= dist;
				continue;
			}
			if(dist == intervalDist) {
				resultCoordinates.add(step.endLocation);
				continue;
			}
			LatLng startPoint = decodedPath.get(0);
			
			LatLng endPoint = null;
			for(int i=1; i<decodedPath.size(); i++) {
				endPoint = decodedPath.get(i);
				double pathDist = Coordinates.getDistance(startPoint.lat, startPoint.lng, endPoint.lat, endPoint.lng);
				if(pathDist<intervalDist) {
					intervalDist -= pathDist;
					startPoint = endPoint;
					continue;
				}
				while(pathDist>=intervalDist) {
					startPoint = Coordinates.getInterpolationPoint(startPoint.lat, startPoint.lng, endPoint.lat, endPoint.lng, intervalDist);
					pathDist -= intervalDist;
					resultCoordinates.add(startPoint);
					intervalDist=markingInterval;
				}
				intervalDist = markingInterval-pathDist;
				startPoint = endPoint;
			}
		}
		if(intervalDist < markingInterval)
			resultCoordinates.add(result.routes[0].legs[0].endLocation);
		return resultCoordinates;
	}

	public LatLng getSource() {
		return source;
	}

	public void setSource(LatLng source) {
		this.source = source;
	}

	public LatLng getDestination() {
		return destination;
	}

	public void setDestination(LatLng destination) {
		this.destination = destination;
	}

	public GeoApiContext getContext() {
		return context;
	}

	public void setContext(GeoApiContext context) {
		this.context = context;
	}
}
