import java.util.concurrent.Semaphore;

class objeto{
	public static Semaphore semaforo=new Semaphore(5);
	public static Semaphore semaforoSala=new Semaphore(5);
	public static int contador_filas=0;
	public static int  PERSONAS=5;
	public static int contador_personas=0;
	static ventanas ventana= new ventanas("Fila del la feria.");
	static ventanas ventana2= new ventanas("Fila del la atraccion.");
}

class ejecutaHilos extends Thread{
	
	private int id;
	
	public  ejecutaHilos( int j) {
		this.id=j;
	}
	public void run() {
		
		
		try {
			objeto.ventana.escribecadena("Hay "+objeto.contador_filas+"personas esperando en la fila\n");			
			sleep(ramdon());
			if(objeto.semaforo.availablePermits()==1 && objeto.semaforoSala.availablePermits()==5) {
				objeto.semaforo.acquire();
				objeto.ventana2.escribecadena("Soy la persona" + id +" y me han dado un pase"+"\n");
				for(int i=0;i<objeto.PERSONAS;i++) {
					objeto.contador_filas--;
					objeto.semaforoSala.acquire();
					objeto.ventana2.escribecadena("Se han montado "+(i+1)+" personas en la atraccion."+"\n");
					ramdon2();
				}
				objeto.semaforoSala.release(5);
				objeto.semaforo.release(5);
				System.out.println("1");
				objeto.ventana.escribecadena("Hay "+objeto.contador_filas+"personas esperando en la fila\n");
			}
			else {
				objeto.semaforo.acquire();
				objeto.ventana2.escribecadena("Soy la persona" + id +" y me han dado un pase"+"\n");	
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
		
		ejecutaHilos hilos[];
		hilos= new ejecutaHilos[PERSONAS];
		
		for(int i=0;i<PERSONAS;i++) {
			hilos[i]= new ejecutaHilos((i+1));
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
