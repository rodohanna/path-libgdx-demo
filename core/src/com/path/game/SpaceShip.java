package com.path.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class SpaceShip {
    private static final int SPEED = 300;
    private static final int IDLE_SPEED = 10;
    private static final int IDLE_ROTATION_SPEED = 5;
    private final Sprite sprite;
    private final Vector3 lastMovingDirection = new Vector3();

    public SpaceShip() {
        Texture shipTexture = new Texture("asteroids/ship_1.png");
        this.sprite = new Sprite(shipTexture);
    }

    public void update(float delta, OrthographicCamera camera) {
        Vector3 directionVector = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);

        camera.unproject(directionVector);

        directionVector.sub(getPosition()).nor();

        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            float speedX = directionVector.x * SPEED * delta;
            float speedY = directionVector.y * SPEED * delta;
            lastMovingDirection.set(directionVector);

            sprite.setPosition(sprite.getX() + speedX, sprite.getY() + speedY);
            float rotationDegrees = new Vector2(directionVector.x, directionVector.y).angleDeg();
            sprite.setRotation(rotationDegrees - 90);
        } else {
            float idleSpeedX = lastMovingDirection.x * IDLE_SPEED * delta;
            float idleSpeedY = lastMovingDirection.y * IDLE_SPEED * delta;
            sprite.setRotation(sprite.getRotation() + IDLE_ROTATION_SPEED * delta);
            sprite.setPosition(sprite.getX() + idleSpeedX, sprite.getY() + idleSpeedY);
        }

    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public Vector3 getPosition() {
        return new Vector3(sprite.getX(), sprite.getY(), 0);
    }
}
