package eu.citadel.converter.io.index;

public class CitadelCityInfo implements Comparable<CitadelCityInfo> {
	private Long id;
	private String name, datasets;
	private float lat, lon;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDatasets() {
		return datasets;
	}
	public void setDatasets(String datasets) {
		this.datasets = datasets;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLon() {
		return lon;
	}
	public void setLon(float lon) {
		this.lon = lon;
	}
	@Override
	public int compareTo(CitadelCityInfo o) {
		if(o == null || o.getName() == null || getName() == null) return 0;
		return getName().compareTo(o.getName());
	}

}
