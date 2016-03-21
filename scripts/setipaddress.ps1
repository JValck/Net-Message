#-------CHECK PARAMETERS-----------
param(
    [string]$setStatic = $(Read-Host "Change IP-address to static? [Y/N]")
)
#-------END CHECK PARAMETERS-------


#-------START FUNCTIONS------------
function pingNetwork(){
    #start scanning the range from 192.168.2.50 to 192.168.2.60
    Write-Host("Scanning network...")
    for($idx = 50; $idx -le 60; $idx++){
        ping -n 1 192.168.2.$idx
        $pingedIp = "192.168.2."+$idx
        $ip = arp -a | select-string $pingedIp |% { $_.ToString().Trim().Split(" ")[0] }
        if($ip){
            $occupiedIPs+=$ip
        }
    }
}

function GiveStaticIP($interfaceName){
    pingNetwork
    for($suffix = 50; $suffix -le 60; $suffix++){
        $ip = "192.168.2."+$suffix
        if($occupiedIPs -contains $ip){
            if($suffix -eq 60){
                Write-Error "No IP-address available"
                break
            }else{
                continue
            }
        }else{
            netsh interface ipv4 set address name=`"$interfaceName`" source=static address=$ip mask=255.255.255.0 gateway=0.0.0.0
            break
        }
    }
}

function GiveDynamicIP($interfaceName){
    netsh interface ipv4 set address name=`"$interfaceName`" source=dhcp
}

function ChangeIP($interfaceName){
    Write-Host("Current IP-configuration `n------------------------")
    netsh interface ipv4 show addresses $interfaceName
    Write-Host("Updating IP-address")
    if($toStatic -eq $true){
        GiveStaticIP($interfaceName)
    }else{
        GiveDynamicIP($interfaceName)
    }    
    Write-Host("New IP-configuration `n--------------------")
    netsh interface ipv4 show addresses $interfaceName
    Write-Host("DONE!")
}

function Windows10(){
    Write-Host("Detected Windows 10")
    #currently only English is tested on Win10
    ChangeIP "Ethernet"
}

function Windows7(){
    Write-Host("Detected Windows 7")
    if($language -eq "nl"){
        ChangeIP("LAN-verbinding")
    }elseif($language -eq "en"){
        ChangeIP("Local Area Connection")
    }
}

function Windows8(){
    Write-Host("Detected Windows 8")
    Windows7
}

function Windows8_1(){
    Write-Host("Detected Windows 8.1")
    Windows7
}

function CheckInputParams(){

}
#-----END FUNCTIONS----------


#Convert parameter to boolean
$toStatic = $false
if($setStatic -eq "Y" -or $setStatic -eq "y"){
    $toStatic = $true
}
$occupiedIPs = @()
$language = Get-UICulture | select Name |% {$_.Name}
$language = $language.Substring(0,2)


Write-Host("Starting IP Configuration...")

$osVersion = [Environment]::OSVersion.Version
if($osVersion -ge (new-object 'Version' 6,1) -and $osVersion -lt (new-object 'Version' 6,2)){
    Windows7
} elseif ($osVersion -ge (new-object 'Version' 6,2) -and $osVersion -lt (new-object 'Version' 6,3)){
    Windows8
} elseif($osVersion -ge (new-object 'Version' 6,3) -and $osVersion -lt (new-object 'Version' 10)){
    Windows8_1
}else{
    Windows10
}

