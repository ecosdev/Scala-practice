

import java.awt.BorderLayout
import java.awt.Component
import java.io.IOException
import java.net.URL
import javax.media.CannotRealizeException
import javax.media.Manager
import javax.media.NoPlayerException
import javax.media.Player
import javax.swing.JDialog
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class PanelMedios(urlMedios: URL) extends JDialog{

	  setLayout(new BorderLayout())
	  
	  Manager.setHint(Manager.LIGHTWEIGHT_RENDERER,true)
	  
	  try{
	    val reproductorMedios = Manager.createRealizedPlayer(urlMedios)
	    val video = reproductorMedios.getVisualComponent()
	    val controles = reproductorMedios.getControlPanelComponent()
	    
	    if(video != null)
	    	add(video,BorderLayout.CENTER)
     
	    if(controles != null)
	    	add(controles,BorderLayout.SOUTH)
     
	    reproductorMedios.start()
	  }catch{
	    case ex: NoPlayerException => println("No se encontro reproductor de medios")
	    case ex: CannotRealizeException => println("No se pudo realizar el reproductor de medios")
	    case ex: IOException => println("Error al leer el origen")
	  }
	  setLocationRelativeTo(null);
}
	

