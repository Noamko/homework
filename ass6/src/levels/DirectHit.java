package levels;

import configuration.Config;
import gamelogic.GameLevel;
import gamelogic.LevelInformation;
import geometry.Line;
import geometry.Point;
import objects.Ball;
import objects.Block;
import objects.Paddle;
import ui.Background;
import ui.Sprite;
import vector.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * direct hit class.
 */
public class DirectHit implements LevelInformation {
    private Block theRedBlockOfTerror;
    private List<Block> blocks;
    private DHBackground bg;
    private Paddle paddle;

    /**
     * constructor.
     */
    public DirectHit() {
        blocks = new ArrayList<>();
        bg = new DHBackground();
        paddle = new Paddle();
    }

    /**
     * load the level components.
     * @param gl Gamelevel
     */
    public void load(GameLevel gl) {
        theRedBlockOfTerror = new Block(new Point(Config.WINDOW_WIDTH / 2 - 15, 175 - 15), 30, 30);
        theRedBlockOfTerror.setColor(Color.RED);
        blocks.add(theRedBlockOfTerror);

        Ball ball = new Ball(Config.BALL_STARTING_POSITION, Config.BALL_RADIUS, gl.getEnvironment());
        ball.setVelocity(Velocity.fromAngleAndSpeed(180, Config.BALL_SPEED));
        ball.addToGame(gl);
    }

    @Override
    /**
     * get the paddle of the level.
     * @return Paddle
     */
    public Paddle getPaddle() {
        return paddle;
    }

    @Override
    /**
     * return the amount of balls in the level.
     * @return int
     */
    public int numberOfBalls() {
        return 1;
    }

    @Override
    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return List
     */
    public List<Velocity> initialBallVelocities() {
        return null;
    }

    @Override
    /**
     * return the paddle speed.
     * @return int
     */
    public int paddleSpeed() {
        return Config.PADDLE_SPEED;
    }

    @Override
    /**
     * return the paddle width.
     * @return int
     */
    public int paddleWidth() {
        return Config.PADDLE_WIDTH;
    }

    @Override
    /**
     * the level name will be displayed at the top of the screen.
     * @return String
     */
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    /**
     * Returns a sprite with the background of the level.
     * @return Sprite
     */
    public Sprite getBackground() {
        return bg;
    }

    @Override
    /**
     * The Blocks that make up this level, each block contains.
     * its size, color and location.
     * @return List
     */
    public List<Block> blocks() {
        return blocks;
    }

    @Override
    /**
     * Number of blocks that should be removed.
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return int
     */
    public int numberOfBlocksToRemove() {
        return blocks.size();
    }
}

/**
 * background class.
 */
class DHBackground extends Background {
    /**
     * direct hit background.
     */
    public DHBackground() {
        Line l = new Line(Config.WINDOW_WIDTH / 2, 50, Config.WINDOW_WIDTH / 2, 300);
        addBlock(new Point(0, 0), Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT, Color.black);
        addLine(l, Color.BLUE);
        addLine(new Line(250, 175, 550, 175), Color.BLUE);
        addCircle(l.middle(), 100, Color.BLUE, true);
        addCircle(l.middle(), 50, Color.BLUE, true);
    }
}
