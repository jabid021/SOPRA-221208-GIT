package model;

import java.util.ArrayList;
import java.util.List;

public class Parcelle {

	private Integer id;
	private int superficie;
	private Zone zone;
	
	
	public Parcelle(Integer id, int superficie, Zone zone) {
		this.id = id;
		this.superficie = superficie;
		this.zone = zone;
	}

	public Parcelle(int superficie, Zone zone) {
		this.superficie = superficie;
		this.zone = zone;
	}

	public Integer getId() {
		return id;
	}

	public int getSuperficie() {
		return superficie;
	}

	public Zone getZone() {
		return zone;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	

	@Override
	public String toString() {
		return "Parcelle [id=" + id + ", superficie=" + superficie + ", zone=" + zone + "]";
	}
	
	
	
	
}
