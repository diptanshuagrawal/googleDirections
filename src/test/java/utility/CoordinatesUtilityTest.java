package utility;

import static org.junit.Assert.assertEquals;
import org.junit.Test;



public class CoordinatesUtilityTest {
	@Test
	public void testGetInterpolationPoints() {
		assertEquals("NaN,NaN",Coordinates.getInterpolationPoint(0, 0, 0, 0, 0).toString()); 
	}
	
	@Test
	public void testGetDistance() {
		assertEquals(0,Coordinates.getDistance(0, 0, 0, 0), 0.0001); 
	}
}
