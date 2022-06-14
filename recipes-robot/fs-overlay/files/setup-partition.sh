#!/bin/sh

DEV=/dev/mmcblk0
PART=${DEV}p4

create_dirs() {
 	# create dirs for fsoverlay
        test -d /var/home/ || install -d /var/home/
        test -d /var/.work/home/ || install -d /var/.work/home/
}

mount_part() {
	test -d /var || install -d /var
	#avoid mounting /dev/mmcblk0p4 as we
	#check if it needs formatting
	if ! findmnt -S $PART >/dev/null
	then
		if ! mount -t ext4 $PART /var >/dev/null
		then
			# format the partition
			echo 'Partiton 4 - /dev/mmcblk0p4 being formatted. This partition is used by fsoverlay.' | systemd-cat -p info
			mkfs.ext4 $PART 
			tune2fs -r 0 -c 0 $PART 
		fi

	fi
}

# image_type_tezi_ot3.bbclass would have this partition setup when emmc is flashed using Toradex update utility.
# This would just act as an additional check usually, (in case user formats this partition, etc.
if ! test -e $PART
then
	# create partition using sfdisk
	echo 'Partiton 4 not found, Creating partition /dev/mmcblk0p4. This partition is used by fsoverlay.' | systemd-cat -p info
	echo 'size=+, type=L' | sfdisk -N 4 $DEV 
fi
mount_part
create_dirs
