#!/bin/sh

#获得当前路径

CURRENT_PATH=$(cd `dirname $0`; pwd)

echo "1: rm -rf $CURRENT_PATH/src/main/resources/*"
rm -rf $CURRENT_PATH/src/main/resources/*

echo "2: git date update start ..."
#svn update
#git clone https://wilyquan:123456@github.com/wilyquan/nb.git
#git pull origin master
git fetch --all
git reset --hard origin/master
#git fetch 只是下载远程的库的内容，不做任何的合并 git reset 把HEAD指向刚刚下载的最新的版本
echo "~~~~~~ git date update end ..."

#开始处理配置文件,复制对应的配置文件替换
echo "3: #开始处理配置文件"
echo $CURRENT_PATH/src/main/resources/iot-release.properties $CURRENT_PATH/src/main/resources/iot.properties 
cp -rf $CURRENT_PATH/src/main/resources/iot-release.properties $CURRENT_PATH/src/main/resources/iot.properties 
#cp -rf $CURRENT_PATH/src/main/resources/cache/ehcache-local-en.xml $CURRENT_PATH/src/main/resources/cache/ehcache-local.xml
#cp -rf $CURRENT_PATH/src/main/resources/cache/ehcache-rmi-en.xml $CURRENT_PATH/src/main/resources/cache/ehcache-rmi.xml

#服务器路径
SDIR=/netbean/server/netbean-server
#源war包名称
WAR_SNAME=iot-admin
#目的war包名称
WAR_DNAME=ROOT
#等待时间
WAITSEC=10

#build项目
echo "4: MAVEN INSTALL"
mvn clean install -U -DRUNTIME_ENV=product -P product
BACK_NUM=$(date +%Y%m%d%H%M)

echo "5: STOP TOMCAT"
$SDIR/bin/catalina.sh stop
echo "sleeping ${WAITSEC} seconds"
sleep ${WAITSEC}s

echo "6: copy war to server war"
mv -f $SDIR/webapps/$WAR_DNAME.war $SDIR/bakapps/$WAR_DNAME.war.$BACK_NUM
cp -f $CURRENT_PATH/target/$WAR_SNAME.war $SDIR/webapps/$WAR_DNAME.war

echo "7: TOMCAT START"
$SDIR/bin/catalina.sh start

#mv -f /opt/server/iot-server/webapps/iot.war /opt/server/iot-server/bakapps/iot.war.$BACK_NUM
#cp /opt/deploy/jv-master/iot-admin/target/iot-admin.war /opt/server/iot-server/webapps/iot.war
