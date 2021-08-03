# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Tools to generate block map (AKA bmap) and flash images using bmap"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   debian/copyright
# NOTE: Original package / source metadata indicates license is: GPLv2
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "GPLv2 & Unknown"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://debian/copyright;md5=8289f982a9a21bcb71a4a605bec73ddf"

SRC_URI = "git://github.com/intel/bmap-tools;protocol=https"

# Modify these as desired
PV = "3.6+git${SRCPV}"
SRCREV = "7925ab87d7f4a0bd7a3936eebb4238297c007a0b"

S = "${WORKDIR}/git"

# NOTE: spec file indicates the license may be "GPL-2.0"
inherit setuptools3

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python3-compression python3-core python3-crypt python3-datetime python3-fcntl python3-io python3-logging python3-math python3-misc python3-mmap python3-six python3-unittest python3-xml"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    backports
#    gpgme
#    mock
#    six.moves
#    six.moves.urllib
#    six.moves.urllib.error
#    unittest2
