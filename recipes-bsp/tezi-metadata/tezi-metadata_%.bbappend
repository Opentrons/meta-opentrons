SRC_URI_append = " \
               file://wrapup-ot3.sh \
"

do_deploy_ot3_wrapup () {
    install -m 644 ${WORKDIR}/wrapup.sh ${DEPLOYDIR}
}

addtask do_deploy_ot3_wrapup before do_build after deploy
