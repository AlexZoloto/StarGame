package ru.geekbrains.stargame.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.stargame.base.ActionListener;
import ru.geekbrains.stargame.base.ScaledTouchUpBotton;
import ru.geekbrains.stargame.math.Rect;

public class ButtonPlay extends ScaledTouchUpBotton {
    public ButtonPlay(TextureAtlas atlas, ActionListener actionListener) {
        super(atlas.findRegion("play"), actionListener,0.7f);
        setHeightProportion(0.10f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setLeft(worldBounds.getLeft());
        setBottom(worldBounds.getBottom());
    }
}
