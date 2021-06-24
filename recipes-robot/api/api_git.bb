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
SRCREV = "bf8fbe8a98c14061af8d5bbb22d7a6b95a25eaab"
inherit setuptools3 insane
INSANE_SKIP = "arch"
RDEPENDS_${PN} += "python3-numpy python3-typing-extensions python3-jsonschema python3-aionotify python3-pyserial python3-fcntl"
S = "${WORKDIR}/git"
DISTUTILS_SETUP_PATH = "${S}/api/"
#do_install(){

#	rm -rf ${D}/${D}${PYTHON_SITEPACKAGES_DIR}/opentrons/resources/scripts/lpc21isp
#}







