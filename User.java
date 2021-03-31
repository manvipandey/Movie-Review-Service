import java.util.HashMap;

class User {
	String name;
	String status;
	HashMap<String, HashMap<String, Integer>> movieAndScore = new HashMap<>();

	User(String name) {
		this.name = name;
		this.status = "viewer";
	}

	int addReview(String movieTitle, int score) {
		if (status.equals("critic")) {
			score = 2 * score;
		}
		HashMap<String, Integer> innerMap = new HashMap<>();
		innerMap.put(status, score);
		movieAndScore.put(movieTitle, innerMap);
		if (movieAndScore.size() == 3) {
			status = "critic";
		}
		return score;
	}
}