package model;

public class Projectile extends Element {
    public Projectile(int x, int y) {
        super(x, y);
    }

    public Position moveUp() {
        return new Position(getPosition().getX(), getPosition().getY() - 1);
    }
}