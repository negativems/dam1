package ga.mmbh.cfgs.netflixdb.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import ga.mmbh.cfgs.netflixdb.models.Show;
import ga.mmbh.cfgs.netflixdb.NetflixApp;

public class ShowManager {

	private final List<Show> showsList = new ArrayList<>();
	
	public ShowManager() {
		loadShows();
	}
	
	private void loadShows() {
		File file = new File("resources/netflix_titles.csv");
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(file, "UTF-8");
			scanner.nextLine(); // Skip the first one
			
			// Iterates every row of the csv
			int i = 0;
			while (scanner.hasNextLine() && i < 20) {
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
							z++;
							continue;
						}
					}
					
					rowList.add(value);
					z++;
				}
				
				Show show = new Show(rowList.toArray(new String[0]));
				showsList.add(show);
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
//		System.out.println("Hay " + showsList.size() + " shows:");
		
//		for (int i = 0; i < 10; i++) {
//			Show show = showsList.get(i);
//			System.out.println(showsList.get(i).getDescription());
//		}
		
		scanner.close();
	}
	
	
	public List<Show> getShows() {
		return this.showsList;
	}
	
	
}
