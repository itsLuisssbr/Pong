import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball extends Entity{
	public Ball(int x, int y, int width, int height, int speed) {
		
		super(x, y, width, height, speed);
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
	}
	public void tick() {
		Random rand = new Random();
		double ang = rand.nextDouble();
		/*double xx = Math.cos(ang);
		double yy = Math.sin(ang);
		x+=xx;
		y+=yy;
		*/
		x+=(speed * Math.cos(ang));
		y+= (speed * Math.sin(ang));
		if(x <= 0 || x >= Main.width) speed*= -1;
		Rectangle ball = new Rectangle(x, y, width, height);
		Rectangle player = new Rectangle(Main.player.x, Main.player.y, Main.player.width, Main.player.height);
		Rectangle enemy = new Rectangle(Main.enemy.x, Main.enemy.y, Main.enemy.width, Main.enemy.height);
		if(ball.intersects(player)) x*=-1;
		else if(ball.intersects(enemy)) x*=-1;
		
	
	}
	public void render(Graphics g) {
		g.fillRect(x, y, width, height);
	}
}
