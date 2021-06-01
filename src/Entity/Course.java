package entity;
/**
 * Title: Course
 * Description: The entity class for recorded courses.
 *
 * @author MingdaJia
 * @version 1.0.1
 */
public class Course {
	private int courseid;
	private int coachid;

	public Course() {
		super();
	}

	public Course(int courseid, int coachid) {
		super();
		this.courseid = courseid;
		this.coachid = coachid;
	}

	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public int getCoachid() {
		return coachid;
	}

	public void setCoachid(int coachid) {
		this.coachid = coachid;
	}

}
