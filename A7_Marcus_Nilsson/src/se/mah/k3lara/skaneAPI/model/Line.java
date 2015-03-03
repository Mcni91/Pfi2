package se.mah.k3lara.skaneAPI.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import se.mah.k3lara.skaneAPI.control.Helpers;

public class Line {
	private String line;
	private Calendar depTime;
	private String depTimeDeviation;
	
	private String name;
	private String timingPoint;
	private String stopPoint;
	private String lineTypeId;
	private String lineTypeName;
	private String towards;
	private String trainNo;
	private String deviations;
	private String runNo;
	private String depDeviationAffect;
	
	
	public String getDepDeviationAffect() {
		return depDeviationAffect;
	}
	public void setDepDeviationAffect(String depDeviationAffect) {
		this.depDeviationAffect = depDeviationAffect;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTimingPoint() {
		return timingPoint;
	}
	public void setTimingPoint(String timingPoint) {
		this.timingPoint = timingPoint;
	}
	public String getStopPoint() {
		return stopPoint;
	}
	public void setStopPoint(String stopPoint) {
		this.stopPoint = stopPoint;
	}
	public String getLineTypeId() {
		return lineTypeId;
	}
	public void setLineTypeId(String lineTypeId) {
		this.lineTypeId = lineTypeId;
	}
	public String getLineTypeName() {
		return lineTypeName;
	}
	public void setLineTypeName(String lineTypeName) {
		this.lineTypeName = lineTypeName;
	}
	public String getTowards() {
		return towards;
	}
	public void setTowards(String towards) {
		this.towards = towards;
	}
	public String getTrainNo() {
		return trainNo;
	}
	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}
	public String getDeviations() {
		return deviations;
	}
	public void setDeviations(String deviations) {
		this.deviations = deviations;
	}
	public String getRunNo() {
		return runNo;
	}
	public void setRunNo(String runNo) {
		this.runNo = runNo;
	}
	public Line() {
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public Calendar getDepTime() {
		return depTime;
	}
	public void setDepTime(Calendar depTime) {
		this.depTime = depTime;
	}
	public String getDepTimeDeviation() {
		return depTimeDeviation;
	}
	public void setDepTimeDeviation(String depTimeDeviation) {
		this.depTimeDeviation = depTimeDeviation;
	}
	
	
	/**
	 *Get minutes to departure including deviations
	 * @param int minutes before departure shown as "HH:MM" instead of "M min"
	 * @return String in format "HH:MM" or "M min"
	 * */
	public String getFormatedDeparture(int maxMinutes){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Calendar cal = depTime;
		
		//Add delay, if any
		if (!depTimeDeviation.equals("")) {
			cal.add(Calendar.MINUTE, Integer.parseInt(depTimeDeviation));
		}
		
		String departure = Helpers.timeToDeparture(dateFormat.format(cal.getTime()));
		
		if(Integer.parseInt(departure) > maxMinutes){
			departure = Helpers.formatTime(dateFormat.format(cal.getTime()));
		}else if(Integer.parseInt(departure) < 1){
			departure = "0 min";
		}else{
			departure += " min";
		}
		return departure;
	}
	
	//More methods here for the rest of the tags
	//And perhaps some special methods ????
	
}
