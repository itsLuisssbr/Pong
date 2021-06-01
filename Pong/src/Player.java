import java.awt.Color;
import java.awt.Graphics;

public class Player extends Entity{
	public boolean u, d;
	public Player(int x, int y, int width, int height, int speed) {
		super(x, y, width, height, speed);
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
	}
	
	public void tick() {
		if(u) y-=speed;
		else if(d) y+=speed;
		if(y <= 0) y = 0;
		else if(y + height >= Main.height) y = Main.height - height;
		
	}
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x,  y,  width,  height);
	}

}
