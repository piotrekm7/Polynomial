package pl.edu.agh.polynomial.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import pl.edu.agh.polynomial.Polynomial;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
<<<<<<< HEAD
                return new GwtApplicationConfiguration(960, 540);
=======
                return new GwtApplicationConfiguration(1280, 720);
>>>>>>> origin/master
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new Polynomial();
        }
}