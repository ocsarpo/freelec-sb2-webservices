#!/usr/bin/env bash

# 쉬고 있는 profile 찾기: real1이 사용중이면 real2가 쉬고있고 반대면 real1이 쉬고있음

function find_idle_profile()
{
  # 현재 엔진엑스가 바라보고 있는 스프링부트가 정상 동작하는 지 확인
  # 응답값을 httpStatus로 받음 정상:200, 오류 400 ~ 503
  # 400 이상은 모두 예외로 보고 real2를 현재 profile로 사용함
 RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

 if [ ${RESPONSE_CODE} -ge 400 ] # 400 보다 크면 에러
 then
   CURRENT_PROFILE=real2
 else
   CURRENT_PROFILE=$(curl -s http://localhost/profile)
 fi

 if [ ${CURRENT_PROFILE} == real1 ]
 then
   IDLE_PROFILE=real2
 else
   IDLE_PROFILE=real1
 fi

# bash 스크립트는 값 반환기능이 없어서 마지막줄의 출력을 클라에서(find_idle_port) 잡아서 사용함 그러니 중간에 echo 쓰면 안됨
 echo "${IDLE_PROFILE}"
}

# 쉬고있는 profile의 port 찾기
function find_idle_port()
{
  IDLE_PROFILE=$(find_idle_profile)

  if [ ${IDLE_PROFILE} == real1 ]
  then
    echo "8081"
  else
    echo "8082"
  fi
}