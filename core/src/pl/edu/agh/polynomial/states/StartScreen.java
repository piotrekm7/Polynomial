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

import pl.edu.agh.polynomial.Polynomial;

import static pl.edu.agh.polynomial.Polynomial.skin;

/**
 * Created by Piotr Muras on 03.12.2016.
 */

public class StartScreen extends State {

    private BitmapFont sofiaProSoftMedium46px = new BitmapFont(Gdx.files.internal("SofiaProSoftMedium46px.fnt"));
    private Label podajStopien = new Label("Podaj stopień wielomianu 1-24", new Label.LabelStyle(sofiaProSoftMedium46px, Color.BLACK));
    private GlyphLayout layout = new GlyphLayout(); // do mierzenia długości tekstu w px
    private TextField stopien;
    private Image dalej;
    private Image bg;
    private Image Copyright;
    private Label blad1 = new Label("Błąd! \n Podaj lliczbę z podanego zakresu", new Label.LabelStyle(sofiaProSoftMedium46px, Color.BLACK));

    public StartScreen(GameStateManager gsm) {
        super(gsm);
        bg = new Image(skin.getDrawable("bg"));
        addActor(bg);

        Copyright = new Image(Polynomial.skin.getDrawable("Copyright"));
        Copyright.setScale(0.58f);
        Copyright.setPosition((int) (Polynomial.WIDTH - Copyright.getWidth() * 0.58), upY((int) (Copyright.getHeight() * 0.85)));
        addActor(Copyright);

        layout.setText(sofiaProSoftMedium46px, podajStopien.getText());
        podajStopien.setPosition(Polynomial.WIDTH / 2 - layout.width / 2, upY(75));
        addActor(podajStopien);
        TextField.TextFieldStyle tStyle = new TextField.TextFieldStyle();
        tStyle.fontColor = Color.RED;
        tStyle.font = sofiaProSoftMedium46px;
        tStyle.background = Polynomial.skin.getDrawable("ramka1");
        stopien = new TextField("", tStyle);
        stopien.setMessageText("0");
        stopien.setPosition(Polynomial.WIDTH / 2 - stopien.getWidth() / 2, upY(230));
        stopien.setAlignment(Align.center);
        stopien.setMaxLength(2);
        stopien.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        addActor(stopien);
        dalej = new Image(Polynomial.skin.getDrawable("dalej"));
        dalej.setPosition(Polynomial.WIDTH / 2 - dalej.getWidth() / 2, upY(400));
        addActor(dalej);
        Gdx.input.setInputProcessor(this);
        startEnterAnimation();
    }


    @Override
    public void handleInput(float x, float y) {
        if ((x - dalej.getX() - dalej.getWidth() / 2) * (x - dalej.getX() - dalej.getWidth() / 2) + (y - upY((int) dalej.getY()) + dalej.getHeight() / 2) * (y - upY((int) dalej.getY()) + dalej.getHeight() / 2) < dalej.getWidth() / 2 * dalej.getWidth() / 2) {
            if (!stopien.getText().isEmpty() && Integer.parseInt(stopien.getText()) <= 24 && Integer.parseInt(stopien.getText()) > 0) {
                startEndAnimationAndPushNewState(new MainScreen(gsm, Integer.parseInt(stopien.getText())));
            } else {
                layout.setText(sofiaProSoftMedium46px, blad1.getText());
                blad1.setPosition(-layout.width / 4 + 30, -layout.height / 4 - 10);
                blad1.setFontScale(0.6f);
                blad1.setAlignment(2);
                addActor(blad1);
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

