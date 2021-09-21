# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/Opentrons/opentrons.git;protocol=https;branch=edge;"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

inherit insane

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"
PIPENV_APP_BUNDLE_PROJECT_ROOT = "${S}/robot-server"
PIPENV_APP_BUNDLE_DIR = "/opt/opentrons-robot-server"
PIPENV_APP_BUNDLE_USE_GLOBAL = "numpy systemd-python"
PIPENV_APP_BUNDLE_STRIP_HASHES = "yes"
PIPENV_APP_BUNDLE_EXTRAS = ""

do_compile_append () {
    rm -rf ${PIPENV_APP_BUNDLE_SOURCE_VENV}/opentrons/resources/scripts
}

RDEPENDS_${PN} += " python3-numpy python3-systemd"

inherit pipenv_app_bundle
