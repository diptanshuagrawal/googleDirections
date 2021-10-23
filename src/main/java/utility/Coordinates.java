package utility;

import com.google.maps.model.LatLng;

public class Coordinates {
	
	public Coordinates() {};
	
	public static LatLng getInterpolationPoint(double lat1, double lon1, double lat2, double lon2, double intervalDist) {
		double dist = getDistance(lat1,lon1,lat2,lon2);
		double ratio = (intervalDist/(dist));
		double x = (1-ratio)*lat1 + ratio*lat2;
		double y = lon1 + (ratio*(lon2-lon1));
		return new LatLng(x, y);
	}
	
	public static double getDistance(double lat1, double lon1, double lat2, double lon2) {
		  double theta = lon1 - lon2;
		  double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		  dist = Math.acos(dist);
		  dist = rad2deg(dist);
		  dist = dist * 60 * 1.1515;
		  dist = dist * 1.609344*1000;
		  return (dist);
		}

	public static double deg2rad(double deg) {
		  return (deg * Math.PI / 180.0);
		}

	public static double rad2deg(double rad) {
		  return (rad * 180.0 / Math.PI);
	}
}
