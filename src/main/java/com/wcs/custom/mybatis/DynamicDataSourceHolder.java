package com.wcs.custom.mybatis;
/**
 * mybatis自定义数据源管理器
 * @author wcs
 *
 */
class DynamicDataSourceHolder {
	private static final ThreadLocal<String> holder = new ThreadLocal<String>();

	static void setDataSource(String name) {
		holder.set(name);
	}

	static String getDataSource() {
		return holder.get();
	}
}