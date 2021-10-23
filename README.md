## Location Simulator

The project takes source and destination coordinates as input. It returns a list of all the coordinates between the two given points at an interval of 50 metres.

#### Requirements

For building and running the application you need:
* JDK 8
* Maven 3

### Running the application locally:

* Execute the `main` method in `Application.Java` class  in your IDE, pass the parameter {env} {password for decryption}

* Password for decryption: LocusDiptanshu

#### Contents:

* `Application.properties` file present in resource folder for different environments. It currently contains the encrypted api key for Google directions api.

* `ApiConfig.java` reads the properties file based on the environment and returns the decrypted api key.

* `Security.java` utility class to encrypt/decrypt strings using encryption/decryption password. The class uses Jaspyt library for the purpose.

* `SingletonContext.java` is used to create a singleton instance of the GeoApiContext.

* `Coordinates.java` utility class provides methods to work on a pair of coordinates. Such as calculating distance between two points or getting an interpolated point at a fixed distance between two points.

* `MapMarker.java` is where the main logic for creating **markers** resides. It uses the java utlity dor directions api. Following is the logic flow:
	1. Build `DirectionsApiRequest` object with context and source and destination coordinates.
	2. Call **Google Directions Api**.
	3. Get **polylines data** for each step in the leg. (Each step is broken down into multiple coordinates to keep different angles of path into consideration)
	4. With the help of `Coordinates.java` utility class, interpolate the interval marker points.
	5. Return a list of marker points between source and destination.

* `MapMarkerTest.java` tests `MapMarker.java` class.

* `CoordinatesUtilityTest.java` tests `Coordinates.java` class.


