package ru.geekbrains.stargame.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.stargame.base.ActionListener;
import ru.geekbrains.stargame.base.ScaledTouchUpBotton;
import ru.geekbrains.stargame.math.Rect;

public class ButtonExit extends ScaledTouchUpBotton {
    public ButtonExit(TextureAtlas atlas, ActionListener actionListener) {
        super(atlas.findRegion("exit"), actionListener,0.7f);
        setHeightProportion(0.10f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setRight(worldBounds.getRight());
        setBottom(worldBounds.getBottom());
    }
}