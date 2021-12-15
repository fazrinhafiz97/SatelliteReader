import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Program {

	// international space satellite station id
	private static final int ISS_ID = 25544;
	// api url
	private static final String API_URL = "https://api.wheretheiss.at/v1/satellites/" + ISS_ID
			+ "/positions?timestamps=";

	// 1. to convert the given date and create the timestamps
	// 2. call the api with time stamps
	// 3. parse the json and show the results

	public static void main(String[] args) {
		// read the date time stamp from command line
		String datetime = args[0];

		// parse the date time and get the time stamps in the format for API
		String stamps = getTimeStamps(datetime);

		// if user enters date in wrong format. exit the application
		if (stamps == null) {
			return;
		}
		String resultJson = null;
		try {
			// call the api and read the json
			resultJson = getJson(stamps);

			// parse the json and show the results
			if (resultJson != null) {
				parseJson(resultJson);

			} else {
				// if the api returns results not as expected
				System.err.println("Error getting data");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String getTimeStamps(String datetime) {
		try {
			// convert the user entered date in a format
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mmaa");
			// convert string to date
			Date date = formatter.parse(datetime);
			String timestamp = "";

			// run the loop so that we have time
			// 1 hour before and 1 hour after the time specified
			// where time interval between each date is 10 minutes
			for (int i = 60; i >= -60; i = i - 10) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - i);
				timestamp += calendar.getTimeInMillis() + ",";

			}

			// return the time stamp with comma removed at the end
			return timestamp.substring(0, timestamp.length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return null, if the date is not formatted correctly
		return null;
	}

	private static String getJson(String datetime) throws IOException, InterruptedException {

		// create the api by inserting the date time stamps in it
		String dte = API_URL + datetime + "&units=miles";
		System.out.println(dte);
		// create a http request
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(dte)).build();
		// call the request
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		// if api returns ok result
		if (response.statusCode() == HttpsURLConnection.HTTP_OK) {
			// return the json result
			return response.body();
		}

		return null;
	}

	private static void parseJson(String resultJson) {
		// this will parse json
		Gson gson = new Gson();
		// convert the json into the list of Satelite objects
		ArrayList<Satellite> list = gson.fromJson(resultJson, new TypeToken<ArrayList<Satellite>>() {
		}.getType());

		// iterate over the list and show the string representation of each object
		for (Satellite satellite : list) {
			System.out.println(satellite);
		}
	}

}
