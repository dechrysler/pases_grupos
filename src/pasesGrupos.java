import java.util.concurrent.Semaphore;

class objeto{
	public static int contador_filas=0;
	public static int  personas=5;
	public static int contador_personas=0;
	static ventanas ventana= new ventanas("Fila del la feria.");
	static ventanas ventana2= new ventanas("Fila del la atraccion.");
}

class ejecutaHilos extends Thread{
	
	private Semaphore sema;
	int id=0;
	objeto contadores;
	public  ejecutaHilos(Semaphore s ) {
		this.sema=s;

	}
	public void run() {
		
		
		try {
			sleep(ramdon());
			objeto.ventana.escribecadena("Hay "+objeto.contador_filas+"personas esperando en la fila\n");
			sleep(ramdon());
			sema.acquire();
			objeto.contador_personas++;
			objeto.contador_filas--;
			objeto.ventana2.escribecadena("Esperando en la sala interna de la cola"+objeto.contador_personas+"\n");
			objeto.ventana.escribecadena("Hay "+objeto.contador_filas+"personas esperando en la fila\n");	
			if(objeto.contador_personas==5) {	
				objeto.contador_personas=objeto.contador_personas - 5;
				sema.release(5);
				objeto.ventana2.escribecadena("Salieron ya 5 personas.Pueden pasar las siguientes\n");
				sleep(ramdon());
			}
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		
	}
	public int  ramdon() {
		return (int) (Math.random()*1000+1);
	}
	public int  ramdon2() {
		return (int) (Math.random()*400+1);
	}
}
public class pasesGrupos {

	public final static int PERSONAS=30;
	public static void main(String[]agrs) throws InterruptedException {
		Semaphore semaforo=new Semaphore(5);
		ejecutaHilos hilos[];
		hilos= new ejecutaHilos[PERSONAS];
		
		for(int i=0;i<PERSONAS;i++) {
			hilos[i]= new ejecutaHilos(semaforo);
			hilos[i].start();
			objeto.contador_filas++;
			hilos[i].join((long) hilos[i].ramdon2());
		}
		try {
			Thread.sleep(300);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
			
		}
		for(int i =0;i<PERSONAS;i++)
		{
			try {
				
				hilos[i].join();
				
			}
			catch(InterruptedException e){ e.printStackTrace();}
		}
		
	}
}
