#!/bin/bash
head -n 23 dz05.txt | wc -m #stdout: 1813
tail -n 24 dz05.txt | wc -m #stdout: 1877
cat dz05.txt | grep -i lorem | wc -c > ./bytes.txt #stdout: 1684
tar -xf archive.tar -C ./extracted && echo "Sadrzaj foldera 'extracted':"&& ls ./extracted #stdout: dz05.txt
