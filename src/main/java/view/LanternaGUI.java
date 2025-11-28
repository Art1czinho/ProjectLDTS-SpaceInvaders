package view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import model.Position;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class LanternaGUI implements GUI {
    private final Screen screen;

    public LanternaGUI(int width, int height) throws IOException {
        // Cria o terminal
        Terminal terminal = new DefaultTerminalFactory()
                .setInitialTerminalSize(new TerminalSize(width, height))
                .createTerminal();

        this.screen = new TerminalScreen(terminal);

        this.screen.setCursorPosition(null); // Esconde o cursor (o pisca-pisca)
        this.screen.startScreen();           // Inicia a janela
        this.screen.doResizeIfNecessary();   // Garante o tamanho certo
    }

    // --- Métodos de Desenho Específicos ---
    @Override
    public KeyStroke getNextAction() throws IOException {
        return screen.pollInput(); // pollInput retorna null logo se não houver tecla
    }

    @Override
    public void drawHero(Position position) {
        drawCharacter(position.getX(), position.getY(), '^', "#336699"); // Azul
    }

    @Override
    public void drawMonster(Position position) {
        drawCharacter(position.getX(), position.getY(), 'M', "#CC3333"); // Vermelho
    }

    @Override
    public void drawText(Position position, String text, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(position.getX(), position.getY(), text);
    }

    // Método auxiliar privado (para não repetir código)
    private void drawCharacter(int x, int y, char c, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(x, y, "" + c);
    }

    // --- Métodos de Controlo ---

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
    @Override
    public void drawProjectile(Position position) {
        drawCharacter(position.getX(), position.getY(), '.', "#FFFF33"); // Amarelo
    }
}