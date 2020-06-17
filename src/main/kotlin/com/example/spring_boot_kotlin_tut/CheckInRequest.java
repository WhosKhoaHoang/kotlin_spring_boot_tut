package com.example.spring_boot_kotlin_tut;

// This is a Java class that can be used in the HotelController,
// which is defined in Kotlin. This illustrates the interoperability
// between Java and Kotlin code.

public class CheckInRequest {
  private String hotelName;
  private int numGuests;

  public String getHotelName() {
    return hotelName;
  }

  public void setHotelName(String hotelName) {
    this.hotelName = hotelName;
  }

  public int getNumGuests() {
    return numGuests;
  }

  public void setNumGuest(int numGuests) {
    this.numGuests = numGuests;
  }
}
