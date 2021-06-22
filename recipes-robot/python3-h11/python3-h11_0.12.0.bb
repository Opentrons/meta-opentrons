# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "A pure-Python, bring-your-own-I/O implementation of HTTP/1.1"
HOMEPAGE = "https://github.com/python-hyper/h11"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=f5501d19c3116f4aaeef89369f458693"

SRC_URI[md5sum] = "6b23d6eafbeaadf43ce26ec0a9a99594"
SRC_URI[sha1sum] = "05aba93845fdb3f0a57c820df1ceae864746721c"
SRC_URI[sha256sum] = "47222cb6067e4a307d535814917cd98fd0a57b6788ce715755fa2b6c28b56042"
SRC_URI[sha384sum] = "cf5e4874badf8d42e2c1163589ca4343cad38ee7cd856006c704f48659f3fb1441a1c423ae71ed28cc43f1f590be9c6d"
SRC_URI[sha512sum] = "ba2a2c185af270185ba5e1fd80ec9308e35afcd60a125a77cbeae2669f462983d063339371312f154a7c80c154ac97796ab36782b36549235435dcfd81b93421"

S = "${WORKDIR}/h11-${PV}"

inherit setuptools3 pypi

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python3-core python3-io python3-json python3-netclient python3-netserver"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    pytest
