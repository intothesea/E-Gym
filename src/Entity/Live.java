package entity;
/**
 * Title: Live
 * Description: The entity class for live courses.
 *
 * @author MingdaJia
 * @version 1.0.1
 */
public class Live extends Course {
	private String startTime;
	private String endTime;

	public Live() {
		super();
	}

	public Live(int courseid, int coachid, String starttime, String endtime) {
		super(courseid, coachid);
		this.startTime = starttime;
		this.endTime = endtime;
	}

	public String getStarttime() {
		return startTime;
	}

	public void setStarttime(String starttime) {
		this.startTime = starttime;
	}

	public String getEndtime() {
		return endTime;
	}

	public void setEndtime(String endtime) {
		this.endTime = endtime;
	}

}
