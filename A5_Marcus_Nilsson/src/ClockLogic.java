
public class ClockLogic implements ClockInterface{
	private DigitalClockGUI clockGUI;
	private boolean alarmSet;
	private int alarmHour;
	private int alarmMinute;
	
	public ClockLogic(DigitalClockGUI clockIn) {
		this.clockGUI = clockIn;
		
		// We will need another thread were all the updating and repainting and updating is done 
		Thread thread = new ClockThread(this); //This creates a thread
		thread.setName("UpdateThread");
		thread.start(); //this starts a thread when ok with preparations etc
		//the method run() in the thread is called when the thred starts.
	}
	
	public void setAlarm(int hours, int minutes){
		if (hours >= 0 && hours < 24 && minutes >= 0 && minutes < 60){
			this.alarmHour = hours;
			this.alarmMinute = minutes;
			alarmSet = true;
			clockGUI.alarmSet(alarmHour, alarmMinute);
		}
	}
	public void clearAlarm(){
		alarmSet = false;
		clockGUI.alarmClear();
	}

	@Override
	public void update(int hourIn, int minuteIn, int secondIn) {
		clockGUI.setTime(hourIn, minuteIn, secondIn);
		if (alarmSet) {
			if (alarmHour == hourIn && alarmMinute == minuteIn) {
				clearAlarm();
			}
		}
	}
}
