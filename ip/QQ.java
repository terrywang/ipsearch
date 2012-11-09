package ip;

/**
 * 定义一些QQ用到的常量
 *
 * @author 马若劼
 */
public interface QQ {    
	/** QQ UDP包的头部字节长度 */
    public static final int QQ_UDP_HEADER_LENGTH = 7;
	/** QQ TCP包的头部字节长度 */
    public static final int QQ_TCP_HEADER_LENGTH = 9;
	/** QQ包的尾部字节长 */
    public static final int QQ_TAIL_LENGTH = 1;
    
    /** 代理类型 - 无代理 */
    public static final int PROXY_NONE = 0;
    /** 代理类型 - Socks5代理 */
    public static final int PROXY_SOCKS5 = 1;
    /** 代理类型 - Http代理 */
    public static final int PROXY_HTTP = 2;
    
	/** 不需要确认的包的发送次数，这个值应该是随便的，由于QQ Logout包发了4次，所以我选4 */ 
    public static final int QQ_ACK_PACKET_SEND_TIME = 4;

	/** 最开始登陆时发送的初始密钥，用来加密登陆消息 */
    public static byte[] iniKey = new byte[] {
        0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01,
      	0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01
    };
	/** QQ登陆请求中第23字节到51字节的固定内容 */
    public static byte[] login_23_51 = new byte[] {
    	0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
    	0x00, 0x00, 0x00, 0x00, (byte)0xBF, 0x14, 0x11, 0x20,
    	0x03, (byte)0x9D, (byte)0xB2, (byte)0xE6, (byte)0xB3, 0x11, (byte)0xB7, 0x13,
    	(byte)0x95, 0x67, (byte)0xDA, 0x2C, 0x01 
    };
	/** QQ登陆请求中第53字节到68字节的固定内容 */
    public static byte[] login_53_68 = new byte[] {
        (byte)0x82, 0x2A, (byte)0x91, (byte)0xFD, (byte)0xA5, (byte)0xCA, 0x67, 0x4C,
        (byte)0xAC, (byte)0x81, 0x1F, 0x6F, 0x52, 0x05, (byte)0xA7, (byte)0xBF
    };  
    
	/** 包最大大小 */
	public static final int MAX_PACKET_SIZE = 65535;
	/** 消息最大长度 */
	public static final int MAX_SEND_IM_SIZE = 400;
	/** 密钥长度 */
	public static final int QQ_KEY_LENGTH = 16;
	/** 登陆信息长度 */
	public static final int QQ_LOGIN_DATA_LENGTH = 69;
    
	/** QQ UDP缺省端口 */
	public static final int QQ_UDP_PORT = 8000;
	/** QQ TCP缺省端口 */
	public static final int QQ_TCP_PORT = 80;
	/** 使用HTTP代理时连接QQ服务器的端口 */
	public static final int QQ_HTTP_PORT = 443;
	   
    /** QQ缺省编码方式 */
	public static final String QQ_CHARSET_DEFAULT = "GBK";
	/** 消息编码，好像可以自己胡乱定义 */
	public static final char QQ_IM_ENCODING_GB = 0x8602;
	public static final char QQ_IM_ENCODING_EN = 0x0000;	
	public static final char QQ_IM_ENCODING_BIG5 = 0x8603;
	
	/** 单位: ms */
	public static final long QQ_SENDQUEUE_TIMEOUT = 5000;
	/** 最大重发次数 */
	public static final int QQ_RESEND_MAX = 5;
	/** Keep Alive包发送间隔，单位: ms */
	public static final long QQ_KEEP_ALIVE_INTERVAL = 60000;

	/** QQ分组的名称最大字节长度，注意一个汉字是两个字节 */
	public static final int QQ_MAX_GROUP_NAME_BYTE = 16;
	/** QQ昵称的最长长度 */
	public static final int QQ_NAME_LENGTH_MAX = 250;
	/** QQ消息的最长长度 */
	public static final int QQ_MSG_IM_MAX = 1500;
	/** QQ缺省表情个数 */
	public static final int QQ_SMILEY_AMOUNT = 96;
	/** 用户的信息的字段个数 */
	public static final int QQ_CONTACT_FIELDS = 37;
	/** 用户备注信息的字段个数 */
	public static final int QQ_REMARK_FIELDS = 7;
	
	/** 客户端版本号标志-client GB QQ2000c build 630 */
	public static final char QQ_CLIENT_062E = 0x062E;
	/** 客户端版本号标志-client En QQ2000c build 305 */
	public static final char QQ_CLIENT_072E = 0x072E;
	/** 客户端版本号标志-client En QQ2000c build 630 */
	public static final char QQ_CLIENT_0801 = 0x0801;
	/** 客户端版本号标志-client Gb QQ2003c build 0808 */
	public static final char QQ_CLIENT_0A1D = 0x0A1D;
	/** 客户端版本号标志-client Gb QQ2003c build 0925 */
	public static final char QQ_CLIENT_0B07 = 0x0B07;
	/** 客户端版本号标志-client GB QQ2003iii build 0117 */
	public static final char QQ_CLIENT_0B2F = 0x0B2f;
	/** 客户端版本号标志-GB QQ2003iii build 0304 (offical release) */
	public static final char QQ_CLIENT_0B35 = 0x0B35;
	/** 客户端版本号标志-QQ20003 III 提示升级后代码变成了这个 */
	public static final char QQ_CLIENT_0B37 = 0x0B37;
	/** 客户端版本号标志-QQ2004 会员内测版 */
	public static final char QQ_CLIENT_0C0B = 0x0C0B;
	/** 客户端版本号标志-QQ2004 预览版 */
	public static final char QQ_CLIENT_0C0D = 0x0C0D;
	/** 客户端版本号标志-server */
	public static final char QQ_SERVER_0100 = 0x0100;
	
	/** 程序缺省使用的客户端版本号 */
	public static final char QQ_CLIENT = QQ_CLIENT_0B37;
	
	/** 这是QQ的普通用途的消息包开头字节 */
	public static final byte QQ_PACKET_TAG = 0x02;
	/** 这是文件传输时的控制消息包开头字节 */
	public static final byte QQ_FILE_CONTROL_PACKET_TAG = 0x00;
	/** 这是文件传输时的数据消息包开头字节 */
	public static final byte QQ_FILE_DATA_PACKET_TAG = 0x03;
    /** 这是文件中转包的开头字节 */
	public static final byte QQ_FILE_AGENT_PACKET_TAG = 0x4;	
	/** 这个是QQ普通用途消息包的尾部字节 */
	public static final byte QQ_PACKET_TAIL = 0x03;
		
	/** 正常登陆 */
	public static final byte QQ_LOGIN_MODE_NORMAL = 0x0A;
	/** 隐身登陆 */
	public static final byte QQ_LOGIN_MODE_HIDDEN = 0x28;
	/** 性别-男 */
	public static final byte QQ_FRIEND_GENDER_GG = 0x0;
	/** 性别-女 */
	public static final byte QQ_FRIEND_GENDER_MM = 0x1;
	/** 性别-未知 */
	public static final byte QQ_FRIEND_GENDER_UNKNOWN = (byte) 0xFF;
	
	/** 标志-QQ会员 */
	public static final byte QQ_FRIEND_FLAG_QQ_MEMBER = 0x01;
	/** 标志-手机 */
	public static final byte QQ_FRIEND_FLAG_MOBILE = 0x10;
	/** 标志-手机绑定 */
	public static final byte QQ_FRIEND_FLAG_BIND_MOBILE = 0x20;
	
	/** 在线状态-在线 */
	public static final byte QQ_FRIEND_STATUS_ONLINE = 10;
	/** 在线状态-离线 */
	public static final byte QQ_FRIEND_STATUS_OFFLINE = 20;
	/** 在线状态-离开 */
	public static final byte QQ_FRIEND_STATUS_AWAY = 30;
	/** 在线状态-隐身 */
	public static final byte QQ_FRIEND_STATUS_HIDDEN = 40;

	/** 是否有摄像头(这是虚拟摄像头功能) */
	public static final int QQ_MISC_STATUS_HAVING_VIDEO = 0x00000001;
	
	/** 登录信息-成功 */
	public static final byte QQ_LOGIN_REPLY_OK = 0x00;
	/** 登录信息-重定向 */
	public static final byte QQ_LOGIN_REPLY_REDIRECT = 0x01;
	/** 登录信息-密码错误 */
	public static final byte QQ_LOGIN_REPLY_PWD_ERROR = 0x02;
	/** 登录信息-其他错误 */
	public static final byte QQ_LOGIN_REPLY_MISC_ERROR = 0x03;
	/** 上载好友分组成功 */
	public static final byte QQ_UPLOAD_GROUP_FRIEND_OK = 0x00;
	/** 上载好友备注成功 */
	public static final byte QQ_UPLOAD_FRIEND_REMARK_OK = 0x00;
	/** 改变在线状态成功 */
	public static final byte QQ_CHANGE_STATUS_REPLY_OK = 0x30;
	/** 发送消息成功 */
	public static final byte QQ_SEND_IM_REPLY_OK = 0x00;
	/** 发送认证消息成功 */
	public static final byte QQ_ADD_FRIEND_AUTH_REPLY_OK = 0x30;
	/** 删除好友成功 */
	public static final byte QQ_DELETE_FRIEND_REPLY_OK = 0x00;
	/** 把自己从对方好友中删除成功 */
	public static final byte QQ_REMOVE_SELF_REPLY_OK = 0x00;
	/** 得到密钥成功 */
	public static final byte QQ_REQUEST_KEY_REPLY_OK = 0x00;

	/** 好友列表从第一个好友开始 */
	public static final char QQ_FRIEND_LIST_POSITION_START = 0x0000;
	/** 好友列表已经全部得到 */
	public static final char QQ_FRIEND_LIST_POSITION_END = 0xFFFF;
	/** 在线好友列表从第一个好友开始 */
	public static final byte QQ_FRIEND_ONLINE_LIST_POSITION_START = 0x00;
	/** 在线好友列表已经全部得到 */
	public static final byte QQ_FRIEND_ONLINE_LIST_POSITION_END = (byte)0xFF;	
	/** 不对得到的好友列表排序 */
	public static final byte QQ_FRIEND_LIST_UNSORTED = 0;
	/** 对得到的好友列表排序 */
	public static final byte QQ_FRIEND_LIST_SORTED = 1;
	
	/** 命令常量 - 登出 */
	public static final char QQ_CMD_LOGOUT = 0x0001;
	/** 命令常量 - 保持在线状态 */
	public static final char QQ_CMD_KEEP_ALIVE = 0x0002;
	/** 命令常量 - 修改自己的信息 */
	public static final char QQ_CMD_MODIFY_INFO = 0x0004;
	/** 命令常量 - 查找用户 */
	public static final char QQ_CMD_SEARCH_USER = 0x0005;
	/** 命令常量 - 得到好友信息 */
	public static final char QQ_CMD_GET_USER_INFO = 0x0006;
	/** 命令常量 - 添加一个好友 */
	public static final char QQ_CMD_ADD_FRIEND = 0x0009;
	/** 命令常量 - 删除一个好友 */
	public static final char QQ_CMD_DELETE_FRIEND = 0x000A;
	/** 命令常量 - 发送验证信息 */
	public static final char QQ_CMD_ADD_FRIEND_AUTH = 0x000B;
	/** 命令常量 - 改变自己的在线状态 */
	public static final char QQ_CMD_CHANGE_STATUS = 0x000D;
	/** 命令常量 - 确认收到了系统消息 */
	public static final char QQ_CMD_ACK_SYS_MSG = 0x0012;
	/** 命令常量 - 发送消息 */
	public static final char QQ_CMD_SEND_IM = 0x0016;
	/** 命令常量 - 接收消息 */
	public static final char QQ_CMD_RECV_IM = 0x0017;
	/** 命令常量 - 把自己从对方好友名单中删除 */
	public static final char QQ_CMD_REMOVE_SELF = 0x001C;
	/** 请求一些操作需要的密钥，比如文件中转，视频也有可能 */
	public static final char QQ_CMD_REQUEST_KEY = 0x001D;
	/** 命令常量 - 电话？？可能是收到了手机消息的意思 */
	public static final char QQ_CMD_CELL_PHONE_1 = 0x0021;
	/** 命令常量 - 登陆 */
	public static final char QQ_CMD_LOGIN = 0x0022;
	/** 命令常量 - 得到好友列表 */
	public static final char QQ_CMD_GET_FRIEND_LIST = 0x0026;
	/** 命令常量 - 得到在线好友列表 */
	public static final char QQ_CMD_GET_FRIEND_ONLINE = 0x0027;
	/** 命令常量 - 又是电话？ */
	public static final char QQ_CMD_CELL_PHONE_2 = 0x0029;
	/** 命令常量 - 群相关命令 */
	public static final char QQ_CMD_CLUSTER_CMD = 0x0030;
	/** 命令常量 - 测试连接 */
	public static final char QQ_CMD_TEST = 0x0031;
	/** 命令常量 - 上传下载好友分组的名字 */
	public static final char QQ_CMD_GROUP_NAME_OP = 0x003C;
	/** 命令常量 - 上传分组中的好友QQ号列表 */
	public static final char QQ_CMD_UPLOAD_GROUP_FRIEND = 0x003D;
	/** 命令常量 - 上传下载好友备注 */
	public static final char QQ_CMD_FRIEND_REMARK_OP = 0x003E;
	/** 命令常量 - 下载分组中的好友QQ号列表 */
	public static final char QQ_CMD_DOWNLOAD_GROUP_FRIEND = 0x0058;
	/** 命令常量 - 接收到系统消息 */
	public static final char QQ_CMD_RECV_MSG_SYS = 0x0080;
	/** 命令常量 - 好友改变状态 */
	public static final char QQ_CMD_RECV_MSG_FRIEND_CHANGE_STATUS = 0x0081;
	
	/** 请求密钥类型 - 未知 */
	public static final byte QQ_REQUEST_UNKNOWN_KEY = 0x3;
	/** 请求密钥类型 - 文件中转密钥 */
	public static final byte QQ_REQUEST_FILE_AGENT_KEY = 0x4;
	
	/** 群操作命令 - 创建群 */
	public static final byte QQ_CLUSTER_CMD_CREATE_CLUSTER		= 0x01;
	/** 群操作命令 - 成员选项？暂时未用到 */
	public static final byte QQ_CLUSTER_CMD_MEMBER_OPT			= 0x02;
	/** 群操作命令 - 修改群资料 */
	public static final byte QQ_CLUSTER_CMD_MODIFY_CLUSTER_INFO	= 0x03;
	/** 群操作命令 - 得到群资料 */
	public static final byte QQ_CLUSTER_CMD_GET_CLUSTER_INFO	= 0x04;
	/** 群操作命令 - 激活群 */
	public static final byte QQ_CLUSTER_CMD_ACTIVATE_CLUSTER	= 0x05;
	/** 群操作命令 - 搜索群 */
	public static final byte QQ_CLUSTER_CMD_SEARCH_CLUSTER		= 0x06;
	/** 群操作命令 - 加入群 */
	public static final byte QQ_CLUSTER_CMD_JOIN_CLUSTER		= 0x07;
	/** 群操作命令 - 加入群的验证消息 */
	public static final byte QQ_CLUSTER_CMD_JOIN_CLUSTER_AUTH	= 0x08;
	/** 群操作命令 - 退出群 */
	public static final byte QQ_CLUSTER_CMD_EXIT_CLUSTER		= 0x09;
	/** 群操作命令 - 发送群消息 */
	public static final byte QQ_CLUSTER_CMD_SEND_IM				= 0x0A;
	/** 群操作命令 - 得到在线成员 */
	public static final byte QQ_CLUSTER_CMD_GET_ONLINE_MEMBER	= 0x0B;
	/** 群操作命令 - 得到成员资料 */
	public static final byte QQ_CLUSTER_CMD_GET_MEMBER_INFO		= 0x0C;
	/** 群操作命令回复 - 成功 */
	public static final byte QQ_CLUSTER_CMD_REPLY_OK					= 0x00;
	/** 群操作命令回复 - 不存在这个群 */
	public static final byte QQ_CLUSTER_CMD_REPLY_NO_SUCH_CLUSTER		= 0x02;
	/** 群操作命令回复 - 你不是该群的成员 */
	public static final byte QQ_CLUSTER_CMD_REPLY_NOT_MEMBER			= 0x0a;
	/** 群类型常量 - 固定群 */
	public static final byte QQ_CLUSTER_TYPE_PERMANENT = 0x01;
	/** 群类型常量 - 临时群 */
	public static final byte QQ_CLUSTER_TYPE_TEMPORARY = 0x02;
	/** 群认证类型 - 不需认证 */
	public static final byte QQ_CLUSTER_NO_AUTH		= 0x01;
	/** 群认证类型 - 需要认证 */
	public static final byte QQ_CLUSTER_NEED_AUTH   = 0x02;
	/** 群认证类型 - 拒绝任何人加入 */
	public static final byte QQ_CLUSTER_NO_ADD  	= 0x03;
	/** 群认证消息类型 - 请求加入群 */
	public static final byte QQ_CLUSTER_AUTH_REQUEST   = 0x01;
	/** 群认证消息类型 - 同意加入群 */
	public static final byte QQ_CLUSTER_AUTH_APPROVE   = 0x02;
	/** 群认证消息类型 - 拒绝加入群 */
	public static final byte QQ_CLUSTER_AUTH_REJECT    = 0x03;
	/** 加入群的回复码 - 加入成功 */
	public static final byte QQ_CLUSTER_JOIN_OK			= 0x01;	
	/** 加入群的回复码 - 对方需要认证 */
	public static final byte QQ_CLUSTER_JOIN_NEED_AUTH	= 0x02;
	/** 群的搜索方式 - 根据群号搜索 */
	public static final byte QQ_SEARCH_CLUSTER_BY_ID 	= 0x01;
	/** 群的搜索方式 - 搜索示范群 */
	public static final byte QQ_SEARCH_DEMO_CLUSTER		= 0x02;
	  
	/** 消息回复类型 - 正常回复 */
	public static final byte QQ_IM_NORMAL_REPLY = 0x01;
	/** 消息回复类型 - 自动回复 */
	public static final byte QQ_IM_AUTO_REPLY = 0x02;
	
	// 消息来源，主要是MessageQueue使用，和协议关系不大
	/** 来自好友 */
	public static final int QQ_IM_FROM_FRIEND = 0;
	/** 来自系统 */
	public static final int QQ_IM_FROM_SYS = 1;
	/** 来自群 */
	public static final int QQ_IM_FROM_CLUSTER = 2;
	
	/** 组名操作类型 - 上传 */
	public static final byte QQ_UPLOAD_GROUP_NAME = 0x2;
	/** 组名操作类型 - 下载 */
	public static final byte QQ_DOWNLOAD_GROUP_NAME = 0x1;
	
	/** 备注操作类型 - 上传 */
	public static final byte QQ_UPLOAD_FRIEND_REMARK = 0x1;
	/** 备注操作类型 - 下载 */
	public static final byte QQ_DOWNLOAD_FRIEND_REMARK = 0x3;
	
	// 这两个常量用在下载好友分组时
	/** 号码类型 - 号码代表一个用户 */
	public static final byte QQ_ID_IS_FRIEND = 0x1;
	/** 号码类型 - 号码是一个群 */
	public static final byte QQ_ID_IS_CLUSTER = 0x4;
	
	// 消息类型，就是ReceiveIMHeader中的类型，对于有些类型，我们做为通知来处理
	//    而不是显示在消息窗口中，比如请求加入，验证之类的消息
	/** 来自好友的消息 */
	public static final char QQ_RECV_IM_TO_BUDDY = 0x0009;
	/** 来自陌生人的消息 */
	public static final char QQ_RECV_IM_TO_UNKNOWN = 0x000A;
	/** 来自群的消息 */
	public static final char QQ_RECV_IM_CLUSTER_IM = 0x0020;
	/** 通知我被加入到一个群，这个群先前已经建立，我是后来被加的 */
	public static final char QQ_RECV_IM_ADDED_TO_CLUSTER = 0x0021;
	/** 我被踢出一个群 */
	public static final char QQ_RECV_IM_DELETED_FROM_CLUSTER = 0x0022;
	/** 有人请求加入群 */
	public static final char QQ_RECV_IM_REQUEST_JOIN_CLUSTER = 0x0023;
	/** 同意对方加入群 */	
	public static final char QQ_RECV_IM_APPROVE_JOIN_CLUSTER = 0x0024;
	/** 拒绝对方加入群 */
	public static final char QQ_RECV_IM_REJECT_JOIN_CLUSTER = 0x0025;
	/** 通知我被加入到一个群，我是在群被创建的时候就被加的 */
	public static final char QQ_RECV_IM_CREATE_CLUSTER = 0x0026;
	/** 收到的系统消息 */ 
	public static final char QQ_RECV_IM_SYS_MESSAGE = 0x0030;
	/** 同一个QQ号在其他地方登录，我被踢出 */
	public static final char QQ_RECV_IM_KICK_OUT = 0x0001;
	
	// 消息类型，这个类型比上面的类型又再低一级，他们基本从属于QQ_RECV_IM_TO_BUDDY
	//    所以他们是normalIMHeader中的类型
	/** 普通文件消息 */
	public static final char QQ_IM_NORMAL_TEXT = 0x000B;
	/** 一个TCP连接请求 */
	public static final char QQ_IM_TCP_REQUEST = 0x0001;
	/** 接收TCP连接请求 */
	public static final char QQ_IM_ACCEPT_TCP_REQUEST = 0x0003;
	/** 拒绝TCP连接请求 */
	public static final char QQ_IM_REJECT_TCP_REQUEST = 0x0005;
	/** UDP连接请求 */
	public static final char QQ_IM_UDP_REQUEST = 0x0035;
	/** 接受UDP连接请求 */
	public static final char QQ_IM_ACCEPT_UDP_REQUEST = 0x0037;
	/** 拒绝UDP连接请求 */
	public static final char QQ_IM_REJECT_UDP_REQUEST = 0x0039;
	/** 通知文件传输端口 */
	public static final char QQ_IM_NOTIFY_IP = 0x003B;
	/** 请求对方主动连接 */
	public static final char QQ_IM_ARE_YOU_BEHIND_FIREWALL = 0x003F;
	/** 未知含意 */
	public static final char QQ_IM_ARE_YOU_BEHIND_PROXY = 0x0041;
	/** 未知含意，0x0041的回复 */
	public static final char QQ_IM_YES_I_AM_BEHIND_PROXY = 0x0042;
	/** 通知文件中转服务器信息 */
	public static final char QQ_IM_NOTIFY_FILE_AGENT_INFO = 0x004B;
	/** 取消TCP或者UDP连接请求 */
	public static final char QQ_IM_REQUEST_CANCELED = 0x0049;
	
	// 认证类型，加一个人为好友时是否需要验证等等
	/** 不需认证 */
	public static final byte QQ_AUTH_NO_AUTH = 0;
	/** 需要认证 */
	public static final byte QQ_AUTH_NEED_AUTH = 1;
	/** 对方拒绝加好友 */
	public static final byte QQ_AUTH_NO_ADD = 2;
	
	// 这三个常量用在添加好友认证的包中，表示你是请求，或者你拒绝还是同意别人的请求
	/** 通过认证 */
	public static final byte QQ_MY_AUTH_APPROVE = 0x30;
	/** 拒绝认证  */
	public static final byte QQ_MY_AUTH_REJECT = 0x31;
	/** 请求认证 */
	public static final byte QQ_MY_AUTH_REQUEST = 0x32;
	
	// 联系方法的可见类型
	/** 完全公开 */
	public static final int QQ_CONTACT_OPEN = 0;
	/** 仅好友可见 */
	public static final int QQ_CONTACT_ONLY_FRIENDS = 1;
	/** 完全保密 */
	public static final int QQ_CONTACT_CLOSE = 2;
	
	// 系统通知的类型
	/** 自己被别人加为好友 */
	public static final char QQ_MSG_SYS_BEING_ADDED = 1;
	/** 对方请求加你为好友 */
	public static final char QQ_MSG_SYS_ADD_FRIEND_REQUEST = 2;
	/** 同意对方加自己为好友 */
	public static final char QQ_MSG_SYS_ADD_FRIEND_APPROVED = 3;
	/** 拒绝对方加自己为好友 */
	public static final char QQ_MSG_SYS_ADD_FRIEND_REJECTED = 4;
	/** 未知含意 */
	public static final char QQ_MSG_SYS_UPDATE_HINT = 9;
	
	// 这是搜索用户时指定的搜索类类型，比如是查看全部在线用户，还是自定义查找
	/** 看谁在线上 */
	public static final byte QQ_SEARCH_ALL = 0x31;
	/** 自定义搜索 */
	public static final byte QQ_SEARCH_CUSTOM = 0x30;
	
	// 群消息设置常量字符串，和协议无关
	/** 阻止该群一切消息 */
	public static final String MSG_BLOCK = "block";
	/** 接受该群消息 */
	public static final String MSG_ACCEPT = "accept";
	/** 自动弹出消息 */
	public static final String MSG_EJECT = "eject";
	/** 记录该群消息但不提示 */
	public static final String MSG_RECORD = "record";
	
	/* 以下为专用于文件传输时的常量 */
	/** 初始中转服务器，从这些初始的服务器开始请求，直到请求到一个愿意提供中转的服务器为止 */
	public static final String[] originalAgents = new String[] {
	        "218.133.40.38",
	        "218.133.40.36",
	        "219.133.40.36"
	};
    /** QQ文件传送包的头部字节长度 */
    public static final int QQ_FILE_PACKET_HEADER_LENGTH = 12;
    
    // 文件数据信息包的命令类型
    /** heart beat */
    public static final char QQ_FILE_CMD_HEART_BEAT 			= 0x0001;
    /** heart beat的确认 */
    public static final char QQ_FILE_CMD_HEART_BEAT_ACK 		= 0x0002;
    /** 文件传输已完成 */
    public static final char QQ_FILE_CMD_TRANSFER_FINISHED 		= 0x0003;
    /** 文件操作 */
    public static final char QQ_FILE_CMD_FILE_OP 				= 0x0007;
    /** 文件操作的确认 */
    public static final char QQ_FILE_CMD_FILE_OP_ACK 			= 0x0008;
    
    // QQ_FILE_CMD_FILE_OP携带的信息类型
    /** 文件基本信息 */
    public static final byte QQ_FILE_BASIC_INFO = 0x1;
    /** 文件数据 */
    public static final byte QQ_FILE_DATA_INFO = 0x2;
    /** 文件EOF */
    public static final byte QQ_FILE_EOF = 0x3;
    
    // 文件控制信息传输包的命令类型
    /** 发送者say hello */
    public static final char QQ_FILE_CMD_SENDER_SAY_HELLO 		  = 0x0031;
    /** 对发送者hello的确认 */
    public static final char QQ_FILE_CMD_SENDER_SAY_HELLO_ACK	  = 0x0032;
    /** 接收者say hello */
    public static final char QQ_FILE_CMD_RECEIVER_SAY_HELLO		  = 0x0033;
    /** 对接受者hello的确认 */
    public static final char QQ_FILE_CMD_RECEIVER_SAY_HELLO_ACK	  = 0x0034;
    /** 对通知IP的确认，即对QQ_IM_NOTIFY_IP的确认 */
    public static final char QQ_FILE_CMD_NOTIFY_IP_ACK 			  = 0x003C;
    /** 试探连接 */
    public static final char QQ_FILE_CMD_PING    				  = 0x003D;
    /** 试探连接的确认 */
    public static final char QQ_FILE_CMD_PONG 					  = 0x003E;
    /** 主动连接对方 */
    public static final char QQ_FILE_CMD_YES_I_AM_BEHIND_FIREWALL = 0x0040;
    
    // 文件中转信息包的命令类型
    /** 发送者请求对方提供中转服务 */
    public static final char QQ_FILE_CMD_REQUEST_AGENT = 0x0001;
    /** 接收者向中转服务器报到 */
    public static final char QQ_FILE_CMD_CHECK_IN = 0x0002;
    /** 转发包，这个包里面内嵌着一个文件数据信息包 */
    public static final char QQ_FILE_CMD_FORWARD = 0x0003;
    /** 传输结束 */
    public static final char QQ_FILE_CMD_FORWARD_FINISHED = 0x0004;
    /** 服务器通知可以开始传输数据 */
    public static final char QQ_FILE_CMD_IT_IS_TIME = 0x0005;
    /** 我已经准备好 */
    public static final char QQ_FILE_CMD_I_AM_READY = 0x0006;
    
    // QQ_FILE_CMD_REQUEST_AGENT命令的应答类型
    /** 批准中转请求 */
    public static final char QQ_FILE_AGENT_SERVICE_APPROVED = 0x0000;
    /** 我现在忙，你找别人吧 */
    public static final char QQ_FILE_AGENT_SERVICE_REDIRECTED = 0x0001;
	
    // 请求传送文件消息中的一个标志字节，0x65后面的那个，意思不明，姑且这样
    /** UDP，可能不是这意思 */
    public static final byte QQ_TRANSFER_FILE_UDP = 0;
    /** 直接UDP，可能不是这意思 */
    public static final byte QQ_TRANSFER_FILE_DIRECT_UDP = 1;
    /** TCP，可能不是这意思 */
    public static final byte QQ_TRANSFER_FILE_TCP = 2;
    /** 直接TCP，可能不是这意思 */
    public static final byte QQ_TRANSFER_FILE_DIRECT_TCP = 3;
    
	// 传送文件的各种情况，每种情况有其对应的连接策略，这个和协议无关，我自己定义的
    /** 双方位于同一个防火墙后 */
	public static final int QQ_SAME_LAN = 0;
	/** 双方都不在防火墙后 */
	public static final int QQ_NONE_BEHIND_FIREWALL = 1;
	/** 我在防火墙后 */
	public static final int QQ_I_AM_BEHIND_FIREWALL = 2;
	/** 他在防火墙后 */
	public static final int QQ_HE_IS_BEHIND_FIREWALL = 3;
	/** 双方在不同的防火墙后 */
	public static final int QQ_ALL_BEHIND_FIREWALL = 4;
	
	// say hello时的hello byte，不同的情况这个字节不一样
	/** 双方位于同一个防火墙后的Hello */
	public static final byte QQ_SAME_IN_TO_SAME_IN_HELLO = 0;
	/** 一个人在防火墙后，内部的人是发送方时的Hello */
	public static final byte QQ_IN_TO_OUT_HELLO = 1;
	/** 一个人在防火墙后，外部的人是发送方时的Hello */
	public static final byte QQ_OUT_TO_IN_HELLO = 2;
	/** 两个都不在防火墙内时的Hello */
	public static final byte QQ_OUT_TO_OUT_HELLO = 0;
	
	/** 最大的做MD5的长度，当传送一个文件时，如果这个文件很大，则只对文件的前面一部分做MD5 */
	public static final int QQ_MAX_FILE_MD5_LENGTH = 10002432;
}
