### 开发环境搭建和shell编程

#### xshell工具下载安装

#### xshell连接服务器

+ mac自带ssh见上一节

#### jdk下载安装

+ 将java源码包传输到服务器

+ 解压

  ```shell
  tar -zxvf java...
  ```

+ 将解压包移动到/usr/目录下

+ 设置环境变量

  ```shell
  vim /etc/profile
  # 文件末尾加上
  export JAVA_HOME=/usr/javajdk
  export PATH=$JAVA_HOME/bin:$PATH
  
  #退出使用命令生效
  source /etc/profile
  ```

  

#### Tomcat下载安装

+ 同样解压源码包

+ 进入加压包内部

  ```shell
  sh startup.sh
  ```

+ 暂时开启8080端口

  ```shell
  /sbin/iptables -I INPUT -p tcp --dport 8080 -j ACCEPT
  ```

+ 在主机的浏览器上访问172.16.161.128:8080

+ 发布web项目

  + 将web项目打包成war包，传输到服务器的tomcat/webapp目录下
  + 使用idea开发的项目中
    + 在out目录下的artifacts目录下就是war包里

#### Mysql下载安装

+ 安装步骤

  ```shell
  # 下载mysql的repo源
  wget http://dev.mysql.com/get/mysql57-community-release-el7-8.noarch.rpm
  
  # 安装rpm包
  rpm -ivh mysql57-community-release-el7-8.noarch.rpm
  
  # 安装mysql
  yum install mysql-server
  
  # 启动服务
  service mysqld start
  
  # 查看mysql状态
  systemctl status mysqld
  
  # 去日志中查询初始密码
  grep password /var/log/mysqld.log 
  
  # 使用初始密码登陆
  mysql -u root -p
  passwrd:...
  
  # 更改初始密码
  alter user 'root'@'localhost' identified by '!Lhy1178594290';
  
  
  ```

  

#### mysql启动登陆

#### 图形化工具访问数据库

#### shell程序编写和执行

#### 变量定义和使用规则

#### 算术运算符的使用

#### 条件判断符和if判断

#### case语句的使用

#### for循环的使用

#### while循环的使用

#### 函数的使用