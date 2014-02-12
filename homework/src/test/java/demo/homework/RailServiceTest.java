package demo.homework;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RailServiceTest {

	private RailService railService;

	@Before
	public void initialize() {
		railService = new RailServiceImpl();
	}

	@Test
	public void testAddPath() {
		railService.addPath("AB1");
		assertEquals("AB1", railService.getAllPaths());
	}

	@Test
	public void testAddPath2() {
		railService.addPath("AB1", "BC1");
		assertEquals("AB1,BC1", railService.getAllPaths());
	}

	@Test
	public void testAddPath3() {
		railService.addPath("AB1", "BC1", "AB2");
		assertEquals("AB2,BC1", railService.getAllPaths());
	}

	@Test
	public void testAddPath4() {
		railService.addPath("AB1", "BC1", "AB2", "BA3");
		assertEquals("AB2,BA3,BC1", railService.getAllPaths());
	}

	@Test
	public void testAddPathExample() {
		railService.addPath("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2",
				"EB3", "AE7");
		assertEquals("AD5,AE7,AB5,BC4,CD8,CE2,DE6,DC8,EB3",
				railService.getAllPaths());
	}

	@Test
	public void testCalculateDistance() {
		railService.addPath("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2",
				"EB3", "AE7");

		assertEquals(Integer.valueOf(9), railService.calculateDistance("ABC"));
		assertEquals(Integer.valueOf(5), railService.calculateDistance("AD"));
		assertEquals(Integer.valueOf(13), railService.calculateDistance("ADC"));
		assertEquals(Integer.valueOf(22),
				railService.calculateDistance("AEBCD"));
		assertEquals(null, railService.calculateDistance("AED"));

	}

	@Test
	public void testSearch() {
		railService.addPath("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2",
				"EB3", "AE7", "DF1", "FG1", "GF1", "GH1", "HF1");

		List<Way> search = railService.search("B", "E", new Condition<Way>() {

			public boolean needed(Way way) {
				return way.stops() < 4;
			}

			public boolean apply(Way way) {
				return way.getPath().endsWith("E") && needed(way);
			}
		});

		assertEquals(2, search.size());

		for (Way way : search) {
			System.out.println(way.getPath() + " : " + way.getDistance());
		}

	}

}
