DESCRIPTION = "Script for connecting to WIFI AP"
SUMMARY = "Connect OT3 to a wifi AP"
LICENSE = "CLOSED"

RDEPENDS_${PN} += "bash"

SRC_URI = "file://wifi-connect.sh"

do_install_append() {
    install -d ${D}${bindir}/wifi-connect
    install -m 0755 ${WORKDIR}/wifi-connect.sh ${D}${bindir}/wifi-connect
}

FILES_${PN} = "\
    ${bindir} \
"
