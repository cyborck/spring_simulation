package com.cyborck.spring;

import com.cyborck.spring.world.Anchor;
import com.cyborck.spring.world.PhysicsObjekt;
import com.cyborck.spring.world.WorldObject;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SpringTest {
    private static final int WIDTH = 2000;
    private static final int HEIGHT = 2000;

    private final List<WorldObject> objects;
    private final List<Spring> springs;
    private final Gui gui;

    private LocalTime lastUpdate;

    public SpringTest () {
        objects = new ArrayList<>();
        springs = new ArrayList<>();

        test1();

        gui = new Gui( WIDTH, HEIGHT, objects, springs );

        startSim();
    }

    public static void main ( String[] args ) {
        System.setProperty( "sun.java2d.uiScale", "1.0" );
        new SpringTest();
    }

    private void test10 () {
        //net with fixed edges
        //vibrating net: k=10
        int size = 10;

        WorldObject[][] nodes = new WorldObject[ size ][ size ];
        for ( int i = 1; i < size - 1; i++ ) {
            for ( int j = 1; j < size - 1; j++ ) {
                int x = ( int ) Math.round( ( j + 1 ) * WIDTH / ( double ) ( size + 1 ) );
                int y = ( int ) Math.round( ( i + 1 ) * HEIGHT / ( double ) ( size + 1 ) );

                nodes[ i ][ j ] = new PhysicsObjekt( new Vector( x, y ), 1 );
            }
        }
        for ( int i = 0; i < size; i++ ) {
            int x, y;

            x = ( int ) Math.round( ( i + 1 ) * WIDTH / ( double ) ( size + 1 ) );
            y = ( int ) Math.round( HEIGHT / ( double ) ( size + 1 ) );
            nodes[ 0 ][ i ] = new Anchor( new Vector( x, y ) );

            y = ( int ) Math.round( size * HEIGHT / ( double ) ( size + 1 ) );
            nodes[ size - 1 ][ i ] = new Anchor( new Vector( x, y ) );

            x = ( int ) Math.round( WIDTH / ( double ) ( size + 1 ) );
            y = ( int ) Math.round( ( i + 1 ) * HEIGHT / ( double ) ( size + 1 ) );
            nodes[ i ][ 0 ] = new Anchor( new Vector( x, y ) );

            x = ( int ) Math.round( size * WIDTH / ( double ) ( size + 1 ) );
            nodes[ i ][ size - 1 ] = new Anchor( new Vector( x, y ) );
        }

        int restLength = ( int ) Math.round( WIDTH / ( double ) ( size + 1 ) / 5d );
        double k = 0.1;
        Spring[][] horizontalSprings = new Spring[ size ][ size - 1 ];
        for ( int i = 0; i < size; i++ )
            for ( int j = 0; j < size - 1; j++ )
                horizontalSprings[ i ][ j ] = new Spring( restLength, k, nodes[ i ][ j ], nodes[ i ][ j + 1 ] );
        Spring[][] verticalSprings = new Spring[ size - 1 ][ size ];
        for ( int i = 0; i < size - 1; i++ )
            for ( int j = 0; j < size; j++ )
                verticalSprings[ i ][ j ] = new Spring( restLength, k, nodes[ i ][ j ], nodes[ i + 1 ][ j ] );

        for ( WorldObject[] o: nodes )
            objects.addAll( Arrays.asList( o ) );

        for ( Spring[] s: horizontalSprings )
            springs.addAll( Arrays.asList( s ) );
        for ( Spring[] s: verticalSprings )
            springs.addAll( Arrays.asList( s ) );
    }

    private void test9 () {
        //net with fixed corners
        int size = 10;

        WorldObject[][] nodes = new WorldObject[ size ][ size ];
        for ( int i = 0; i < size; i++ ) {
            for ( int j = 0; j < size; j++ ) {
                int x = ( int ) Math.round( ( j + 1 ) * WIDTH / ( double ) ( size + 1 ) );
                int y = ( int ) Math.round( ( i + 1 ) * HEIGHT / ( double ) ( size + 1 ) );

                nodes[ i ][ j ] = new PhysicsObjekt( new Vector( x, y ), 1 );
            }
        }
        int x = ( int ) Math.round( WIDTH / ( double ) ( size + 1 ) );
        int y = ( int ) Math.round( HEIGHT / ( double ) ( size + 1 ) );
        nodes[ 0 ][ 0 ] = new Anchor( new Vector( x, y ) );
        x = ( int ) Math.round( size * WIDTH / ( double ) ( size + 1 ) );
        nodes[ 0 ][ size - 1 ] = new Anchor( new Vector( x, y ) );
        y = ( int ) Math.round( size * HEIGHT / ( double ) ( size + 1 ) );
        nodes[ size - 1 ][ size - 1 ] = new Anchor( new Vector( x, y ) );
        x = ( int ) Math.round( WIDTH / ( double ) ( size + 1 ) );
        nodes[ size - 1 ][ 0 ] = new Anchor( new Vector( x, y ) );

        int restLength = ( int ) Math.round( WIDTH / ( double ) ( size + 1 ) / 5d );
        double k = 1;
        Spring[][] horizontalSprings = new Spring[ size ][ size - 1 ];
        for ( int i = 0; i < size; i++ )
            for ( int j = 0; j < size - 1; j++ )
                horizontalSprings[ i ][ j ] = new Spring( restLength, k, nodes[ i ][ j ], nodes[ i ][ j + 1 ] );
        Spring[][] verticalSprings = new Spring[ size - 1 ][ size ];
        for ( int i = 0; i < size - 1; i++ )
            for ( int j = 0; j < size; j++ )
                verticalSprings[ i ][ j ] = new Spring( restLength, k, nodes[ i ][ j ], nodes[ i + 1 ][ j ] );

        for ( WorldObject[] o: nodes )
            objects.addAll( Arrays.asList( o ) );

        for ( Spring[] s: horizontalSprings )
            springs.addAll( Arrays.asList( s ) );
        for ( Spring[] s: verticalSprings )
            springs.addAll( Arrays.asList( s ) );
    }

    private void test8 () {
        //hanging net
        int size = 10;

        WorldObject[][] nodes = new WorldObject[ size ][ size ];
        for ( int i = 0; i < size; i++ ) {
            for ( int j = 0; j < size; j++ ) {
                int x = ( int ) Math.round( ( j + 1 ) * WIDTH / ( double ) ( size + 1 ) );
                int y = ( int ) Math.round( ( i + 1 ) * HEIGHT / ( double ) ( size + 1 ) );

                nodes[ i ][ j ] = new PhysicsObjekt( new Vector( x, y ), 1 );
            }
        }
        int x = ( int ) Math.round( WIDTH / ( double ) ( size + 1 ) );
        int y = ( int ) Math.round( HEIGHT / ( double ) ( size + 1 ) );
        nodes[ 0 ][ 0 ] = new Anchor( new Vector( x, y ) );
        x = ( int ) Math.round( size * WIDTH / ( double ) ( size + 1 ) );
        nodes[ 0 ][ size - 1 ] = new Anchor( new Vector( x, y ) );

        int restLength = ( int ) Math.round( WIDTH / ( double ) ( size + 1 ) / 2d );
        double k = 0.3;
        Spring[][] horizontalSprings = new Spring[ size ][ size - 1 ];
        for ( int i = 0; i < size; i++ )
            for ( int j = 0; j < size - 1; j++ )
                horizontalSprings[ i ][ j ] = new Spring( restLength, k, nodes[ i ][ j ], nodes[ i ][ j + 1 ] );
        Spring[][] verticalSprings = new Spring[ size - 1 ][ size ];
        for ( int i = 0; i < size - 1; i++ )
            for ( int j = 0; j < size; j++ )
                verticalSprings[ i ][ j ] = new Spring( restLength, k, nodes[ i ][ j ], nodes[ i + 1 ][ j ] );

        for ( WorldObject[] o: nodes )
            objects.addAll( Arrays.asList( o ) );

        for ( Spring[] s: horizontalSprings )
            springs.addAll( Arrays.asList( s ) );
        for ( Spring[] s: verticalSprings )
            springs.addAll( Arrays.asList( s ) );
    }

    private void test7 () {
        //weird
        Anchor a1 = new Anchor( new Vector( 100, 100 ) );
        Anchor a2 = new Anchor( new Vector( WIDTH - 100, 100 ) );

        PhysicsObjekt[] weights = new PhysicsObjekt[ 3 ];
        for ( int i = 0; i < weights.length; i++ ) {
            double x = ( i + 1 ) * WIDTH / ( double ) ( weights.length + 1 );
            double y = ( i + 1 ) * HEIGHT / ( double ) ( weights.length + 1 );
            Vector position = new Vector( x, y );
            weights[ i ] = new PhysicsObjekt( position, new Random().nextInt( 5 ) + 1 );
        }
        PhysicsObjekt w1 = new PhysicsObjekt( new Vector( WIDTH / 2d, HEIGHT - 100 ), 0.1 );

        Spring first = new Spring( 300, 0.01, a1, weights[ 0 ] );
        Spring last = new Spring( 300, 0.01, weights[ weights.length - 1 ], a2 );
        Spring[] others = new Spring[ weights.length - 1 ];
        for ( int i = 0; i < others.length; i++ )
            others[ i ] = new Spring( 100, 0.01, weights[ i ], weights[ i + 1 ] );
        Spring s1 = new Spring( 1000, 0.1, a1, weights[ weights.length - 1 ] );
        Spring s2 = new Spring( 1000, 0.1, weights[ 0 ], a2 );
        Spring s3 = new Spring( 100, 0.01, weights[ weights.length / 2 ], w1 );

        objects.add( a1 );
        objects.add( a2 );
        objects.addAll( Arrays.asList( weights ) );
        objects.add( w1 );
        springs.add( first );
        springs.add( last );
        springs.addAll( Arrays.asList( others ) );
        springs.add( s1 );
        springs.add( s2 );
        springs.add( s3 );
    }

    private void test6 () {
        //chain
        Anchor a1 = new Anchor( new Vector( 100, 100 ) );
        Anchor a2 = new Anchor( new Vector( WIDTH - 100, 100 ) );

        PhysicsObjekt[] weights = new PhysicsObjekt[ 5 ];
        for ( int i = 0; i < weights.length; i++ )
            weights[ i ] = new PhysicsObjekt( new Vector( ( i + 1 ) * WIDTH / ( double ) ( weights.length + 1 ), ( i + 1 ) * HEIGHT / ( double ) ( weights.length + 1 ) ), Math.random() * 5 );

        Spring first = new Spring( 300, 0.01, a1, weights[ 0 ] );
        Spring last = new Spring( 300, 0.01, weights[ weights.length - 1 ], a2 );
        Spring[] others = new Spring[ weights.length - 1 ];
        for ( int i = 0; i < others.length; i++ )
            others[ i ] = new Spring( 100, 0.01, weights[ i ], weights[ i + 1 ] );

        objects.add( a1 );
        objects.add( a2 );
        objects.addAll( Arrays.asList( weights ) );
        springs.add( first );
        springs.add( last );
        springs.addAll( Arrays.asList( others ) );
    }

    private void test5 () {
        //triangle
        Anchor a1 = new Anchor( new Vector( WIDTH / 2d, 0 ) );

        PhysicsObjekt w1 = new PhysicsObjekt( new Vector( WIDTH / 3d, 900 ), 1 );
        PhysicsObjekt w2 = new PhysicsObjekt( new Vector( 2 * WIDTH / 3d, 900 ), 1 );

        Spring s1 = new Spring( 1000, 0.01, a1, w1 );
        Spring s2 = new Spring( 1000, 0.01, a1, w2 );
        Spring s3 = new Spring( 1000, 0.01, w1, w2 );

        objects.add( a1 );
        objects.add( w1 );
        objects.add( w2 );
        springs.add( s1 );
        springs.add( s2 );
        springs.add( s3 );
    }

    private void test4 () {
        //2 weights connected
        Anchor a1 = new Anchor( new Vector( WIDTH / 3d, 0 ) );
        Anchor a2 = new Anchor( new Vector( 2 * WIDTH / 3d, 0 ) );

        PhysicsObjekt w1 = new PhysicsObjekt( new Vector( WIDTH / 3d, 900 ), 1 );
        PhysicsObjekt w2 = new PhysicsObjekt( new Vector( 2 * WIDTH / 3d, 900 ), 1 );

        Spring s1 = new Spring( 1000, 0.01, a1, w1 );
        Spring s2 = new Spring( 1000, 0.01, a2, w2 );
        Spring s3 = new Spring( 1000, 0.01, w1, w2 );

        objects.add( a1 );
        objects.add( a2 );
        objects.add( w1 );
        objects.add( w2 );
        springs.add( s1 );
        springs.add( s2 );
        springs.add( s3 );
    }

    private void test3 () {
        //two springs
        Anchor a1 = new Anchor( new Vector( WIDTH / 3d, 0 ) );
        Anchor a2 = new Anchor( new Vector( 2 * WIDTH / 3d, 0 ) );

        PhysicsObjekt w1 = new PhysicsObjekt( new Vector( WIDTH / 3d, 900 ), 1 );
        PhysicsObjekt w2 = new PhysicsObjekt( new Vector( 2 * WIDTH / 3d, 900 ), 1 );

        Spring s1 = new Spring( 1000, 0.01, a1, w1 );
        Spring s2 = new Spring( 10, 0.01, a2, w2 );

        objects.add( a1 );
        objects.add( a2 );
        objects.add( w1 );
        objects.add( w2 );
        springs.add( s1 );
        springs.add( s2 );
    }

    private void test2 () {
        //single spring at bottom
        Anchor a1 = new Anchor( new Vector( WIDTH / 2d, HEIGHT ) );
        PhysicsObjekt w1 = new PhysicsObjekt( new Vector( WIDTH / 2d, 900 ), 5 );
        Spring s1 = new Spring( 1000, 0.01, a1, w1 );

        objects.add( a1 );
        objects.add( w1 );
        springs.add( s1 );
    }

    private void test1 () {
        //single spring
        Anchor a1 = new Anchor( new Vector( WIDTH / 2d, 0 ) );
        PhysicsObjekt w1 = new PhysicsObjekt( new Vector( WIDTH / 2d, 900 ), 1 );
        Spring s1 = new Spring( 1000, 0.01, a1, w1 );

        objects.add( a1 );
        objects.add( w1 );
        springs.add( s1 );
    }

    private void startSim () {
        while ( true ) update();
    }

    private void update () {
        LocalTime now = LocalTime.now();

        if ( lastUpdate != null ) {
            double delta = lastUpdate.until( now, ChronoUnit.NANOS ) * Math.pow( 10, -9 ); //seconds
            if ( delta != 0 ) {
                gui.getContent().repaint();

                for ( Spring s: springs )
                    s.update( delta );
                for ( WorldObject o: objects )
                    o.update( delta );
            }
        }

        lastUpdate = now;
    }
}
