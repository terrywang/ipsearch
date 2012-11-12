IP Search Verion 1.1 Build 20080101 What's New

Update
来自纯真的 IP 数据库格式有变化，若使用最新版本的 QQWry.dat 将无法反向查，也不打算再研究解决问题了，学不到什么东西了，也没有人会用。没有价值的事情就不做了。

程序在在切换某些 Look and Feel 的时候没有标题框，Metal 和 Alloy 下都是正常的。下一版准备加设置面板设置一些常用设置。参考JFC Demo优化代码，加入 -Dcom.sun.management.jmxremote 参数，可以用 jconsole 以及 1.6.0_07 加入的 jvisualvm 监控。Jconsole直接可以detect到，如果没有发现可以用jps查看当前jvm的pid。

此程序是研究 LumaQQ source code 写的一个小程序，类似于纯真版 IP 数据包带的工具 showip.exe 类似，只不过这个是 Java Swing 实现，理论上 Write once, run everywhere！

目前有些功能没有时间完成，仅仅做了根据IP查地理位置和根据地理位置查IP地址段。目前是基于 Swing 的 UI，SWT 的改写计划无限期推后。

TODO:
About 中显示所有 JVM 相关环境变量，权当是一个检查系统变量的工具用了:-)

【使用自定义IP地址数据库】
现在改写了程序，利用配置文件可以自定义QQWry.dat所在的位置，比如你有显示IP的QQ各种版本你可以设置为安装目录下的 QQWry.dat，只要在 ipsearch.conf 中修改。

需要注意 Windows 上，如果是 S:\QQ2003\QQWry.dat，你就需要修改成 S:\\QQ2003\\QQWry.dat，因为\在java中是转义符号，两个\\才表示Windows下的目录分隔符。

【切换语言说明】
修改安装目录下 ipsearch.conf 配置文件，把 locale = Locale.CHINESE 这个 key-value 对中的值修改成指定的值，然后重新启动程序即可。
简体中文   locale = Locale.CHINESE
英文   locale = Locale.CHINESE

在主程序中通过菜单动态刷新语言，暂时没有时间研究实现 -_-z

native2ascii [-reverse] [-encoding encoding] [inputfile [outputfile]]
例子
正转:To:Unicode
native2ascii -encoding gbk Native2ASCII.properties Native2ASCII_zh_CN.properties
反转:Reverse Unicode To 指定码
native2ascii -reverse -encoding Big5 Native2ASCII_zh_CN.properties Native2ASCII_zh_TW.properties

【系统需求】
Linux
适用于所有的 Linux 发行版本，x86 以及 x86_64。2.4, 2.6+ 内核。
需要JRE或者JDK 1.4以上的支持。推荐使用JDK 1.5，在Linux下完美显示中文。

Mac OS X 10.5^ Intel

Windows

【如何运行】
GNU/Linux
chmod u+x IPSearch.sh 或者 chmod 750 IPSearch.sh
./IPSearch.sh

Mac OS X
chmod u+x IPSearch.sh 或者 chmod 750 IPSearch.sh
./IPSearch.sh

Windows
需要JRE 1.4.2以上的支持
双击 IPSearch.bat 运行

【Web版本】
Permanently down.

【联系我】
Email: terry.wang [at] live [dot] com
Homepage:
http://terry.im
http://terrywang.net/archives/83

【感谢】
感谢 LumaQQ 小组的优质代码和 stubma 的帮助。还有纯真网络奉献的IP数据。

■ 历史记录
[2009.05.01]
1. 优化 Linux 和Windows下的启动脚本，加入 JVM 参数 -Dsun.java2d.opengl=true 由于使用硬件加速大幅度提升了启动速度。
感谢Linux Toy提供的信息：http://linuxtoy.org/archives/speed-up-slow-java-apps.html
2. 劳动节，劳动一下，迁移Blog数据，顺便更新IP Search。

【2008.01.01】
1. 更新About中个人主页link。
2. 若使用最新的wry.dat，反向查询失效。

【2004.11.01】
1. 修正了一些bug，美化了一些字体显示。

【2004.9.30】
1. 修正了程序在 JDK 1.5 上的bug
2. 加入实时改变 Look & Feel 的功能

【2004.9.28】
1. 基本实现了资源的国际化

【2004.8.31】
1. 增加了读取QQWry.dat数据数量，版本和更新日期，修正了一些小bug。
2. 增加工具栏图标。
3. 增加了多语言支持，目前支持英文和简体中文。

【2004.8.29】
1. 发现一个大bug，导致无法正确读取IP数据文件。现用用配置文件的方法加以修正，下一版中会有更好的解决方案。
2. 增加了 Linux 下的执行脚本。
	
【2004.7.12】
1. 初始发布

【Linux下中文字体问题】
此方法适用于Fedora Core 1，2，暂时没有在其他发行版上测试过。

首先，安装JDK 1.5.0或者仅仅JRE 1.5.0也可以。

然后，把字体文件Simsun.ttf拷贝到JRE的字体目录$JAVA_HOME/jre/lib/fonts下，接着编辑字体目录下的文件fonts.dir，把第一行的数字加1，然后在最后加一行加上simsun.ttf -SungtiL GB-medium-r-normal--0-0-0-0-c-0-gb2312.1988-0，保存。

最后把字体配置文件改名为fontconfig.RedHat.properties这种形式(Fedora Core 1,2也是Redhat的衍生物)，把多余的fontconfig开头的文件全部移到别的地方备份起来以防万一。

介绍一下其字体配置文件的加载方案。其加载顺序为（JavaHome指JRE的根目录，下同）：
        JavaHome/lib/fontconfig.OS.Version.properties
        JavaHome/lib/fontconfig.OS.Version.bfc
        JavaHome/lib/fontconfig.OS.properties
        JavaHome/lib/fontconfig.OS.bfc
        JavaHome/lib/fontconfig.Version.properties
        JavaHome/lib/fontconfig.Version.bfc
        JavaHome/lib/fontconfig.properties
        JavaHome/lib/fontconfig.bfc
其中，OS字段可以是：
Windows： "98", "2000", "Me", "XP", "2003"。
Solaris：空。
Linux： "Sun", "RedHat", "Turbo", "SuSE"。
而Version字段指该OS的版本号。

在Fedora Core 2上JDK 1.5.0中文支持问题之研究，最初发布于2004年10月，在Java Research等多个网站，后来被广泛转载。
http://docs.google.com/Doc?id=dhdx8dp4_56dx4r87k4

English
IPSearch Swing Edition
Description:
A tool based on Java Swing which locates an IP to physical location or the other way around. It is quite simple and possibly junk to some experts. I wrote this just to test my Swing and WSH/Bash scripting skills, as well as the Java cross-platform feature. 

To be honest this was my first attempt to implement a swing application using MVC design pattern while it seemed to be not quite successful. The source codes look ugly and I don't dare to share it. If you do require one for study purpose, drop me a line.

System Requirements:
Operating sytems:
GNU/Linux
Mac OS X
Microsoft Windows
Sun Solaris
Java Runtime Environment: JRE 1.5.0_03^ JRE 1.6.0_07^ JRE 1.7.0_07^
