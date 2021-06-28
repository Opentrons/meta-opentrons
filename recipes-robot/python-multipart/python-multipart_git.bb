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
#   docs/source/_themes/LICENSE
LICENSE = "Apache"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=6c0c06fcb50bf07318f487fcf8c5da80 \
                    file://docs/source/_themes/LICENSE;md5=da1f8f97f9ee64ad7466c3c531ad2c5b"


# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "ed8c47aa9f9011f5c5e1fe0d1ac53cdd026acaed"

S = "${WORKDIR}/git"

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
