LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM ?= "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SUMMARY = "ot3 fs overlay partition mount service"
BB_STRICT_CHECKSUM = "0"
DEPENDS = "ot3-overlay-mount-points"
inherit systemd
SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = "mnt-var.mount"

SRC_URI_append = " file://mnt-var.mount "
FILES_${PN} += "${systemd_unitdir}/system/mnt-var.mount"

do_install_append() {
  install -d ${D}/${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/data.mount ${D}/${systemd_unitdir}/system
}
