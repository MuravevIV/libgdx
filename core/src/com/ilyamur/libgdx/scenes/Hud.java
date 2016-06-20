package com.ilyamur.libgdx.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ilyamur.libgdx.MarioBrosGame;

public class Hud {

    private Stage stage;

    @SuppressWarnings("FieldCanBeLocal")
    private Integer worldTimer = 300;

    @SuppressWarnings("FieldCanBeLocal")
    private Integer score = 0;

    private Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
    private Label countDownLabel;
    private Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label marioLabel;

    public Hud(SpriteBatch spriteBatch) {
        FitViewport viewport = new FitViewport(MarioBrosGame.V_WIDTH, MarioBrosGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);
        stage.addActor(createTable());
    }

    private Table createTable() {
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        createLabels();

        table.add(marioLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countDownLabel).expandX();

        return table;
    }

    private void createLabels() {
        countDownLabel = new Label(String.format("%03d", worldTimer), labelStyle);
        scoreLabel = new Label(String.format("%06d", score), labelStyle);
        timeLabel = new Label("TIME", labelStyle);
        levelLabel = new Label("1-1", labelStyle);
        worldLabel = new Label("WORLD", labelStyle);
        marioLabel = new Label("MARIO", labelStyle);
    }

    public Stage getStage() {
        return stage;
    }
}
