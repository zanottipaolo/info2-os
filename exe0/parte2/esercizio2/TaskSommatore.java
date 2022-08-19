package esercizio2;

class TaskSommatore extends Thread{
	int[] serieNumerica = new int[50];
	int indexStart = 0;
	int indexEnd = 0;
	int somma = 0;
	
	public TaskSommatore(int[] serieNumerica, int indexStart, int indexEnd) {
		this.serieNumerica = serieNumerica;
		this.indexStart = indexStart;
		this.indexEnd = indexEnd;
	}
	
	public void run() {
		for(int i=indexStart; i<=indexEnd; i++) {
			somma += serieNumerica[i];
		}
	}
	
	public int getResult() {
		return somma;
	}
}