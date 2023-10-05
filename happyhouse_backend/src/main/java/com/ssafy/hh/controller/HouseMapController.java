package com.ssafy.hh.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.hh.dto.HouseInfoDto;
import com.ssafy.hh.dto.SidoGugunCodeDto;
import com.ssafy.hh.service.HouseMapService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/map")
public class HouseMapController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	@Autowired
	private HouseMapService haHouseMapService;
	
	@ApiOperation(value = "시,도 목록을 반환한다", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.debug("sido : {}", haHouseMapService.getSido());
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 시,도의 하위 구,군 목록을 반환한다", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		logger.debug("gugun : {}", haHouseMapService.getGugunInSido(sido));
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 구,군의 하위 법정동 목록을 반환한다", response = List.class)
	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "해당 구,군 하위의 아파트 목록을 반환한다", response = List.class)
	@GetMapping("/apt/{gugunCode}")
	public ResponseEntity<List<HouseInfoDto>> gugunApt(@PathVariable String gugunCode) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInGugun(gugunCode), HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 법정동 하위의 아파트 목록을 반환한다", response = List.class)
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInDong(dong), HttpStatus.OK);
	}
	
	@ApiOperation(value = "해당 keyword로 검색한 결과의 아파트 목록을 반환한다", response = List.class)
	@GetMapping("{keyword}")
	public ResponseEntity<List<HouseInfoDto>> searchApt(@PathVariable String keyword) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.searchApt(keyword), HttpStatus.OK);
	}
	
}
