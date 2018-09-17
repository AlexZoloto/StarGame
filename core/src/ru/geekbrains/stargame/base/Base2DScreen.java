package ru.geekbrains.stargame.base;

        import com.badlogic.gdx.Game;
        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.InputProcessor;
        import com.badlogic.gdx.Screen;
        import com.badlogic.gdx.math.Vector2;

public class Base2DScreen implements Screen, InputProcessor{
    private Game game;

    public Base2DScreen(Game game) {
        this.game = game;
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {
        System.out.println("show");
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
        System.out.println("pause");
    }

    @Override
    public void resume() {
        System.out.println("resume");
    }

    @Override
    public void hide() {
        System.out.println("hide");
    }

    @Override
    public void dispose() {
        System.out.println("dispose");
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("keyDown keycode = " + keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println("keyUp keycode = " + keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        System.out.println("keyTyped character = " + character);
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    protected void changePosition(Vector2 position, float positionX, float positionY, Vector2 speed){
        if (position.x < positionX){
            position.x += speed.x;
        }
        if (position.x > positionX){
            position.x -= speed.x;
        }
        if (position.y < positionY){
            position.y += speed.y;
        }
        if (position.y > positionY){
            position.y -= speed.y;
        }
    }
}
