/**
@author      Mateusz Mróz
@version	 1.0
*/

package pw.childcontrol.server.database.hb.entity;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Area")
@SequenceGenerator(name = "SEQ_AREA", sequenceName = "AREA_SEQUENCE", allocationSize = 1)
public class Area implements Serializable{

	private static final long serialVersionUID = -1368610416389160733L;
	
	@Id
	private long idArea;
	
	private long idAreaPoint;
	private long idChild;
	private AreaPoint areaPoint;

	Area(){
		
	}
	
	public void setAreaPoint(AreaPoint areaPoint){
    	this.areaPoint = areaPoint;
    }
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @PrimaryKeyJoinColumn
	public AreaPoint getAreaPoint(){
    	return areaPoint;
    }
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AREA")
	public long getIdArea() {
		return idArea;
	}

	public void setIdArea(long idArea) {
		this.idArea = idArea;
	}

	public long getIdAreaPoint() {
		return idAreaPoint;
	}

	public void setIdAreaPoint(long idAreaPoint) {
		this.idAreaPoint = idAreaPoint;
	}

	public long getIdChild() {
		return idChild;
	}

	public void setIdChild(long idChild) {
		this.idChild = idChild;
	}
}
