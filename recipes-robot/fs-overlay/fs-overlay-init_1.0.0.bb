SUMMARY = "fs overlay initialization script"
DESCRIPTION = "Script to initialize fs overlays"
# todo: change to APACHE-2 once qa check-sum error work around is figured out
LICENSE = "CLOSED"
PR = "r3"

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
