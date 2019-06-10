package Model;

public class Upload {

	private String contact_id;
	private String first_name;
	private String last_name;
	private String photo;
	public String getContact_id() {
		return contact_id;
	}
	public void setContact_id(String contact_id) {
		this.contact_id = contact_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Upload() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Upload(String contact_id,String first_name,String last_name,String photo) {
		this.contact_id=contact_id;
		this.first_name=first_name;
		this.last_name=last_name;
		this.photo=photo;
	}
}
