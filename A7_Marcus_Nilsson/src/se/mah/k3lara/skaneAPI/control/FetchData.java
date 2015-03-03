package se.mah.k3lara.skaneAPI.control;

import java.util.Calendar;

import se.mah.k3lara.skaneAPI.model.Lines;
import se.mah.k3lara.skaneAPI.model.Station;
import se.mah.k3lara.skaneAPI.view.GUI;
import se.mah.k3lara.skaneAPI.xmlparser.Parser;

public class FetchData extends Thread{
	private GUI gui;
	private String stationID;
	
	public FetchData(GUI gui_in, String stationID_in) {
		this.gui = gui_in;
		this.stationID = stationID_in;
	}
	
	@Override
	public void run() {
		while(true) {
			Lines lines = Parser.getStationResults(new Station(stationID));
			gui.updateTable(lines);
			try {
				Thread.sleep(60000); //Update 1 time/min
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}