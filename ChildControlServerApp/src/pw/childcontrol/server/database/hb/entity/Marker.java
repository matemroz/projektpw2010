/**
@author      Mateusz Mróz
@version	 1.0
*/

package pw.childcontrol.server.database.hb.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Marker")
@SequenceGenerator(name = "SEQ_MARKER", sequenceName = "MARKER_SEQUENCE", allocationSize = 1)
public class Marker implements Serializable {

	private static final long serialVersionUID = 5126945551644416118L;

	@Id
	private long idMarker;

	private float x;
	private float y;
	private Date datetime;
	private Children child;
	
	Marker() {

	}
	
	public void setChild(Children child){
		this.child = child;
	}
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @PrimaryKeyJoinColumn
	public Children getChild(){
		return child;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MARKER")
	public long getIdMarker() {
		return idMarker;
	}

	public void setIdMarker(long idMarker) {
		this.idMarker = idMarker;
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

	@Column(name = "datetime", nullable = false)
	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
}
