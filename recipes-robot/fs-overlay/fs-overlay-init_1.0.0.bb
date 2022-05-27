<<<<<<< HEAD
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM ?= "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SUMMARY = "fs overlay initialization script"
DESCRIPTION = "Script to initialize fs overlays"

SRC_URI =  " \
    file://fsoverlay-init.sh \
    file://fsoverlay-init.service \
"

inherit allarch systemd

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "fsoverlay-init.service"

do_compile () {
}

do_install () {
    install -d ${D}/${sbindir}
    install -m 0755 ${WORKDIR}/fsoverlay-init.sh ${D}/${sbindir}

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/fsoverlay-init.service ${D}${systemd_unitdir}/system
}
