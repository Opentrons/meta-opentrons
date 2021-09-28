SRC_URI_append = "\
  file://verdin-imx8mm_MCP2518_overlay.dts \
  "

FILESEXTRAPATHS_append := ":${THISDIR}/overlays"

do_prep_opentrons_overlays () {
    cp ${WORKDIR}/*.dts ${WORKDIR}/git/overlays/
}

addtask prep_opentrons_overlays after do_patch before do_collect_overlays
