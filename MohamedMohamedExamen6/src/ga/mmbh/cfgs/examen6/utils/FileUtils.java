package ga.mmbh.cfgs.examen6.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

	/**
	 * Crea un archivo a partir de su nombre
	 * @param fileName Nombre del archivo con su extensión
	 * @return
	 */
	public static File createFile(String fileName) {
		File file = new File("resources/" + fileName);
		
		try {
			if (file.exists()) file.delete();
			if (!file.createNewFile()) {
				System.out.println("No se ha podido crear el archivo: " + file.getName());
				return null;
			}
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error");
			e.printStackTrace();
			return null;
		}

		return file;
	}

	/**
	 * Escribe datos en un archivo
	 * @param file Archivo donde se va a escribir
	 * @param data Datos a introducir
	 */
	public static void writeToFile(File file, String data) {
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(data);
			
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.out.println("Ha ocurrido un error");
			e.printStackTrace();
		}
	}
}
