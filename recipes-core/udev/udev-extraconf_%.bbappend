FILESEXTRAPATHS_prepend := "${THISDIR}/files/:"

#override FILESXTRAPATHS to bring meta-opentrons on top
python () {
    arr = d.getVar('FILESEXTRAPATHS').split(":")
    arr[0], arr[1] = arr[1], arr[0]
    d.setVar('FILESEXTRAPATHS', ':'.join(arr))
}
