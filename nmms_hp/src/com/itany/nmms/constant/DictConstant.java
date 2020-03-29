package com.itany.nmms.constant;

public interface DictConstant {
    /**
     * 员工角色：系统管理员
     */
    public static final String STAFF_ROLE_SYSTEM_MANAGER ="1001";
    /**
     * 员工角色：普通管理员
     */
    public static final String STAFF_ROLE_SIMPLE_MANAGER ="1002";
    /**
     * 后台分页默认显示第一页
     */
    public static final String BACKEND_PAGE_NO_DEFAULT_VALUE="1";
    /**
     * 后台分页默认每页显示3条
     */
    public static final String BACKEND_PAGE_SIZE_DEFAULT_VALUE="2";
    /**
     * 商品编号前缀
     */
    public static final String PRODUCT_NO_PREFIX = "SP";

    /**
     * 商品编号的序列号的初始值
     */
    public static final String PRODUCT_NO_SEQUENCE_INIT = "000001";

    /**
     * 商品编号的序列号的最大值
     */
    public static final String PRODUCT_NO_SEQUENCE_MAX = "999999";
    /**
     * 存放商品图片的根目录的名字
     */
    public static final String PRODUCT_IMAGE_ROOT_FOLDER_NAME="/productImage";
}
