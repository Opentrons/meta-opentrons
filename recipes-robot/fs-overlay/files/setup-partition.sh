#!/bin/sh

DEV=/dev/mmcblk0
PART=${DEV}p4

create_dirs() {
 	# create dirs for fsoverlay
	test -d /etc/data || install -d /etc/data
	test -d /var/etc/data || install -d /var/etc/data/
        test -d /var/home/ || install -d /var/home/
	test -d /var/.work/etc/data/ || install -d /var/.work/etc/data
        test -d /var/.work/home/ || install -d /var/.work/home/
}

mount_part() {
	test -d /var || install -d /var
	#avoid mounting /dev/mmcblk0p4 as we
	#check if it needs formatting
	if ! findmnt -S /dev/mmcblk0p4 >/dev/null
	then
		if ! mount -t ext4 $PART /var >/dev/null
		then
			# format the partition
			mkfs.ext4 $PART 
			tune2fs -r 0 -c 0 $PART 
		fi

	fi
}

if ! test -e /dev/mmcblk0p4
then
	# create partition using sfdisk
	echo 'size=+, type=L' | sfdisk -N 4 $DEV 
fi
mount_part
create_dirs
