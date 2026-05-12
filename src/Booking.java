public class Booking {

    String movieName;
    int seatCount;

    Booking(String movieName, int seatCount) {
        this.movieName = movieName;
        this.seatCount = seatCount;
    }

    public String getDetails() {
        return "Movie: " + movieName + " | Seats: " + seatCount;
    }
}