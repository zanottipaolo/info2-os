package CokeMachine;

public class ClasseTest 
{
	public static void main(String[] Args)
	{
		//CokeMachineIF c= new CokeMachine();
		CokeMachineIF c = new CokeMachine2();
		//CokeMachineIF c= new CokeMachine2();
		//CokeMachineIF c= new CokeMachine3();
		
		Fornitore f = new Fornitore(c);
		f.start();
		
		for(int i=0;i<5;i++)
		{
			Utente u= new Utente(c,i+1);
			u.start();
		}
	}
}
