package com.ssafy.hh.service;

import java.util.List;

import com.ssafy.hh.dto.HouseInfoDto;
import com.ssafy.hh.dto.SidoGugunCodeDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> searchApt(String keyword) throws Exception;
	List<HouseInfoDto> getAptInGugun(String gugunCode) throws Exception;
	
}
