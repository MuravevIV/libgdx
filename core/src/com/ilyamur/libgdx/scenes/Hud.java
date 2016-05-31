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

    private Integer worldTimer = 300;
    private float timeCount = 0;
    private Integer score = 0;

    private Label countDownLabel;
    private Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label marioLabel;

    public Hud(SpriteBatch spriteBatch) {
        FitViewport viewport = new FitViewport(MarioBros.V_WIDTH, MarioBros.V_HEIGHT, new OrthographicCamera());
        createStage(viewport, spriteBatch);
    }

    private void createStage(Viewport viewport, SpriteBatch spriteBatch) {
        stage = new Stage(viewport, spriteBatch);
        stage.addActor(createTable());
    }

    private Table createTable() {
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        createLabels(labelStyle);

        table.add(marioLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countDownLabel).expandX();

        return table;
    }

    private void createLabels(Label.LabelStyle labelStyle) {
        countDownLabel = createLabel(String.format("%03d", worldTimer), labelStyle);
        scoreLabel = createLabel(String.format("%06d", score), labelStyle);
        timeLabel = createLabel("TIME", labelStyle);
        levelLabel = createLabel("1-1", labelStyle);
        worldLabel = createLabel("WORLD", labelStyle);
        marioLabel = createLabel("MARIO", labelStyle);
    }

    private Label createLabel(String format, Label.LabelStyle labelStyle) {
        return new Label(format, labelStyle);
    }

    public Stage getStage() {
        return stage;
    }
}
