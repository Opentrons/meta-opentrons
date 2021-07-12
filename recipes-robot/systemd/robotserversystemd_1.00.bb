inherit systemd

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = "opentrons-robot-server.service"
LICENSE="CLOSED"
SRC_URI_append = "  file://opentrons-robot-server.service file://opentrons.target file://opentrons.targets.wants/ "
FILES_${PN} += "${systemd_system_unitdir}/opentrons-robot-server.service"
FILES_${PN} += "${systemd_system_unitdir}/opentrons.target"
FILES_${PN} += "${systemd_system_unitdir}/opentrons.targets.wants"

RDEPENDS_${PN} = "nginx python3-uvicorn"
do_install_append() {
  install -d ${D}${systemd_system_unitdir}
  install -d ${D}/etc/systemd/system
  install -m 0644 ${WORKDIR}/opentrons-robot-server.service ${D}${systemd_system_unitdir}
  install -m 0644 ${WORKDIR}/opentrons.target ${D}${systemd_system_unitdir}
  cp -r ${WORKDIR}/opentrons.targets.wants ${D}${systemd_system_unitdir}/opentrons.targets.wants
  cd ${D}/etc/systemd/system
  ln -s -r ${D}${systemd_system_unitdir}/opentrons.target default.target
}
