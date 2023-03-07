package project;

public class Lines extends Stop{
	//Bryan Evans 12/10/2021
	//The subclass of the parent class, Stop, contains the train line elements of each cell/stop in the arraylist.
	
	private int red;
	private int green;
	private int blue;
	private int brown;
	private int purple;
	private int pink;
	private int orange;
	
	public Lines() {
		red = -1;
		green = -1;
		blue = -1;
		brown = -1;
		purple = -1;
		pink = -1;
		orange = -1;
	}
	
	public Lines(String name, Double latitude, Double longitude, String desc, Boolean wheelchair, int red, int green, int blue, int brown, int purple, int pink, int orange) {
		super(name,latitude,longitude,desc, wheelchair);
		setred(red);
		setgreen(green);
		setblue(blue);
		setbrown(brown);
		setpurple(purple);
		setpink(pink);
		setorange(orange);
	}

	public int getred() {
		return red;
	}
	public int getgreen() {
		return green;
	}
	public int getblue() {
		return blue;
	}
	public int getbrown() {
		return brown;
	}
	public int getpurple() {
		return purple;
	}
	public int getpink() {
		return pink;
	}
	public int getorange() {
		return orange;
	}
	
	public void setred(int red) {
		this.red = red;
	}
	public void setgreen(int green) {
		this.green = green;
	}
	public void setblue(int blue) {
		this.blue = blue;
	}
	public void setbrown(int brown) {
		this.brown = brown;
	}
	public void setpurple(int purple) {
		this.purple = purple;
	}
	public void setpink(int pink) {
		this.pink = pink;
	}
	public void setorange(int orange) {
		this.orange = orange;
	}
	
	public String toString() {
		return super.toString()+","+red+","+green+","+blue+","+brown+","+purple+","+pink+","+orange;
	}
	
	public boolean equals(Lines l) {
		if(l.red==this.red && l.green==this.green && l.blue==this.blue && l.brown==this.brown && l.purple==this.purple && l.pink==this.pink && l.orange==this.orange) {
			return true;
		}else {
			return false;
		}
	}
}
