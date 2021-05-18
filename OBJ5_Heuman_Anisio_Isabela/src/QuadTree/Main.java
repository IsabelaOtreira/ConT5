
//IFRJ - Instituto Federal do Rio de Janeiro
//Jogos para Consoles - OBJ 05
//Professor José Ricardo Junior
//Alunos: Heuman Antunes - Anisio Corrêa - Isabela Otreira
//15_05_2021
package QuadTree;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main extends JFrame 
{
	
public Main() {
        iniInt();
    }
    
    private void iniInt() 
    {
        
        add(new Grid());
        
        setResizable(false);
        pack();
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