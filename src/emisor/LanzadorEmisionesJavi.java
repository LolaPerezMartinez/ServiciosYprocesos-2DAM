package emisor;

public class LanzadorEmisionesJavi extends Thread{
	private String nombre;
	private EmisorJavi emisor;
	
	
	public LanzadorEmisionesJavi(String nombre, EmisorJavi emisor) {
		this.nombre = nombre;
		this.emisor = emisor;
	}


	public String getNombre() {
		return nombre;
	}


	public EmisorJavi getEmisorJavi() {
		return emisor;
	}


	@Override
	public void run() {
		emisor.emite();
	}

	@Override
	public String toString() {
		//new/ runnable/ terminated
		StringBuilder sb = new StringBuilder();
	
		sb.append("Lanzador de emisiones:\n");
		sb.append("======================\n");
		sb.append("- Nombre: " + nombre  + "\n");
		sb.append("- Emisor: " + emisor.getNombre() + "\n");
		sb.append("- Hilo:\n");
		sb.append("    - name: " + getName() + "\n");
		sb.append("    - id: " + "\n");
		sb.append("    - alive?: " + "\n");
		sb.append("    - prioridad: " + "\n");
		sb.append("    - interrumpido: " + "\n");
		sb.append("    - threadGroup: " + "\n");
		sb.append("    - state: " + getState() +  "\n");
		
		return sb.toString();
		
		
	}
	
	
		
		
	
	
	
}
