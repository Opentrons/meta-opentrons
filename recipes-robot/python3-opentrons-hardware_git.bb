# Hardware projects - Python utilties to testing Opentrons hardwawre 

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

#SRC_URI = "git://github.com/Opentrons/opentrons.git;protocol=https;branch=edge;"
SRC_URI = "git://github.com/Opentrons/opentrons.git;protocol=https;branch=edge;"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "${AUTOREV}"

inherit setuptools3
S = "${WORKDIR}/git"
DISTUTILS_SETUP_PATH = "${S}/hardware/"
#FILESEXTRAPATHS_prepend := "${THISDIR}:"
