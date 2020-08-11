#!/bin/bash

USR_CTX=bioface

# top dir that should be owed by bioface
own_dirs=( "/opt/${USR_CTX}" "/var/${USR_CTX}" )

# subdir structure
struct_dirs=( "/opt/${USR_CTX}/etc" "/opt/${USR_CTX}/bin" "/opt/${USR_CTX}/lib" )

# only root has enough power to use this script

if [[ `id -u` -ne 0 ]]; then
  echo "This script should be run as root"
  exit 1
fi

# create bioface user if didn't exist
getent passwd ${USR_CTX} > /dev/null 2>&1
if [[ $? -ne 0 ]]; then
  useradd -d /opt/${USR_CTX} ${USR_CTX}
fi

# privacy filter
umask 027

for i in "${own_dirs[@]}"
do
  if [ ! -d $i ]; then
    mkdir -p $i
  fi
  chown -R ${USR_CTX}.${USR_CTX} $i
done

for i in "${struct_dirs[@]}"
do
  if [ ! -d $i ]; then
    sudo -u ${USR_CTX} mkdir -p $i
  fi
done
