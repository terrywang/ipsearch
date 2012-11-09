#!/bin/bash

# 判断执行文件的类型，如果是一个符号链接，得到这个链接的目的路径，在以前的版本中，这个任务是用
# readlink -f 命令完成的。这种作法稍微有些问题，比如你的系统里面可能没有readlink命令，或者低版本的
# readlink还不支持-f参数，所以在这里先采用readlink，如果readlink失败，则再用file命令和一个
# 模式匹配来得到目的路径。目前我不确定是否每个平台上的file命令都是返回symbolic link to 这种字符串，
# 如果不是，把symbolic link to 替换成你的file命令返回的那样
PREFIX=
if [ -L "$0" ]; then
	PREFIX=`readlink -f $0`
	if [ $? -eq 0 ]; then
		PREFIX=`dirname $PREFIX`		
	else 
        	PREFIX=`file $0`
        	PREFIX=${PREFIX##*symbolic link to }
        	PREFIX=`dirname $PREFIX`
	fi
else
        PREFIX=`dirname $0`
fi

# 在这里我判断得到的路径是否是绝对路径，如果不是绝对
# 路径，我要再做一些处理得到绝对路径
case $PREFIX in
        /*)
        ;;
        *)
        cd $PREFIX
        PREFIX=`pwd`
        ;;
esac

# 这里我设置Java到类路径，安装目录下面的lib目录
# 包含了所有需要用到的jar文件，这些都必须加到类路径中
cp=
for i in $PREFIX/lib/*.jar; do
        cp=$i:$cp
done

# 这里是开始运行了，为了用户的方便，这里尝试3种情况，首先检查用户安装的是不是带JRE版本的，如果是
# 则使用自带的JRE。如果失败，检查JAVA_HOME是否设置了，如果设置了，使用JAVA_HOME，如果仍然失败，
# 则在/usr下面找寻一个可用的JRE。如果/usr下面没有，则再在/opt下面找寻JRE，如果/opt下面也没有，
# 则在用户主目录下找寻JRE，如果还找不到，那就是真的失败了
if [ -e $PREFIX/java ]; then
	$PREFIX/java/bin/java -classpath $cp ip.IPSearch "ipsearch.conf" &
elif [ ! -z $JAVA_HOME ]; then
	$JAVA_HOME/bin/java -classpath $cp ip.IPSearch "ipsearch.conf" &
else
	found=0
	for i in `find /usr -name "java" | grep bin`; do
		if [ -x "$i" ]; then
			$i -classpath $cp ip.IPSearch "ipsearch.conf" &
			found=1
			break
		fi
	done
	
	if [ $found -eq 0 ]; then
		for i in `find /opt -name "java" | grep bin`; do
			if [ -x "$i" ]; then
				$i -classpath $cp ip.IPSearch "ipsearch.conf" -Dsun.java2d.opengl=true &
				found=1
				break
			fi		
		done
	fi

	if [ $found -eq 0 ]; then
		for i in `find $HOME -name "java" | grep bin`; do
			if [ -x "$i" ]; then
				$i -classpath $cp ip.IPSearch "ipsearch.conf" -Dsun.java2d.opengl=true &
				break
			fi
		done
	fi
fi
