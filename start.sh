#! /bin/sh
rm -f /home/ot3/oe-core/build/tmp-glibc
rm -rf /home/ot3/oe-core/build/deploy
source /home/ot3/oe-core/export
#apply patch 

cd /home/ot3/oe-core/build
echo 'INHERIT += "toradex-mirrors"' >> /home/ot3/oe-core/build/conf/local.conf
echo 'MACHINEOVERRIDES =. "use-head-next:"' >> /home/ot3/oe-core/build/conf/local.conf
echo 'ACCEPT_FSL_EULA = "1"' >> /home/ot3/oe-core/build/conf/local.conf
sed -i '/#MACHINE ?= "verdin-imx8mm"/c\MACHINE ?= "verdin-imx8mm"' /home/ot3/oe-core/build/conf/local.conf
sed -i '/MACHINE ?= "colibri-imx6"/c\#MACHINE ?= "colbri-imx6"' /home/ot3/oe-core/build/conf/local.conf

patch /home/ot3/oe-core/layers/meta-toradex-nxp/recipes-kernel/linux/linux-toradex_5.4-2.3.x.bb /home/ot3/linux-toradex_5.4-2.3.x.patch
bitbake -c cleanall tdx-reference-minimal-image
bitbake tdx-reference-minimal-image
