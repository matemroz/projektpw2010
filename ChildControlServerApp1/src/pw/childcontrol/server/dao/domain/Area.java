package pw.childcontrol.server.dao.domain;

import java.util.List;

public class Area {
	
	public Area(){
		
	}

	/**
	 * Method adds new Point to the points list of area.
	 * 
	 * @param areaPoint - area point to be added
	 */
	public void addAreaPoint(AreaPoint areaPoint){
		
	}
	
	/**
	 * Method lists points of area.
	 * 
	 * @param idChild
	 *            indywidualny numer dziecka
	 * @param point
	 *            punkt z danymi o położeniu geograficznym
	 * @return points list on success, in other way it returns null
	 */
	public List<AreaPoint> getAreaPoints(){
	return null;	
	}
	
	/*
	 * MIN_AREA_POINTS is 3, because area needs to have at least 3 points to be created.
	 */
	private static final int MIN_AREA_POINTS = 3;
	
	private List<AreaPoint> areaPoints;
}
