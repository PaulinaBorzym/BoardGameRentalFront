package com.project.boardgamerentalfront.domain;

import java.time.LocalDate;
import java.util.Objects;

public class BookCall {
    private Long bookCallId;
    private LocalDate bookDate;
    private String phoneNumber;
    private String title;

    public BookCall() {
    }

    public BookCall(LocalDate bookDate, String phoneNumber, String title) {
        this.bookDate = bookDate;
        this.phoneNumber = phoneNumber;
        this.title = title;
    }

    public BookCall(Long bookCallId, LocalDate bookDate, String phoneNumber, String title) {
        this.bookCallId = bookCallId;
        this.bookDate = bookDate;
        this.phoneNumber = phoneNumber;
        this.title = title;
    }

    public Long getBookCallId() {
        return bookCallId;
    }

    public void setBookCallId(Long bookCallId) {
        this.bookCallId = bookCallId;
    }

    public LocalDate getBookDate() {
        return bookDate;
    }

    public void setCallDate(LocalDate callDate) {
        this.bookDate = callDate;
    }

    public void setBookDate(LocalDate bookDate) {
        this.bookDate = bookDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCall bookCall = (BookCall) o;
        return Objects.equals(bookCallId, bookCall.bookCallId) && Objects.equals(bookDate, bookCall.bookDate) &&
                Objects.equals(phoneNumber, bookCall.phoneNumber) && Objects.equals(title, bookCall.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookCallId, bookDate, phoneNumber, title);
    }
}
