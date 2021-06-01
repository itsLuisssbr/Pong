import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends Entity{
	public Enemy(int x, int y, int width, int height, int speed) {
		super(x, y, width, height, speed);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public void tick() {
		
		if(y <= 0) y = 0;
		else if(y + height >= Main.height) y = Main.height - height;
	}
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}
}
