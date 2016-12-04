package pl.edu.agh.polynomial.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.Align;

import pl.edu.agh.polynomial.Polynomial;

import static pl.edu.agh.polynomial.Polynomial.skin;

/**
 * Created by Piotr Muras on 03.12.2016.
 */

public class StartScreen extends State {

    private BitmapFont sofiaProSoftMedium46px = new BitmapFont(Gdx.files.internal("SofiaProSoftMedium46px.fnt"));
    private Label podajStopien = new Label("Podaj stopień wielomianu" , new Label.LabelStyle(sofiaProSoftMedium46px , Color.BLACK));
    private GlyphLayout layout = new GlyphLayout(); // do mierzenia długości tekstu w px
    private TextField stopien;
    private Image dalej;

    public StartScreen(GameStateManager gsm) {
        super(gsm);
        layout.setText(sofiaProSoftMedium46px , podajStopien.getText());
        podajStopien.setPosition(Polynomial.WIDTH/2-layout.width/2 , upY(100));
        addActor(podajStopien);
        TextField.TextFieldStyle tStyle = new TextField.TextFieldStyle();
        tStyle.fontColor = Color.RED;
        tStyle.font = sofiaProSoftMedium46px;
        tStyle.background = Polynomial.skin.getDrawable("ramka");
        stopien = new TextField("" , tStyle);
        stopien .setMessageText("0");
        stopien.setPosition(Polynomial.WIDTH/2-stopien.getWidth()/2,upY(200));
        stopien.setAlignment(Align.center);
        stopien.setMaxLength(2);
        stopien.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        addActor(stopien);
        dalej=new Image(Polynomial.skin.getDrawable("dalej"));
        dalej.setPosition(Polynomial.WIDTH/2-dalej.getWidth()/2,upY(400));
        addActor(dalej);
        Gdx.input.setInputProcessor(this);
        startEnterAnimation();
    }

    @Override
    public void handleInput(float x, float y){
        if((x-dalej.getX()-dalej.getWidth()/2)*(x-dalej.getX()-dalej.getWidth()/2) + (y-dalej.getY()-dalej.getHeight()/2)*(y-dalej.getY()-dalej.getHeight()/2) < dalej.getWidth()*dalej.getWidth()){
            if(!stopien.getText().isEmpty()){
                startEndAnimationAndPushNewState(new MainScreen(gsm , Integer.parseInt(stopien.getText())));
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        act();
        draw();
        drawShadow();
    }
}

