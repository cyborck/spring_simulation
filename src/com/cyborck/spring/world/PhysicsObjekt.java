package com.cyborck.spring.world;

import com.cyborck.spring.Vector;

import java.awt.*;

public class PhysicsObjekt extends WorldObject {
    private static final double DENSITY = 0.001; //mass / area
    private static final Vector GRAVITY = new Vector( 0, 1 );

    private Vector velocity; //units / seconds
    private Vector acceleration;
    private double mass;

    public PhysicsObjekt ( Vector position, double mass ) {
        super( position );
        this.position = position;
        this.mass = mass;
        velocity = new Vector( 0, 0 );
        acceleration = new Vector( 0, 0 );
    }

    @Override
    public void applyForce ( Vector force ) {
        acceleration.add( Vector.divide( force, mass ) );
    }

    @Override
    public void update ( double delta ) {
        //air resistance
        velocity.multiply( 0.9995 );

        //add gravity
        Vector gravity = Vector.multiply( GRAVITY, mass * delta );
        applyForce( gravity );

        //move
        velocity.add( acceleration );
        position.add( velocity );
        acceleration = new Vector( 0, 0 );
    }

    @Override
    public void draw ( Graphics2D graphics ) {
        double area = mass / DENSITY;
        double radius = Math.sqrt( area / Math.PI );

        int roundedX = ( int ) Math.round( position.getX() - radius );
        int roundedY = ( int ) Math.round( position.getY() - radius );
        int roundedSize = ( int ) Math.round( radius * 2 );

        graphics.setColor( Color.BLACK );
        graphics.fillOval( roundedX, roundedY, roundedSize, roundedSize );
    }

    public Vector getVelocity () {
        return velocity;
    }

    public void setVelocity ( Vector velocity ) {
        this.velocity = velocity;
    }

    public double getMass () {
        return mass;
    }

    public void setMass ( double mass ) {
        this.mass = mass;
    }
}
