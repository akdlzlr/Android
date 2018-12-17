package com.example.student.mymovieapp.format;

/**
 * Created by student on 2018-12-14.
 */

public class BookData {
    String movieStartTime;
    int movieTotalSeat;
    int movieBookSeat;
    String moviePlayTime;
    String movieName;
    String movieDate;

    public BookData(String movieStartTime, int movieTotalSeat, int movieBookSeat,
                    String moviePlayTime, String movieName, String movieDate) {
        this.movieStartTime = movieStartTime;
        this.movieTotalSeat = movieTotalSeat;
        this.movieBookSeat = movieBookSeat;
        this.moviePlayTime = moviePlayTime;
        this.movieName = movieName;
        this.movieDate = movieDate;
    }

    public String getMovieStartTime() {
        return movieStartTime;
    }

    public void setMovieStartTime(String movieStartTime) {
        this.movieStartTime = movieStartTime;
    }

    public int getMovieTotalSeat() {
        return movieTotalSeat;
    }

    public void setMovieTotalSeat(int movieTotalSeat) {
        this.movieTotalSeat = movieTotalSeat;
    }

    public int getMovieBookSeat() {
        return movieBookSeat;
    }

    public void setMovieBookSeat(int movieBookSeat) {
        this.movieBookSeat = movieBookSeat;
    }

    public String getMoviePlayTime() {
        return moviePlayTime;
    }

    public void setMoviePlayTime(String moviePlayTime) {
        this.moviePlayTime = moviePlayTime;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(String movieDate) {
        this.movieDate = movieDate;
    }
}
