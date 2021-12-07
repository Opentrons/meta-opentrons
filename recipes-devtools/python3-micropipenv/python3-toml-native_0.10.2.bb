SUMMARY = "Python Library for Tom's Obvious, Minimal Language"
LICENSE = "MIT"
HOMEPAGE = "https://github.com/uiri/toml"
LIC_FILES_CHKSUM = "file://LICENSE;md5=16c77b2b1050d2f03cb9c2ed0edaf4f0"

SRC_URI[sha256sum] = "b3bda1d108d5dd99f4a20d24d9c348e91c4db7ab1b749200bded2f839ccbe68f"
SRC_URI[md5sum] = "59bce5d8d67e858735ec3f399ec90253"
S = "${WORKDIR}/toml-${PV}"

DEPENDS = "python3-native "

inherit native setuptools3 pypi
