SUMMARY = "fs overlay initialization script"
DESCRIPTION = "Script to do any first boot init, started as a systemd service which removes itself once finished"
LICENSE = "CLOSED"
PR = "r3"

SRC_URI =  " \
    file://fsoverlay-initscript.sh \
    file://fsoverlay-initscript.service \
"

inherit allarch systemd

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "fsoverlay-initscript.service"

do_compile () {
}

do_install () {
    install -d ${D}/${sbindir}
    install -m 0755 ${WORKDIR}/fsoverlay-initscript.sh ${D}/${sbindir}

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/fsoverlay-initscript.service ${D}${systemd_unitdir}/system
}
