class Main {
	public static void main(String[] args) {
		DataBase db = new DataBase();

		db.addMovie("Don", "2006", "Action", "Comedy");
		db.addMovie("Tiger", "2008", "Drama");
		db.addMovie("Padmaavat", "2006", "Comedy");
		db.addMovie("Lunchbox", "2021", "Drama");
		db.addMovie("Guru", "2006", "Drama");
		db.addMovie("Metro", "2006", "Romance");
		db.addMovie("Veer Zaara", "2006", "Romance");
		db.addMovie("Dostana", "2008", "Romance");

		db.addUsers("SRK");
		db.addUsers("Salmaan");
		db.addUsers("Deepika");

		db.addMovieReview("SRK", "Don", 2);
		db.addMovieReview("SRK", "Padmaavat", 8);
		db.addMovieReview("Salmaan", "Don", 5);
		db.addMovieReview("Deepika", "Don", 9);
		db.addMovieReview("Deepika", "Guru", 6);
		db.addMovieReview("SRK", "Don", 10);
		db.addMovieReview("Deepika", "Lunchbox", 5);
		db.addMovieReview("SRK", "Tiger", 5);
		db.addMovieReview("SRK", "Metro", 7);
		db.addMovieReview("SRK", "Veer Zaara", 10);
		db.addMovieReview("SRK", "Dostana", 5);

		db.topNMoviesByCriticReviewScoreInGivenGenre(2, "Romance");

		System.out.println(db.avgReviewScoreOfAMovie("Don"));

		db.avgReviewScoreInAYear("2006");

		db.displayUsersWithStatus();

		db.displayAllReviewsByAUser("SRK");

		db.displayAllReviewsByAUser("Deepika");

		db.displayAllReviewsByAUser("Salmaan");

	}
}