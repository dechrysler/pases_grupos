

import java.awt.TextArea;

import javax.swing.JFrame;

class ventanas{
	private JFrame ventana=new JFrame();
	private TextArea texto_ventana=new TextArea();
	ventanas(String nombre){
		ventana.setName(nombre);
		ventana.setTitle(nombre);
		ventana.setSize(400, 400);
		ventana.setVisible(true);
		ventana.add(texto_ventana);
	}
	public void escribecadena(String cadena){
		texto_ventana.setText(texto_ventana.getText() + cadena);
	}
}
