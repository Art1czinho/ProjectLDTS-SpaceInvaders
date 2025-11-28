package controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import model.Arena;
import model.Position;
import view.GameViewer;
import view.GUI;

import java.io.IOException;

public class GameController {
    private final Arena arena;
    private final GameViewer viewer;

    public GameController(Arena arena, GameViewer viewer) {
        this.arena = arena;
        this.viewer = viewer;
    }

    public void start() throws IOException {
        int FPS = 20; // Frames por segundo
        int frameTime = 1000 / FPS; // Tempo de cada frame em milissegundos

        while (true) {
            long startTime = System.currentTimeMillis();

            // 1. INPUT (Não bloqueante)
            KeyStroke key = viewer.getGui().getNextAction(); // Vais ter de alterar este método na GUI!

            if (key != null) {
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                    viewer.getGui().close();
                }
                if (key.getKeyType() == KeyType.EOF) {
                    break;
                }
                processKey(key);
            }

            // 2. UPDATE (Física do jogo)
            arena.moveProjectiles();
            // futuramente: arena.moveMonsters();

            // 3. DRAW
            viewer.draw(arena);

            // 4. ESPERA (Para manter o FPS estável)
            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                // Ignorar interrupções
            }
        }
        viewer.getGui().close();
    }

    private void processKey(KeyStroke key) {
        // Vamos usar a lógica do Space Invaders (Esquerda/Direita)
        // Mas podes usar Cima/Baixo se quiseres mover livremente

        if (key.getKeyType() == KeyType.ArrowLeft) {
            moveHero(arena.getHero().moveLeft());
        }
        if (key.getKeyType() == KeyType.ArrowRight) {
            moveHero(arena.getHero().moveRight());
        }
        if (key.getKeyType() == KeyType.ArrowUp) {
            moveHero(arena.getHero().moveUp());
        }
        if (key.getKeyType() == KeyType.ArrowDown) {
            moveHero(arena.getHero().moveDown());
        }
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == ' ') {
            arena.shoot();
        }

    }

    private void moveHero(Position position) {
        arena.moveHero(position);
    }
}