package Entity;

public class Live extends Course {
	private String starttime;
	private String endtime;

	public Live() {
		super();
	}

	public Live(int courseid, int coachid, String starttime, String endtime) {
		super(courseid, coachid);
		this.starttime = starttime;
		this.endtime = endtime;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

}
