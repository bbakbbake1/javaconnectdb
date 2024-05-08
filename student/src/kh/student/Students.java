package kh.student;


public class Students {
	private int no;
	private String sd_num;
	private String sd_name;
	private String sd_id;
	private String sd_passwd;
	private String s_num;
	private String sd_birthday;
	private String sd_phone;
	private String sd_address;

	public Students() {
		super();
	}

	public Students(int no, String sd_name, String sd_id, String sd_passwd, String s_num, String sd_address) {
		super();
		this.no = no;
		this.sd_name = sd_name;
		this.sd_id = sd_id;
		this.sd_passwd = sd_passwd;
		this.s_num = s_num;
		this.sd_address = sd_address;
	}

	public Students(int no, String sd_num, String sd_name, String sd_id, String sd_passwd, String s_num,
			String sd_birthday, String sd_phone, String sd_address) {
		super();
		this.no = no;
		this.sd_num = sd_num;
		this.sd_name = sd_name;
		this.sd_id = sd_id;
		this.sd_passwd = sd_passwd;
		this.s_num = s_num;
		this.sd_birthday = sd_birthday;
		this.sd_phone = sd_phone;
		this.sd_address = sd_address;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getSd_num() {
		return sd_num;
	}

	public void setSd_num(String sd_num) {
		this.sd_num = sd_num;
	}

	public String getSd_name() {
		return sd_name;
	}

	public void setSd_name(String sd_name) {
		this.sd_name = sd_name;
	}

	public String getSd_id() {
		return sd_id;
	}

	public void setSd_id(String sd_id) {
		this.sd_id = sd_id;
	}

	public String getSd_passwd() {
		return sd_passwd;
	}

	public void setSd_passwd(String sd_passwd) {
		this.sd_passwd = sd_passwd;
	}

	public String getS_num() {
		return s_num;
	}

	public void setS_num(String s_num) {
		this.s_num = s_num;
	}

	public String getSd_birthday() {
		return sd_birthday;
	}

	public void setSd_birthday(String sd_birthday) {
		this.sd_birthday = sd_birthday;
	}

	public String getSd_phone() {
		return sd_phone;
	}

	public void setSd_phone(String sd_phone) {
		this.sd_phone = sd_phone;
	}

	public String getSd_address() {
		return sd_address;
	}

	public void setSd_address(String sd_address) {
		this.sd_address = sd_address;
	}

	@Override
	public String toString() {
		return "no=" + no + "," + sd_num + "," + sd_name + "," + sd_id + "," + sd_passwd + "," + s_num + ","
				+ sd_birthday + "," + sd_phone + "," + sd_address;
	}

}
