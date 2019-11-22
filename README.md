# 酷Q机器人http查询接口

通过酷Q的http插件查询求生之路2、csgo等valve起源引擎服务器、以及使用rcon命令操作服务器

## 项目简介
- 基于Spring boot创建，从[RconA2sAPi](https://github.com/hundunzhidian/RconA2sAPi/)项目扩展而来
- 可以通过qq通信查询服务器、rcon命令操作服务器
- Rcon库协议基于[rconed](http://rconed.sf.net/)，修复了大部分错误，以及修复了对中文不兼容的问题


## 安装运行
- 使用前，请先阅读[酷Qhttp插件](https://github.com/richardchien/coolq-http-api/)使用方式，并配置为服务器的地址,若下面的web服务器端口为9090，则酷Q插件配置为:
```shell
"post_url": "http://127.0.0.1:9090"
```
**注意酷Qhttp的配置方式为ini或者json方式，配置post_url项即可**
- 项目基于JDK1.8,请下载JDK1.8及以上版本运行，运行命令,以下的qqrobota2srcon-0.0.1-SNAPSHOT.jar均为打包后的jar名称，可能有所不同，但文件一定要是jar格式:
```shell
java -jar qqrobota2srcon-0.0.1-SNAPSHOT.jar --server.port=9090
```
*其中，-jar 后面的参数为项目jar文件，9090为web服务端口号*
**注意：若Windows运行出现乱码，请在启动项前加入-Dfile.encoding=utf-8参数，例**
```shell
java -Dfile.encoding=utf-8 -jar qqrobota2srcon-0.0.1-SNAPSHOT.jar --server.port=9090
```
## 配置
- 配置文件基于springboot的properties文件规范，可以查看config目录下例子，文件名称不要随意更改
####服务器ip,以;隔开
```shell
config.server.ip=127.0.0.1:27015;127.0.0.2:27015
```
####服务器密码,不要使用特殊字符,以;隔开,跟上面ip对应
```shell
config.server.password=aaa;bbb
```
####服务器名称,英文或数字，不要使用特殊字符，以;隔开，跟上面ip一一对应
```shell
config.server.name=server1;server2
```
####管理员qq号,数字,以;隔开，执行rcon命令时需要校验
```shell
config.server.qq=123123
```
**注意：配置为范例，请结合实际情况更改**

## 使用命令

- */list* 
    查询当前已注册服务器列表
- */server*
    查询当前已注册服务器列表信息，包括服务器名称、当前地图、当前人数、网络延迟
    **若带有服务器名称(例:/server server1)，则查询服务器名称为server1的信息**
- */players*
    **若带有附加参数(例: /players 123.123.123.123:27015)，则查询参数ip的服务器人数，否则查询已注册服务器的人数**
    **若带有服务器名称(例:/players server1)，则查询服务器名称为server1的信息**
- */exec name cmd*  执行服务器命令，name为配置项里面的服务器名称，cmd为rcon命令
    例：
     ```shell
     /exec server1 map c2m1_highway
     ```