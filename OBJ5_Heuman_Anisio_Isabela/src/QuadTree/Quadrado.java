package QuadTree;
//IFRJ - Instituto Federal do Rio de Janeiro
//Jogos para Consoles - OBJ 05
//Professor José Ricardo Junior
//Alunos: Heuman Antunes - Anisio Corrêa - Isabela Otreira
//15_05_2021
public class Quadrado {
	
	public int x;
	public int y;
	public int lar;
	public int alt;

	public Quadrado(int a, int b, int c, int d)
	{
		this.x = a;
		this.y = b;
		this.lar = c;
		this.alt = d;
	}
	public boolean Contains(Particula particula) {
		
		return (particula.x > this.x &&
				particula.x < this.x + this.lar &&
				particula.y > this.y &&
				particula.y < this.y + this.alt);
	}
}
