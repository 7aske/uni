package base;

public class Movie {
    private final String title;
    private final Movie.Code code;

    public Movie(String title, Movie.Code code) {

        this.title = title;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public Movie.Code getCode() {
        return code;
    }

    public enum Code {
        NEW, CHILDRENS, REGULAR
    }
}
