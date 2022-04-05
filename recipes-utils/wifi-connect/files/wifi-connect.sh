#!/bin/bash

set -euo pipefail

# Read SSID
ssid=
if [ -z "${1+x}" ]; then
	echo -n "SSID: " && read -r ssid
else
	ssid="$1"
fi

# If the connection already exists, activate it.
if nmcli c | awk '{print $1}' | tail -n+2 | grep -E "^${ssid}$" > /dev/null; then
	nmcli c up "${ssid}"
	exit $?
fi

# Obtain network information in less than 1 minute
timeout=$(( $(date +%s) + 60 ))
info=
while true; do
	info=$(nmcli dev wifi list | grep "${ssid}" || true)
	if [[ -n "${info}" ]]; then
		break
	else
		current_time=$(date +%s)
		if [[ ${current_time} -gt ${timeout} ]]; then
			echo "Couldn't obtain info for $ssid"
			exit 1
		else
			sleep 5s
		fi
	fi
done

# Add connection
output=$(nmcli c add type "wifi" ifname "*" con-name "${ssid}" autoconnect "yes" save "yes" ssid "${ssid}")
exit_code=$?

if [[ $exit_code -eq 0 ]] ; then
	uuid=$(echo "${output}" | grep -E -o '[[:xdigit:]]{8}-[[:xdigit:]]{4}-[[:xdigit:]]{4}-[[:xdigit:]]{4}-[[:xdigit:]]{12}')

	# Check security needed for that network
	if [[ "${info}" =~ 802.1X ]]; then
		# Read password
		echo -n "IDENTITY: " && read -r identity
		echo -n "PASSWORD: " && read -r -s pass
		nmcli c modify "${uuid}" 802-1x.identity "${identity}" wifi-sec.key-mgmt wpa-eap 802-1x.eap peap 802-1x.phase2-auth mschapv2 802-1x.password "${pass}"
	elif [[ "${info}" =~ WPA2 ]]; then
		# Read password
		echo -n "PASSWORD: " && read -r -s pass
		nmcli c modify "${uuid}" wifi-sec.key-mgmt wpa-psk wifi-sec.psk "${pass}"
	fi

	# Activate connection
	nmcli c up "${uuid}"
else
	echo "${output}"
	exit $exit_code
fi

exit 0
