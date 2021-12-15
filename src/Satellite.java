import java.util.Date;

public class Satellite {
	private String name;
	private int id;
	private double latitude;
	private double longitude;
	private double altitude;
	private double velocity;
	private String visiblity;
	private String footprint;
	private long timestamp;
	private double daynum;
	private double solar_lat;
	private double solar_lon;
	private String unit;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public String getVisiblity() {
		return visiblity;
	}

	public void setVisiblity(String visiblity) {
		this.visiblity = visiblity;
	}

	public String getFootprint() {
		return footprint;
	}

	public void setFootprint(String footprint) {
		this.footprint = footprint;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public double getDaynum() {
		return daynum;
	}

	public void setDaynum(double daynum) {
		this.daynum = daynum;
	}

	public double getSolar_lat() {
		return solar_lat;
	}

	public void setSolar_lat(double solar_lat) {
		this.solar_lat = solar_lat;
	}

	public double getSolar_lon() {
		return solar_lon;
	}

	public void setSolar_lon(double solar_lon) {
		this.solar_lon = solar_lon;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		// represents the string representation of this class
		return new Date(timestamp).toString() + ", Location (" + this.latitude + "," + this.longitude + ")";
	}

}
