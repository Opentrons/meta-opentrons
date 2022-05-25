LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM ?= "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SUMMARY = "ot3 fs overlay partition mount service"
inherit systemd
SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = "var.mount etc-data.mount home.mount partition.service"

SRC_URI_append = " file://var.mount \
	file://etc-data.mount \
	file://home.mount \
	file://var.mount \
	file://partition.service \
	file://setup-partition.sh \
"
FILES_${PN} += "${systemd_unitdir}/system/var.mount ${systemd_unitdir}/system/etc-data.mount ${systemd_unitdir}/system/home.mount ${systemd_unitdir}/system/partition.service ${sbindir}/setup-partition.sh"

do_install_append() {
  install -d ${D}/${sbindir}
  install -m 0755 ${WORKDIR}/setup-partition.sh ${D}/${sbindir}
  install -d ${D}/${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/home.mount ${D}/${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/etc-data.mount ${D}/${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/var.mount ${D}/${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/partition.service ${D}/${systemd_unitdir}/system

}
