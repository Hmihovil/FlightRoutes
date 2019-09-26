package domaci209;

import java.io.*;
import java.util.Scanner;

import org.json.simple.*;
import org.json.simple.parser.*;

public class Aerodrom {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"Frankfurt - FRA\nLondon - LHR\nAmsterdam - AMS\nKrakow - KRK\nParis - CDG\nPrague - PRG\nZurich - ZRH\nBelgrade - BEG\n");
		System.out.print("Odakle putujete: ");
		String polazak = sc.nextLine();
		System.out.print("Dokle putujete: ");
		String dolazak = sc.nextLine();
		System.out.println();
		JSONParser parser = new JSONParser();
		try {
			Double lati1 = null;
			Double long1 = null;
			Double lati2 = null;
			Double long2 = null;
			FileReader fr = new FileReader("airports.json");
			JSONArray array = (JSONArray) parser.parse(fr);
			for (Object o : array) {
				JSONObject airport = (JSONObject) o;
				String iata = (String) airport.get("iata");
				if (iata.equals(polazak)) {
					lati1 = (Double) airport.get("latitude");
					long1 = (Double) airport.get("longitude");
					String city = (String) airport.get("city");
					System.out.print(" " + city + " ");
				}
				if (iata.equals(dolazak)) {
					lati2 = (Double) airport.get("latitude");
					long2 = (Double) airport.get("longitude");
					String city2 = (String) airport.get("city");
					System.out.print(" " + city2 + " ");
				}
			}
			System.out.println("\n Ukupno: " + distance(lati1, long1, lati2, long2, "K") + " km");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	private static int distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		} else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
					+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			if (unit == "K") {
				dist = dist * 1.609344;
			}
			return (int) (dist);
		}

	}
}
