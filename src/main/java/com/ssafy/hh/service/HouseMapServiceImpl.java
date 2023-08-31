package com.ssafy.hh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.hh.dto.HouseInfoDto;
import com.ssafy.hh.dto.SidoGugunCodeDto;
import com.ssafy.hh.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private HouseMapMapper houseMapMapper;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return houseMapMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return houseMapMapper.getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return houseMapMapper.getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return houseMapMapper.getAptInDong(dong);
	}
	
	@Override
	public List<HouseInfoDto> searchApt(String keyword) throws Exception {
		return houseMapMapper.searchApt(keyword);
	}
	
	@Override
	public List<HouseInfoDto> getAptInGugun(String gugunCode) throws Exception {
		return houseMapMapper.getAptInGugun(gugunCode);
	}
}
