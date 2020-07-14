### linux系统概述和编程基础

#### linux系统概述

+ 概述
  + Linux系统诞生于1991年，在林纳斯·托瓦兹(Linus Torvalds)上大学期间因不满意教学中使用的 MINIX操作系统并出于个人爱好设计和研发的。 
  + Linux系统是一套免费、自由、开发源代码的类Unix操作系统，是一个基于Posix(Portable Operating System Interface)标准和多用户、多任务、支持多线程的操作系统。
  +  Linux能运行主要的Unix工具软件、应用程序和网络协议，支持32位和64位硬件。
+ 主要发行版本
  + Redhat:目前最大的Linux发行商，功能全面并且稳定，于2018年10月被IBM以340亿美元的价格 收购。 
  + Ubuntu:目前最好的Linux桌面版，拥有很人性化的亮丽的交互界面，强大的软件源支持。 
  + CentOS:是RHEL的克隆版本，可以认为是免费版本的Redhat系统。

#### Vmware的下载和安装

+ windows下载vm pro
+ mac下载vm fusion（这边下载的是fusion10）

#### Centos系统的安装

+ 直接移centos镜像到vm里就可以

#### 联网（Mac）

+ 虚拟机选择nat模式

+ fusion默认安装在app里，我们需要在mac中查看虚拟网卡的网关和子网掩码(vmnet1是仅主机模式配置，vmnet8是nat模式配置)

  ```shell
  cat /Library/Preferences/VMware Fusion/vmnet8/nat.conf
  ```

+ 查看到网关和子网掩码我们开始配置ip

  ```shell
  TYPE=Ethernet
  BOOTPROTO=static
  DEFROUTE=yes
  PEERDNS=yes
  PEERROUTES=yes
  IPV4_FAILURE_FATAL=no
  IPV6INIT=yes
  IPV6_AUTOCONF=yes
  IPV6_DEFROUTE=yes
  IPV6_PEERDNS=yes
  IPV6_PEERROUTES=yes
  IPV6_FAILURE_FATAL=no
  NAME=eno16777736
  UUID=88a1ac28-0767-4233-aa94-43abed75d34b
  DEVICE=eno16777736
  ONBOOT=yes
  IPADDR=172.16.161.128
  GATEWAY=172.16.161.2
  NETMASK=255.255.255.0
  DNS1=114.114.114.114 #公共域名解析服务器ip，114挺好用，其他的也可以用，百度解决
  
  ```

+ 重启网络服务，测试链接

  ```shell
  service network restart
  ping baidu.com
  ```

#### mac启动ssh和ftp服务与虚拟机通讯

+ ssh

  + 打开终端
  + command+shift+k（根据虚拟机中系统ip进行配置）

+ ftp

  + macos自带scp命令用于处理文件传输问题

  + 上传本地文件到远程服务器

    ```shell
    scp -r local_folder remote_username@remote_ip:remote_folder
    
    #example 将1.txt上传到服务器中的/home/apple目录
    scp -r ./1.txt apple@172.16.161.128:/home/apple
    ```

  + 从远程服务器下载文件到本地

    ```shell
    scp -r remote_username@remote_ip:remote_folder local_folder 
    
    #example 将test目录搬运到mac当前目录
    scp -r apple@172.16.161.128:/home/apple/test ./ 
    ```

  + scp常见参数

    ```shell
    1.-v 显示进度
    2.-r 递归处理
    3.-C 压缩选项
    4.-P 选择端口
    5. man scp
    ```

    

#### 目录结构

#### 查看目录内容的命令

#### 路径切换和查看命令

#### 用户查看和切换

#### 用户管理和查看命令目录

#### 文件操作的相关命令

#### 目录操作相关命令

#### 查找操作的相关命令

#### 权限管理相关命令

#### 进程相关命令

#### 其他命令

#### 命令模式和编辑模式的切换

#### 命令模式的常用命令

#### 底行模式和注意事项

