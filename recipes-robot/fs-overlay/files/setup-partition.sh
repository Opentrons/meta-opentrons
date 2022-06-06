if ! test -e /dev/mmcblk0p4
then
	# create partition using sfdisk
	echo 'size=+, type=L' | sfdisk -N 4 /dev/mmcblk0
	test -d /var || install -d /var
	if ! mount /dev/mmcblk0p4 /var
	then
		# format the partition
		mkfs.ext4 /dev/mmcblk0p4
		tune2fs -r 0 -c 0 /dev/mmcblk0b4
	fi
	if mount /dev/mmcblk0p4 /var
	then
		# create dirs for fsoverlay
		test -d /var/etc/  || install -d /var/etc/
		test -d /var/home/ || install -d /var/home/
		test -d /var/.work/etc/ || install -d /var/.work/etc/
		test -d /var/.work/home/ || install -d /var/.work/home/

	fi
fi
