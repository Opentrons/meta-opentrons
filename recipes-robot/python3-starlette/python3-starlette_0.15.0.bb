# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "The little ASGI library that shines."
HOMEPAGE = "https://github.com/encode/starlette"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE.md
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=11e8c8dbfd5fa373c703de492140ff7a"

SRC_URI = "https://files.pythonhosted.org/packages/38/75/8b3bdd2129c89ef31a0a211ba4f33cc5ed084cb9bbdf361569b0184fab71/starlette-${PV}.tar.gz"
SRC_URI[md5sum] = "d9a111162a9ac83ac092e1fdf5118c56"
SRC_URI[sha1sum] = "bc26e8cc24b5e4461d1707a31922917aeff395d8"
SRC_URI[sha256sum] = "5c6e71fe8a11f690f78ab13363b0993eb7e5446986d3549902a81505afdaa08b"
SRC_URI[sha384sum] = "a305d747c5b32e2de053ee43a3a3aa1aa6c3f72ad88fc40ec5d70caeb1d55a399b316cace4c757fbcd64dcbe332977ea"
SRC_URI[sha512sum] = "f8e1d23434a6ce3e08f8c1bb6b4f2341bfcd30e40eb893a3ab298409f550170cd9f31c97bb1aebd521eedbf5b661ee3c42e8790d29ebcb154884433104ade86f"

S = "${WORKDIR}/starlette-${PV}"

inherit setuptools3

# The following configs & dependencies are from setuptools extras_require.
# These dependencies are optional, hence can be controlled via PACKAGECONFIG.
# The upstream names may not correspond exactly to bitbake package names.
#
# Uncomment this line to enable all the optional features.
#PACKAGECONFIG ?= "full full:python_version < "3.10""
PACKAGECONFIG[full] = ",,,python3-itsdangerous python3-jinja2 python3-python-multipart python3-pyyaml python3-requests"
#PACKAGECONFIG[full:python_version < "3.10"] = ",,,python3-graphene"

# WARNING: the following rdepends are from setuptools install_requires. These
# upstream names may not correspond exactly to bitbake package names.
RDEPENDS_${PN} += "python3-anyio"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python3-asyncio python3-core python3-crypt python3-email python3-html python3-io python3-json python3-multipart python3-netclient python3-numbers python3-shell python3-threading python3-typing"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    anyio.streams.stapled
#    graphql.error
#    graphql.execution.executors.asyncio
#    itsdangerous.exc
#    yaml
