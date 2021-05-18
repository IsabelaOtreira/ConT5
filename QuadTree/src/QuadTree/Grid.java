//IFRJ - Instituto Federal do Rio de Janeiro
//Jogos para Consoles - OBJ 05
//Professor José Ricardo Junior
//Alunos: Heuman Antunes - Anisio Corrêa - Isabela Otreira
//15_05_2021
package QuadTree;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.JPanel;

public class Grid extends JPanel implements ActionListener
{
	private final int GridLar = 800;
    private final int GridAlt = 600;
    private final int DELAY = 100;
    private static boolean Foi = true;
    boolean QuadAtivo = true;
    private Timer timer;   
    private Quadrado quadrado = new Quadrado(1,1,GridLar, GridAlt);
    private QuadTree quadTree = new QuadTree(quadrado, 4);
    static List<QuadTree> quads = new ArrayList();
    
	int quant = 1000;
	Random rand = new Random();
    Particula[] particulas;
    
    public Grid() {
    	
    	particulas = new Particula[quant];
    	
        for(int i = 0; i < quant; i++) {
        	Particula particula = new Particula(rand.nextInt(quadrado.lar - 2) + 2, rand.nextInt(quadrado.alt - 2) + 2);
        	particulas[i] = particula;
        	particulas[i].id = i;
        	particulas[i].velo = rand.nextInt(9)+1;
        	particulas[i].direcao = rand.nextInt(4);
        	if (QuadAtivo == true) {
        	quadTree.ColocaP(particula);	
        	}
        }
        ComecaG();
    }
    
    private void ComecaG() {
        setBackground(Color.blue);
        setPreferredSize(new Dimension(GridLar, GridAlt));
        Comeca();
    }
  
    public void Desenha(Graphics g) 
    {
    	if (QuadAtivo) {
    	g.drawRect(1,1,GridLar, GridAlt);   	
    	g.setColor(Color.red);
    	    	
    	for (int i = 0; i < quads.size(); i++) 
		{
			g.drawRect(quads.get(i).quadrado.x, quads.get(i).quadrado.y, 
					quads.get(i).quadrado.lar, quads.get(i).quadrado.alt);
		}
    	
    	}
    	
    	for (int i = 0; i < quant; i++) 
    	{
    		if(particulas[i].parttocou) {
    		g.setColor(Color.MAGENTA);
    		}
    		else {	
    			g.setColor(Color.green);
    		}
        	g.fillOval(particulas[i].x, particulas[i].y, 7, 7);
    	}   	
    	java.awt.Toolkit.getDefaultToolkit().sync();      	  
    }
    public void PartMovi() {   	  	

        for(int i = 0; i < quant; i++) {        	        	
        	
        	if(particulas[i].direcao == 0) {
	
        		particulas[i].y += particulas[i].velo;
	        	
        	}
        	if(particulas[i].direcao == 1) {
	
        		particulas[i].y -= particulas[i].velo;
	        	
        	}
        	if(particulas[i].direcao == 2) {

        		particulas[i].x -= particulas[i].velo;
	        	
        	}
        	if(particulas[i].direcao == 3) {

        		particulas[i].x += particulas[i].velo;
        	}
        	
        	if(particulas[i].x <= 1) {
        		particulas[i].direcao = 3;
        	}
        	if(particulas[i].x >= 799) {
        		particulas[i].direcao = 2;
        	}
        	if(particulas[i].y <= 1) {
        		particulas[i].direcao = 0;
        	}
        	if(particulas[i].y >= 599) {
        		particulas[i].direcao = 1;
        	}
        	       	
        	if (QuadAtivo == true) {
        	quadTree.ColocaP(particulas[i]);	
        	}
        	}
    }
    
    public void ColisaoPart(Particula particula) {
    	
    	for(int i = 0; i < particulas.length; i++) {
    		if((particulas[i].x - particula.x) * (particulas[i].x - particula.x) + (particulas[i].y - particula.y) * 
    				(particulas[i].y - particula.y) <= 24.5f && particulas[i].id != particula.id)
    		{
    			particula.parttocou = true;

    			if (particula.direcao == 0) {
    				particula.direcao = 1;
				}

				else if (particula.direcao == 1) {
					particula.direcao = 0;
				}
				else if (particula.direcao == 2) {
					particula.direcao = 3;
				}
				else if (particula.direcao == 3) {
					particula.direcao = 2;
				}
    			return;
    		}
    		else
    		{
    			particula.parttocou = false;
    		}
    	}
    }
    
  @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);

        Desenha(g);
    }
      private void Comeca() 
    {       
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public void actionPerformed(ActionEvent e) {

        if (Foi == true) 
        {	       	
        	Tempo tempo = new Tempo();
        	tempo.ContaTemp();
        	if (QuadAtivo == true) {
        	quads.clear();
        	quadTree = new QuadTree(quadrado, 4);
        	}
        	PartMovi();
        	if (QuadAtivo == true) {
        	for(int i = 0; i < quads.size(); i++)
        	{
        		quads.get(i).Colisor();
        	}
        	}
        	else {
        		
        		for(int i = 0; i < particulas.length; i++)
        		{
        			ColisaoPart(particulas[i]);
        		}
        	}
        }
        	
        repaint();
        Tempo tempo = new Tempo();
        tempo.ParaTemp();
    }


  //  class MyKeyListener extends KeyAdapter {
    	//  public void keyPressed(KeyEvent e) {
    	 //   if (e.getKeyChar() == 'Enter') {
    	    //	QuadAtivo = true;
    	   // }
    	
    }

