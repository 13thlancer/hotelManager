package com.whxx.hms.model.dto;

import java.util.List;

/**
 * 预授权列表
 * @ClassName: PreLicence 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年5月31日 上午9:33:36
 */
public class PreLicenceListDto {
	
	private List<PreLicenceDto> prelicenceListDto;

	public List<PreLicenceDto> getPrelicenceListDto() {
		return prelicenceListDto;
	}

	public void setPrelicenceListDto(List<PreLicenceDto> prelicenceListDto) {
		this.prelicenceListDto = prelicenceListDto;
	}
	
}
