package levels;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import configuration.Config;
import gamelogic.GameLevel;
import gamelogic.LevelInformation;
import geometry.Line;
import geometry.Point;
import objects.Ball;
import objects.Block;
import objects.Paddle;
import ui.Background;
import ui.Circle;
import ui.Sprite;
import ui.Stripe;
import vector.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//TODO create this level!
public class WideEasy implements LevelInformation {
    private List<Block> blocks;
    private Paddle paddle;
    Background bg;
    public WideEasy() {
        blocks = new ArrayList<>();
        bg = new WEBackground();
        paddle = new Paddle();
    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return null;
    }

    @Override
    public int paddleSpeed() {
        return Config.PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return paddle.getWidth();
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return bg;
    }

    @Override
    public List<Block> blocks() {
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks.size();
    }

    @Override
    public void load(GameLevel gl) {
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PINK, Color.CYAN};
        for (int i = 0; i < 15; i++) {
                Block b = new Block(new Point(Config.WALL_SIZE + Config.BLOCK_WIDTH * i, 270),
                        Config.BLOCK_WIDTH, Config.BLOCK_HEIGHT);
                b.setColor(colors[i / 3]);
                blocks.add(b);
        }

        for (int i = 0; i < numberOfBalls(); i++) {
            int relativeX = Config.WINDOW_WIDTH / 2;
            int relativeY = Config.WINDOW_HEIGHT / 2;
            if(i < 5) {
                Ball b = new Ball(new Point(
                        relativeX / 1.1 - i * 30,relativeY  + i * 30),5, gl.getEnvironment());
                b.setVelocity(Velocity.fromAngleAndSpeed(180, Config.BALL_SPEED));
                b.addToGame(gl);
            }
            if(i >= 5) {
                Ball b = new Ball(new Point(
                        relativeX * 1.1 + (i-5) * 30,relativeY + (i-5) * 30),5, gl.getEnvironment());
                b.setVelocity(Velocity.fromAngleAndSpeed(180, Config.BALL_SPEED));
                b.addToGame(gl);
            }
        }
        paddle.setWidth(600);
    }

    @Override
    public Paddle getPaddle() {
        return paddle;
    }
}

class WEBackground extends Background  {
    public WEBackground() {
        addBlock(new Point(0, 0), Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT, Color.white);
        //Sun
        Point sunCenter = new Point(Config.WINDOW_WIDTH/4, Config.WINDOW_HEIGHT/4);
        for (int i =1; i < 1000; i+=20) {
            addLine(new Line(sunCenter,new Point(i,270)),Color.ORANGE);
        }
        addCircle(sunCenter, 90, Color.decode("#F4FFB9"));
        addCircle(sunCenter, 80, Color.decode("#FFFF55"));
        addCircle(sunCenter, 70, Color.decode("#FFD30A"));
    }
}
