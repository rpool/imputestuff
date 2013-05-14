package nl.vu.psy.maxpc;

public class SnpScore {
	private String rs;
	private long pos;
	private double apc;
	private double sdapc;

	public SnpScore(String rs, long pos, double apc, double sdapc) {
		this.rs = rs;
		this.pos = pos;
		this.apc = apc;
		this.sdapc = sdapc;
	}

	public String getRs() {
		return rs;
	}

	public long getPos() {
		return pos;
	}

	public double getApc() {
		return apc;
	}
	
	public String toString(){
		return rs + " " + pos + " " + apc + " " + sdapc;
	}

	public double getSdapc() {
		return sdapc;
	}
}
