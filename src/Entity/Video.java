package Entity;

public class Video extends Course {
	private String type;
	private String localpath;

	public Video() {
		super();
	}

	public Video(int courseid, int coachid, String type, String localpath) {
		super(courseid, coachid);
		this.type = type;
		this.localpath = localpath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocalpath() {
		return localpath;
	}

	public void setLocalpath(String localpath) {
		this.localpath = localpath;
	}

}
