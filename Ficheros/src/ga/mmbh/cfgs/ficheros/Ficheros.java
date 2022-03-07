package ga.mmbh.cfgs.ficheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import ga.mmbh.cfgs.ficheros.models.Show;

public class Ficheros {
	
	private final List<Show> showsList = new ArrayList<>();
	
	public static void main(String[] args) {
		File file = new File("resources/netflix_titles.csv");
		Scanner scanner = null;
		try {
			scanner = new Scanner(file, "UTF-8");
			scanner.nextLine(); // Para omitir el primero
			
			int i = 0;
			while (scanner.hasNextLine() && i < 1) {
				String line = scanner.nextLine();
				
				List<String> list = new ArrayList<>();
				
				Iterator<String> iterator = Arrays.asList(line.split(",")).iterator();
				
				int z = 0;
				boolean foundComma = false;
				boolean firstCommaFounded = true;
				while (iterator.hasNext()) {
					String value = iterator.next();
					if (value.contains("\"")) foundComma = !foundComma;
					
					if (foundComma) {
						if (firstCommaFounded) {
							list.add(value);
							firstCommaFounded = false;
						} else {
							list.set(z, list.get(z) + value);
						}
					} 
					
					if (!foundComma && value.contains("\"")) {
						list.set(list.size() - 1, list.get(list.size() - 1) + value);
					} else if (!foundComma) {
						list.add(value);
						firstCommaFounded = true;
						z++;
					}
					
				}
				
				i++;
				
				for (String string : list) {
					System.out.println(string);
				}
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		scanner.close();
	}
	
}
