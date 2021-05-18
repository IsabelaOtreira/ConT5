
//IFRJ - Instituto Federal do Rio de Janeiro
//Jogos para Consoles - OBJ 05
//Professor José Ricardo Junior
//Alunos: Heuman Antunes - Anisio Corrêa - Isabela Otreira
//15_05_2021
package QuadTree;
import java.util.ArrayList;
import java.util.List;

public class QuadTree {
	
	private List <Particula> particulas = new ArrayList<Particula>();
	int quantT;
	public Quadrado quadrado;
	public QuadTree cimaE;
	public QuadTree cimaD;
	public QuadTree baixoE;
	public QuadTree baixoD;
	public boolean dividido = false;
	
	public QuadTree(Quadrado quadrado, int quantT) 
	{
		this.quadrado = quadrado;
		this.quantT = quantT;
		Grid.quads.add(this);
	}

	public void ColocaP(Particula particula) 
	{
		if(this.quadrado.Contains(particula) == false)
		{
			return;
		}
						
		if(particulas.size() < quantT && !particulas.contains(particula)) 
		{
			particulas.add(particula);
		}
		else 
		{			
			if(dividido == false) 
			{			
				Divisao();
				
				for (int i = 0; i < particulas.size(); i++) 
				{
					this.cimaE.ColocaP(particulas.get(i));
					this.cimaD.ColocaP(particulas.get(i));
					this.baixoE.ColocaP(particulas.get(i));
					this.baixoD.ColocaP(particulas.get(i));		
				}
			}						
			this.cimaE.ColocaP(particula);
			this.cimaD.ColocaP(particula);
			this.baixoE.ColocaP(particula);
			this.baixoD.ColocaP(particula);
		}
	}
	public void Divisao() 
	{
		Quadrado cimaE = new Quadrado(quadrado.x, 
				quadrado.y,
				quadrado.lar/2,quadrado.alt/2);
		this.cimaE = new QuadTree(cimaE, quantT);
				
		Quadrado baixoE = new Quadrado(quadrado.x + quadrado.lar / 2 , 
				quadrado.y,
				quadrado.lar/2,quadrado.alt/2);
		this.cimaD = new QuadTree(baixoE, quantT);
		
		Quadrado cimaD = new Quadrado(quadrado.x, 
				quadrado.y + quadrado.alt/2,
				quadrado.lar/2,quadrado.alt/2);
		this.baixoD = new QuadTree(cimaD, quantT);
		
		Quadrado baixoD = new Quadrado(quadrado.x + quadrado.lar / 2 , 
				quadrado.y + quadrado.alt/2,
				quadrado.lar/2,quadrado.alt/2);
		this.baixoE = new QuadTree(baixoD, quantT);
		
		dividido = true;				
	}
	
	public void Colisor() 
	{
		for (int i = 0; i < particulas.size(); i++) 
		{
			int k = 0;
			
			for (int j = 0; j < particulas.size(); j++)
			{
				if (Matematica(particulas.get(i), particulas.get(j)) && particulas.get(i) != particulas.get(j))
				{
					k++;
				}		
			}
			if (k > 0) {
				if (particulas.get(i).direcao == 0) {
					particulas.get(i).direcao = 1;
				}
				
				else if (particulas.get(i).direcao == 1) {
					particulas.get(i).direcao = 0;
				}
				else if (particulas.get(i).direcao == 2) {
					particulas.get(i).direcao = 3;
				}
				else if (particulas.get(i).direcao == 3) {
					particulas.get(i).direcao = 2;
				}												
				particulas.get(i).parttocou = true;
			}
			else {
				particulas.get(i).parttocou = false;			
			}
		}
	}
	
	boolean Matematica(Particula part1, Particula part2)
	{
		return(((part2.x - part1.x) * (part2.x - part1.x) + (part2.y - part1.y) * (part2.y - part1.y)) <= 24.5f);					
	}
}
