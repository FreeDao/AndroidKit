/**
*作者： danielyip 
*版权:Copyright@2012,whty,All right reserved.
*【更新记录】
*Version	Date						Company		Developer		Revise
*-------	----						-------		---------		------
*1.00		2012-10-22上午11:18:27		whty		danielyip		create
*/
package com.hh.kit.database;
/**
 *<dl>
 *<dt>数据库常量定义</dt>
 *<dd></dd>
 *</dl>
 */
public class ConstSQLite {

	/**数据库名称**/
	public final static String DATABASE_NAME="whty_wriecity.db";
	
	
	/**地区表**/
	public final static String Tbl_Area="tbl_area";
	
	/**省份表**/
	public final static String Tbl_Province="tbl_province";
	
	/**城市表**/
	public final static String Tbl_City="tbl_city";
	/**常驻城市**/
	public final static String REGULARLY_CITY="regularly_city";
	
	/**客户端日志-访问标识表**/
	public final static String Tbl_LogProc="tbl_logproc";
	
	/**客户端日志-访问信息表**/
	public final static String Tbl_LogVisitinfos="tbl_logvisitinfos";
	/**客户端日志-分享信息表**/
	public final static String Tbl_LogShareinfos="tbl_logshareinfos";
	/**客户端日志-搜索信息表**/
	public final static String Tbl_LogSearchinfos="tbl_logsearchinfos";
	/**客户端日志-推荐信息表**/
	public final static String Tbl_LogRecommendinfos="tbl_logrecommendinfos";
	/**客户端日志-页面访问信息表**/
	public final static String Tbl_LogPagelocationinfos="tbl_logpagelocationinfos";
	/**客户端日志-营销活动结果信息表**/
	public final static String Tbl_LogMarketinginfos="tbl_logmarketinginfos";
	/**客户端日志-客户端响应表**/
	public final static String Tbl_LogClientresponseinfos="tbl_logclientresponseinfos";
	/**客户端日志-客户端出错表**/
	public final static String Tbl_LogClienterrorinfos="tbl_clienterrorinfos";	
	/**客户端日志-应用信息表**/
	public final static String Tbl_LogAppinfos="tbl_logappinfos";
	/**客户端日志-城市切换信息表**/
	public final static String Tbl_LogSwitchCityinfos="tbl_logswitchcityinfos";
	/**客户端日志-应用内统计**/
	public final static String Tbl_LogAppvisit="tbl_logappvisit";
	/**客户端日志-收藏统计**/
	public final static String Tbl_LogCollecttion="tbl_collecttion";
	
	
	/**地区表-新版**/
	public final static String Tbl_Area_New="tbl_area";
	
	/**省份表-新版**/
	public final static String Tbl_Province_New="tbl_province";
	
	/**城市表-新版**/
	public final static String Tbl_City_New="tbl_city";
	
	/**热门城市表-新版**/
	public final static String Tbl_HotCity_New="tbl_hotcity";
	
	/**
	 * 摇一摇数据库
	 */
	 public final static String SHAKE_HISTORY_DB = "shake_history_app.db";
	 /**
	  * 我的历史记录表
	  */
	 public final static String MY_HISTORY_APP_DB = "my_history_app.db";
	 
	 /**
	  * 城市切换数据库
	  */
	 public final static String CITY_DB = "city.db";
	 
	 
	 /**
	  * 城市切换数据库-新版
	  */
	 public final static String New_Citydb = "chinacity.db";
	 
	 
	/**
	 * 数据库版本号
	 */
	 public final static int VERSION_DB = 2;
	 
	 /**
		 * 城市列表数据库版本号
		 */
	 public final static int VERSION_City_DB = 5;
		 
	 /**
	  * 城市切换数据库版本号-新版
	  */
	 public final static int VERSION_New_Citydb = 1000;
	 
	 
	/**
	 * Resource映射表
	 */
	public final static String APP_HISTORY="app_history";
	/**
	 * Resource 表
	 */
	public final static String RESOURCE_TABLE="resource";
	/**
	 * portab 表
	 */
	public final static String PORTAB_TABLE="portal";
	
	/**
	 * param 表
	 */
	public final static String PARAM_TABLE="param";
	
	/**
	 *  extendinfo 表
	 */
	public final static String EXTENDINFO_TABLE="extendinfo";
	

	/**
	 *  product 表
	 */
	public final static String PRODUT_TABLE="product";
	
	

	/**
	 *  bosscode 表
	 */
	public final static String BOSSCODE_TABLE="bosscode";
	

	/**
	 *  KEY 表
	 */
	public final static String KEY_TABLE="key";
	/**客户端-WGT安装记录表**/
	public final static String Tbl_wgt_download="tbl_wgt_download";

	
	/**
	 * 客户端日志流量统计信息
	 */
	public final static String STREAM_AMOUNT_TABLE="stream_amount_table";
	/**
	 * 微博站点类型信息表
	 */
	public final static String SNSTYPE_INFO_TABLE="snstype_info_table";
	/**
	 * SNS用户绑定存储信息表
	 */
	public final static String SNSACCOUNTBIND_TABLE="snsaccountbind_table";
	
}
