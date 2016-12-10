package pl.edu.agh.polynomial.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

import pl.edu.agh.polynomial.Polynomial;

/**
 * Created by Piotr Muras on 04.12.2016.
 */

public class MainScreen extends State {

    private BitmapFont sofiaProSoftMedium46px = new BitmapFont(Gdx.files.internal("SofiaProSoftMedium46px.fnt"));
    private Label podajWspolczynniki = new Label("Podaj współczynniki wielomianu" , new Label.LabelStyle(sofiaProSoftMedium46px , Color.BLACK));
    private GlyphLayout layout = new GlyphLayout(); // do mierzenia długości tekstu w px


    private static Array<TextField> wspolczynniki = new Array<TextField>();

    public static Array<TextField> getWspolczynniki() {
        return wspolczynniki;
    }

    private Array<Label>  potega = new Array<Label>();

    private Label[] x;


    private Image dalej;


    MainScreen(GameStateManager gsm , int stopienWielomianu){
        super(gsm);

        layout.setText(sofiaProSoftMedium46px , podajWspolczynniki.getText());
        podajWspolczynniki.setPosition(Polynomial.WIDTH/2-layout.width/2 , upY(75));
        addActor(podajWspolczynniki);


        TextField.TextFieldStyle tStyle = new TextField.TextFieldStyle();
        tStyle.fontColor = Color.RED;
        tStyle.font = sofiaProSoftMedium46px;
        tStyle.background =  Polynomial.skin.getDrawable("ramka");


        x = new Label[stopienWielomianu];
        for(int i=0; i<stopienWielomianu; i++){
            TextField wspolczynnik = new TextField("",tStyle);
            wspolczynnik.setMessageText("0");
            int h = i / 5;
            int w = i % 5;
            wspolczynnik.setPosition(200*w+5,upY(h*65+150));
            wspolczynnik.setAlignment(Align.center);
            wspolczynnik.setMaxLength(2);
            wspolczynnik.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
            wspolczynniki.add(wspolczynnik);

            x[i] = new Label("x", new Label.LabelStyle(sofiaProSoftMedium46px,Color.BLACK));
            layout.setText(sofiaProSoftMedium46px , x[i].getText());
            x[i].setPosition( 200*w+wspolczynnik.getWidth()/2+50,upY(h*65+150) );
        }

        Iterator<TextField> iter = wspolczynniki.iterator();
        while(iter.hasNext()){
            TextField wspolczynnik = iter.next();
            addActor(wspolczynnik);
        }
        for(Label i:x){
            addActor(i);
        }



        dalej=new Image(Polynomial.skin.getDrawable("dalej"));
        dalej.setPosition(Polynomial.WIDTH/2-dalej.getWidth()/2,0);
        addActor(dalej);
        Gdx.input.setInputProcessor(this);
        startEnterAnimation();

    }



    @Override
    public void handleInput(float x, float y) {
        if((x-dalej.getX()-dalej.getWidth()/2)*(x-dalej.getX()-dalej.getWidth()/2) + (y-upY((int)dalej.getY())+dalej.getHeight()/2)*(y-upY((int)dalej.getY())+dalej.getHeight()/2) < dalej.getWidth()/2*dalej.getWidth()/2){
            startEndAnimationAndPushNewState(new MiejscaZerowe(gsm));
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        act();
        draw();
        drawShadow();
    }
}
