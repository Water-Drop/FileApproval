# FileApproval
using servicemix



##功能：
客户端（client）发送文件的id 文件名 大小等信息，服务器（service）判断用户名是否含有特殊字符，以及文件大小是否超过2000，如果正常，返回true，不正常返回false

##需求的环境
   1. JDK 1.6 or higher
   
   	* FileApproval/RequiredLib/jdk-8u45-macosx-x64.dmg 双击安装，不解释！

   2. Maven 2.2.1 or higher
   
   	* 将解压后的apache-maven-3.3.3文件夹移到/usr/local/maven目录（不存在则新建）下，并重命名为maven-3.3.3，即：/usr/local/maven/maven-3.3.3
   
   	* 终端中执行 vi ~/.bash_profile
   
   		输入
		M3_HOME=/usr/local/maven/maven-3.3.3
	
		PATH=$M3_HOME/bin:$PATH
		
		JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home 
	
		export M3_HOME

		export PATH
	
		export JAVA_HOME
	* 打开终端 输入mvn -version，检查maven是否安装好