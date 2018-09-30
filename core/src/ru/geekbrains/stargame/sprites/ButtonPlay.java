package ru.geekbrains.stargame.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.stargame.base.ActionListener;
import ru.geekbrains.stargame.base.ScaledTouchUpButton;
import ru.geekbrains.stargame.math.Rect;


public class ButtonPlay extends ScaledTouchUpButton {
    public ButtonPlay(TextureAtlas atlas, ActionListener actionListener) {
        super(atlas.findRegion("play"), actionListener, 0.7f);
        setHeightProportion(0.10f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setLeft(worldBounds.getLeft());
    }
}
