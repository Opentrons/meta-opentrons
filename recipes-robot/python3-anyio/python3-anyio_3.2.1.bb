# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "High level compatibility layer for multiple asynchronous event loop implementations"
HOMEPAGE = "UNKNOWN"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c0a769411d2af7894099e8ff75058c9f"

SRC_URI[md5sum] = "5063d7dc95718a563461fa69f19b16ef"
SRC_URI[sha1sum] = "e5a99ea88ef0835053ae1a9059c1074eaa9b9f91"
SRC_URI[sha256sum] = "07968db9fa7c1ca5435a133dc62f988d84ef78e1d9b22814a59d1c62618afbc5"
SRC_URI[sha384sum] = "d93ca1ef9e5650de68752f9accefb46675e8ad85fe547ada3d8df0170c0ab435f9165194057357dc0bc66970f6187e6e"
SRC_URI[sha512sum] = "ef78e5531a917e3a5ddc1106f45ba556ca147e52ab29ed6cb25a3bd287148a15a015942dde6b9535d683fbe5910affed27df6ce5c4049251610f689cd151ee89"

S = "${WORKDIR}/anyio-${PV}"
DEPENDS += "${PYTHON_PN}-setuptools-scm-native"
inherit setuptools3 pypi

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS_${PN} += "python3-asyncio python3-core python3-io python3-misc python3-pickle python3-typing python3-typing-extensions python3-unittest"

# WARNING: We were unable to map the following python package/module
# dependencies to the bitbake packages which include them:
#    _pytest.fixtures
#    _pytest.monkeypatch
#    _pytest.pytester
#    _pytest.tmpdir
#    anyio
#    anyio._backends._asyncio
#    anyio._core._compat
#    anyio.abc
#    anyio.from_thread
#    anyio.lowlevel
#    anyio.streams.buffered
#    anyio.streams.file
#    anyio.streams.memory
#    anyio.streams.stapled
#    anyio.streams.text
#    anyio.streams.tls
#    mock
#    pytest
#    pytest_mock.plugin
#    trio
#    trustme
#    uvloop
