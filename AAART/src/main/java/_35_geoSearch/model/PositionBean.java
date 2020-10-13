package _35_geoSearch.model;

import java.io.Serializable;

public class PositionBean implements Serializable, Comparable<PositionBean>{
	
	private static final long serialVersionUID = 1L;

	int no;
	String title;
	String time;
	String uid;
	String city;
	String district;	
	String village;	
	String address;
	double latitude;	
	double longitude;
	double distance;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public PositionBean() {
		super();
	}

	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getUid() {
		return uid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	@Override
	public int compareTo(PositionBean other) {
		// TODO Auto-generated method stub
		if(this.distance==other.distance) {
			return 0;
		}else if(this.distance>other.distance) {
			return 1;
		}else {
			return -1;
		}
	}
	
//	@Override
//	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		builder.append("PositionBean [no=");
//		builder.append(no);
//		builder.append(", uid=");
//		builder.append(uid);
//		builder.append(", city=");
//		builder.append(city);
//		builder.append(", district=");
//		builder.append(district);
//		builder.append(", village=");
//		builder.append(village);
//		builder.append(", address=");
//		builder.append(address);
//		builder.append("]");
//		return builder.toString();
//	}
	
}
