# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Backported and Experimental Type Hints for Python 3.5+"
HOMEPAGE = "https://github.com/python/typing/blob/master/typing_extensions/README.rst"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "PSF"
LIC_FILES_CHKSUM = "file://LICENSE;md5=64fc2b30b67d0a8423c250e0386ed72f"

SRC_URI[md5sum] = "9b5b33ae64c94479fa0862cf8ae89d58"
SRC_URI[sha1sum] = "efa40572330e9a3c38ba519028f36d7f93647a39"
SRC_URI[sha256sum] = "50b6f157849174217d0656f99dc82fe932884fb250826c18350e159ec6cdf342"
SRC_URI[sha384sum] = "d09475149a005dc74431d4fe5dec64b7b685d7dbef5002fe3a4575ac471d942a142af98004571d24c482772d863d8e64"
SRC_URI[sha512sum] = "1c262aedb092d506bcd90d033a640fa6e1f9131f95eafb77d30ed21ff7d6b0f492b6092d3523ecb773bc54904679e0fa1aa8c3b4af62d77f1a7e6fe5fd6cb10c"


inherit setuptools3 pypi

# WARNING: the following rdepends are from setuptools install_requires. These
# upstream names may not correspond exactly to bitbake package names.
RDEPENDS_${PN} += "python3-typing"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python3-core"
