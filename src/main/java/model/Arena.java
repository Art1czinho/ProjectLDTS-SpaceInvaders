package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Arena {
    private final int width;
    private final int height;

    private Hero hero;
    private List<Monster> monsters;
    private List<Projectile> projectiles;

    public void shoot() {
        // Cria uma bala na mesma posição do Heroi
        Projectile p = new Projectile(hero.getPosition().getX(), hero.getPosition().getY());
        projectiles.add(p);
    }

    public void moveProjectiles() {
        // Usamos um Iterator para poder remover balas enquanto percorremos a lista
        Iterator<Projectile> iterator = projectiles.iterator();

        while (iterator.hasNext()) {
            Projectile p = iterator.next();
            p.setPosition(p.moveUp());

            // Se a bala sair do topo do ecrã (y < 0), desaparece
            if (p.getPosition().getY() < 0) {
                iterator.remove();
            }
        }
    }

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;

        // Inicializa o Heroi no centro, em baixo
        this.hero = new Hero(width / 2, height - 2);

        this.monsters = new ArrayList<>();
        this.projectiles = new ArrayList<>();

        createMonsters();
    }

    // Cria uma formação inicial de monstros
    private void createMonsters() {
        for (int x = 5; x < width - 5; x+=2) { // Espaçamento
            for (int y = 1; y < 5; y++) {
                monsters.add(new Monster(x, y));
            }
        }
    }

    // --- Getters para a View poder desenhar ---

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public Hero getHero() { return hero; }

    public List<Monster> getMonsters() { return monsters; }

    public List<Projectile> getProjectiles() { return projectiles; }
    public void moveHero(Position position) {
        // Agora verificamos se é válido ESPECIFICAMENTE para o herói
        if (isValidHeroPosition(position)) {
            hero.setPosition(position);
        }
    }

    // Validação geral (para não sair do ecrã)
    private boolean isValid(Position position) {
        return position.getX() >= 0 && position.getX() < width &&
                position.getY() >= 0 && position.getY() < height;
    }

    // Validação ESPECÍFICA do Herói (Barreira Invisível)
    private boolean isValidHeroPosition(Position position) {
        // 1. Primeiro vê se está dentro do ecrã normal
        if (!isValid(position)) return false;

        // 2. A REGRA NOVA: O Herói não pode subir além de certo ponto.
        // Vamos dizer que ele só pode andar nas últimas 5 linhas.
        int barreiraInvisivel = height - 5;

        // Se o Y for menor que a barreira, ele está a tentar ir demasiado para cima
        return position.getY() >= barreiraInvisivel;
    }
}