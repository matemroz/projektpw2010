package pw.childcontrol.server.dao.domain;

import java.util.Date;

public class Child {
	private int idChild;
	private AreaPoint areaPoint;
	private String childName;
	private String imei;
	private String key;
	private Date dateOfBirth;

	public Child(int idChild){
		this.idChild = idChild;
	}
	
	public int getIdChild() {
		return idChild;
	}

	public void setIdChild(int idChild) {
		this.idChild = idChild;
	}

	public AreaPoint getIdArea() {
		return areaPoint;
	}

	public void setIdArea(AreaPoint areaPoint) {
		this.areaPoint = areaPoint;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
