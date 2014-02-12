package demo.homework;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ChallengeTest {

	private RailService railService;
	
	@Before
	public void initialize() {
		railService = new RailServiceImpl();
		railService.addPath("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7");
	}

	@Test
	public void test_1to5() {
		
		assertEquals(Integer.valueOf(9), railService.calculateDistance("ABC"));
		assertEquals(Integer.valueOf(5), railService.calculateDistance("AD"));
		assertEquals(Integer.valueOf(13), railService.calculateDistance("ADC"));
		assertEquals(Integer.valueOf(22), railService.calculateDistance("AEBCD"));
		assertNull("Way should not exist.", railService.calculateDistance("AED")); //"SIN RUTA"

	}

	@Test
	public void test_6() {
		
		List<Way> search = railService.search("C", "C", new Condition<Way>() {
			
			public boolean needed(Way way) {
				return way.stops() < 4;
			}

			public boolean apply(Way way) {
				return way.getPath().endsWith("C");
			}
		});
		
		assertNotNull(search);
		assertEquals(2, search.size());
		
		print(search);

	}

	@Test
	public void test_7() {
		
		List<Way> search = railService.search("A", "C", new Condition<Way>() {
			
			public boolean needed(Way way) {
				return way.stops() < 5;
			}

			public boolean apply(Way way) {
				return way.getPath().endsWith("C") && way.stops() == 4;
			}
		});
		
		assertNotNull(search);
		assertEquals(3, search.size());
		
		print(search);

	}
	
	@Test
	public void test_8() {
		
		Way way = railService.searchShortest("A", "C");
		
		assertNotNull(way);
		assertEquals(9, (int)way.getDistance());
		
		print(way);

	}

	@Test
	public void test_9() {
		
		Way way = railService.searchShortest("B", "B");
		
		assertNotNull(way);
		assertEquals(9, (int)way.getDistance());
		
		print(way);

	}

	@Test
	public void test_10() {
		
		List<Way> search = railService.search("C", "C", new Condition<Way>() {
			
			public boolean needed(Way way) {
				return way.getDistance() < 30;
			}

			public boolean apply(Way way) {
				return way.getPath().endsWith("C");
			}
		});
		
		assertNotNull(search);
		assertEquals(7, search.size());
		
		print(search);

	}

	private void print(List<Way> search) {
		for (Way way : search)
			print(way);
	}

	private void print(Way way) {
		System.out.println(way.getPath()+" : "+way.getDistance());
	}

}
