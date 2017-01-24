
import scala.swing._
import event._
import Swing._
import java.net.URL
import java.net.MalformedURLException
import java.io.File
import javax.swing.JPanel
import java.awt.Point


object Biblioteca extends SimpleGUIApplication{
	var urlMedios: URL = null
	val chooser = new FileChooser
	var archivos: List[String] = Nil
	var urlArchivos: List[URL] = Nil
	var listView = new ListView(archivos)
 
	def top = new MainFrame {
		title = "Scala Media Player"
		setLocationRelativeTo(this)
  
		//Menu Principal
		menuBar = new MenuBar;
		val archivo = new Menu("Archivo");
		menuBar.contents += archivo

		archivo.contents += new MenuItem(Action("Abrir"){
			if (chooser.showOpenDialog(archivo) == FileChooser.Result.Approve){
				try{
					urlMedios = chooser.selectedFile.toURL
				}catch{
					case ex: MalformedURLException => println("No se pudo crear URL para el archivo")
				}
    
				val panelMedios = new PanelMedios(urlMedios)
				panelMedios.setSize(300,300)
				panelMedios.setVisible(true)
				
				urlArchivos = urlArchivos.::(urlMedios)
				archivos = archivos.::(urlMedios.getFile)
				listView = new ListView(archivos)
				uiUpdate
				ManejadorArchivos.toFile("biblioteca.txt",archivos)
			}
		})
		archivo.contents += new MenuItem(Action("Salir"){
			exit
		})

		val biblioteca = new MenuItem(Action("Biblioteca"){
				archivos = ManejadorArchivos.fromFile("biblioteca.txt")
				listView = new ListView(archivos)
				uiUpdate
		})
		menuBar.contents += biblioteca
		
		//Biblioteca
		val botonReproducir = new Button("Reproducir")
 
		def uiUpdate{
			contents = new BoxPanel(Orientation.Vertical){
				contents += new ScrollPane(listView)
				contents += botonReproducir
			}
		}
		uiUpdate
  
		listenTo(botonReproducir)
		reactions += {
			case ButtonClicked(botonReproducir) => {
				try{
					val file = new File(listView.selection.items(0))
					val panelMedios = new PanelMedios(file.toURL)
					panelMedios.setSize(300,300)
					panelMedios.setVisible(true)
				}catch{
					case ex: MalformedURLException => println("No se pudo crear URL para el elemento")
				}
			}
		}
	}
}

