# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Data validation and settings management using python 3.6 type hinting"
HOMEPAGE = "https://github.com/samuelcolvin/pydantic"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=215ca7f54cdc98f95ee96c7511d64158"

SRC_URI = "https://files.pythonhosted.org/packages/0d/0f/5dd883399fca772c441f4fd8db85320fa7a912fa2f917f0cee1f681f9a93/pydantic-${PV}.tar.gz"
SRC_URI[md5sum] = "e405c4574fe2573f0624543d58e0f738"
SRC_URI[sha1sum] = "ae74f7d1c1102ce760c0cbaaeea7dde399b2c844"
SRC_URI[sha256sum] = "f17ec336e64d4583311249fb179528e9a2c27c8a2eaf590ec6ec2c6dece7cb3f"
SRC_URI[sha384sum] = "884e90c50710ddae377b4267a887fb18c97c7b2658a8ce6b14b2ce17183b0c02f38039658f17e7ec19ada46cafd4b050"
SRC_URI[sha512sum] = "b8caa2ba0746f656fa3ab6cc9f70c6b6f79adf8824128f95c42ceefeb2c7e944bd491a48d3b4b175d6968116b5f1211de3a32b6b4e8715fd5aa2dd4759784731"

S = "${WORKDIR}/pydantic-${PV}"

inherit setuptools3

# The following configs & dependencies are from setuptools extras_require.
# These dependencies are optional, hence can be controlled via PACKAGECONFIG.
# The upstream names may not correspond exactly to bitbake package names.
#
# Uncomment this line to enable all the optional features.
#PACKAGECONFIG ?= ":python_version < "3.7" dotenv email typing_extensions"
PACKAGECONFIG[dotenv] = ",,,python3-python-dotenv"
PACKAGECONFIG[email] = ",,,python3-email-validator"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python3-core python3-datetime python3-dotenv python3-image python3-io python3-netclient python3-numbers python3-pickle python3-typing-extensions"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    cython
#    email_validator
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
