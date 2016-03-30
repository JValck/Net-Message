#!/usr/bin/perl

$ipSetted = 0;
#get all ip-addresses from 192.168.2.50 to 192.168.2.60
for($idx = 50; $idx < 61; $idx++){
	$out = qx(ping -c 1 192.168.2.$idx);

	if($out =~ /Unreachable/){
		#IP does not exist, set this ip
		print "Found valid IP\n";
		qx(sudo ip addr add 192.168.2.$idx dev enp2s0);
		$ipSetted = 1;
		last;
	}
}

if($ipSetted == 0){
	print "ERROR: Could not set an IP. All the IPs are occupied, try setting an IP manually";
}
