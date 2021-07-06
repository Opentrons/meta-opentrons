# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "ASGI specs, helper code, and adapters"
HOMEPAGE = "https://github.com/django/asgiref/"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f09eb47206614a4954c51db8a94840fa"

SRC_URI[md5sum] = "5afe03ecc39ae94950ff95e80166d945"
SRC_URI[sha1sum] = "443406aad46a664ef7ce0eaeca7d3481eb6d3c06"
SRC_URI[sha256sum] = "d1216dfbdfb63826470995d31caed36225dcaf34f182e0fa257a4dd9e86f1b78"
SRC_URI[sha384sum] = "756d16a8dedea71a0752e48e0bee3e16689f637bfa4ee2cc42df1c9c04e2819d82a02cb7e31ab16bec758341e52f6bb1"
SRC_URI[sha512sum] = "e224ce82f9156e3efde316f3e209a9fbb9494cb9347d6652e19ed130921143de8d3e2a45f50352bf13fcdf229c291e68a0348fff31de144e58cd1a53b4eb0c31"

S = "${WORKDIR}/asgiref-${PV}"

inherit setuptools3 pypi

# The following configs & dependencies are from setuptools extras_require.
# These dependencies are optional, hence can be controlled via PACKAGECONFIG.
# The upstream names may not correspond exactly to bitbake package names.
#
# Uncomment this line to enable all the optional features.
#PACKAGECONFIG ?= ":python_version < "3.8" tests"
#PACKAGECONFIG[:python_version < "3.8"] = ",,,python3-typing_extensions"
#PACKAGECONFIG[tests] = ",,,python3-mypy python3-pytest python3-pytest-asyncio"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python3-core"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    asyncio
#    asyncio.coroutines
#    concurrent.futures
#    contextvars
#    functools
#    inspect
#    io
#    logging
#    os
#    queue
#    random
#    string
#    tempfile
#    threading
#    traceback
#    types
#    warnings
#    weakref
