SECTION = "x11/libs"
SUMMARY = "x11 robot app autostart"
DESCRIPTION = \
"This installs a /usr/bin/x-window-manager script which will start the \
robot app as the last step of the xserver-nodm-init X init. If the script \
ever returns, X will be killed."

LICENSE = "Apache-2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit allarch

RDEPENDS_${PN} += "robot-app xserver-nodm-init"

X_APPLICATION = "opentrons"
INITIAL_PATH = "/opt/opentrons-app"

FILESEXTRAPATHS_prepend := "${THISDIR}/x-window-robot-app:"
S = "${WORKDIR}"

SRC_URI = " \
    file://x-window-manager.in \
"

do_compile () {
    sed -e "s:@PATH@:${INITIAL_PATH}:" -e "s:@APP@:${X_APPLICATION}:" x-window-manager.in > x-window-manager
}

do_install () {
    install -d ${D}/${bindir}
    install -m 0755 ${S}/x-window-manager ${D}/${bindir}
}

# make sure xinput_calibrator is only started once
pkg_postinst_${PN}() {
    rm -f /etc/xdg/autostart/xinput_calibrator.desktop
}
