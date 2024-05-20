package com.niu.constant;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 一些常量
 * @date 2024/4/22 09:45:29
 */
public class Constants {

    public static final String LOGOUT_URL = "/api/logout";

    //导出Excel的接口路径
    public static final String EXPORT_EXCEL_URI = "/api/exportExcel";

    public static final String LOGIN_URI = "/api/login";

    //redis的key命名规范：项目名：模块名：唯一业务参数（比如用户id）
    public static final String REDIS_JWT_KEY = "dlyk:user:login:";

    //jwt过期时间，七天(REDIS用秒为单位)
    public static final Long EXPIRE_TIME = 60 * 60 * 24 * 7L;

    //jwt默认的过期时间，30分钟(REDIS用秒为单位)
    public static final Long DEFAULT_EXPIRE_TIME = 60 * 30L;

    //每页显示10条数据（用户）
    public static final int PAGE_SIZE = 10;

    //每页显示8条数据（活动）
    public static final int PAGE_SIZE_A = 8;

    //每页显示2条数据（活动备注）
    public static final int PAGE_SIZE_A_R = 2;

    //请求头中的token名（字母大小写转换是Ctrl + Shift + U）
    public static final String TOKEN_NAME = "Authorization";

    //到redis里查询负责人的key的值，这里不需要额外定义动态部分，因为只是下拉菜单的负责人列表
    public static final String REDIS_OWNER_KEY = "dlyk:user:owner";

    //如果内容是空的，就给一个空串
    public static final String EMPTY = "";

    //psfs导出的excel文件名
    public static final String EXCEL_FILE_NAME = "客户信息数据";
}
