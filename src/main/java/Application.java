import java.io.IOException;
import java.util.List;
import java.util.Scanner;


import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;

import map.directions.*;

public class Application {

	public static void main(String[] args) throws IOException, ApiException{
		// TODO Auto-generated method stub
		
		Scanner sc= new Scanner(System.in);  
		System.out.print("Enter lattitude of source:");
		double lat1= sc.nextDouble();
		System.out.print("Enter longitude of source:");
		double lng1= sc.nextDouble(); 
		System.out.print("Enter lattitude of destination:");
		double lat2= sc.nextDouble();
		System.out.print("Enter longitude of destination:");  
		double lng2= sc.nextDouble();
		sc.close();
		
		//building direction api context with API Key
		GeoApiContext context = SingletonContext.getContext(args[0], args[1]).context;
		MapMarker marker = new MapMarker(context, new com.google.maps.model.LatLng(lat1, lng1), new com.google.maps.model.LatLng(lat2, lng2));
		List<LatLng> resultCoordinates = marker.getResults(50);

		// Invoking shutdown after application is done making requests
		context.shutdown();
		
		for(LatLng coordinate: resultCoordinates)
			System.out.println(coordinate);

	}
}
