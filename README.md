# FileApproval
using servicemix



##功能：
客户端（client）发送文件的id 文件名 大小等信息，服务器（service）判断用户名是否含有特殊字符，以及文件大小是否超过2000，如果正常，返回true，不正常返回false

##需求的环境
   1. JDK 1.6 or higher
   
   	* RequiredLib/jdk-8u45-macosx-x64.dmg 双击安装，不解释！

   2. Maven 2.2.1 or higher
   
   	* 将解压后的apache-maven-3.3.3（in RequiredLib）文件夹移到/usr/local/maven目录（不存在则新建）下（找不到这个文件夹请看最后），并重命名为maven-3.3.3，即：/usr/local/maven/maven-3.3.3
   
   	* 终端中执行 vi ~/.bash_profile
   
   		输入
		M3_HOME=/usr/local/maven/maven-3.3.3
	
		PATH=$M3_HOME/bin:$PATH
		
		JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home 
	
		export M3_HOME

		export PATH
	
		export JAVA_HOME
	* 打开终端 输入mvn -version，检查maven是否安装好
##运行方法
1. 打开终端，切换文件夹（命令cd）到FileApproval文件夹
2. 运行mvn install，maven会自动安装构建项目所需要的文件，第一次比较慢orz（如果在一半发现卡住了，关掉终端，重新来一次...）相当于是编译
3. 另开一个终端，切换文件夹到<servicemix_home>/bin/
4. 运行servicemix文件（命令./servicemix)
5. 在运行servicemix的终端输入features:install examples-camel-cxf-rest
（部署刚刚编译好的项目 install后面是我们项目的标识符）
6. 再开一个终端，切换到FileApproval/camel-cxf-rest-client文件夹
7. 输入指令mvn compile exec:java（编译并运行client的代码）
	* 在client中，src/main/java/org/apache/servicemix/examples/camel/rest/client/Client.java代码中，我们初始化了3个File对象，然后调用服务的相应接口
	
	* 在控制台中，可以看到调用的结果
	
##修改代码后的运行方法

1. 在运行servicemix的终端输入features:uninstall examples-camel-cxf-rest
（对项目解除部署）
2. 其他步骤同上

##实现逻辑
1. FileApproval/camel-cxf-rest-client实现了对服务调用的client
/src/main/java/org/apache/servicemix/examples/camel/rest/client/Client.java代码实现了具体的逻辑，初始化对象，向相应的服务接口发送请求
2. 所有的target文件夹里面的内容都是生成的
3. pom.xml是maven的配置文件，标识项目的版本号神马的
4. FileApproval/camel-cxf-rest-service实现了服务的接口

	* src/main/java/org/apache/servicemix/examples/camel/rest model文件夹定义了model的class，ServiceHandler.java定义了对请求如何进行相应，本项目中使用了字符串正则表达式匹配判断文件名是否包含特殊字符，判断文件大小是否合法（<2000），返回相应的返回值

	* src/main/resources/OSGI-INF/blueprint/blueprint.xml定义了服务请求的来源uri，对请求相应的方法（bean method=...），记录日志文件的信息

5. FileApproval/camel-cxf-rest-route定义了服务的接口
	* src/main/java/org/apache/servicemix/examples/camel/rest/FileService.java定义了服务接口的url，参数，返回值等信息
	* /src/main/resources/OSGI-INF/blueprint/blueprint.xml定义了CXF服务器（CXF是一个web service框架）和服务的相关信息
	* camel 是用来定义路由规则的（就是服务请求的来源url 去向url什么的，blueprint.xml就是它的配置文件）
	
## 寻找usr文件夹
在finder>前往文件夹，输入路径/usr/local
