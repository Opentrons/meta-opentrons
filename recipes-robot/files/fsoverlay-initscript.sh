#!/bin/sh

mount -t overlay -o lowerdir=/var,upperdir=/data/var,workdir=/data/.work/.var none /var


