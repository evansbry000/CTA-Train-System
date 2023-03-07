package project;

public class route {
	//Bryan Evans 12/10/2021
	//The class responsible for the implementation of routes in the program.
	
	public String endstop;
	public String train;
	public String startstop;
	public String transferstop;
	public String transfertrain;
	
	public route() {
		startstop = "Train Station";
		train = "Train";
		endstop = "Train Station";
		transferstop = "Not Needed";
		transfertrain = "Not Needed";
	}
	public route(String startstop, String train, String endstop, String transferstop, String transfertrain) {
		setstartstop(startstop);
		settrain(train);
		setendstop(endstop);
		settransferstop(transferstop);
		settransfertrain(transfertrain);
	}
	
	public String getstartstop() {
		return startstop;
	}
	public String gettrain() {
		return train;
	}
	public String getendstop() {
		return endstop;
	}
	public String gettransferstop() {
		return transferstop;
	}
	public String gettransfertrain() {
		return transfertrain;
	}
	
	public void setstartstop(String startstop) {
		this.startstop = startstop;
	}
	public void settrain(String train) {
		this.train = train;
	}
	public void setendstop(String endstop) {
		this.endstop = endstop;
	}
	public void settransferstop(String transferstop) {
		this.transferstop = transferstop;
	}
	public void settransfertrain(String transfertrain) {
		this.transfertrain = transfertrain;
	}
	
	public String toString() {
		return startstop+","+train+","+endstop+","+transferstop+","+transfertrain;
	}
	
	public boolean equals(route r) {
		if(r.startstop==startstop && r.endstop==endstop && r.transferstop==transferstop && r.transfertrain==transfertrain) {
			return true;
		}else {
			return false;
		}
	}
	
	public String trip() {
		return "Trip from "+ startstop + " to " + endstop+ ": Take "+train+". Transfer Station: "+ transferstop+ ". Transfer Train: "+ transfertrain+".";
	}

}
