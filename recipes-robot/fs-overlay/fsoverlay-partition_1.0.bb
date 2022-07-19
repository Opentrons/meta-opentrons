DESCRIPTION = "Helper recipe  for tezi image class. Sets up workdirs in fsoverlay partition"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM ?= "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit deploy logging

do_install() {
    :
}

do_build() {
    :
}

do_deploy() {
    
    install -d  ${DEPLOY_DIR_IMAGE}/var/.work/home/
    tar -cf ${DEPLOY_DIR_IMAGE}/var.tar.xz ${DEPLOY_DIR_IMAGE}/var/.work/home/
}

addtask deploy after do_isntall  before do_build

PACKAGE_ARCH = "${MACHINE_ARCH}"

