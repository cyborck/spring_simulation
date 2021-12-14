package com.cyborck.spring;

import com.cyborck.spring.world.WorldObject;

import java.awt.*;

public class Spring {
    private double restLength;
    private double k;

    private WorldObject a;
    private WorldObject b;

    public Spring ( double restLength, double k, WorldObject a, WorldObject b ) {
        this.restLength = restLength;
        this.k = k;
        this.a = a;
        this.b = b;
    }

    public void update ( double delta ) {
        Vector dir = Vector.subtract( a.getPosition(), b.getPosition() );
        double stretchDistance = dir.getMagnitude() - restLength;

        Vector force = Vector.multiply( Vector.normalize( dir ), k * stretchDistance );
        force.multiply( delta );
        a.applyForce( force );
        force.multiply( -1 );
        b.applyForce( force );
    }

    public void draw ( Graphics2D g ) {
        Vector aPos = a.getPosition();
        Vector bPos = b.getPosition();

        int x1 = ( int ) Math.round( aPos.getX() );
        int y1 = ( int ) Math.round( aPos.getY() );
        int x2 = ( int ) Math.round( bPos.getX() );
        int y2 = ( int ) Math.round( bPos.getY() );

        g.setStroke( new BasicStroke( 5 ) );
        g.setColor( Color.GREEN );
        g.drawLine( x1, y1, x2, y2 );
    }

    public double getRestLength () {
        return restLength;
    }

    public void setRestLength ( double restLength ) {
        this.restLength = restLength;
    }

    public double getK () {
        return k;
    }

    public void setK ( double k ) {
        this.k = k;
    }

    public WorldObject getA () {
        return a;
    }

    public void setA ( WorldObject a ) {
        this.a = a;
    }

    public WorldObject getB () {
        return b;
    }

    public void setB ( WorldObject b ) {
        this.b = b;
    }
}
