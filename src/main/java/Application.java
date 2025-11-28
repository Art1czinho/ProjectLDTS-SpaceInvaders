import controller.GameController;
import model.Arena;
import view.GameViewer;
import view.LanternaGUI;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try {
            // 1. Setup do Model
            Arena arena = new Arena(40, 20);

            // 2. Setup da View
            LanternaGUI gui = new LanternaGUI(40, 20);
            GameViewer viewer = new GameViewer(gui);

            // 3. Setup do Controller e Início
            GameController controller = new GameController(arena, viewer);
            controller.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
