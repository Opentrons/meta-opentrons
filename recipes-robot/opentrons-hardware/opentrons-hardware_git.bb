# Hardware project - Python utilties to testing Opentrons hardwawre 

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/Opentrons/opentrons.git;protocol=https;branch=edge;"

PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

inherit insane

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"
PIPENV_APP_BUNDLE_PROJECT_ROOT = "${S}/hardware"
PIPENV_APP_BUNDLE_DIR = "/opt/opentrons-hardware"
PIPENV_APP_BUNDLE_EXTRAS = ""

inherit pipenv_app_bundle
