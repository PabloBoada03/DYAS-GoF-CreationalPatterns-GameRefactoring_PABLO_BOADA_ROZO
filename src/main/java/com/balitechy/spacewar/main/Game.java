package com.balitechy.spacewar.main;

import AGameFactory.AGameFactory;
import com.balitechy.spacewar.CColorVectorFactory.CColorVectorFactory;
import com.balitechy.spacewar.CSpriteFactory.*;
import com.balitechy.spacewar.CVectorFactory.CVectorFactory;
import com.balitechy.spacewar.Interfaces.*;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    public final String TITLE = "Space War 2D";

    private boolean running = false;
    private Thread thread;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    private SpritesImageLoader sprites;
    private SBulletController bullets;
    private IBG backgRenderer;
    private IPlayer player;
    private AGameFactory factory;

    public void setFactory(AGameFactory factory) {
        this.factory = factory;
    }

    public AGameFactory getFactory() {
        return factory;
    }

    public SpritesImageLoader getSprites() {
        return sprites;
    }

    public SBulletController getBullets() {
        return bullets;
    }

    public void init() {
        requestFocus();

        // Solo cargar sprites si la fábrica es de tipo Sprite
        if (factory instanceof CSpriteFactory) {
            sprites = new SpritesImageLoader("/sprites.png");
            try {
                sprites.loadImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Crear componentes usando la fábrica seleccionada
        player = factory.crearPlayer(
                (WIDTH * SCALE - SPlayer.WIDTH) / 2,
                HEIGHT * SCALE - 50
        );
        bullets = new SBulletController();
        backgRenderer = factory.crearBG();

        // Listener de teclado
        addKeyListener(new SInputHandler(this));
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_RIGHT:
                player.setVelX(5);
                break;
            case KeyEvent.VK_LEFT:
                player.setVelX(-5);
                break;
            case KeyEvent.VK_UP:
                player.setVelY(-5);
                break;
            case KeyEvent.VK_DOWN:
                player.setVelY(5);
                break;
            case KeyEvent.VK_SPACE:
                bullets.addBullet(factory.crearBullet(player.getX() + 28 - 5, player.getY() - 18));
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_LEFT:
                player.setVelX(0);
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                player.setVelY(0);
                break;
        }
    }

    private synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    @Override
    public void run() {
        init();

        long lastTime = System.nanoTime();
        final double numOfTicks = 60.0;
        double ns = 1000000000 / numOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(updates + " ticks, fps " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    public void tick() {
        player.tick();
        bullets.tick();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        try {
            backgRenderer.render(g, this);
            player.render(g);
            bullets.render(g);
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        System.out.println("=== SPACE WAR 2D ===");
        System.out.println("Selecciona el estilo gráfico:");
        System.out.println("1. Sprite");
        System.out.println("2. Vector");
        System.out.println("3. Color Vector");
        System.out.print("Opción: ");

        int opcion = sc.nextInt();
        sc.close();

        Game game = new Game();

        // Configurar fábrica según la elección
        switch (opcion) {
            case 1:
                game.setFactory(new CSpriteFactory(game));
                break;
            case 2:
                game.setFactory(new CVectorFactory(game));
                break;
            case 3:
                game.setFactory(new CColorVectorFactory(game));
                break;
            default:
                System.out.println("Opción no válida, usando Sprite por defecto.");
                game.setFactory(new CSpriteFactory(game));
                break;
        }

        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }
}
