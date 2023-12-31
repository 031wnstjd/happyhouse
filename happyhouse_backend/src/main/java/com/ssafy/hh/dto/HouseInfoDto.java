package com.ssafy.hh.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value ="HouseInfoDto (아파트 종합 정보)", description = "아파트번호, 아파트명, 지역정보 등이 담긴 Class")
public class HouseInfoDto {
	
	@ApiModelProperty(value = "아파트 코드번호")
	private int aptCode;
	@ApiModelProperty(value = "아파트명")
	private String aptName;
	@ApiModelProperty(value = "법정동 코드번호")
	private String dongCode;
	@ApiModelProperty(value = "법정동 이름")
	private String dongName;
	@ApiModelProperty(value = "시,도 이름")
	private String sidoName;
	@ApiModelProperty(value = "구,군 이름")
	private String gugunName;
	@ApiModelProperty(value = "건축연도")
	private int buildYear;
	@ApiModelProperty(value = "지번(주소)")
	private String jibun;
	@ApiModelProperty(value = "위도")
	private String lat;
	@ApiModelProperty(value = "경도")
	private String lng;
	@ApiModelProperty(value = "아파트 이미지")
	private String img;
	@ApiModelProperty(value = "최근 거래가격")
	private String recentPrice;

	public int getAptCode() {
		return aptCode;
	}

	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public int getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getRecentPrice() {
		return recentPrice;
	}

	public void setRecentPrice(String recentPrice) {
		this.recentPrice = recentPrice;
	}

	@Override
	public String toString() {
		return "HouseInfoDto [aptCode=" + aptCode + ", aptName=" + aptName + ", dongCode=" + dongCode + ", dongName="
				+ dongName + ", sidoName=" + sidoName + ", gugunName=" + gugunName + ", buildYear=" + buildYear
				+ ", jibun=" + jibun + ", lat=" + lat + ", lng=" + lng + ", img=" + img + ", recentPrice=" + recentPrice
				+ "]";
	}

}
