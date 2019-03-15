package org.pursuit.feedbackfaircodechallenge.model;

public final class User {
     public GeoLocation geo;
     public String name;
     public String email;
     public String phone;

    public User(String name) {
        this.name = name;
    }

    public User(GeoLocation geo, String name, String email, String phone) {
        this.geo = geo;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
