# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "FastAPI framework, high performance, easy to learn, fast to code, ready for production"
HOMEPAGE = "https://github.com/tiangolo/fastapi"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=95792ff3fe8e11aa49ceb247e66e4810"

SRC_URI[md5sum] = "31ffdc1778dc829c10cf164318ea8f0b"
SRC_URI[sha1sum] = "1e98ff44d026c8669a271a5952b64f8b95e130d9"
SRC_URI[sha256sum] = "8359e55d8412a5571c0736013d90af235d6949ec4ce978e9b63500c8f4b6f714"
SRC_URI[sha384sum] = "8d96c8a504f4af09d3d734e80796c77f5b42d67afd890c433aca221df0e9d4a1cdc41ea260f253040996de38e44332e4"
SRC_URI[sha512sum] = "d3f6f08de74ab2edab56e4de5cbb7106807cdcb17c5e635a18ce1825cb7a359b13100b8a591b0b8f1151655db424116001d8f885d66cd6b2e45d1897486a6097"

S = "${WORKDIR}/fastapi-${PV}"

inherit distutils3 pypi

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python3-asyncio python3-compression python3-core python3-datetime python3-email python3-json python3-logging python3-misc python3-netclient python3-numbers python3-typing python3-unittest"

