#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)  # 현재 stop.sh가 속한 경로 찾음.
source ${ABSDIR}/profile.sh # 이 문장과 같이 profile.sh의 경로를 찾기 위함 # import 구문

IDLE_PORT=$(find_idle_port)

echo "> $IDLE_PORT 에서 구동중인 애플리케이션 pid 확인"
IDLE_PID=$(lsof -ti tcp:${IDLE_PORT})

if [ -z ${IDLE_PID} ]
then
  echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않음"
else
  echo "> kill -15 $IDLE_PID"
  kill -15 ${IDLE_PID}
  sleep 5
fi