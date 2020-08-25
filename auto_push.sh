#!/bin/bash
# git远程分支地址
GIT_REMOTE_URL="https://github.com/sea-io/SIPs.git"

# git账号
GIT_ACCOUT="baiTea1"

# git账号密码
GIT_PASSWORD="qkhqcc687889,"

# 执行commit
function commit() {
    git add * && git commit -m $1
}

function push() {
    git push -u origin master
}

if [ $1 ]
 then
 echo "提交信息$1"
    commit $1
    push
 else
    echo "缺少提交信息，退出"
    exit 
fi
