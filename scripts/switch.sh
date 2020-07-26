#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

function switch_proxy(){
  IDLE_PORT=$(find_idle_port)

  echo "> 전환할 포트: $IDLE_PORT"
  echo "> 포트 전환"
  # 홑따옴표를 써야 $service_url을 변수가 아닌 그대로 인식할 수 있음
  # echo를 사용해 파이프라인으로 넘겨 뒤에서 받아 service-url.inc에 덮어씀
  echo 'set \$service_url http://127.0.0.1:${IDLE_PORT};' | sudo tee /etc/nginx/conf.d/service-url.inc

  echo "> 엔진엑스 Reload"
  # reload는 restart와 달리 끊김없이 다시 불러옴.
  # 다만 중요 설정은 반영되지 않으니 restart를 사용해야 함
  # 지금은 외부파일 service-url을 다시 불러오는 것이기 때문에. reload를 사용함
  sudo service nginx reload
}