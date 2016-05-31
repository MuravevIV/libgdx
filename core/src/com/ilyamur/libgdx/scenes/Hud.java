package com.ilyamur.libgdx.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ilyamur.libgdx.MarioBros;

public class Hud {

    private Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    private Label countDownLabel;
    private Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label marioLabel;
    private Label.LabelStyle labelStyle;

    public Hud(SpriteBatch sb) {
        worldTimer = 300;
        timeCount = 0;
        score = 0;
        viewport = new FitViewport(MarioBros.V_WIDTH, MarioBros.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        countDownLabel = createLabel(String.format("%03d", worldTimer));
        scoreLabel = createLabel(String.format("%06d", score));
        timeLabel = createLabel("TIME");
        levelLabel = createLabel("1-1");
        worldLabel = createLabel("WORLD");
        marioLabel = createLabel("MARIO");

        table.add(marioLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countDownLabel).expandX();

        stage.addActor(table);
    }

    private Label createLabel(String format) {
        return new Label(format, labelStyle);
    }

    public Stage getStage() {
        return stage;
    }
}
