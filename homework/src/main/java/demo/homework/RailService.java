package demo.homework;

import java.util.List;

public interface RailService {

	void addPath(String... paths);

	String getAllPaths();

	Integer calculateDistance(String string);

	List<Way> search(String from, String to, Condition<Way> condition);

	Way searchShortest(final String from, final String to);

}
