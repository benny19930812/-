package _35_register.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;

public class MemberBean implements Serializable {
	private static final long serialVersionUID = 1L;
	Integer pKey;
	String memberId;
	String name;
	String password;
	String address;
	String email;
	String tel;
	String userType;
	Timestamp registerTime;
	Double totalAmt;
	Double ticketAmt;
	Double productAmt;
	Double classAmt;
	Double reservationQut;
	Double artshopQut;
	Double classQut;
	Integer preference;

	public MemberBean(Integer pKey, String memberId, String name, String password, String address, String email,
			String tel, String userType, Timestamp registerTime, Double totalAmt, Double ticketAmt, Double productAmt, 
			Double classAmt, Double reservationQut, Double artshopQut, Double classQut, Integer preference) {
		
		this.pKey = pKey;
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.address = address;
		this.email = email;
		this.tel = tel;
		this.userType = userType;
		this.registerTime = registerTime;
		this.totalAmt = totalAmt;
		this.ticketAmt = ticketAmt;
		this.productAmt = productAmt;
		this.classAmt = classAmt;
		this.reservationQut = reservationQut;
		this.artshopQut = artshopQut;
		this.classQut = classQut;
		this.preference = preference;
	}


	public MemberBean() {
	}


	public Integer getpKey() {
		return pKey;
	}


	public void setpKey(Integer pKey) {
		this.pKey = pKey;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	public Timestamp getRegisterTime() {
		return registerTime;
	}


	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}


	public Double getTotalAmt() {
		return totalAmt;
	}


	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}


	public Double getTicketAmt() {
		return ticketAmt;
	}


	public void setTicketAmt(Double ticketAmt) {
		this.ticketAmt = ticketAmt;
	}


	public Double getProductAmt() {
		return productAmt;
	}


	public void setProductAmt(Double productAmt) {
		this.productAmt = productAmt;
	}


	public Double getClassAmt() {
		return classAmt;
	}


	public void setClassAmt(Double classAmt) {
		this.classAmt = classAmt;
	}


	public Double getReservationQut() {
		return reservationQut;
	}


	public void setReservationQut(Double reservationQut) {
		this.reservationQut = reservationQut;
	}


	public Double getArtshopQut() {
		return artshopQut;
	}


	public void setArtshopQut(Double artshopQut) {
		this.artshopQut = artshopQut;
	}


	public Double getClassQut() {
		return classQut;
	}


	public void setClassQut(Double classQut) {
		this.classQut = classQut;
	}


	public Integer getPreference() {
		return preference;
	}


	public void setPreference(Integer preference) {
		this.preference = preference;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
