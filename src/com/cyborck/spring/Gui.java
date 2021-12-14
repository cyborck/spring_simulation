package com.cyborck.spring;

import com.cyborck.spring.world.WorldObject;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Gui extends JFrame {
    private final Component content;

    public Gui ( int width, int height, List<WorldObject> worldObjects, List<Spring> springs ) {
        content = new Content( width, height, worldObjects, springs );

        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setResizable( false );

        getContentPane().add( content );

        pack();
        setLocationRelativeTo( null );
        setVisible( true );
    }

    public Component getContent () {
        return content;
    }

    private static class Content extends Component {
        private final List<WorldObject> worldObjects;
        private final List<Spring> springs;
        private final int width;
        private final int height;

        public Content ( int width, int height, List<WorldObject> worldObjects, List<Spring> springs ) {
            this.worldObjects = worldObjects;
            this.springs = springs;
            this.width = width;
            this.height = height;
            setPreferredSize( new Dimension( width, height ) );
        }

        @Override
        public void paint ( Graphics g ) {
            Graphics2D graphics = ( Graphics2D ) g;
            graphics.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

            //draw background
            graphics.setColor( Color.GRAY );
            graphics.fillRect( 0, 0, width, height );

            for ( Spring spring: springs )
                spring.draw( graphics );
            for ( WorldObject wo: worldObjects )
                wo.draw( graphics );
        }
    }
}
