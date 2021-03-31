import java.util.ArrayList;
import java.util.Arrays;

class Movie {
	String title;
	String year;
	int viewerScore = 0;
	int criticScore = 0;
	ArrayList<String> genres;

	Movie(String title, String year, String... genres) {
		this.title = title;
		this.year = year;
		this.genres = new ArrayList<>(Arrays.asList(genres));
	}

	void updateViewerScore(int score) {
		viewerScore += score;
	}

	void updateCriticScore(int score) {
		criticScore += score;
	}
}