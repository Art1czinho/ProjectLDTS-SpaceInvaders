package view;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.input.KeyStroke;
import model.Position;

import java.io.IOException;

public interface GUI {
    // Ações de desenho básicas
    void drawHero(Position position);
    void drawProjectile(Position position);
    void drawMonster(Position position);
    void drawText(Position position, String text, String color);
    KeyStroke getNextAction() throws IOException;

    // Ações de controlo do ecrã
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;
}