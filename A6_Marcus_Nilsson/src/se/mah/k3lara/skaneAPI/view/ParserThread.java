package se.mah.k3lara.skaneAPI.view;

import java.util.ArrayList;

import se.mah.k3lara.skaneAPI.control.Constants;
import se.mah.k3lara.skaneAPI.model.Journey;
import se.mah.k3lara.skaneAPI.model.Journeys;
import se.mah.k3lara.skaneAPI.xmlparser.Parser;

public class ParserThread extends Thread{
	private TestGUI testgui;
	private String input1;
	private String input2;
	
	public ParserThread(TestGUI testgui_in, String input_in) {
		this.testgui = testgui_in;
		this.input1 = input_in;
	}
	
	public ParserThread(TestGUI testgui_in, String input1_in, String input2_in) {
		this.testgui = testgui_in;
		this.input1 = input1_in;
		this.input2 = input2_in;
	}
	
	@Override
	public void run() {
		if (input2 == null) {
			testgui.updateTextArea( Parser.getStationsFromURL(input1) );
		} else {
			String url = Constants.getURL(input1, input2, 1);
			Journeys journeys = Parser.getJourneys( url );
			ArrayList<Journey> journeysList = journeys.getJourneys();
			testgui.updateTextArea( journeysList.get(0) );
		}
		
	}
}
