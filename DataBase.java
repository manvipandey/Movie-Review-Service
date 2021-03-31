import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;

class DataBase {

	ArrayList<User> users = new ArrayList<>();
	ArrayList<Movie> movies = new ArrayList<>();

	void addUsers(String userName) {
		users.add(new User(userName));
	}

	void addMovie(String title, String year, String... genres) {
		movies.add(new Movie(title, year, genres));
	}

	void addMovieReview(String userName, String movieName, int score) {
		Movie movieObject = null;
		boolean flag = false;
		for (Movie movie : movies) {
			if (movie.title.equals(movieName)) {
				if (Integer.parseInt(movie.year) >= 2021) {
					System.out.println("Movie yet to be released !");
					return;
				} else {
					movieObject = movie;
					break;
				}
			}
		}
		for (User user : users) {
			if (user.name.equals(userName)) {
				if (!user.movieAndScore.containsKey(movieName)) {
					int currScore = user.addReview(movieName, score);
					if (currScore == score)
						movieObject.viewerScore += currScore;
					else
						movieObject.criticScore += currScore;
					System.out.println("Review added!");
				} else {
					System.out.println("Multiple reviews not allowed!");
				}
				flag = true;
				break;
			}
		}
		if (!flag)
			System.out.println("User not found. Try adding user first.");

	}

	HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {

		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

	void topNMoviesByCriticReviewScoreInGivenGenre(int n, String genre) {
		HashMap<String, Integer> moviesByGenre = new HashMap<>();
		for (Movie movie : movies) {
			if (movie.genres.contains(genre) && movie.criticScore > 0) {
				moviesByGenre.put(movie.title, movie.criticScore);
			}
		}
		if (moviesByGenre.size() == 0) {
			System.out.println("No movies available!");
			return;
		}
		moviesByGenre = sortByValue(moviesByGenre);
		int counter = 0;
		for (String s : moviesByGenre.keySet()) {
			System.out.printf("%-20s %-20s %n", s, moviesByGenre.get(s));
			counter++;
			if (counter == n)
				return;
		}
	}

	float avgReviewScoreOfAMovie(String movieName) {
		int score = 0;
		int count = 0;
		for (User user : users) {
			if (user.movieAndScore.containsKey(movieName)) {
				count++;
				if (user.movieAndScore.get(movieName).containsKey("viewer"))
					score += (int) user.movieAndScore.get(movieName).get("viewer");
				else
					score += (int) user.movieAndScore.get(movieName).get("critic");
			}
		}
		if (count > 0)
			return (score / count);
		else
			System.out.println("No reviews are available for this movie !");
		return -1;
	}

	void avgReviewScoreInAYear(String year) {
		int count = 0;
		float score = 0;
		for (Movie movie : movies) {
			if (movie.year.equals(year)) {
				float movieScore = avgReviewScoreOfAMovie(movie.title);
				if (movieScore != -1) {
					score += movieScore;
					count++;
				}
			}
		}
		if (count > 0)
			System.out.println(score / count);
		else
			System.out.println("No movie available !");
	}

	void displayUsersWithStatus() {
		for (User user : users) {
			System.out.printf("%-20s %-20s %n", user.name, user.status);
		}
	}

	void displayAllReviewsByAUser(String username) {
		boolean flag = false;
		for (User user : users) {
			if (user.name.equals(username)) {
				if (user.movieAndScore.size() > 0) {
					System.out.printf("%-20s %-20s %-20s %n", "Movie Name", "Status", "Score");
					user.movieAndScore.forEach((movieName, innerMap) -> {
						innerMap.forEach((status, score) -> {
							System.out.printf("%-20s %-20s %-20s %n", movieName, status, score);
						});
					});
				} else {
					System.out.println("No reviews by this user.");
				}
				flag = true;
			}
		}
		if (!flag)
			System.out.println("User not found !");
	}

}
