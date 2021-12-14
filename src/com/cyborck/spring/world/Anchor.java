package com.cyborck.spring.world;

import com.cyborck.spring.Vector;

import java.awt.*;

public class Anchor extends WorldObject {
    private static final int SIZE = 30;

    public Anchor ( Vector position ) {
        super( position );
    }

    @Override
    public void update ( double delta ) {
        //do nothing
    }

    @Override
    public void applyForce ( Vector force ) {
        //do nothing
    }

    @Override
    public void draw ( Graphics2D g ) {
        int x = ( int ) Math.round( position.getX() - SIZE / 2d );
        int y = ( int ) Math.round( position.getY() - SIZE / 2d );

        g.setColor( Color.BLACK );
        g.fillRect( x, y, SIZE, SIZE );
    }
}
