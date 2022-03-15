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
	
	private final static List<Show> showsList = new ArrayList<>();
	
	public static void main(String[] args) {
		File file = new File("resources/netflix_titles.csv");
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(file, "UTF-8");
			scanner.nextLine(); // Para omitir el primero
			
			int i = 0;
			// Iterates every row of the csv
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				List<String> rowList = new ArrayList<>();
				Iterator<String> iterator = Arrays.asList(line.split(",")).iterator();
				
				int z = 0;
				boolean insideList = false;
				boolean firstListElement = false;
				boolean lastListElement = false;
				
				// Iterates every element (separated by comma) on the row
				while (iterator.hasNext()) {
					String value = iterator.next().replaceAll("\"\"", "'");
					
					firstListElement = value.contains("\"") && !insideList;
					lastListElement = value.contains("\"") && insideList;
					
					// Toggle insideList boolean every time '"' is founded.
					if (value.contains("\"")) {
						insideList = !insideList;
					}
					
					if (insideList || lastListElement) {
						if (firstListElement) {
							if (value.charAt(value.length() - 1) == '"') {
								insideList = !insideList;
							}
							
							rowList.add(value + ",");
							if (rowList.size() - 1 > z) z++;
							continue;
						} else if (!lastListElement) {
							rowList.set(z, rowList.get(z) + value + ",");
							continue;
						} else {
							rowList.set(z, rowList.get(z) + value);
							System.out.println(rowList.get(z));
							z++;
							continue;
						}
					}
					
					System.out.println(value);
					rowList.add(value);
					z++;
				}
				
				Show show = new Show(rowList.toArray(new String[0]));
				showsList.add(show);
				
				i++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Hay " + showsList.size() + " shows:");
		
//		for (int i = 0; i < 10; i++) {
//			Show show = showsList.get(i);
//			System.out.println(showsList.get(i).getTitle());
//		}
		
		scanner.close();
	}
	
}
