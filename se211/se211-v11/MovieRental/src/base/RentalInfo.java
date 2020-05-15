package base;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RentalInfo {

    public double getMovieFreqRenterPts(Movie movie, MovieRental r) {
        // determine amount for each movie
        switch (movie.getCode()) {
            case Movie.Code.REGULAR:
                if (r.getDays() > 2) {
                    return (r.getDays() - 2) * 1.5 + 2;
                } else {
                    return 2;
                }
            case Movie.Code.NEW:
                return r.getDays() * 3;
            case Movie.Code.CHILDRENS:
                if (r.getDays() > 3) {
                    return (r.getDays() - 3) * 1.5 + 1.5;
                } else {
                    return 1.5;
                }
        }
    }

    public String statement(Customer customer) {
        HashMap<String, Movie> movies = new HashMap();
        movies.put("F001", new Movie("Ran", Movie.Code.NEW));
        movies.put("F002", new Movie("Trois Couleurs: Bleu", Movie.Code.REGULAR));
        movies.put("F003", new Movie("Cars 2", Movie.Code.CHILDRENS));
        movies.put("F004", new Movie("Latest Hit Release", Movie.Code.NEW));

        double totalAmount = 0;
        int frequentRenterPoints = 0;

        String result = String.format("Rental Record for %s\n", customer.getName());

        for (MovieRental r : customer.getRentals()) {
            Movie movie = movies.get(r.getMovieId());
            double thisAmount = getMovieFreqRenterPts(movie, r);

            //add frequent renter points
            // add bonus for a two day new release rental
            if (movie.getCode() == "new" && r.getDays() > 2) {
                frequentRenterPoints += 2;
            } else {
                frequentRenterPoints += 1;
            }

            //print figures for this rental
            result += String.format("\t%s\t%.2f\n", movie.getTitle(), thisAmount);
            totalAmount += thisAmount;
        }

        // add footer lines
        result += String.format("Amount owed is %.2f\n", totalAmount);
        result += String.format("You earned %d frequent renter points\n", frequentRenterPoints);

        return result;
    }
}
