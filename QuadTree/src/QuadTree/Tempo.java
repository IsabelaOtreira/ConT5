package QuadTree;
public class Tempo {
	
	public int TempoCome;
	public int TempoFin;
	
	public void ContaTemp () {
		TempoCome = (int) System.currentTimeMillis();				
	}
	
	public void ParaTemp() {
		
		TempoFin = (int) System.currentTimeMillis();
		System.out.println("Time : " + ((TempoFin - TempoCome)));
		
	}

}
