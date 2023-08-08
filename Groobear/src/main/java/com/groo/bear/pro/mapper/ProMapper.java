package com.groo.bear.pro.mapper;

import java.util.List;
import java.util.Map;

import com.groo.bear.pro.service.provo.ProAuthVO;
import com.groo.bear.pro.service.provo.ProGroupManageVO;
import com.groo.bear.pro.service.provo.ProGroupVO;
import com.groo.bear.pro.service.provo.ProHideVO;
import com.groo.bear.pro.service.provo.ProPartiAlarmVO;
import com.groo.bear.pro.service.provo.ProPerSettingVO;
import com.groo.bear.pro.service.provo.ProUsersVO;
import com.groo.bear.pro.service.provo.ProVO;

public interface ProMapper {
	//프로젝트 생성
	public void createPro(Map<String, Object> map);
	
	//프로젝트 조회
	public List<ProVO> readProject(Map<String, Object> map);
	public List<ProVO> readProjectHide(String id, String proPartiFilter, String proRange);
	public List<ProVO> readProjectStar(String id, String proPartiFilter, String proRange);
	public List<ProVO> readProjectNoGroup(String id, String proPartiFilter, String proRange);
	
	//유저 정렬, 필터 조회
	public ProUsersVO readOrder(String id);
	
	//프로젝트 참가자수 조회
	public List<ProVO> readProjectParti(String id);
	
	//프로젝트 즐겨찾기 변경
	public int updateStar(String proStarCheck, int proMemNo);
	
	//프로젝트 그룹
	// 프로젝트 그룹 목록 조회
	public List<ProGroupVO> readProjectGroup(String id);
	// 프로젝트 그룹 프로젝트 조회
	public List<ProGroupVO> readProjectGroupDetail(int groupNo, String id, String proPartiFilter, String proRange);
	// 프로젝트 그룹 생성
	public int createProjectGroup(String groupName, String id);
	// 프로젝트 그룹 생성 후 번호 조회
	public int readProjectGroupNo();
	//프로젝트 그룹 이름 수정
	public int updateGroupName(ProGroupVO vo);
	//프로젝트 그룹 삭제
	public int deleteGroup(int groupNo);
	//프로젝트 그룹(관리)
	// 개인 전체 프로젝트 조회(그룹용)
	public List<ProGroupManageVO> readPerAllPro(String id);
	// 프로젝트 그룹의 프로젝트 체크 여부
	public List<ProGroupManageVO> readGroupCheckPro(int groupNo, String id);
	// 그룹에 프로젝트 추가
	public int createGroupProManage(ProGroupManageVO vo);
	// 그룹에 프로젝트 제거
	public int deleteGroupProManage(ProGroupManageVO vo);
	
	
	
	//프로젝트 필터 수정
	public int updateProjectFilter(String proPartiFilter, String id);
	
	//프로젝트 정렬 수정
	public int updateProjectOrder(String proRange, String id);
	
	//회원 가입시 프로젝트 그룹 생성
	public int createProGroup(String id);
	
	//프로젝트 권한 조회
	public ProAuthVO readProAuth(int proNo);
	
	//프로젝트 숨김 여부
	public int updateProHide(ProHideVO vo);
	
	//프로젝트 초대 알람
	// 미수락 프로젝트
	public List<ProPartiAlarmVO> readNoPartiPro(String id);
	// 프로젝트 참가 수락
	public int updateProPartiY(int proMemNo);
	// 프로젝트 참가 거절
	public int deleteProPartiN(int proMemNo);
	// 미수락 프로젝트 수
	public int readNoPartiProCount(String id);
	
	//프로젝트 개인 설정 조회
	public ProPerSettingVO readProPerSetting(int proMemNo);
	
}
