//IFRJ - Instituto Federal do Rio de Janeiro
//Jogos para Consoles - OBJ 05
//Professor José Ricardo Junior
//Alunos: Heuman Antunes - Anisio Corrêa - Isabela Otreira
//15_05_2021
package quadtree;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Main extends JFrame 
{
	
public Main() {
        
        initUI();
    }
    
    private void initUI() 
    {
        
        add(new Board());
        
        setResizable(false);
        pack();
        //define o titulo da aplicação
        setTitle("Quadtree estruturado - JAVA");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new Main();
            ex.setVisible(true);
        });
    }

}