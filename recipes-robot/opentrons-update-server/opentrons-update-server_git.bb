# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

# TODO (amitl, 2022-03-21): MOVE BACK TO EDGE!!!
SRC_URI = "git://github.com/Opentrons/opentrons.git;protocol=https;branch=RET-71-ot3-update-server;"

RDEPENDS_${PN} += " bmap-tools libubootenv nginx python3-dbus python3-aiohttp systemd-python"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

inherit insane systemd

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = "opentrons-update-server-ot3.service"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"
PIPENV_APP_BUNDLE_PROJECT_ROOT = "${S}/update-server"
PIPENV_APP_BUNDLE_DIR = "/opt/opentrons-update-server"
PIPENV_APP_BUNDLE_STRIP_HASHES = "yes"
PIPENV_APP_BUNDLE_EXTRAS = ""
PIPENV_APP_BUNDLE_USE_GLOBAL = "python3-aiohttp systemd-python"

do_install_append() {
  install -d ${D}/${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/opentrons-update-server-ot3.service ${D}/${systemd_unitdir}/system
}

inherit pipenv_app_bundle
