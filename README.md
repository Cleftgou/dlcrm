# 第一部分 项目概述

## 项目简介

​		**动力CRM项目是一款商业的集营销销售为一体的客户关系管理系统，其采用信息化、数字化方式来进行营销销售及客户管理；通过技术方式实现的这一套系统，可用于自动化分析销售、市场营销、客户服务等各个流程，建立起以客户为中心的信息化管理，从而支持更加有效的市场营销、销售与服务等各个环节，提高效率，提高效益。**

## 技术架构

- **前端：Html、CSS、JavaScript、Vue、Axios、Element Plus**
- **前端环境：node.js(18.19.1)、npm(10.5.0)**
- **后端：Spring Boot、Spring Security、MyBatis、Spring AI**
- **第三方组件：JWT、EasyExcel、ECharts**
- **服务器：Linux部署Nginx服务器**
- **数据库：MySQL、Redis**
- **项目依赖管理：Maven**
- **项目版本控制：Git**
- **项目开发工具：IDEA、Apipost、XShell、Xftp 7、Another Redis Desktop Manager**

## VMware虚拟机配置

- **虚拟机环境（CentOS 7 + JDK21+mysql8.0.34+redis7.2.4+nginx1.26.0）**

- **虚拟机root用户密码123456，mysql账号root密码123456**

- **安装步骤以及应用配置见尚硅谷的linux安装教程**

- **让主机链接到Linux虚拟机（授予root远程访问的能力）**

    - 命令如下

      ```
      进入mysql ：mysql -uroot -p123456
      
      sql命令（这里的user表是数据库自带的）：update user set host = '%' where user = 'root';
      
      查询确认结果即可：mysql> SELECT user,host FROM mysql.user WHERE user='root';
      +------+------+
      | user | host |
      +------+------+
      | root | %    |
      +------+------+
      1 row in set (0.00 sec)
      ```



- **需要关闭centOS的防火墙（教程链接https://blog.csdn.net/JierenDong/article/details/80543161）**

    - 这里是具体的命令

      ```
      1.首先切换到 root：su -root
      2.进入 /bin 目录：cd /bin
      3.执行命令：systemctl stop firewalld.service（关闭防火墙)
              systemctl disable firewalld.service（关闭防火墙自动启动）
      4.查看防火墙状态：systemctl status firewalld.service（查看防火墙服务状态）
      
      看到这样就OK了：
      ● firewalld.service - firewalld - dynamic firewall daemon
      Loaded: loaded (/usr/lib/systemd/system/firewalld.service; disabled; vendor preset: enabled)
      Active: inactive (dead)
      Docs: man:firewalld(1)
      4月 22 18:21:44 localhost.localdomain systemd[1]: Starting firewalld - dynamic firewall daemon...
      4月 22 18:21:44 localhost.localdomain systemd[1]: Started firewalld - dynamic firewall daemon.
      4月 22 18:21:44 localhost.localdomain firewalld[839]: WARNING: AllowZoneDrifting is enabled. This i...w.
      4月 24 11:05:43 localhost.localdomain systemd[1]: Stopping firewalld - dynamic firewall daemon...
      4月 24 11:05:44 localhost.localdomain systemd[1]: Stopped firewalld - dynamic firewall daemon.
      Hint: Some lines were ellipsized, use -l to show in full.
      ```

- **启动redis**

    - 具体命令

      ```
      redis服务器启动命令：redis-server /myredis/redis7.conf
      查看redis客户端服务器状态：ps -ef|grep redis|grep -v grep
      redis客户端启动：redis-cli -a 123456
      客户端测试命令：127.0.0.1:6379> ping
      客户端登录后关闭redis服务器：shutdown
      ```

- **启动nginx（具体配置见原版word文档）**

    - 具体命令

      ```
      nginx启动命令：/opt/nginx/sbin/nginx -c /opt/nginx/conf/nginx.conf
      查看nginx状态：# ps -ef | grep nginx
      ```

    