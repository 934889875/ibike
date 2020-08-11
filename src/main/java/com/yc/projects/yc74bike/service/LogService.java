package com.yc.projects.yc74bike.service;

public interface LogService {
    public void save(String log);
    
	/**
 	充值日志
	 */
	public void savePayLog(  String log);
	public void saveRepairLog(  String log);
	public void saveDriverLog(  String log);
}
