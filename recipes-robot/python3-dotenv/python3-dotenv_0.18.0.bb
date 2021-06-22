# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Read key-value pairs from a .env file and set them as environment variables"
HOMEPAGE = "https://github.com/theskumar/python-dotenv"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=55ee2c3471d386636a719c8ccac40b31"

SRC_URI = "https://files.pythonhosted.org/packages/7a/1c/4dbbae00fc9a3bc605fc2d92dc0e197fcf0b6e0357d217d1819afe8206af/python-dotenv-${PV}.tar.gz"
SRC_URI[md5sum] = "87e2bcc039142b4408337eddb210462a"
SRC_URI[sha1sum] = "fe1933c49c953ef446a0bdf35c6a099a31745a09"
SRC_URI[sha256sum] = "effaac3c1e58d89b3ccb4d04a40dc7ad6e0275fda25fd75ae9d323e2465e202d"
SRC_URI[sha384sum] = "d1208fb1e8ad405a70defb57af1364b30287d26b83c5cf3b0b2c144193874eedb5aae0dbdeec69323b9a62e1a3fe13dd"
SRC_URI[sha512sum] = "4b120ee45602ce703ee360fb5af07a8854bcb1ecd9084057e00324b152cf230fa6f065b374b6eebbfb7069b32762624ea61dd5d2fa6ac62cb7e3a2bb6ddfc1ed"


inherit setuptools3 pypi

# The following configs & dependencies are from setuptools extras_require.
# These dependencies are optional, hence can be controlled via PACKAGECONFIG.
# The upstream names may not correspond exactly to bitbake package names.
#
# Uncomment this line to enable all the optional features.
#PACKAGECONFIG ?= "cli"
PACKAGECONFIG[cli] = ",,,python3-click"

# WARNING: the following rdepends are from setuptools install_requires. These
# upstream names may not correspond exactly to bitbake package names.
RDEPENDS_${PN} += "python3-typing"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python3-core python3-io python3-logging python3-typing"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    IPython.core.magic
#    IPython.core.magic_arguments
#    StringIO
