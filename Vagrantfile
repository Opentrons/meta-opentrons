# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  config.vm.box = "debian/ot3"
  config.vm.hostname = "buster64.box"
  config.vm.network "private_network", type: "dhcp"

  config.vm.provider "virtualbox" do |vb|
    vb.gui = false
    vb.customize [
      "modifyvm", :id,
      "--name", "mfgtools",
      "--cpus", 1,
      "--memory", "512",
      "--usb", "on",
      "--usbehci", "on",
    ]
  end

  config.vm.provision "shell", env: {"VERSION" => "1.4.127"}, inline: <<-SHELL
    apt-get update
    apt-get upgrade -y
    apt-get install -y \
        build-essential cmake pkg-config \
        libbz2-dev libssl-dev libusb-1.0-0-dev libzip-dev
    wget -q -O /usr/local/bin/uuu \
        https://github.com/NXPmicro/mfgtools/releases/download/uuu_${VERSION}/uuu
    chmod +x /usr/local/bin/uuu
    uuu -udev >> /etc/udev/rules.d/99-uuu.rules
    udevadm control --reload-rules
  SHELL
end
