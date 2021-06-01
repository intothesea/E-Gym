package entity;
/**
 * Title: video
 * Description: The entity class for videos.
 *
 * @author MingdaJia
 * @version 1.0.1
 */
public class Video extends Course {
	private String type;
	private String localPath;

	public Video() {
		super();
	}

	public Video(int courseid, int coachid, String type, String localpath) {
		super(courseid, coachid);
		this.type = type;
		this.localPath = localpath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocalpath() {
		return localPath;
	}

	public void setLocalpath(String localpath) {
		this.localPath = localpath;
	}

}
