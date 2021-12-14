package com.cyborck.spring;

public class Vector {
    private double x;
    private double y;

    public Vector () {
        x = 0;
        y = 0;
    }

    public Vector ( double x, double y ) {
        this.x = x;
        this.y = y;
    }

    public Vector ( Vector a, Vector b ) {
        x = b.getX() - a.getX();
        y = b.getY() - a.getY();
    }

    public static Vector normalize ( Vector v ) {
        return divide( v, v.getMagnitude() );
    }

    public static Vector add ( Vector v1, Vector v2 ) {
        double x = v1.getX() + v2.getX();
        double y = v1.getY() + v2.getY();
        return new Vector( x, y );
    }

    public static Vector subtract ( Vector v1, Vector v2 ) {
        double x = v2.getX() - v1.getX();
        double y = v2.getY() - v1.getY();
        return new Vector( x, y );
    }

    public static Vector multiply ( Vector v, double d ) {
        double x = v.getX() * d;
        double y = v.getY() * d;
        return new Vector( x, y );
    }

    public static Vector divide ( Vector v, double d ) {
        double x = v.getX() / d;
        double y = v.getY() / d;
        return new Vector( x, y );
    }

    public void normalize () {
        divide( getMagnitude() );
    }

    public void add ( Vector v ) {
        x += v.getX();
        y += v.getY();
    }

    public void subtract ( Vector v ) {
        x -= v.getX();
        y -= v.getY();
    }

    public void multiply ( double d ) {
        x *= d;
        y *= d;
    }

    public void divide ( double d ) {
        x /= d;
        y /= d;
    }

    public double getMagnitude () {
        return Math.sqrt( x * x + y * y );
    }

    public double getX () {
        return x;
    }

    public void setX ( double x ) {
        this.x = x;
    }

    public double getY () {
        return y;
    }

    public void setY ( double y ) {
        this.y = y;
    }
}
