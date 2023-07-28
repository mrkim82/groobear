package com.groo.bear.pro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groo.bear.pro.mapper.ProPostMapper;
import com.groo.bear.pro.service.ProPostService;
import com.groo.bear.pro.service.postvo.ProPostChartVO;
import com.groo.bear.pro.service.postvo.ProPostCommentVO;
import com.groo.bear.pro.service.postvo.ProPostFeedVO;
import com.groo.bear.pro.service.postvo.ProPostUserVO;
import com.groo.bear.pro.service.postvo.ProPostVO;
import com.groo.bear.pro.service.postvo.ProPostWorkVO;
import com.groo.bear.pro.service.postvo.ProPostWritingVO;

@Service
public class ProPostServiceImpl implements ProPostService {
	@Autowired
	ProPostMapper ppm;
	
	@Override
	public ProPostVO readTopMenu(int proNo, String id) {
		return ppm.readTopMenu(proNo, id);
	}

	@Override
	public int readTopMenuCount(String id, int proNo) {
		return ppm.readTopMenuCount(id, proNo);
	}

	@Override
	public List<ProPostUserVO> readProjectParti(int proNo) {
		return ppm.readProjectParti(proNo);
	}

	@Override
	public void createPostWriting(ProPostWritingVO vo) {
		ppm.createPostWriting(vo);
	}

	@Override
	public void createPostWork(ProPostWorkVO vo) {
		ppm.createPostWork(vo);
	}

	@Override
	public List<ProPostWritingVO> readPostWriting(int postType) {
		return ppm.readPostWriting(postType);
	}

	@Override
	public List<ProPostCommentVO> readPostWritingComment(int proNo) {
		return ppm.readPostWritingComment(proNo);
	}

	@Override
	public int createPostComment(ProPostCommentVO vo) {
		ppm.createPostComment(vo);
		return vo.getComNo();
	}

	@Override
	public int deletePostComment(int comNo) {
		return ppm.deletePostComment(comNo);
	}

	@Override
	public int updatePostComment(ProPostCommentVO vo) {
		return ppm.updatePostComment(vo);
	}

	@Override
	public List<ProPostFeedVO> readFeedPost(int proNo , int postType) {
		return ppm.readFeedPost(proNo, postType);
	}

	@Override
	public int updateWorkPostStatus(ProPostWorkVO vo) {
		return ppm.updateWorkPostStatus(vo);
	}

	@Override
	public List<ProPostChartVO> readPostChart(int proNo) {
		List<ProPostChartVO> list = ppm.readPostChart(proNo);
	    List<ProPostChartVO> updatedList = new ArrayList<>();

	    for (int i = 1; i <= 5; i++) {
	        boolean found = false;
	        for (ProPostChartVO proPostChartVO : list) {
	            if (Integer.parseInt(proPostChartVO.getWorkStatus()) == i) {
	                found = true;
	                updatedList.add(proPostChartVO);
	                break;
	            }
	        }
	        if (!found) {
	            ProPostChartVO emptyVO = new ProPostChartVO();
	            emptyVO.setWorkStatus(String.valueOf(i));
	            emptyVO.setCount(0); // 나머지 값은 0으로 설정
	            updatedList.add(emptyVO);
	        }
	    }
	    return updatedList;
	}

	@Override
	public int updateProfileMemo(ProPostUserVO vo) {
		return ppm.updateProfileMemo(vo);
	}

	@Override
	public int deleteProPost(int proPostNo) {
		return ppm.deleteProPost(proPostNo);
	}

}
