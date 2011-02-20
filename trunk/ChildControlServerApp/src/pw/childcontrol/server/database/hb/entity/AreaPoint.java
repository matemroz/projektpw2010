/**
@author      Mateusz Mróz
@version	 1.0
*/

package pw.childcontrol.server.database.hb.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "AreaPoint")
@SequenceGenerator(name = "SEQ_AREA_POINT", sequenceName = "AREA_POINT_SEQUENCE", allocationSize = 1)
public class AreaPoint implements Serializable {

	private static final long serialVersionUID = -7656978903003278307L;

	@Id
	private long idAreaPoint;

	private long idArea;
	private float x;
	private float y;
	
	AreaPoint(){
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AREA_POINT")
	public long getIdAreaPoint() {
		return idAreaPoint;
	}

	public void setIdAreaPoint(long idAreaPoint) {
		this.idAreaPoint = idAreaPoint;
	}

	public long getIdArea() {
		return idArea;
	}

	public void setIdArea(long idArea) {
		this.idArea = idArea;
	}

	@Column(name = "x", nullable = false)
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	@Column(name = "y", nullable = false)
	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
