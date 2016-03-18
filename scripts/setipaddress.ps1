#START FUNCTIONS
function ChangeIP($interfaceName){
    Write-Host("Current IP-configuration \n -----------------------------")
    netsh interface ipv4 show addresses $interfaceName
    Write-Host("Updating IP-address")
    for($suffix = 50; $suffix -le 60; $suffix++){
        $ip = "192.168.2."+$suffix
        if(!($occupiedIPs -contains $ip)){
            netsh interface ipv4 set address name=$interfaceName source=static address=$ip mask=255.255.255.0
        }
    }
}

function Windows10(){
    Write-Host("Detected Windows 10")
    #currently only English is tested on Win10
    ChangeIP "Ethernet"
}

#END FUNCTIONS


















Write-Host("Starting IP Configuration...")

#start scanning the range from 192.168.2.50 to 192.168.2.60
Write-Host("Scanning network...")
$occupiedIPs = @()

for($idx = 50; $idx -le 60; $idx++){
    ping -n 1 192.168.2.$idx
    $pingedIp = "192.168.2."+$idx
    $ip = arp -a | select-string $pingedIp |% { $_.ToString().Trim().Split(" ")[0] }
    if($ip){
        $occupiedIPs+=$ip
    }
}

$language = Get-UICulture | select Name |% {$_.Name}
$language = $language.Substring(0,2)

if([Environment]::OSVersion.Version -eq (new-object 'Version' 6,1)){

} elseif ([Environment]::OSVersion.Version -eq (new-object 'Version' 6,2)){

} elseif([Environment]::OSVersion.Version -eq (new-object 'Version' 6,3)){

}else{
    Windows10
}

