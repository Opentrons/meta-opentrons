SUMMARY = "Tools to generate block map (AKA bmap) and flash images using bmap"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://debian/copyright;md5=8289f982a9a21bcb71a4a605bec73ddf"

SRC_URI = "git://github.com/intel/bmap-tools;protocol=https"

# Modify these as desired
PV = "3.6+git${SRCPV}"
SRCREV = "7925ab87d7f4a0bd7a3936eebb4238297c007a0b"

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS_${PN} += "python3-compression python3-core python3-crypt python3-datetime python3-fcntl python3-io python3-logging python3-math python3-misc python3-mmap python3-six python3-unittest python3-xml"

