# change to APACHE-2 and get rid of qa check-sum errors 
LICENSE = "CLOSED"
BB_STRICT_CHECKSUM = "0"
DEPENDS = "ot3-overlay-mount-points"
inherit systemd
SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = "data.mount"

SRC_URI_append = " file://data.mount "
FILES_${PN} += "${systemd_unitdir}/system/data.mount"

do_install_append() {
  install -d ${D}/${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/data.mount ${D}/${systemd_unitdir}/system
}
