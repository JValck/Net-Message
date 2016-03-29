#!/usr/bin/perl

#get all ip-addresses from subnet
system("sudo arp-scan 192.168.2.0/24");
if($? == -1){
	print "Trying to install arp-scan...\n";
	system("sudo dnf -y install arp-scan");
	system("sudo arp-scan 192.168.2.0/24");
}