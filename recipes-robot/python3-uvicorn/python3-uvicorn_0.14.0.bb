# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "The lightning-fast ASGI server."
HOMEPAGE = "https://github.com/encode/uvicorn"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE.md
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=206119e27d6b034e7ce70d73063c82a8"

SRC_URI[md5sum] = "f5abf722d60d4709f94dfd2614dfa5ce"
SRC_URI[sha1sum] = "006af749fadc976cfdf19d7c86f9c1be15ab873a"
SRC_URI[sha256sum] = "45ad7dfaaa7d55cab4cd1e85e03f27e9d60bc067ddc59db52a2b0aeca8870292"
SRC_URI[sha384sum] = "957bbd7f300a364b7f7a0baacfed2ffabfa1ccc2f03a7c7560c76eb268f46ffcce9af28b5c27117b18637933d36fa90a"
SRC_URI[sha512sum] = "4a7cd5af623f19957546ce44594adf5c30fbd77c0649b77544630445e135bdae0d7ab019624b4b7bb121afd0f3ac44229f8ec8099d6e9957eb49d67264f4b171"

S = "${WORKDIR}/uvicorn-${PV}"

inherit setuptools3 pypi

# The following configs & dependencies are from setuptools extras_require.
# These dependencies are optional, hence can be controlled via PACKAGECONFIG.
# The upstream names may not correspond exactly to bitbake package names.
#
# Uncomment this line to enable all the optional features.
#PACKAGECONFIG ?= ":python_version < "3.8" standard standard:sys_platform != "win32" and (sys_platform != "cygwin" and platform_python_implementation != "pypy") standard:sys_platform == "win32""
#PACKAGECONFIG[:python_version < "3.8"] = ",,,python3-typing-extensions"
#PACKAGECONFIG[standard] = ",,,python3-pyyaml python3-httptools python3-python-dotenv python3-watchgod python3-websockets"
#PACKAGECONFIG[standard:sys_platform != "win32" and (sys_platform != "cygwin" and platform_python_implementation != "pypy")] = ",,,python3-uvloop!"
#PACKAGECONFIG[standard:sys_platform == "win32"] = ",,,python3-colorama"

# WARNING: the following rdepends are from setuptools install_requires. These
# upstream names may not correspond exactly to bitbake package names.
RDEPENDS_${PN} += "python3-asgiref python3-click python3-h11"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python3-asyncio python3-core python3-email python3-html python3-io python3-json python3-logging python3-multiprocessing python3-netclient python3-typing"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    asgiref.typing
#    dotenv
#    gunicorn.workers.base
#    typing_extensions
#    websockets.extensions.permessage_deflate
#    wsproto
#    wsproto.connection
#    wsproto.extensions
#    wsproto.utilities
#    yaml
