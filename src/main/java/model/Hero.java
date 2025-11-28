package model;

public class Hero extends Element {
    private int energy; // Exemplo de atributo extra

    public Hero(int x, int y) {
        super(x, y);
        this.energy = 100;
    }
    public Position moveUp() {
        return new Position(getPosition().getX(), getPosition().getY() - 1);
    }

    public Position moveDown() {
        return new Position(getPosition().getX(), getPosition().getY() + 1);
    }

    public Position moveLeft() {
        return new Position(getPosition().getX() - 1, getPosition().getY());
    }

    public Position moveRight() {
        return new Position(getPosition().getX() + 1, getPosition().getY());
    }

    // Podemos adicionar métodos específicos depois, ex: decreaseEnergy()
}