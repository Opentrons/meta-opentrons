# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "WSGI HTTP Server for UNIX"
HOMEPAGE = "https://gunicorn.org"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f75f3fb94cdeab1d607e2adaa6077752"

SRC_URI[md5sum] = "db8a7c5c2064000af70286534803bf1d"
SRC_URI[sha1sum] = "21bae657b8de9e90987b954bb411ecdfcada6ea5"
SRC_URI[sha256sum] = "e0a968b5ba15f8a328fdfd7ab1fcb5af4470c28aaf7e55df02a99bc13138e6e8"
SRC_URI[sha384sum] = "74fb803a39ca75f9950fa905a4c75f96ed135bc535d824503fe94f9e75af7fc5e4d600425c7c5154643c094e6c99447a"
SRC_URI[sha512sum] = "586c7373504b4018fd462dc9c728991a8cc9f2be71fc2fb5aa23707ff6157dd3953121f6a070cae64d58b10f9ddf77ad59b66ed33981d37919b4764c60609027"

S = "${WORKDIR}/gunicorn-${PV}"

inherit setuptools3 pypi

# The following configs & dependencies are from setuptools extras_require.
# These dependencies are optional, hence can be controlled via PACKAGECONFIG.
# The upstream names may not correspond exactly to bitbake package names.
#
# Uncomment this line to enable all the optional features.
#PACKAGECONFIG ?= "eventlet gevent setproctitle tornado"
PACKAGECONFIG[eventlet] = ",,,python3-eventlet"
PACKAGECONFIG[gevent] = ",,,python3-gevent"
PACKAGECONFIG[setproctitle] = ",,,python3-setproctitle"
PACKAGECONFIG[tornado] = ",,,python3-tornado"

# WARNING: the following rdepends are from setuptools install_requires. These
# upstream names may not correspond exactly to bitbake package names.
RDEPENDS_${PN} += "python3-setuptools"

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python3-asyncio python3-core python3-datetime python3-email python3-html python3-io python3-logging python3-math python3-netclient python3-pkg-resources python3-shell"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    eventlet.greenio
#    eventlet.wsgi
#    gevent.pool
#    gevent.server
#    greenlet
#    inotify.adapters
#    inotify.constants
#    paste.deploy
#    tornado.httpserver
#    tornado.ioloop
#    tornado.web
#    tornado.wsgi
