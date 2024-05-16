package demo;

public class products {
	public int pid;
	public String pname;
	public int price;
	public String pimg;
	public int hsn_code;
	public int cat_id;
	public int disc;
	private int gstRate;

	public products(int pid, String pname, int price, String i, int hsn_code, int cat_id, int disc) {
		this.pid = pid;
		this.pname = pname;
		this.price = price;
		this.pimg = i;
		this.hsn_code = hsn_code;
		this.cat_id = cat_id;
		this.disc = disc;

	}

	public products(String pname, int price, String pimg) {
		// TODO Auto-generated constructor stub
		this.pname = pname;
		this.price = price;
		this.pimg = pimg;
	}

	public void setpid(int pid) {
		this.pid = pid;
	}

	public int getpid() {
		return pid;
	}

	public void setpname(String pname) {
		this.pname = pname;
	}

	public String getpname() {
		return pname;
	}

	public void setprice(int price) {
		this.price = price;
	}

	public int getprice() {
		return price;
	}

	public void setpimg(String pimg) {
		this.pimg = pimg;
	}

	public String getpimg() {
		return pimg;
	}

	public void sethsn_code(int hsn_code) {
		this.hsn_code = hsn_code;
	}

	public int gethsn_code() {
		return hsn_code;
	}

	public void setcat_id(int cat_id) {
		this.cat_id = cat_id;
	}

	public int getcat_id() {
		return cat_id;
	}

	public void setdiscount(int disc) {
		this.disc = disc;

	}

	public int getdiscount() {
		return disc;
	}

	public void setGstRate(int gstRate) {
		this.gstRate = gstRate;
	}

	public int getGstRate() {
		return gstRate;
	}
}
