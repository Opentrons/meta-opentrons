#! /bin/sh
rm -f /home/ot3/oe-core/build/tmp-glibc
source /home/ot3/oe-core/export
cd /home/ot3/oe-core/build
echo 'INHERIT += "toradex-mirrors"' >> /home/ot3/oe-core/build/conf/local.conf
echo 'MACHINEOVERRIDES =. "use-head-next:"' >> /home/ot3/oe-core/build/conf/local.conf
echo 'ACCEPT_FSL_EULA = "1"' >> /home/ot3/oe-core/build/conf/local.conf
sed -i '/#MACHINE ?= "verdin-imx8mm"/c\MACHINE ?= "verdin-imx8mm"' /home/ot3/oe-core/build/conf/local.conf
sed -i '/MACHINE ?= "colibri-imx6"/c\#MACHINE ?= "colbri-imx6"' /home/ot3/oe-core/build/conf/local.conf
bitbake -c cleanall tdx-reference-minimal-image
bitbake tdx-reference-minimal-image
