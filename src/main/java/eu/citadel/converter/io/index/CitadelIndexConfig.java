package eu.citadel.converter.io.index;

import java.util.Date;

public class CitadelIndexConfig {
	private long userId;
	private String saveFileUrl;
	private String saveIndexUrl;
	private String charset;
	private String datasetFile;
	private int type;
	private String location, language, publisher, source, licence, title, description;
	private Date releaseDate;
	private float latitude, longitude;

	public String getDatasetFile() {
		return datasetFile;
	}
	public void setDatasetFile(String datasetFile) {
		this.datasetFile = datasetFile;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getLicence() {
		return licence;
	}
	public void setLicence(String licence) {
		this.licence = licence;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getSaveFileUrl() {
		return saveFileUrl;
	}
	public void setSaveFileUrl(String saveFileUrl) {
		this.saveFileUrl = saveFileUrl;
	}
	public String getSaveIndexUrl() {
		return saveIndexUrl;
	}
	public void setSaveIndexUrl(String saveIndexUrl) {
		this.saveIndexUrl = saveIndexUrl;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
}
