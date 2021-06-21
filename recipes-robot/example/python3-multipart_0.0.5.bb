# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "A streaming multipart parser for Python"
HOMEPAGE = "http://github.com/andrew-d/python-multipart"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE.txt
LICENSE = "Apache"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=6c0c06fcb50bf07318f487fcf8c5da80"

SRC_URI[md5sum] = "4c0f8475a23eb5f28704bb3cbce9253a"
SRC_URI[sha1sum] = "23374b3a622d4467415ee46a25699e0e8a3f1393"
SRC_URI[sha256sum] = "f7bb5f611fc600d15fa47b3974c8aa16e93724513b49b5f95c81e6624c83fa43"
SRC_URI[sha384sum] = "8fc5d7b34a15c0468394752fa8ea47f76808bca61523f57631efe556f15e892e87c5e2eb2954e2059bd7a4dca24fc0a9"
SRC_URI[sha512sum] = "159472175c7b21ddcfb6d026d2efe1dc68ceacb6b30afef7f2aab078542eef6faf61d7e40abb2d22d18346c88e2fbee12283795f3e4a8b1f36673a5dd9163a8e"

S = "${WORKDIR}/python-multipart-${PV}"

inherit setuptools3 pypi

# WARNING: the following rdepends are from setuptools install_requires. These
# upstream names may not correspond exactly to bitbake package names.
RDEPENDS_${PN} += "python3-six"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python3-core python3-io python3-logging python3-math python3-netclient python3-numbers python3-shell python3-six python3-unittest"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    mock
#    pytest
#    unittest2
#    urlparse
#    yaml
