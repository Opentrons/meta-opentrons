# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Data validation and settings management using python 3.6 type hinting"
HOMEPAGE = "https://github.com/samuelcolvin/pydantic"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2c02ea30650b91528657db64baea1757"

SRC_URI[md5sum] = "7845d2f3c8fe8602f73f53ec5b6dfa29"
SRC_URI[sha1sum] = "c2bbe1f60e4bb6858eb8e64c0f1a8f2d4764b623"
SRC_URI[sha256sum] = "26464e57ccaafe72b7ad156fdaa4e9b9ef051f69e175dbbb463283000c05ab7b"
SRC_URI[sha384sum] = "699573e203a36fd03a574e0f789bdfdae1411c39801a606e811aca59b0f86a89d74d82d1408e80b2072d8b24a8f76b47"
SRC_URI[sha512sum] = "9ee60bcfcc37a7b4db9432e319f2c0fd9875bbea603af88e1bf1ce2627220468dd190efe74bd38f37c2394f854687485f8dd2602f1e1535a25adb2e7dbbeb44a"

S = "${WORKDIR}/pydantic-${PV}"

inherit setuptools3 pypi

# The following configs & dependencies are from setuptools extras_require.
# These dependencies are optional, hence can be controlled via PACKAGECONFIG.
# The upstream names may not correspond exactly to bitbake package names.
#
# Uncomment this line to enable all the optional features.
#PACKAGECONFIG ?= ":python_version < "3.7" dotenv email"
#PACKAGECONFIG[:python_version < "3.7"] = ",,,python3-dataclasses"
PACKAGECONFIG[dotenv] = ",,,python3-python-dotenv"
PACKAGECONFIG[email] = ",,,python3-email-validator"

# WARNING: the following rdepends are from setuptools install_requires. These
# upstream names may not correspond exactly to bitbake package names.
RDEPENDS_${PN} += "python3-typing-extensions"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python3-core python3-datetime python3-dotenv python3-image python3-io python3-netclient python3-numbers python3-pickle python3-typing-extensions"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    cython
#    email_validator
#    hypothesis.strategies
#    mypy.errorcodes
#    mypy.nodes
#    mypy.options
#    mypy.plugin
#    mypy.plugins
#    mypy.semanal
#    mypy.server.trigger
#    mypy.types
#    mypy.typevars
#    mypy.util
