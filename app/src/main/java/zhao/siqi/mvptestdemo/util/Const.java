package zhao.siqi.mvptestdemo.util;

import zhao.siqi.mvptestdemo.BuildConfig;

/**
 * APP使用的常量
 * Created by zhangzhi on 2017/3/28.
 */
public class Const {

    public static boolean DEBUG = BuildConfig.DEBUG;// 日志打印开关
    public static final String HTTP_LOG_KEY = "http";// 网络数据日志的打印的tag


    /////////////////////////  项目  /////////////////////////////////////

    public static final String UN_INPUT_STR = "999"; // 未输入用999代表
    public static final int UN_INPUT_INT = 999;
    public static final int UN_INPUT_INT_ZERO = 0;
    public static final int LOGIN_OUT_TIME = 10998; // 登录token过期
    public static final int PAGE_NUMBER = 10; // 列表页每页返回数目

    public static final String PROJECT_NAME = "project_name"; // 项目名称
    public static final String PROJECT_ID = "project_id"; // 项目编号

    public static final String ADD_TYPE = "add_type"; //添加人员
    public static final String ADD_FROM_CORE = "add_pi"; // 添加中心，选择PI,CRC 等

    public static final String CRITICAL_TIME_TYPE = "critical_time_type";
    public static final int CRITICAL_TIME_ADD = 0; // 新建
    public static final int CRITICAL_TIME_SCAN = 1; // 浏览
    public static final int CRITICAL_TIME_EDIT = 2; // 修改

    public static final String PROJECT_PROGRESS_TYPE = "project_progress_type";
    public static final int PROJECT_PROGRESS_ADD = 0; // 新建
    public static final int PROJECT_PROGRESS_SCAN = 1; // 浏览
    public static final int PROJECT_PROGRESS_EDIT = 2; // 修改
    public static final String PROJECT_PROGRESS_IS_DELETE = "project_progress_is_delete";

    public static final String CRITICAL_TIME_ID = "critical_time_id";
    public static final String CRITICAL_TIME_IS_DELETE = "critical_time_is_delete";
    public static final String PROJECTDEBRIEF_ID = "projectDebrief_id";


    /////////////////////////  通讯录  /////////////////////////////////////

    public static final String S_TYPE = "s_type"; // 搜索类型
    public static final String C_S_TYPE = "c_s_type"; // 通讯录 搜索类型

    public static final String CHOSE_GROUP_ID = "chose_group_id"; // 选择分组
    public static final String CHOSE_GROUP_NAME = "chose_group_name"; // 选择分组的名字
    public static final String CHOSE_PROJECT_ID = "chose_project_id"; // 选择项目的ID
    public static final String CHOSE_PROJECT_NAME = "chose_project_name"; // 选择项目的name

    public static final String ADD_BY_COMPANY = "add_by_company"; // 从公司通讯录添加
    public static final String PHONE_ITEM_CAN_CLICK = "phone_item_can_click"; // 从公司通讯录添加

    public static final String CONTACT_NAME = "contact_name"; // 联系人姓名
    public static final String CONTACT_PHONE = "contact_phone"; // 联系人手机

    public static final String SHOW_CHOSE_PROJECT = "show_chose_project";
    public static final String IS_CHAGE_PARAMS = "is_chage_params";
    public static final String HINT_SHARE_GROUP = "hint_share_group"; // 是否显示分组

    public static final String PERSON_NAME = "person_name";
    public static final String PERSON_PHONE = "person_phone";
    public static final String PERSON_COLOR = "person_color";

    public static final String CHOSE_PROJECT = "chose_project";

    /**
     * 选择分组
     */
    public static final String CHOOSE_GROUP_NAME_ONE = "choose_group_name_one";
    public static final String CHOOSE_GROUP_NAME_TWO = "choose_group_name_two";
    public static final String CHOOSE_GROUP_MAP_ONE = "choose_group_map_one";
    public static final String CHOOSE_GROUP_MAP_TWO = "choose_group_map_two";


    /////////////////////////  其他  /////////////////////////////////////
    public static final String URL_WEBVIEW = "url_webview"; // tnm
    public static final String TITLE_ID = "title_id";
    public static final String identify = "identify";

    /**
     * 个人信息修改标志
     */
    public static final String DETAIL_CONTENT_MARK = "detail_content_mark";

    public static final String DETAIL_CONTENT = "detail_content";
    public static final String DETAIL_TITLE = "detail_title";
    public static final String DETAIL_KEY = "detail_key";
    public static final String DETAIL_TYPE = "detail_type";

    public static final int DETAIL_CONTENT_ONELINE = 0;
    public static final int DETAIL_CONTENT_MORELINE = 1;

    public static final int REQUEST_NORMAL = 110;
    public static final int REQUEST_MULTI = 111;

    /**
     * 跳转标记
     */
    public static final String JUMP_FROM_PROJECT_CONTACT = "jump_from_project_contact";
    public static final String JUMP_FROM_PHONE_CONTACT = "jump_from_phone_contact";
    public static final String JUMP_FROM_PERSON_FRAGMENT = "jump_from_person_fragment";
    public static final String JUMP_FROM_CHAT = "jump_from_chat";
    public static final String JUMP_FROM_CONTACT_INFO = "jump_from_contact_info";

    /**
     * 刷新页面标识
     */
    public static final String CONTACT = "contact";
    public static final String PRO_PROG_LIST = "pro_prog_list";
    public static final String CRITICAL_TIME_LIST = "critical_time_list";
    public static final String FTE_LIST = "fte_list";
    public static final String COMPANY_CONTACT = "company_contact";
    public static final String PHONE_CONTACT = "phone_contact";

    /**
     * 搜索类型：通讯录  项目
     */
    public enum SearchType {

        CONTACT, PROJECT;

        public static SearchType valueOf(int value) {

            switch (value) {
                case 1:
                    return CONTACT;
                case 2:
                    return PROJECT;
            }

            return CONTACT;
        }
    }

    /**
     * 搜索类位   手机  项目
     */
    public static final int Contact_Search_Type_ALL = 0;
    public static final int Contact_Search_Type_COMPANY = 1;
    public static final int Contact_Search_Type_PHONE = 2;
    public static final int Contact_Search_Type_PROJECT = 3;


    /**
     * 相机  相册标识
     */
    public static final int CAMERA_TAG = 0;
    public static final int ALBUM_TAG = 1;

    /**
     * 请求加载相机
     */
    public static final int REQUEST_ALUM = 101;

    /**
     * 请求加载系统照相机
     */
    public static final int REQUEST_CAMERA = 100;

    /**
     * 裁截
     */
    public static final int REQUEST_CROP = 102;

    /**
     * 头像圆角角度值
     */
    public static final int PHOTO_RADIUS_SMALL = 11;
    public static final int PHOTO_RADIUS_MIDDLE = 14;
    public static final int PHOTO_RADIUS_LARGE = 18;

    /**
     * 日报
     */
    public static final String DAILY_ID = "daily_id";

}
