# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Async http client/server framework (asyncio)"
HOMEPAGE = "https://github.com/aio-libs/aiohttp"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE.txt
# NOTE: Original package / source metadata indicates license is: Apache
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "Unknown & MIT & Apache"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=3bf3d48554bdca1ea7fdb48de378c2ca \
                    file://vendor/http-parser/LICENSE-MIT;md5=9bfa835d048c194ab30487af8d7b3778"

SRC_URI[md5sum] = "7052a8e9877921d73da98d2b18c9a145"
SRC_URI[sha1sum] = "a4cb0a27e56df34bea5a4ef9d8534667e8108d12"
SRC_URI[sha256sum] = "493d3299ebe5f5a7c66b9819eacdcfbbaaf1a8e84911ddffcdc48888497afecf"
SRC_URI[sha384sum] = "671eb72dd15a466fa30479d7beb4d767d39573160a43145fadff6476e2cd4bbbe1fa7331f795d3b21935b3772424a11f"
SRC_URI[sha512sum] = "1697390a8f40a1de0335343f7c3451a1b772e181364edd30c5834abf5e8f3020bdf3fa4226d435e4c2b73340bea567188089d67a1fc93826e1a51bc5e419f0d7"

S = "${WORKDIR}/aiohttp-${PV}"

inherit setuptools3 pypi

# The following configs & dependencies are from setuptools extras_require.
# These dependencies are optional, hence can be controlled via PACKAGECONFIG.
# The upstream names may not correspond exactly to bitbake package names.
#
# Uncomment this line to enable all the optional features.
#PACKAGECONFIG ?= ":python_version < "3.7" speedups"
#PACKAGECONFIG[:python_version < "3.7"] = ",,,python3-idna-ssl"
PACKAGECONFIG[speedups] = ",,,python3-aiodns python3-brotlipy python3-cchardet"

# WARNING: the following rdepends are from setuptools install_requires. These
# upstream names may not correspond exactly to bitbake package names.
RDEPENDS_${PN} += "python3-async-timeout python3-attrs python3-chardet python3-multidict python3-typing-extensions python3-yarl"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python3-asyncio python3-attrs python3-core python3-crypt python3-datetime python3-email python3-html python3-io python3-json python3-logging python3-math python3-misc python3-netclient python3-netserver python3-pickle python3-stringold python3-typing python3-typing-extensions python3-unittest"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    aiohttp._frozenlist
#    aiohttp._helpers
#    aiohttp._http_parser
#    aiohttp._http_writer
#    aiohttp._websocket
#    brotli
#    gunicorn.config
#    gunicorn.workers
#    idna_ssl
#    pytest
#    tokio
#    uvloop
