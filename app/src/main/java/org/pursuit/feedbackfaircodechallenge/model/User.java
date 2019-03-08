package org.pursuit.feedbackfaircodechallenge.model;

public final class User {
    final public GeoLocation geo;
    final public String name;
    final public String email;
    final public String phone;

    public User(GeoLocation geo, String name, String email, String phone) {
        this.geo = geo;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
