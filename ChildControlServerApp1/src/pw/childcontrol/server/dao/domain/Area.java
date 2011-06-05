package pw.childcontrol.server.dao.domain;

import java.util.List;

public class Area {
	private AreaPoint areaPoint;
	private double r;//Radius

	public Area(AreaPoint areaPoint, double r) {
		this.areaPoint = areaPoint;
		this.r = r;
	}
	
	public AreaPoint getAreaPoint() {
		return this.areaPoint;
	}
	
	public boolean setAreaPoint(AreaPoint areaPoint) {
		if(areaPoint == null) {
			return false;
		}
		
		this.areaPoint = areaPoint;
		return true;
	}
	
	public double getRadius() {
		return this.r;
	}
	
	public boolean setRadius(double r) {
		if (r <= 0) {
			return false;
		}
		
		this.r = r;
		return true;
	}
}
