package project;

public class Stop {
	//Bryan Evans 12/10/2021
	//The parent class of the Lines class. Contains the first 5 elements of each cell/stop on the arraylist.
	
	public String name;
	public Double latitude;
	public Double longitude;
	public String desc;
	public boolean wheelchair;
	
	public Stop(){
		name = "Train Station";
		latitude = 0.0;
		longitude = 0.0;
		desc = "surface";
		wheelchair = false;
	}
	public Stop(String name, Double latitude, Double longitude, String desc, boolean wheelchair) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		setdesc(desc);
		setwheelchair(wheelchair);
	}
	
	public String getname() {
		return name;
	}
	public Double getlatitude() {
		return latitude;
	}
	public Double getlongitude() {
		return longitude;
	}
	public String getdesc() {
		return desc;
	}
	public boolean getwheelchair() {
		return wheelchair;
	}
	
	public void setname(String name) {
		this.name = name;
	}
	public void setlatitude(Double latitude) {
		this.latitude = latitude;
	}
	public void setlongitude(Double longitude) {
		this.longitude = longitude;
	}
	public void setdesc(String desc) {
		this.desc = desc;
	}
	public void setwheelchair(boolean wheelchair) {
		this.wheelchair = wheelchair;
	}
	
	public String toString() {
		return name+","+latitude+","+longitude+","+desc+","+wheelchair;
	}
	
	public boolean equals(Stop s) {
		if(s.name==name && s.latitude==latitude && s.longitude==longitude && s.wheelchair==wheelchair && s.desc==desc) {
			return true;
		}else {
			return false;
		}
	}
	public int getred() {
		Lines lines = new Lines();
		
		return lines.getred();
	}
	public int getgreen() {
		Lines lines = new Lines();
		return lines.getgreen();
	}
	public int getblue() {
		Lines lines = new Lines();
		return lines.getblue();
	}
	public int getbrown() {
		Lines lines = new Lines();
		return lines.getbrown();
	}
	public int getpurple() {
		Lines lines = new Lines();
		return lines.getpurple();
	}
	public int getpink() {
		Lines lines = new Lines();
		return lines.getpink();
	}
	public int getorange() {
		Lines lines = new Lines();
		return lines.getorange();
	}
	

}
