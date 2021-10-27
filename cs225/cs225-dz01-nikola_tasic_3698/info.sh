#!/usr/bin/bash

set -v

uname -a

lscpu | head -n 16

free -h

lspci

lsusb
