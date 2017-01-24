

import java.io._
import scala.io.Source

object ManejadorArchivos {
	def toFile(file: String, lista: List[String]){
		val writer = new PrintWriter(new File(file))
		for(elemento <- lista){
			writer.write(elemento+"\n")
		}
		writer.close()
	}
 
	def fromFile(file: String) = 
		Source.fromFile(file).getLines.toList                 

	
}
