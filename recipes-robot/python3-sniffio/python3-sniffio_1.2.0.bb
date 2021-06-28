# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Sniff out which async library your code is running under"
HOMEPAGE = "https://github.com/python-trio/sniffio"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
# NOTE: Original package / source metadata indicates license is: MIT & Apache-2.0
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "MIT & Apache-2.0 & Unknown"
LIC_FILES_CHKSUM = "file://LICENSE.MIT;md5=e62ba5042d5983462ad229f5aec1576c \
                    file://LICENSE.APACHE2;md5=3b83ef96387f14655fc854ddc3c6bd57 \
                    file://LICENSE;md5=fa7b86389e58dd4087a8d2b833e5fe96"

SRC_URI[md5sum] = "2d7cc6c3a94d3357d333a4ade4a83de8"
SRC_URI[sha1sum] = "8b195bcc5c3acdd3702df0df2c5c0024599fb926"
SRC_URI[sha256sum] = "c4666eecec1d3f50960c6bdf61ab7bc350648da6c126e3cf6898d8cd4ddcd3de"
SRC_URI[sha384sum] = "006b7041741b27fce3822f9ec1f0c9a27304db2a33c09b303b552bf59b361e467376fd800ca47bc3001c49fef5079f26"
SRC_URI[sha512sum] = "96da4e0f0fff99b122aa729da052e97c2dd75f19aab4dd3728f8e337875b0fac1b3d8d96c8c3a67ca96f15dbf07a593b89e323bf04a8ee0501d7414454030719"

S = "${WORKDIR}/sniffio-${PV}"

inherit setuptools3 pypi

# The following configs & dependencies are from setuptools extras_require.
# These dependencies are optional, hence can be controlled via PACKAGECONFIG.
# The upstream names may not correspond exactly to bitbake package names.
#
# Uncomment this line to enable all the optional features.
#PACKAGECONFIG ?= ":python_version < '3.7'"
#PACKAGECONFIG[:python_version < '3.7'] = ",,,python3-contextvars"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python3-asyncio python3-core python3-numbers python3-typing"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    curio
#    curio.meta
#    pytest
