package controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import model.Arena;
import model.Position;
import view.GameViewer;

import java.io.IOException;

public class GameController {
    private final Arena arena;
    private final GameViewer viewer;

    public GameController(Arena arena, GameViewer viewer) {
        this.arena = arena;
        this.viewer = viewer;
    }

    public void start() throws IOException {
        int FPS = 20;
        int frameTime = 1000 / FPS;

        while (true) {
            long startTime = System.currentTimeMillis();

            // --- CICLO DE INPUT ---
            // Lê TODAS as teclas pendentes.
            // Se carregares em 3 teclas rápido, ele processa as 3 antes de desenhar.
            KeyStroke key;
            while ((key = viewer.getGui().getNextAction()) != null) {

                // Sair do jogo ('q' ou fechar janela)
                if ((key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                        || key.getKeyType() == KeyType.EOF) {
                    viewer.getGui().close();
                    return; // <--- O SEGREDO: Encerra o método start() imediatamente
                }

                processKey(key);
            }

            // --- UPDATE (Física) ---
            arena.moveProjectiles();
            // arena.moveMonsters(); (Fica para depois)

            // --- DRAW (Desenhar) ---
            viewer.draw(arena);

            // --- TIMER (Sincronização) ---
            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                // Ignorar interrupções
            }
        }
    }

    private void processKey(KeyStroke key) {
        // Movimento (Esquerda / Direita)
        if (key.getKeyType() == KeyType.ArrowLeft) {
            moveHero(arena.getHero().moveLeft());
        }
        if (key.getKeyType() == KeyType.ArrowRight) {
            moveHero(arena.getHero().moveRight());
        }

        // Movimento Vertical (com a tal barreira invisível)
        if (key.getKeyType() == KeyType.ArrowUp) {
            moveHero(arena.getHero().moveUp());
        }
        if (key.getKeyType() == KeyType.ArrowDown) {
            moveHero(arena.getHero().moveDown());
        }

        // Disparar
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == ' ') {
            arena.shoot();
        }
    }

    private void moveHero(Position position) {
        arena.moveHero(position);
    }
}