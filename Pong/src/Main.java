import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Main extends Canvas implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L;
	private Thread thread;
	private JFrame frame;
	private boolean isRunning;
	public static final int width = 800;
	public static final int height = 600;
	public static final int scale = 1;
	public static Player player;
	public static Ball ball;
	public static Enemy enemy;
	BufferedImage image = new BufferedImage(width*scale, height*scale, BufferedImage.TYPE_INT_RGB);
	
	public Main() {
		this.setPreferredSize(new Dimension(width*scale , height*scale));
		this.addKeyListener(this);
		player = new Player(0, (height - 150) / 2, 15, 150, 3);
		enemy = new Enemy(width - 15, (height - 150) / 2, 15, 150, 0);
		ball = new Ball((width - 16) / 2, (height - 16) / 2, 16, 16, 4);
		initFrame();
	}
	private void initFrame() {
		frame = new JFrame("Pong");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
	
	public void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	public void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, width*scale, height*scale);
		player.render(g);
		enemy.render(g);
		ball.render(g);
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, width*scale, height*scale, null);
		bs.show();
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) player.u = true;
		else if(e.getKeyCode() == KeyEvent.VK_S) player.d = true;
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) player.u = false;
		else if(e.getKeyCode() == KeyEvent.VK_S) player.d = false;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void run() {
		requestFocus();
		while(isRunning) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
	}
}
