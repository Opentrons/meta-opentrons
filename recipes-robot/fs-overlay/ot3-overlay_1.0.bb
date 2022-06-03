LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM ?= "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SUMMARY = "ot3 fs overlay partition mount service"
inherit systemd
SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = "var.mount overlay_dirs.service"

SRC_URI_append = " file://var.mount \
	file://overlay_dirs.service \
	file://setup-overlay-dirs.sh \
"
FILES_${PN} += "${systemd_unitdir}/system/var.mount ${systemd_unitdir}/system/overlay_dirs.service"

do_install_append() {
  install -d ${D}/${sbindir}
  install -m 0755 ${WORKDIR}/setup-overlay-dirs.sh ${D}/${sbindir}
  install -d ${D}/${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/var.mount ${D}/${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/overlay_dirs.service ${D}/${systemd_unitdir}/system

}
