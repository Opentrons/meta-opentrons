# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Python bindings for 0MQ"
HOMEPAGE = "https://pyzmq.readthedocs.org"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   COPYING.LESSER
#   COPYING.BSD
#   bundled/zeromq/COPYING
#   examples/LICENSE
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "LGPL & BSD"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=12c592fa0bcfff3fb0977b066e9cb69e \
                    file://COPYING.BSD;md5=11c65680f637c3df7f58bbc8d133e96e \
                    file://bundled/zeromq/COPYING;md5=f7b40df666d41e6508d03e1c207d498f \
                    file://examples/LICENSE;md5=df01b3aa4ae6e008c4ac73c9dfb18d33"

SRC_URI[md5sum] = "5111fe3c089ba64ba8798922bab28280"
SRC_URI[sha1sum] = "6db060f53a1261f1b98a0ff91c6fccc89c5c024b"
SRC_URI[sha256sum] = "7040d6dd85ea65703904d023d7f57fab793d7ffee9ba9e14f3b897f34ff2415d"
SRC_URI[sha384sum] = "0dae934f9c2207bcb0efb1dadc612b1c432c1ac1b8b9415d770213774499a3e84cf460ba0edc4a9a5497d1874ec65205"
SRC_URI[sha512sum] = "2589cd5b1370c2236a9b178304006ca83f4cc2c92813ecff7804b780b53ca9e38b562288fafedf2fbdb2ec49e50127b5ca2404cb3f0e2f6f9df26db0dadf4ec3"

S = "${WORKDIR}/pyzmq-${PV}"

inherit setuptools3 pypi

# The following configs & dependencies are from setuptools extras_require.
# These dependencies are optional, hence can be controlled via PACKAGECONFIG.
# The upstream names may not correspond exactly to bitbake package names.
#
# Uncomment this line to enable all the optional features.
#PACKAGECONFIG ?= ":implementation_name == "pypy""
#PACKAGECONFIG[:implementation_name == "pypy"] = ",,,python3-cffi python3-py"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python3-asyncio python3-compression python3-core python3-crypt python3-ctypes python3-datetime python3-distutils python3-io python3-json python3-logging python3-math python3-multiprocessing python3-netclient python3-netserver python3-numbers python3-numpy python3-pickle python3-pprint python3-setuptools python3-shell python3-threading python3-typing python3-unittest python3-unixadmin"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    ConfigParser
#    __pypy__.bufferable
#    colorama
#    constant_names
#    gevent
#    gevent.core
#    gevent.event
#    gevent.hub
#    monotime
#    paramiko
#    pexpect
#    pyczmq
#    pytest
#    pyximport
#    setuptools._distutils.util
#    thread
#    tornado
#    tornado.concurrent
#    tornado.gen
#    tornado.ioloop
#    tornado.log
#    tornado.platform.asyncio
#    tornado.platform.epoll
#    tornado.platform.kqueue
#    tornado.platform.select
#    tornado.stack_context
#    zmq.backend.cffi._cffi
#    zmq.backend.cython._device
#    zmq.backend.cython._poll
#    zmq.backend.cython._proxy_steerable
#    zmq.backend.cython._version
#    zmq.backend.cython.constants
#    zmq.backend.cython.context
#    zmq.backend.cython.error
#    zmq.backend.cython.message
#    zmq.backend.cython.socket
#    zmq.backend.cython.utils
