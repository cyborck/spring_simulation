package com.cyborck.spring.world;

import com.cyborck.spring.Vector;

import java.awt.*;

public abstract class WorldObject {
    protected Vector position;

    public WorldObject ( Vector position ) {
        this.position = position;
    }

    public abstract void update ( double delta );

    public abstract void applyForce ( Vector force );

    public abstract void draw ( Graphics2D g );

    public Vector getPosition () {
        return position;
    }

    public void setPosition ( Vector position ) {
        this.position = position;
    }
}
