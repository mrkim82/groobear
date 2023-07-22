package com.groo.bear.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.pro.mapper.ProPublicCodeMapper;
import com.groo.bear.pro.service.ProGroupVO;
import com.groo.bear.pro.service.PublicCodeService;
import com.groo.bear.pro.service.PublicCodeVO;

@Service
public class PublicCodeServiceImpl implements PublicCodeService {
	
	@Autowired
	ProPublicCodeMapper pccm;
	
	@Override
	public List<PublicCodeVO> readPublicCodeColorAll() {
		return pccm.readPublicCodeColorAll();
	}

	@Override
	public int updateProMemColor(ProGroupVO vo) {
		return pccm.updateProMemColor(vo);
	}

	@Override
	public List<PublicCodeVO> readPublicCodeWorkAll() {
		return pccm.readPublicCodeWorkAll();
	}

}
