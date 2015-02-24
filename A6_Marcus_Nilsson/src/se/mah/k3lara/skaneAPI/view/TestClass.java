package se.mah.k3lara.skaneAPI.view;

import java.util.ArrayList;
import java.util.Calendar;

import se.mah.k3lara.skaneAPI.control.Constants;
import se.mah.k3lara.skaneAPI.model.Journey;
import se.mah.k3lara.skaneAPI.model.Journeys;
import se.mah.k3lara.skaneAPI.model.Station;
import se.mah.k3lara.skaneAPI.xmlparser.Parser;

public class TestClass {

	public static void main(String[] args) {
		/* 3: String searchURL = Constants.getURL("81116","65008",20);
		 * Skickar vilka stationer man vill �ka mellan och antalen resultat man vill ha till metoden getURL i klassen Constants.
		 * F�r tillbaka en textstr�ng som kan anv�dndas som Query.
		 */
		String searchURL = Constants.getURL("80000","81216",20); //Malm� C = 80000,  Lund C, 81216 Malm� Gatorg 80100, H�ssleholm C 93070
		System.out.println(searchURL);
		System.out.println("// Results when searching:");
		
		
		/* 3: Journeys journeys = Parser.getJourneys(searchURL);
		 * Skapar ett nytt Journeys objekt och fyller det med resor som Parser.getJourneys h�mtar och sammanst�ller utifr�n urlen "searchURL" som skapades innan 
		 */
		Journeys journeys = Parser.getJourneys(searchURL);
		for (Journey journey : journeys.getJourneys()) {
			System.out.print(journey.getStartStation()+" - ");
			System.out.print(journey.getEndStation());
			String time = journey.getDepDateTime().get(Calendar.HOUR_OF_DAY)+":"+journey.getDepDateTime().get(Calendar.MINUTE);
			System.out.println(" Departs " + time +" that is in "+journey.getTimeToDeparture()+ " minutes. And it is "+journey.getDepTimeDeviation()+" min late");
		} 
		
	   System.out.println("// Stations when searching for stations containing \"Malm\"");
	   
	   /* 3: ArrayList<Station> searchStations = new ArrayList<Station>();
	    * Deklarerar och intsierar en tom ArrayList av Station-objekt. 
	    */
		ArrayList<Station> searchStations = new ArrayList<Station>(); 
		
		/* searchStations.addAll(Parser.getStationsFromURL("Malm"));
		 * H�mtar alla stationer i malm� och stoppar dem i arraylisten ovan
		 */
		searchStations.addAll(Parser.getStationsFromURL("Malm"));
		for (Station s: searchStations){
			System.out.println(s.getStationName() +" number:" +s.getStationNbr());
		}
	}
}

