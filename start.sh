#! /bin/sh
rm -f /home/ot3/oe-core/build/tmp-glibc
rm -rf /home/ot3/oe-core/build/deploy
source /home/ot3/oe-core/export
cd /home/ot3/oe-core/build
echo 'ACCEPT_FSL_EULA = "1"' >> /home/ot3/oe-core/build/conf/local.conf
echo 'MACHINEOVERRIDES =. “use-head-next:”' >> /home/ot3/oe-core/build/conf/local.conf
sed -i '/#MACHINE ?= "verdin-imx8mm"/c\MACHINE ?= "verdin-imx8mm"' /home/ot3/oe-core/build/conf/local.conf
sed -i '/MACHINE ?= "colibri-imx6"/c\#MACHINE ?= "colbri-imx6"' /home/ot3/oe-core/build/conf/local.conf
bitbake tdx-reference-minimal-image
