//IFRJ - Instituto Federal do Rio de Janeiro
//Jogos para Consoles - OBJ 05
//Professor José Ricardo Junior
//Alunos: Heuman Antunes - Anisio Corrêa - Isabela Otreira
//15_05_2021
package quadtree;

public class Rectangle {
	
	public int x;
	public int y;
	public int w;
	public int h;

	public Rectangle(int z, int h, int w, int b)
	{
		this.x = z;
		this.y = h;
		this.w = w;
		this.h = b;
	}
	public boolean Contains(Point point) {
		
		return (point.x > this.x &&
				point.x < this.x + this.w &&
				point.y > this.y &&
				point.y < this.y + this.h);
	}
}
