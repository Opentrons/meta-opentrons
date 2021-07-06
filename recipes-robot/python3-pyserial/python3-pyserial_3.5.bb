# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Python Serial Port Extension"
HOMEPAGE = "https://github.com/pyserial/pyserial"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE.txt
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=520e45e59fc2cf94aa53850f46b86436"

SRC_URI[md5sum] = "1cf25a76da59b530dbfc2cf99392dc83"
SRC_URI[sha1sum] = "8709dd72730f6d097fc2e039bc0e264dc1c9341a"
SRC_URI[sha256sum] = "3c77e014170dfffbd816e6ffc205e9842efb10be9f58ec16d3e8675b4925cddb"
SRC_URI[sha384sum] = "63429431af8b978ee62965422b56ac0268076cb0e43732476c9be62c0a9d18a9718e1698ce6156a157997f23ef09fecf"
SRC_URI[sha512sum] = "c8df5e50d952d5a6dcf1d9253a6ba953e9763c545a867da66c22c90dfa015aba0194f2a8f29a229d0a5f4dc8bfeeaaab8bcfda4066ed78a18b151bc05e6ae327"

S = "${WORKDIR}/pyserial-${PV}"

inherit setuptools3 pypi

# The following configs & dependencies are from setuptools extras_require.
# These dependencies are optional, hence can be controlled via PACKAGECONFIG.
# The upstream names may not correspond exactly to bitbake package names.
#
# Uncomment this line to enable all the optional features.
#PACKAGECONFIG ?= "cp2110"
PACKAGECONFIG[cp2110] = ",,,python3-hidapi"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python3-core python3-ctypes python3-io python3-logging python3-netclient python3-numbers python3-shell python3-threading"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    Queue
#    System
#    System.IO.Ports
#    hid
#    msvcrt
#    urlparse
