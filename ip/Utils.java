/*
* LumaQQ - Java QQ Client
*
* Copyright (C) 2004 luma <stubma@163.com>
*                    notXX
*
* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
*/
package ip;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.StringTokenizer;


/**
 * 工具类，提供一些方便的方法，有些主要是用于调试用途，有些不是
 *
 * @author 马若劼
 * @author notXX
 */
public class Utils {
    private static Random random;
    
	/**
	 * 把字节数组从offset开始的len个字节转换成一个unsigned int， 因为java里面没有unsigned，所以unsigned
	 * int使用long表示的， 如果len大于8，则认为len等于8。如果len小于8，则高位填0 <br>
	 * (edited by notxx) 改变了算法, 性能稍微好一点. 在我的机器上测试10000次, 原始算法花费18s, 这个算法花费12s.
	 * 
	 * @param in
	 *                   字节数组.
	 * @param offset
	 *                   从哪里开始转换.
	 * @param len
	 *                   转换长度, 如果len超过8则忽略后面的
	 * @return
	 */
	public static long getUnsignedInt(byte[] in, int offset, int len) {
		long ret = 0;
		int end = 0;
		if (len > 8)
			end = offset + 8;
		else
			end = offset + len;
		for (int i = offset; i < end; i++) {
			ret <<= 8;
			ret |= in[i] & 0xff;
		}
		return (ret & 0xffffffffl) | (ret >>> 32);
	}
    
    /**
     * 对给定的byte数组做一次MD5处理，从QQ2003开始采用了两次MD5的方法
     * @param pwd 需要加密的密码字节数组
     * @return 已经加密的密码字节数组
     */
    public static byte[] doMD5(byte[] pwd) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
        }
        md.update(pwd);
        return md.digest();
    }
    
    /**
     * 检查收到的文件MD5是否正确
     * @param file 收到的存在本地的文件
     * @param md5 正确的MD5
     * @return true表示正确
     */
    public static boolean checkFileMD5(RandomAccessFile file, byte[] md5) {
        return compareMD5(getFileMD5(file), md5);
    }
    
    /**
     * 检查收到的文件MD5是否正确
     * @param filename
     * @param md5
     * @return
     */
    public static boolean checkFileMD5(String filename, byte[] md5) {
        return compareMD5(getFileMD5(filename), md5);
    }
    
    /**
     * 计算文件的MD5，最多只计算前面10002432字节
     * @param filename
     * @return
     */
    public static byte[] getFileMD5(String filename) {
        try {
            RandomAccessFile file = new RandomAccessFile(filename, "r");
            byte[] md5 =  getFileMD5(file);
            file.close();
            return md5;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 计算文件的MD5，最多只计算前面10002432字节
     * @param file RandomAccessFile对象
     * @return MD5字节数组
     */
    public static byte[] getFileMD5(RandomAccessFile file) {
        try {
            file.seek(0);
            byte[] buf = (file.length() > QQ.QQ_MAX_FILE_MD5_LENGTH) ? new byte[QQ.QQ_MAX_FILE_MD5_LENGTH] : new byte[(int)file.length()];
            file.readFully(buf);
            return doMD5(buf);
        } catch (IOException e) {
            return null;
        }
    }
    
    /**
     * 得到一个文件的MD5字符串形式
     * @param filename 文件名
     * @return MD5字符串形式，小写。如果发生错误，返回null
     */
    public static String getFileMD5String(String filename) {
        byte[] md5 = getFileMD5(filename);
        if(md5 == null) return null;
        
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < md5.length; i++)
            sb.append(Integer.toHexString(md5[i] & 0xFF));
        return sb.toString().toLowerCase();
    }
    
    /**
     * 比较两个MD5是否相等
     * @param m1
     * @param m2
     * @return true表示相等
     */
    public static boolean compareMD5(byte[] m1, byte[] m2) {
        if(m1 == null || m2 == null) return true;
        for(int i = 0; i < 16; i++) {
            if(m1[i] != m2[i])
                return false;
        }
        return true;
    }
    
    /**
     * 根据某种编码方式得到字符串的字节数组形式
     * @param s 字符串
     * @param encoding 编码方式
     * @return 特定编码方式的字节数组，如果encoding不支持，返回一个缺省编码的字节数组
     */
    public static byte[] getBytes(String s, String encoding) {
        try {
            return s.getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            return s.getBytes();
        }
    }
    
    /**
     * 对原始字符串进行编码转换，如果失败，返回原始的字符串
     * @param s 原始字符串
     * @param srcEncoding 源编码方式
     * @param destEncoding 目标编码方式
     * @return 转换编码后的字符串，失败返回原始字符串
     */
    public static String getString(String s, String srcEncoding, String destEncoding) {
        try {
            return new String(s.getBytes(srcEncoding), destEncoding);
        } catch (UnsupportedEncodingException e) {
            return s;
        }
    }
    
    /**
     * 根据某种编码方式将字节数组转换成字符串
     * @param b 字节数组
     * @param encoding 编码方式
     * @return 如果encoding不支持，返回一个缺省编码的字符串
     */
    public static String getString(byte[] b, String encoding) {
        try {
            return new String(b, encoding);
        } catch (UnsupportedEncodingException e) {
            return new String(b);
        }
    }
    
    /**
     * 根据某种编码方式将字节数组转换成字符串
     * @param b 字节数组
     * @param offset 要转换的起始位置
     * @param len 要转换的长度
     * @param encoding 编码方式
     * @return 如果encoding不支持，返回一个缺省编码的字符串
     */
    public static String getString(byte[] b, int offset, int len, String encoding) {
        try {
            return new String(b, offset, len, encoding);
        } catch (UnsupportedEncodingException e) {
            return new String(b, offset, len);
        }
    }
    
    /**
     * 把字符串转换成int
     * @param s 字符串
     * @param faultValue 如果转换失败，返回这个值
     * @return 如果转换失败，返回faultValue，成功返回转换后的值
     */
    public static int getInt(String s, int faultValue) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return faultValue;
        }
    }
    
    /**
     * 把字符串转换成Integer
     * @param s 字符串
     * @param faultValue 如果转换失败，返回等于这个值的Integer对象
     * @return 如果转换失败，返回faultValue，成功返回转换后的值
     */
    public static Integer getInteger(String s, int faultValue) {
        try {
            return new Integer(s);
        } catch (NumberFormatException e) {
            return new Integer(faultValue);
        }
    }
    
    /**
     * 把字符串转换成Integer
     * @param s 字符串
     * @param faultValue 如果转换失败，返回这个Integer对象
     * @return 如果转换失败，返回faultValue，成功返回转换后的值
     */
    public static Integer getInteger(String s, Integer faultValue) {
        try {
            return new Integer(s);
        } catch (NumberFormatException e) {
            return faultValue;
        }
    }
    
    /**
     * 把字符串转换成char类型的无符号数
     * @param s 字符串
     * @param faultValue 如果转换失败，返回这个值
     * @return 如果转换失败，返回faultValue，成功返回转换后的值
     */
    public static char getChar(String s, int faultValue) {
        return (char)(getInt(s, faultValue) & 0xFFFF);
    }
    
    /**
     * 把字符串转换成byte
     * @param s 字符串
     * @param faultValue 如果转换失败，返回这个值
     * @return 如果转换失败，返回faultValue，成功返回转换后的值
     */
    public static byte getByte(String s, int faultValue) {
        return (byte)(getInt(s, faultValue) & 0xFF);
    }
    
    /**
     * @param ip ip的字节数组形式
     * @return 字符串形式的ip
     */
    public static String getIpStringFromBytes(byte[] ip) {
    	StringBuffer sb = new StringBuffer();
    	sb.append(ip[0] & 0xFF);
    	sb.append('.');   	
    	sb.append(ip[1] & 0xFF);
    	sb.append('.');   	
    	sb.append(ip[2] & 0xFF);
    	sb.append('.');   	
    	sb.append(ip[3] & 0xFF);
    	return sb.toString();
    }
    
    /**
     * 从ip的字符串形式得到字节数组形式
     * @param ip 字符串形式的ip
     * @return 字节数组形式的ip
     */
    public static byte[] getIpByteArrayFromString(String ip) {
        byte[] ret = new byte[4];
        StringTokenizer st = new StringTokenizer(ip, ".");
        try {
            ret[0] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
            ret[1] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
            ret[2] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
            ret[3] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
        } catch (Exception e) {
        }
        return ret;
    }
    
    /**
     * 判断IP是否相等
     * @param ip1 IP的字节数组形式
     * @param ip2 IP的字节数组形式
     * @return true如果两个IP相等
     */
    public static boolean isIpEquals(byte[] ip1, byte[] ip2) {
        return (ip1[0] == ip2[0] && ip1[1] == ip2[1] && ip1[2] == ip2[2] && ip1[3] == ip2[3]);
    }
    
    /**
     * @param cmd 命令类型
     * @return 命令的字符串形式，用于调试
     */
    public static String getCommandString(char cmd) {
        switch (cmd) {
	        case QQ.QQ_CMD_LOGOUT:
	            return "QQ.QQ_CMD_LOGOUT";
	        case QQ.QQ_CMD_KEEP_ALIVE:
	            return "QQ.QQ_CMD_KEEP_ALIVE";
	        case QQ.QQ_CMD_MODIFY_INFO:
	            return "QQ.QQ_CMD_MODIFY_INFO";
	        case QQ.QQ_CMD_SEARCH_USER:
	            return "QQ.QQ_CMD_SEARCH_USER";
	        case QQ.QQ_CMD_GET_USER_INFO:
	            return "QQ.QQ_CMD_GET_USER_INFO";
	        case QQ.QQ_CMD_ADD_FRIEND:
	            return "QQ.QQ_CMD_ADD_FRIEND";
	        case QQ.QQ_CMD_DELETE_FRIEND:
	            return "QQ.QQ_CMD_DELETE_FRIEND";
	        case QQ.QQ_CMD_ADD_FRIEND_AUTH:
	            return "QQ.QQ_CMD_ADD_FRIEND_AUTH";
	        case QQ.QQ_CMD_CHANGE_STATUS:
	            return "QQ.QQ_CMD_CHANGE_STATUS";
	        case QQ.QQ_CMD_ACK_SYS_MSG:
	            return "QQ.QQ_CMD_ACK_SYS_MSG";
	        case QQ.QQ_CMD_SEND_IM:
	            return "QQ.QQ_CMD_SEND_IM";
	        case QQ.QQ_CMD_RECV_IM:
	            return "QQ.QQ_CMD_RECV_IM";
	        case QQ.QQ_CMD_REMOVE_SELF:
	            return "QQ.QQ_CMD_REMOVE_SELF";
	        case QQ.QQ_CMD_LOGIN:
	            return "QQ.QQ_CMD_LOGIN";
	        case QQ.QQ_CMD_GET_FRIEND_LIST:
	            return "QQ.QQ_CMD_GET_FRIEND_LIST";
	        case QQ.QQ_CMD_GET_FRIEND_ONLINE:
	            return "QQ.QQ_CMD_GET_FRIEND_ONLINE";
	        case QQ.QQ_CMD_CLUSTER_CMD:
	            return "QQ.QQ_CMD_CLUSTER_CMD";
	        case QQ.QQ_CMD_RECV_MSG_SYS:
	            return "QQ.QQ_CMD_RECV_MSG_SYS";
	        case QQ.QQ_CMD_RECV_MSG_FRIEND_CHANGE_STATUS:
	            return "QQ.QQ_CMD_RECV_MSG_FRIEND_CHANGE_STATUS";
	        case QQ.QQ_CMD_REQUEST_KEY:
	            return "QQ_CMD_REQUEST_KEY";
	        case QQ.QQ_CMD_GROUP_NAME_OP:
	        	return "QQ_CMD_GROUP_NAME_OP";
	        case QQ.QQ_CMD_UPLOAD_GROUP_FRIEND:
	        	return "QQ_CMD_UPLOAD_GROUP_FRIEND";
	        case QQ.QQ_CMD_DOWNLOAD_GROUP_FRIEND:
	        	return "QQ_CMD_DOWNLOAD_GROUP_FRIEND";
	        case QQ.QQ_CMD_FRIEND_REMARK_OP:
	        	return "QQ_CMD_FRIEND_REMARK_OP";
	        default:
	            return "UNKNOWN_TYPE " + (int)cmd;
        }
    }
    
    /**
     * 得到群操作的字符串形式，仅用于调试
     * @param cmd
     * @return
     */
    public static String getClusterCommandString(byte cmd) {
    	  switch (cmd) {
    	    case QQ.QQ_CLUSTER_CMD_CREATE_CLUSTER:
    	    	return "QQ_CLUSTER_CMD_CREATE_CLUSTER";
    	    case QQ.QQ_CLUSTER_CMD_MEMBER_OPT:
    	    	return "QQ_CLUSTER_CMD_MEMBER_OPT";
    	    case QQ.QQ_CLUSTER_CMD_MODIFY_CLUSTER_INFO:
    	    	return "QQ_CLUSTER_CMD_MODIFY_CLUSTER_INFO";
    	    case QQ.QQ_CLUSTER_CMD_GET_CLUSTER_INFO:   
    	    	return "QQ_CLUSTER_CMD_GET_CLUSTER_INFO";
    	    case QQ.QQ_CLUSTER_CMD_ACTIVATE_CLUSTER:    
    	    	return "QQ_CLUSTER_CMD_ACTIVATE_CLUSTER";
    	    case QQ.QQ_CLUSTER_CMD_SEARCH_CLUSTER:     
    	    	return "QQ_CLUSTER_CMD_SEARCH_CLUSTER";
    	    case QQ.QQ_CLUSTER_CMD_JOIN_CLUSTER:        
    	    	return "QQ_CLUSTER_CMD_JOIN_CLUSTER";
    	    case QQ.QQ_CLUSTER_CMD_JOIN_CLUSTER_AUTH:   
    	    	return "QQ_CLUSTER_CMD_JOIN_CLUSTER_AUTH";
    	    case QQ.QQ_CLUSTER_CMD_EXIT_CLUSTER:        
    	    	return "QQ_CLUSTER_CMD_EXIT_CLUSTER";
    	    case QQ.QQ_CLUSTER_CMD_SEND_IM:          
    	    	return "QQ_CLUSTER_CMD_SEND_IM";
    	    case QQ.QQ_CLUSTER_CMD_GET_ONLINE_MEMBER: 
    	    	return "QQ_CLUSTER_CMD_GET_ONLINE_MEMBER";
    	    case QQ.QQ_CLUSTER_CMD_GET_MEMBER_INFO:  
    	    	return "QQ_CLUSTER_CMD_GET_MEMBER_INFO";
    	    default:                             
    	    	return "Unknown QQ Group Command";
    	  }
    }
    
    /**
     * 返回文件命令的字符串形式，仅用作调试
     * @param command
     * @return
     */
    public static String getFileCommandString(char command) {
    	switch(command) {
    	    case QQ.QQ_FILE_CMD_HEART_BEAT:
    	        return "QQ_FILE_CMD_HEART_BEAT";
    	    case QQ.QQ_FILE_CMD_HEART_BEAT_ACK:
    	        return "QQ_FILE_CMD_HEART_BEAT_ACK";
    	    case QQ.QQ_FILE_CMD_TRANSFER_FINISHED:
    	        return "QQ_FILE_CMD_TRANSFER_FINISHED";
    	    case QQ.QQ_FILE_CMD_FILE_OP:
    	        return "QQ_FILE_CMD_FILE_OP";
    	    case QQ.QQ_FILE_CMD_FILE_OP_ACK:
    	        return "QQ_FILE_CMD_FILE_OP_ACK";
    		case QQ.QQ_FILE_CMD_SENDER_SAY_HELLO:
    			return "QQ_FILE_CMD_SENDER_SAY_HELLO";    	
    		case QQ.QQ_FILE_CMD_SENDER_SAY_HELLO_ACK:
    		    return "QQ_FILE_CMD_SENDER_SAY_HELLO_ACK";
    		case QQ.QQ_FILE_CMD_RECEIVER_SAY_HELLO:
    			return "QQ_FILE_CMD_RECEIVER_SAY_HELLO";    		
    		case QQ.QQ_FILE_CMD_RECEIVER_SAY_HELLO_ACK:
    			return "QQ_FILE_CMD_RECEIVER_SAY_HELLO_ACK";    		
    		case QQ.QQ_FILE_CMD_NOTIFY_IP_ACK:
    			return "QQ_FILE_CMD_NOTIFY_IP_ACK";    		
    		case QQ.QQ_FILE_CMD_PING:
    			return "QQ_FILE_CMD_PING";    		
    		case QQ.QQ_FILE_CMD_PONG:
    			return "QQ_FILE_CMD_PONG";    		
    		case QQ.QQ_FILE_CMD_YES_I_AM_BEHIND_FIREWALL:
    			return "QQ_FILE_CMD_YES_I_AM_BEHIND_FIREWALL";    			
    		default:
    			return "UNKNOWN TYPE " + (int)command;
    	}
    }
    
    /**
     * @param clientTag 客户端标识
     * @return 客户端标识的字符串形式，用于调试
     */
    public static String getClientString(char clientTag) {
        switch (clientTag) {
	        case QQ.QQ_CLIENT_062E:
	            return "GB QQ2000c build 630";
	        case QQ.QQ_CLIENT_072E:
	            return "En QQ2000c build 305";
	        case QQ.QQ_CLIENT_0801:
	            return "En QQ2000c build 630";
	        case QQ.QQ_CLIENT_0A1D:
	            return "GB QQ2003c build 0808";
	        case QQ.QQ_CLIENT_0B07:
	            return "GB QQ2003c build 0925";
	        case QQ.QQ_CLIENT_0B2F:
	            return "GB QQ2003iii build 0117";
	        case QQ.QQ_CLIENT_0B35:  
	            return "GB QQ2003iii build 0304";
	        case QQ.QQ_SERVER_0100:
	            return "QQ Server 0100";
	        default:
	            return "QQ unknown version";
        }
    }
    
    /**
     * 得到状态的字符串表示
     * @param status 状态码
     * @return 字符串形式的状态码
     */
    public static String getStatusString(byte status) {
    	switch(status) {
    		case QQ.QQ_FRIEND_STATUS_ONLINE:
    			return "online";
    		case QQ.QQ_FRIEND_STATUS_OFFLINE:
    			return "offline";
    		case QQ.QQ_FRIEND_STATUS_AWAY:
    			return "away";
    		case QQ.QQ_FRIEND_STATUS_HIDDEN:
    			return "hidden";
    		default:
    			return "Unknown Status";
    	}
    }
    
    /**
     * 这个不是用于调试的，真正要用的方法
     * @param encoding 编码方式
     * @return 编码方式的字符串表示形式
     */
    public static String getEncodingString(char encoding) {
        switch(encoding) {
        	case QQ.QQ_IM_ENCODING_GB:
        	    return "GBK";
        	case QQ.QQ_IM_ENCODING_EN:
        	    return "ISO-8859-1";
        	case QQ.QQ_IM_ENCODING_BIG5:
        	    return "BIG5";
        	default:
        	    return "GBK";
        }
    }
    
    /**
     * 用于调试
     * @param type 收到的消息类型
     * @return 消息类型的字符串表示
     */
    public static String getIMTypeString(char type) {
        switch (type) {
	        case QQ.QQ_RECV_IM_TO_BUDDY:
	            return "QQ_RECV_IM_TO_BUDDY";
	        case QQ.QQ_RECV_IM_TO_UNKNOWN:
	            return "QQ_RECV_IM_TO_UNKNOWN";
	        case QQ.QQ_RECV_IM_CLUSTER_IM:
	            return "QQ_RECV_IM_CLUSTER_IM";
	        case QQ.QQ_RECV_IM_ADDED_TO_CLUSTER:
	            return "QQ_RECV_IM_ADDED_TO_CLUSTER";
	        case QQ.QQ_RECV_IM_DELETED_FROM_CLUSTER:
	            return "QQ_RECV_IM_DEL_FROM_CLUSTER";
	        case QQ.QQ_RECV_IM_REQUEST_JOIN_CLUSTER:
	            return "QQ_RECV_IM_REQUEST_JOIN_CLUSTER";
	        case QQ.QQ_RECV_IM_CREATE_CLUSTER:
	            return "QQ_RECV_IM_CREATE_CLUSTER";
	        case QQ.QQ_RECV_IM_SYS_MESSAGE:
	            return "QQ_RECV_IM_SYS_MESSAGE";
	        case QQ.QQ_RECV_IM_APPROVE_JOIN_CLUSTER:
	            return "QQ_RECV_IM_APPROVE_JOIN_CLUSTER";
	        case QQ.QQ_RECV_IM_REJECT_JOIN_CLUSTER:
	            return "QQ_RECV_IM_REJECT_JOIN_CLUSTER";
	        default:
	            return "QQ_RECV_IM_UNKNOWN";
        }
    }
    
    /**
     * 返回普通消息中的类型，仅用作调试
     * @param type
     * @return
     */
    public static String getNormalIMTypeString(char type) {
        switch(type) {
            case QQ.QQ_IM_NORMAL_TEXT:
                return "QQ_IM_NORMAL_TEXT";
            case QQ.QQ_IM_TCP_REQUEST:
                return "QQ_IM_TCP_REQUEST";
            case QQ.QQ_IM_ACCEPT_TCP_REQUEST:
                return "QQ_IM_ACCEPT_TCP_REQUEST";
            case QQ.QQ_IM_REJECT_TCP_REQUEST:
                return "QQ_IM_REJECT_TCP_REQUEST";
            case QQ.QQ_IM_UDP_REQUEST:
                return "QQ_IM_UDP_REQUEST";
            case QQ.QQ_IM_ACCEPT_UDP_REQUEST:
                return "QQ_IM_ACCEPT_UDP_REQUEST";
            case QQ.QQ_IM_REJECT_UDP_REQUEST:
                return "QQ_IM_REJECT_UDP_REQUEST";
            case QQ.QQ_IM_NOTIFY_IP:
                return "QQ_IM_NOTIFY_IP";
            case QQ.QQ_IM_ARE_YOU_BEHIND_FIREWALL:
                return "QQ_IM_ARE_YOU_BEHIND_FIREWALL";
            case QQ.QQ_IM_ARE_YOU_BEHIND_PROXY:
                return "QQ_IM_ARE_YOU_BEHIND_PROXY";
            case QQ.QQ_IM_YES_I_AM_BEHIND_PROXY:
                return "QQ_IM_YES_I_AM_BEHIND_PROXY";
            case QQ.QQ_IM_REQUEST_CANCELED:
                return "QQ_IM_REQUEST_CANCELED";
            default:
                return String.valueOf((int)type);
        }
    }
    
    /**
     * 返回字符串形式的回复类型，仅用于调试
     * @param type 回复类型
     * @return 字符串形式的回复类型
     */
    public static String getIMReplyType(byte type) {
        switch(type) {
        	case QQ.QQ_IM_NORMAL_REPLY:
        	    return "QQ_IM_TEXT";
        	case QQ.QQ_IM_AUTO_REPLY:
        	    return "QQ_IM_AUTO_REPLY";
        	default:
        	    return "UNKNOWN";
        }
    }
    
    /**
     * 在buf字节数组中的begin位置开始，查找字节b出现的第一个位置
     * @param buf 字节数组
     * @param begin 开始未知索引
     * @param b 要查找的字节
     * @return 找到则返回索引，否则返回-1
     */
    public static int indexOf(byte[] buf, int begin, byte b) {
    	for(int i = begin; i < buf.length; i++) {
    		if(buf[i] == b)
    			return i;
    	}
    	return -1;
    }
    
    /**
     * 在buf字节数组中的begin位置开始，查找字节数组b中只要任何一个出现的第一个位置
     * @param buf 字节数组
     * @param begin 开始未知索引
     * @param b 要查找的字节数组
     * @return 找到则返回索引，否则返回-1
     */
    public static int indexOf(byte[] buf, int begin, byte[] b) {
    	for(int i = begin; i < buf.length; i++) {
    		for(int j = 0; j < b.length; j++)
	    		if(buf[i] == b[j])
	    			return i;
    	}
    	return -1;
    }
    
	/**
	 * @return Random对象
	 */
	public static Random random() {
		if (random == null)
			random = new Random();
		return random;
	}

	/**
	 * 从content的offset位置起的4个字节解析成int类型
	 * @param content 字节数组
	 * @param offset 偏移
	 * @return int
	 */
	public static final int parseInt(byte[] content, int offset) {
		return ((content[offset++] & 0xff) << 24) | ((content[offset++] & 0xff) << 16) | ((content[offset++] & 0xff) << 8) | (content[offset++] & 0xff);
	}

	/**
	 * 从content的offset位置起的2个字节解析成char类型
	 * @param content 字节数组
	 * @param offset 偏移
	 * @return char
	 */
	public static final char parseChar(byte[] content, int offset) {
		return (char) (((content[offset++] & 0xff) << 8) | (content[offset++] & 0xff));
	}
}
