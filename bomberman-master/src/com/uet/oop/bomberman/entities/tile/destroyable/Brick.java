package com.uet.oop.bomberman.entities.tile.destroyable;


import com.uet.oop.bomberman.entities.Entity;
import com.uet.oop.bomberman.entities.DirectionalExplosion;
import com.uet.oop.bomberman.entities.Kondoria;
import com.uet.oop.bomberman.graphics.Screen;
import com.uet.oop.bomberman.graphics.Sprite;
import com.uet.oop.bomberman.Funcion.Coordinates;

public class Brick extends DestroyableTile {

    public Brick(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Screen screen) {
        int x = Coordinates.tileToPixel(_x);
        int y = Coordinates.tileToPixel(_y);

        if (destroy == true) {
            _sprite = movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2);

            screen.renderEntityWithBelowSprite(x, y, this, belowSprite);
        } else
            screen.renderEntity(x, y, this);
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Kondoria)
            return true;
        if (e instanceof DirectionalExplosion)
            destroy();


        return false;
    }


}
