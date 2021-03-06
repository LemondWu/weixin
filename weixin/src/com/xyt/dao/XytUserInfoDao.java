package com.xyt.dao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xxcb.util.GenericHibernateDao;
import com.xyt.po.XytUserInfo;

@Transactional
@Component("XytUserInfoDao")
public class XytUserInfoDao   extends GenericHibernateDao{
	/**
	 * 根据积分编码查找用户信息
	 */
	public XytUserInfo getXytUserInfoByScoreCode(String scoreCode)
	{
		@SuppressWarnings("unchecked")
		List<XytUserInfo> xytUserInfoList = findBySql(" from XytUserInfo where scoreCode = '"+scoreCode+"'", false);
		if(CollectionUtils.isNotEmpty(xytUserInfoList)) {
			return xytUserInfoList.get(0);
		}
		return null;
	}
	
	
	/**
	 * 根据openId查找用户信息
	 */
	@SuppressWarnings("unchecked")
	public XytUserInfo getXytUserInfoByOpenId(String openId)
	{
		List<XytUserInfo> xytUserInfoList = findBySql(" from XytUserInfo where openid = '"+openId+"'", false);
		if(CollectionUtils.isNotEmpty(xytUserInfoList)) {
			return xytUserInfoList.get(0);
		}
		return null;
	}
	
	/**
	 * 根据rid查找用户信息
	 */
	@SuppressWarnings("unchecked")
	public List<XytUserInfo> getXytUserInfoByRid(Integer userId)
	{
		return this.findBySql(" from XytUserInfo where rid = '"+userId+"' order by rid desc", false);
	}
	
	/**
	 * 根据xytCollegeRid查找所有该学校的用户
	 */
	@SuppressWarnings("unchecked")
	public List<XytUserInfo> getXytUserInfoByCollegeRid(Integer xytCollegeRid)
	{
		return this.findBySql(" from XytUserInfo where college_rid = '"+xytCollegeRid+"' order by rid desc", false);
	}
	
	/**
	 * 设置用户的学校属性
	 */
	public boolean joinCampus(Integer userId, Integer collegeId)
	{
		String sql ="update XytUserInfo set college_rid = " + collegeId +" where rid = " + userId;
		return this.executeSQL(sql, false);
	}
	
	/**
	 * 去除学校属性
	 */
	public boolean quitCampus(Integer userId)
	{
		String sql ="update XytUserInfo set college_rid = null where rid = " + userId;
		return this.executeSQL(sql, false);
	}
}
