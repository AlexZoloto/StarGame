package ru.geekbrains.stargame.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.stargame.base.ActionListener;
import ru.geekbrains.stargame.base.ScaledTouchUpButton;
import ru.geekbrains.stargame.math.Rect;

public class ButtonExit extends ScaledTouchUpButton {

    public ButtonExit(TextureAtlas atlas, ActionListener actionListener) {
        super(atlas.findRegion("exit"), actionListener, 0.7f);
        setHeightProportion(0.10f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        setRight(worldBounds.getRight());
    }
}
