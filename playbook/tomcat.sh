#!/bin/bash
#
# Application     This shell script takes care of starting and stopping Application
#
# chkconfig: - 80 20
#
### BEGIN INIT INFO
# Provides: Application
# Required-Start: $network $syslog
# Required-Stop: $network $syslog
# Default-Start:
# Default-Stop:
# Description: Release implementation for Servlet 2.5 and JSP 2.1
# Short-Description: start and stop Application
### END INIT INFO

## Source function library.
#. /etc/rc.d/init.d/functions
#export JAVA_HOME=/usr/java/default
#export JAVA_OPTS="-Dfile.encoding=UTF-8 \
#  -Dcatalina.logbase=/var/log/Application7 \
#  -Dnet.sf.ehcache.skipUpdateCheck=true \
#  -XX:+DoEscapeAnalysis \
#  -XX:+UseConcMarkSweepGC \
#  -XX:+CMSClassUnloadingEnabled \
#  -XX:+UseParNewGC \
#  -XX:MaxPermSize=128m \
#  -Xms512m -Xmx512m"
#export PATH=$JAVA_HOME/bin:$PATH
APP_HOME="$(cd "$(dirname "$0")"; pwd)"
SHUTDOWN_WAIT=20

echo "APP_HOME:$APP_HOME"

app_pid() {
  echo `ps aux | grep org.apache.catalina.startup.Bootstrap |grep $APP_HOME | grep -v grep | awk '{ print $2 }'`
}

start() {
  pid=$(app_pid)
  if [ -n "$pid" ] 
  then
    echo "Application is already running (pid: $pid)"
  else
    # Start Application
    echo "Starting Application"
    ulimit -n 100000
    umask 007
    $APP_HOME/bin/startup.sh
  fi
  return 0
}

stop() {
  pid=$(app_pid)
  if [ -n "$pid" ]
  then
    echo "Stoping Application"

    let kwait=$SHUTDOWN_WAIT
    count=0;
    until [ `ps -p $pid | grep -c $pid` = '0' ] || [ $count -gt $kwait ]
    do
      echo -n -e "\nwaiting for processes to exit";
      sleep 1
      let count=$count+1;
    done

    if [ $count -gt $kwait ]; then
      echo -n -e "\nkilling processes which didn't stop after $SHUTDOWN_WAIT seconds"
      kill -9 $pid
    fi
  else
    echo "Application is not running"
  fi
 
  return 0
}

case $1 in
start)
  start
;; 
stop)   
  stop
;; 
restart)
  stop
  start
;;
status)
  pid=$(app_pid)
  if [ -n "$pid" ]
  then
    echo "Application is running with pid: $pid"
  else
    echo "Application is not running"
  fi
;; 
*)
  echo "Usage: $0 {start|stop|restart|status}"
  exit 0
esac    
exit 0
