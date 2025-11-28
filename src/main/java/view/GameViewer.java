package view;

import model.Arena;
import model.Element;
import model.Monster;
import model.Projectile;
import java.io.IOException;
import java.util.List;


public class GameViewer {
    private final GUI gui;

    public GUI getGui() {
        return gui;
    }

    public GameViewer(GUI gui) {
        this.gui = gui;
    }

    public void draw(Arena arena) throws IOException {
        gui.clear(); // 1. Limpa o ecrã antigo

        // 2. Desenha todos os elementos
        drawElements(arena);

        gui.refresh(); // 3. Mostra tudo ao utilizador
    }

    private void drawElements(Arena arena) {
        // Desenha o Heroi
        gui.drawHero(arena.getHero().getPosition());

        // Desenha os Monstros
        for (Monster monster : arena.getMonsters()) {
            gui.drawMonster(monster.getPosition());
        }
        for (Projectile projectile : arena.getProjectiles()) {
            gui.drawProjectile(projectile.getPosition());
        }
    }
}