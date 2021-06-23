# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Asyncio-powered inotify library"
HOMEPAGE = "https://github.com/rbarrois/aionotify"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=95348062da1183a702f2bfba094b9952"

SRC_URI[md5sum] = "252066f7ab85aa71a89d23d3e42ce041"
SRC_URI[sha1sum] = "84967f33d510f95c5c43418e71f72fbd145f01f8"
SRC_URI[sha256sum] = "64b702ad0eb115034533f9f62730a9253b79f5ff0fbf3d100c392124cdf12507"
SRC_URI[sha384sum] = "525eccbb65f9df9b347f2a7c5ce39e731dcc8c240a0739a2dfde930b92d557c65da089abf8576352f3745b8c5017ae3d"
SRC_URI[sha512sum] = "a5106b2e67b7cf42d182691be352303a41e6f79997ab046ca80dff3c4951f6acec1c3b206bf2d42a15212751a43ac3c3a6a999360bb4b4bf1a58dace0b172575"

S = "${WORKDIR}/aionotify-${PV}"

inherit setuptools3 pypi

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python3-asyncio python3-core python3-ctypes python3-logging"
