package com.alquiler.reservas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="databasechangeloglock")
public class Databasechangeloglock {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	int ID;
	@Column
	boolean LOCKED;
	@Column
	String LOCKGRANTED;
	@Column
	String LOCKEDBY;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public boolean isLOCKED() {
		return LOCKED;
	}
	public void setLOCKED(boolean lOCKED) {
		LOCKED = lOCKED;
	}
	public String getLOCKGRANTED() {
		return LOCKGRANTED;
	}
	public void setLOCKGRANTED(String lOCKGRANTED) {
		LOCKGRANTED = lOCKGRANTED;
	}
	public String getLOCKEDBY() {
		return LOCKEDBY;
	}
	public void setLOCKEDBY(String lOCKEDBY) {
		LOCKEDBY = lOCKEDBY;
	}
	@Override
	public String toString() {
		return "Databasechangeloglock [ID=" + ID + ", LOCKED=" + LOCKED + ", LOCKGRANTED=" + LOCKGRANTED + ", LOCKEDBY="
				+ LOCKEDBY + "]";
	}
	
	
}
