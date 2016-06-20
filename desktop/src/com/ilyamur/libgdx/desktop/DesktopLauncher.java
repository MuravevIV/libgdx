package com.ilyamur.libgdx.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ilyamur.libgdx.MarioBrosGame;

public class DesktopLauncher {

    public static void main(String[] arg) {
        new LwjglApplication(new MarioBrosGame(), new LwjglApplicationConfiguration());
    }
}
